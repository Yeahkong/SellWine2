package com.hzxy.modules.sellwine.controller;


import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.entity.SaleRecord;
import com.hzxy.modules.sellwine.service.SaleRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api("销售记录(按两) 相关的Api")
@RequestMapping("/saleRecord")
public class SaleRecordController {
  @Autowired
  private SaleRecordService saleRecordService;

  @GetMapping("/allSaleRecord")
  @ApiOperation("获取所有销售记录（按两算）")
  private R allSaleRecord(){
    List<SaleRecord> allSaleRecord = saleRecordService.list();
    Map resultMap=new HashMap();
    resultMap.put("allSaleRecord",allSaleRecord);
    return R.ok(resultMap);

  }
  @GetMapping("/getOneSaleRecord")
  @ApiOperation("根据id获取一条销售记录")
  public R getOneSaleRecord(Long id){
    SaleRecord oneSaleRecord=saleRecordService.getById(id);
    Map resultMap=new HashMap();
    resultMap.put("oneSaleRecord",oneSaleRecord);
    return R.ok(resultMap);
  }

  @GetMapping("/page")
  @ApiOperation("获取销售记录的分页数据")
  public R page(@RequestParam Map<String,Object> params){
    PageUtils page=saleRecordService.queryPage(params);
    Map resultMap=new HashMap();
    resultMap.put("page",page);
    return R.ok(resultMap);

  }
  @PostMapping("/addSaleRecord")
  @ApiOperation("添加销售记录")
  public R addSaleRecord(SaleRecord saleRecord){
    boolean result=saleRecordService.save(saleRecord);
    if(result){
      return R.ok("添加销售记录成功！");
    }else{
      return R.error("添加销售记录失败！");
    }
  }

  @PostMapping("/updateSaleRecord")
  @ApiOperation("更新销售记录")
  public R updateSaleRecord(SaleRecord saleRecord){
    boolean result=saleRecordService.updateById(saleRecord);
    if(result){
      return R.ok("更新销售记录成功！");
    }else{
      return R.error("更新销售记录失败！");
    }
  }

  @GetMapping("/deleteSaleRecord")
  @ApiOperation("删除销售记录")
  public R deleteSaleRecord(Long id){
    boolean result=saleRecordService.removeById(id);
    if(result){
      return R.ok("删除销售记录成功！");
    }else{
      return R.error("删除销售记录失败！");
    }
  }









}
