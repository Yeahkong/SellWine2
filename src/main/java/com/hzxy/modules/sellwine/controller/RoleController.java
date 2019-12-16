package com.hzxy.modules.sellwine.controller;

import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.entity.Role;
import com.hzxy.modules.sellwine.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 14:58
 * @Description:
 */
@RestController
@RequestMapping("/wineRole")
@Api("角色 相关Api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/allRole")
    @ApiOperation("全部角色")
    public R allRole(){
        List<Role> allRole = roleService.list();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("allRole",allRole);
        return R.ok(resultMap);
    }


    @PostMapping("/saveRole")
    @ApiOperation("新增角色")
    public R saveRole(@RequestBody Role role){
        role.setCreateTime(new Date());
        boolean saveResult = roleService.save(role);
        if(saveResult){
            return R.ok("新增角色成功");
        }else{
            return R.error("新增角色失败");
        }
    }

}
