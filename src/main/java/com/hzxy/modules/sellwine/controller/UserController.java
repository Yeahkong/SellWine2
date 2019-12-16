package com.hzxy.modules.sellwine.controller;

import com.hzxy.common.utils.R;
import com.hzxy.common.validator.ValidatorUtils;
import com.hzxy.modules.sellwine.entity.User;
import com.hzxy.modules.sellwine.form.UserRegisterForm;
import com.hzxy.modules.sellwine.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 15:12
 * @Description:
 */
@RestController
@Api("用户 相关Api")
@RequestMapping("/wineUser")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public R register(@RequestBody UserRegisterForm userRegisterForm){

        //表单校验
        ValidatorUtils.validateEntity(userRegisterForm);

        User user = new User();
        BeanUtils.copyProperties(userRegisterForm,user);
        user.setPassword(DigestUtils.sha256Hex(userRegisterForm.getPassword()));
        user.setCreateTime(new Date());
        // @TODO createBy需要处理

        boolean saveResult = userService.save(user);
        if(saveResult){
            return R.ok("用户注册成功");
        }else{
            return R.error("用户注册失败");
        }
    }


    @PostMapping("/perfectUserWechatOpenId")
    @ApiOperation("完善用户的微信openid信息")
    public R perfectUserWechatOpenId(@RequestParam Map<String,Object> params){

        String userId = params.get("userId").toString();


        return R.ok("操作成功");
    }

}
