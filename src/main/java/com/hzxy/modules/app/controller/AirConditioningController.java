package com.hzxy.modules.app.controller;

import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.app.entity.AirConditioningEntity;
import com.hzxy.modules.app.service.AirConditioningService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author liuningying
 * @email 1591686150@qq.com
 * @date 2019-11-13 09:43:45
 */
@RestController
@RequestMapping("app/airconditioning")
public class AirConditioningController {
    @Autowired
    private AirConditioningService airConditioningService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("app:airconditioning:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = airConditioningService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("app:airconditioning:info")
    public R info(@PathVariable("id") Long id){
		AirConditioningEntity airConditioning = airConditioningService.getById(id);

        return R.ok().put("airConditioning", airConditioning);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("app:airconditioning:save")
    public R save(@RequestBody AirConditioningEntity airConditioning){
		airConditioningService.save(airConditioning);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("app:airconditioning:update")
    public R update(@RequestBody AirConditioningEntity airConditioning){
		airConditioningService.updateById(airConditioning);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("app:airconditioning:delete")
    public R delete(@RequestBody Long[] ids){
		airConditioningService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
