package com.hzxy.modules.sellwine.controller;

import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.entity.Wine;
import com.hzxy.modules.sellwine.service.WineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 13:26
 * @Description:
 */
@RestController
@Api("酒 相关Api")
@RequestMapping("/wine")
public class WineController {

    @Autowired
    private WineService wineService;

    @GetMapping("/allWine")
    @ApiOperation("获取全部酒")
    public R allWine(){
        List<Wine> allWine = wineService.list();
        Map resultMap = new HashMap();
        resultMap.put("allWine",allWine);
        return R.ok(resultMap);
    }

}
