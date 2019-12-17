package com.hzxy.modules.sellwine.controller;

import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.entity.WineRole;
import com.hzxy.modules.sellwine.service.WineRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 13:56
 * @Description:
 */
@RestController
@RequestMapping("/wineRole")
@Api("角色 相关Api")
public class WineRoleController {

    @Autowired
    private WineRoleService wineRoleService;


    @GetMapping("/allRole")
    @ApiOperation("获取全部角色")
    public R allRole(){
        List<WineRole> wineRoles = wineRoleService.list();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("allRole",wineRoles);
        return R.ok(resultMap);
    }


}
