package com.hzxy.modules.sellwine.controller;

import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.entity.Wine;
import com.hzxy.modules.sellwine.enums.DelFlagEnum;
import com.hzxy.modules.sellwine.service.WineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/allWine")
    @ApiOperation("获取全部酒数据")
    public R allWine(){
        List<Wine> wineList = wineService.list();
        return R.ok(wineList);
    }

    @GetMapping("/singleWine/{wineId}")
    @ApiOperation("获取单个酒信息")
    public R singleWine(@PathVariable Long wineId){
        Wine wine = wineService.getById(wineId);
        return R.ok(wine);
    }


    @PostMapping("/addWine")
    @ApiOperation("添加酒")
    public R addWine(@RequestBody Wine wine){
        boolean saveResult = wineService.save(wine);
        if(saveResult){
            return R.ok("添加酒信息成功");
        }else{
            return R.error("添加酒信息失败");
        }
    }

    @GetMapping("/disableWine/{wineId}")
    @ApiOperation("禁用 酒")
    public R disableWine(@PathVariable Long wineId){
        Wine wine = wineService.getById(wineId);
        if(wine!=null){
            wine.setDelFlag(DelFlagEnum.HIDDEN.getCode());

            boolean updateResult = wineService.updateById(wine);
            if(updateResult){
                return R.ok("禁用成功");
            }else{
                return R.error("禁用失败");
            }
        }else{
            return R.error("查询酒信息为空");
        }
    }

    @PostMapping("/updateWine")
    @ApiOperation("修改酒的信息")
    public R updateWine(@RequestBody Wine wine){
        boolean updateResult = wineService.updateById(wine);
        if(updateResult){
            return R.ok("修改成功");
        }else{
            return R.error("修改失败");
        }
    }

    // 全部  , 单个 ， 添加 ， 修改  , 删除

}
