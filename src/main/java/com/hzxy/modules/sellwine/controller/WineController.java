package com.hzxy.modules.sellwine.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.entity.Wine;
import com.hzxy.modules.sellwine.service.WineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/page")
    @ApiOperation("获取酒的分页数据")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = wineService.queryPage(params);
        return  R.ok().put("data",page);
    }

    @GetMapping("/getOneWine")
    @ApiOperation("根据id得到一瓶酒")
    public R getById(Long id){
        Wine getWine=wineService.getById(id);
        Map resultMap = new HashMap();
        resultMap.put("getWine",getWine);
        return R.ok(resultMap);
    }

    @PostMapping("/addWine")
    @ApiOperation("添加酒")
    public R addWine(Wine wine) {

        Wine checkIsExist = wineService.getOne(new QueryWrapper<Wine>()
                .eq(StringUtils.isNotEmpty(wine.getName()),"name",wine.getName()));
        if(checkIsExist==null){
            //添加酒
            wineService.save(wine);
        }else {
            return R.error("酒已存在");
        }
        return R.ok();
    }

    @PostMapping("/updateWine")
    @ApiOperation("修改酒")
    public R updateWine(Wine wine){
        boolean result = wineService.updateById(wine);
        if(result==true){
            return R.ok();
        }else{
            return R.error("修改酒失败");
        }
    }

    @GetMapping("/deleteWine")
    @ApiOperation("删除酒")
    public R deleteWine( Long id){
        boolean result=wineService.removeById(id);
        if(result) {
            return R.ok("删除成功");
        }else{
            return R.error("删除失败");
        }
    }




}
