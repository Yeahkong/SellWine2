package com.hzxy.modules.sellwine.controller;

import com.hzxy.modules.sellwine.service.WinePercentageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 17:12
 * @Description:
 */
@RestController
@RequestMapping("/wine_percentage")
@Api("提成 相关Api")
public class WinePercentageController {

    @Autowired
    private WinePercentageService winePercentageService;

}
