package com.hzxy.modules.app.controller;

import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.app.entity.OperateRecordEntity;
import com.hzxy.modules.app.service.OperateRecordService;
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
 * @date 2019-12-02 15:57:55
 */
@RestController
@RequestMapping("app/operaterecord")
public class OperateRecordController {
    @Autowired
    private OperateRecordService operateRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("app:operaterecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = operateRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("app:operaterecord:info")
    public R info(@PathVariable("id") Integer id){
		OperateRecordEntity operateRecord = operateRecordService.getById(id);

        return R.ok().put("operateRecord", operateRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("app:operaterecord:save")
    public R save(@RequestBody OperateRecordEntity operateRecord){
		operateRecordService.save(operateRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("app:operaterecord:update")
    public R update(@RequestBody OperateRecordEntity operateRecord){
		operateRecordService.updateById(operateRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("app:operaterecord:delete")
    public R delete(@RequestBody Integer[] ids){
		operateRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
