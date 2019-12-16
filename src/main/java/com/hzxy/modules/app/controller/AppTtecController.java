package com.hzxy.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.DateUtils;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.app.entity.Ttec;
import com.hzxy.modules.app.service.CommonService;
import com.hzxy.modules.app.service.TtecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-12 14:53
 * @Description:
 */
@RestController
@Api("主控板相关接口")
@RequestMapping("/appTtec")
public class AppTtecController {

    @Autowired
    private TtecService ttecService;

    @Autowired
    private CommonService commonService;

    @ApiOperation("保存主控板")
    @PostMapping("/saveTtec")
    public R save(@RequestBody Ttec ttec){
        ttec.setCreateTime(DateUtils.getDateNow());
        boolean result = ttecService.save(ttec);
        if(result){
            return R.ok();
        }else{
            return R.error("保存主控板失败");
        }
    }

    @GetMapping("/allTtec")
    @ApiOperation("获取全部主控板数据")
    public R all(){
        List<Ttec> ttecList = ttecService.list();
        Map resultMap = new HashMap();
        resultMap.put("ttecList",ttecList);
        return R.ok(resultMap);
    }

    @GetMapping("/info/{cnei}")
    @ApiOperation(value="获取单个主控板信息")
    public R info(@PathVariable String cnei){
        if(StringUtils.isNotEmpty(cnei)){
            Ttec ttec = ttecService.getOne(new QueryWrapper<Ttec>()
            .eq(StringUtils.isNotEmpty(cnei),"cnei",cnei));

            return R.ok().put("data",ttec);
        }else{
            return R.error("参数不能为空");
        }
    }

    @GetMapping("/pageTtec")
    @ApiOperation(value = "获取主控板分页数据")
    public R page(@RequestParam Map<String,Object> params){
        PageUtils pageUtils = ttecService.queryPage(params);
        return R.ok().put("data",pageUtils);
    }

    @PostMapping("/updateTtec")
    @ApiOperation(value = "修改主控板数据")
    public R update(@RequestBody Ttec ttec){
         // @TODO
        boolean result = ttecService.updateTtec(ttec);
        if(result){
            return R.ok();
        }else{
            return R.error("修改主控板数据失败");
        }

    }


    @DeleteMapping("/deleteTtec")
    @ApiOperation(value = "删除主控板数据")
    public R delete(@RequestBody String[] cneis){
        if(cneis!=null && cneis.length>0){
            return commonService.deleteTtec(cneis);
        }else{
            return R.error("参数不能为空");
        }

    }


}
