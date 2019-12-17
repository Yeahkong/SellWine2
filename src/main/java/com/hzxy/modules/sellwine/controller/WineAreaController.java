package com.hzxy.modules.sellwine.controller;

import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.VO.WineAreaVO;
import com.hzxy.modules.sellwine.service.WineAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 16:25
 * @Description:
 */
@RestController
@RequestMapping("/wineArea")
@Api("区域 相关Api")
public class WineAreaController {

    @Autowired
    private WineAreaService wineAreaService;

    @GetMapping("/getAreaTreeData")
    @ApiOperation("获取树状结构的区域数据")
    public R getAreaTreeData(){
        WineAreaVO wineAreaVO = wineAreaService.getTreeData(1L);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("treeData",wineAreaVO);
        return R.ok(resultMap);
    }

}
