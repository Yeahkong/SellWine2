package com.hzxy.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.DateUtils;
import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.app.VO.UserFamilyVO;
import com.hzxy.modules.app.entity.AccessTokenEntity;
import com.hzxy.modules.app.entity.UserEntity;
import com.hzxy.modules.app.entity.UserFamilyEntity;
import com.hzxy.modules.app.form.SubUserFrom;
import com.hzxy.modules.app.form.UserFrom;
import com.hzxy.modules.app.service.CommonService;
import com.hzxy.modules.app.service.UserFamilyService;
import com.hzxy.modules.app.service.UserFamilyVOService;
import com.hzxy.modules.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-31 09:57
 * @Description:
 */
@Component
@RestController
@RequestMapping("/appUser")
@Api("app用户接口")
public class AppUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserFamilyVOService userFamilyVOService;
    @Autowired
    private UserFamilyService userFamilyService;
    @Autowired
    private CommonService commonService;

    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息")
    public R updateUser(@RequestBody UserFrom from){
        UserEntity userEntity=new UserEntity();
        userEntity.setMobile(from.getMobile());
        userEntity.setStatus(from.getStatus());
        userEntity.setUserId(from.getUserId());
        boolean updateResult = userService.updateUser(userEntity);
        if(updateResult){
            UserFamilyEntity userFamilyEntity=userFamilyService.getById(from.getUserFamilyId());
            userFamilyEntity.setNickName(from.getNickName());
            updateResult=userFamilyService.updateById(userFamilyEntity);
            if(updateResult) {
                return R.ok("修改信息成功");
            }else{
                return R.error("昵称修改失败");
            }
        }else{
            return R.error("修改信息失败");
        }
    }
    @PostMapping("/addUser")
    @ApiOperation(value = "添加成员")
    public R addUser(@RequestBody SubUserFrom subUserFrom){
        UserEntity userEntity=userService.getOne(new QueryWrapper<UserEntity>().eq("mobile", subUserFrom.getMobile()));
        if(userEntity == null) {
            userEntity =new UserEntity();
            userEntity.setMobile(subUserFrom.getMobile());
            userEntity.setUserName(subUserFrom.getMobile());
            userEntity.setStatus(0);
            userEntity.setCreateTime(DateUtils.getDateNow());
            userEntity.setPassword(DigestUtils.sha256Hex("123456"));
            boolean addResult = userService.save(userEntity);
            if (addResult) {
                userEntity=userService.getOne(new QueryWrapper<UserEntity>().eq("mobile", subUserFrom.getMobile()));
                // 保存关系表
                UserFamilyEntity userFamilyEntity = new UserFamilyEntity();
                userFamilyEntity.setFamilyId(subUserFrom.getFamilyId());
                userFamilyEntity.setIsPrimary(0);
                userFamilyEntity.setUserId(userEntity.getUserId());
                userFamilyEntity.setNickName(subUserFrom.getNickName());
                userFamilyService.save(userFamilyEntity);
                return R.ok("添加成员成功");
            } else {
                return R.error("添加成员失败");
            }
        }else{
            List<Map<String, Object>> queryResult = userFamilyService.listMaps(new QueryWrapper<UserFamilyEntity>()
                    .eq(StringUtils.isNotEmpty(subUserFrom.getFamilyId()),"family_id",subUserFrom.getFamilyId())
                    .eq(StringUtils.isNotEmpty(userEntity.getUserId()),"user_id",userEntity.getUserId()));
            if(queryResult.size()<=0){
                // 保存关系表
                UserFamilyEntity userFamilyEntity = new UserFamilyEntity();
                userFamilyEntity.setFamilyId(subUserFrom.getFamilyId());
                userFamilyEntity.setIsPrimary(0);
                userFamilyEntity.setUserId(userEntity.getUserId());
                userFamilyEntity.setNickName(subUserFrom.getNickName());
                userFamilyService.save(userFamilyEntity);
                return R.ok("添加成员成功");
            }else{
                return R.error("该用户已存在");
            }
        }
    }

    @GetMapping("/getUsers/{familyId}")
    @ApiOperation(value = "获取子用户信息")
    public R getUsers(@PathVariable Long familyId){
        Map<String,Object> condition = new HashMap<>();
        condition.put("familyId",familyId);
        condition.put("isPrimary","0");
        List<UserFamilyVO> userFamilyVOList = userFamilyVOService.getUserFamily(condition);
        return  R.ok().put("data",userFamilyVOList);
    }

    @PostMapping("/deleteUser")
    @ApiOperation(value = "删除用户")
    public R deleteUser(Long userFamilyId){
        boolean result=userFamilyService.removeById(userFamilyId);
        if(result) {
            return R.ok("删除成功");
        }else{
            return R.error("删除失败");
        }
    }

    /**
     * 获取萤石token
     * @return
     */
    @GetMapping("/getAccessToken")
    @ApiOperation(value = "获取萤石token")
    public R getAccessToken(){
        AccessTokenEntity token=commonService.getAccessToken();
        return  R.ok().put("data",token);
    }

}
