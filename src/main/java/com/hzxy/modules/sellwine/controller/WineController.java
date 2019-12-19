package com.hzxy.modules.sellwine.controller;

import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.service.WineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-19 15:28
 * @Description:
 */
@RestController
@RequestMapping("/wine")
@Api("酒 相关Api")
public class WineController {

    @Autowired
    private WineService wineService;

    @GetMapping("/page")
    @ApiOperation("获取酒的分页数据")
    public R page(@RequestParam Map<String,Object> params){
        PageUtils pageUtils = wineService.queryPage(params);
        return R.ok(pageUtils);
    }

}
