package com.hzxy.modules.sellwine.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.VO.WineUserVO;
import com.hzxy.modules.sellwine.entity.WineArea;
import com.hzxy.modules.sellwine.entity.WineRole;
import com.hzxy.modules.sellwine.entity.WineUser;
import com.hzxy.modules.sellwine.enums.DelFlagEnum;
import com.hzxy.modules.sellwine.form.WineUserForAdminForm;
import com.hzxy.modules.sellwine.service.WineAreaService;
import com.hzxy.modules.sellwine.service.WineRoleService;
import com.hzxy.modules.sellwine.service.WineUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 17:06
 * @Description:
 */
@RestController
@RequestMapping("/wineUser")
@Api("用户 相关Api")
@Slf4j
public class WineUserController {

    @Autowired
    private WineUserService wineUserService;

    @Autowired
    private WineRoleService wineRoleService;

    @Autowired
    private WineAreaService wineAreaService;

    @PostMapping("/addWineUser")
    @ApiOperation("管理员添加用户")
    public R addWineUser(@RequestBody WineUserForAdminForm wineUserForAdminForm) {
        // 代理商 需要查重 ,areaId 和 roleId 一样的就不能重复录入
        WineRole wineRole = wineRoleService.getOne(new QueryWrapper<WineRole>().eq("role_name", "店铺管理员"));
        if (!wineUserForAdminForm.getRoleId().equals(wineRole.getId())) {
            WineUser queryForDistinct = wineUserService.getOne(new QueryWrapper<WineUser>()
                    .eq("role_id", wineUserForAdminForm.getRoleId())
                    .eq("area_id", wineUserForAdminForm.getAreaId())
                    .eq("del_flag", DelFlagEnum.SHOW.getCode()));
            if (queryForDistinct != null) {
                return R.error("同区域同级别代理商不能重复录入");
            }
        }

        WineUser wineUser = new WineUser();
        BeanUtils.copyProperties(wineUserForAdminForm, wineUser);
        //处理密码
        wineUser.setPassword(DigestUtils.sha256Hex(wineUserForAdminForm.getPassword()));

        boolean saveResult = wineUserService.save(wineUser);
        if (saveResult) {
            return R.ok("添加用户成功");
        } else {
            return R.error("添加用户失败");
        }
    }

    @GetMapping("/page")
    @ApiOperation("获取用户的分页数据")
    public R page(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils = wineUserService.queryPage(params);
        List<WineUser> wineUsers = (List<WineUser>) pageUtils.getList();
        List<WineUserVO> wineUserVOS = new ArrayList<>();
        for (WineUser wineUser : wineUsers) {
            WineUserVO wineUserVO = new WineUserVO();
            BeanUtils.copyProperties(wineUser, wineUserVO);

            if (wineUser.getAreaId() != null) {

                WineArea wineArea = wineAreaService.getById(wineUser.getAreaId());
                wineUserVO.setAreaName(wineArea.getCurrentName());
            }

            if (wineUser.getCreateBy() != null) {
                WineUser tempUserData = wineUserService.getById(wineUser.getCreateBy());
                wineUserVO.setCreateBy(tempUserData.getUserName());
            }

            if (wineUser.getRoleId() != null) {
                WineRole wineRole = wineRoleService.getById(wineUser.getRoleId());
                wineUserVO.setRoleName(wineRole.getRoleName());
            }

            wineUserVOS.add(wineUserVO);

        }

        pageUtils.setList(wineUserVOS);

        return R.ok(pageUtils);

    }


    @GetMapping("/checkUserName")
    @ApiOperation("检查用户名是否重复")
    public R checkUserName(@RequestParam String userName) {
        WineUser wineUser = wineUserService.getOne(new QueryWrapper<WineUser>()
                .eq("user_name", userName));
        if (wineUser == null) {
            return R.ok("用户名可以使用");
        } else {
            return R.error("用户名重复,请换一个");
        }
    }

    @GetMapping("/disOrEnableUser")
    @ApiOperation("实现用户禁用启用")
    public R disableUser(@RequestParam Map<String, Object> params) {

        Object userId = params.get("userId");
        Object flag = params.get("flag");
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(flag)) {
            return R.error("请正确传递参数");
        } else {
            Long userIdForQuery = Long.parseLong(userId.toString());
            WineUser wineUser = wineUserService.getById(userIdForQuery);
            if (wineUser != null) {
                if (DelFlagEnum.HIDDEN.getCode() == Integer.parseInt(flag.toString())) {
                    wineUser.setDelFlag(DelFlagEnum.HIDDEN.getCode());
                    boolean result = wineUserService.saveOrUpdate(wineUser);
                    if (result) {
                        return R.ok("用户禁用成功");
                    } else {
                        return R.error("用户禁用失败");
                    }
                } else if (DelFlagEnum.SHOW.getCode() == Integer.parseInt(flag.toString())) {
                    WineRole wineRole = wineRoleService.getOne(new QueryWrapper<WineRole>().eq("role_name", "店铺管理员"));
                    if (!wineUser.getRoleId().equals(wineRole.getId())) {
                        WineUser queryForDistinct = wineUserService.getOne(new QueryWrapper<WineUser>()
                                .eq("role_id", wineUser.getRoleId())
                                .eq("area_id", wineUser.getAreaId())
                                .eq("del_flag", DelFlagEnum.SHOW.getCode()));
                        if (wineUser.getId() != queryForDistinct.getId()) {
                            return R.error("同级别代理商已经存在，不能启用当前用户");
                        }

                    }
                    wineUser.setDelFlag(DelFlagEnum.SHOW.getCode());
                    boolean updateResult = wineUserService.updateById(wineUser);
                    if (updateResult) {
                        return R.ok("启用用户成功");
                    } else {
                        return R.error("启用用户失败");
                    }
                } else {
                    return R.error("标志位参数错误");
                }
            } else {
                return R.error("用户不存在");
            }
        }


    }

}
