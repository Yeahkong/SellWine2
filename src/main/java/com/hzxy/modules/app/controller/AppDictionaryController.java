package com.hzxy.modules.app.controller;

import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.app.form.DictionaryForm;
import com.hzxy.modules.app.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-11 20:12
 * @Description:
 */
@RestController
@Api("字典表接口")
@RequestMapping("/appDictionary")
public class AppDictionaryController {

    @Autowired
    DictionaryService dictionaryService;

    @PostMapping("/save")
    @ApiOperation("保存字典信息")
    public R save(@RequestBody DictionaryForm dictionaryForm){
        if(StringUtils.isNotEmpty(dictionaryForm.getDictType()) && StringUtils.isNotEmpty(dictionaryForm.getDictName())){
            return dictionaryService.save(dictionaryForm);
        }else{
            return R.error("类型和中文名称都不能为空");
        }
    }

    @GetMapping("/page")
    @ApiOperation("字典分页数据")
    public R page(@RequestParam Map<String, Object> params){
        return R.ok().put("data",dictionaryService.queryPage(params));
    }

    @GetMapping("/list")
    @ApiOperation("获取某种类型dictType字典数据集合")
    public R list(@RequestParam Map<String,Object> params){
        return R.ok().put("data",dictionaryService.queryMap(params));
    }

}
