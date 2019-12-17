package com.hzxy.modules.sellwine.controller;

import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.entity.BuyRecord;
import com.hzxy.modules.sellwine.service.BuyRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api("购买记录(按瓶) 相关的Api")
@RequestMapping("/buyRecord")
public class BuyRecordController{

  @Autowired
  private BuyRecordService buyRecordService;

  @GetMapping("/allBuyRecord")
  @ApiOperation("获取所有购买记录")
  public R allBuyRecord(){
    List<BuyRecord> allBuyRecord=buyRecordService.list();
    Map resultMap=new HashMap();
    resultMap.put("allBuyRecord",allBuyRecord);
    return R.ok(resultMap);
  }

  @GetMapping("/getOneBuyRecord")
  @ApiOperation("根据id获取一条购买记录")
  public R getOneBuyRecord(Long id){
    BuyRecord getOneBuyRecord=buyRecordService.getById(id);
    Map resultMap = new HashMap();
    resultMap.put("getOneBuyRecord",getOneBuyRecord);
    return R.ok(resultMap);
  }
  @GetMapping("/page")
  @ApiOperation("获取购买记录的分页数据")
  public R page(@RequestParam Map<String, Object> params){
    PageUtils page=buyRecordService.queryPage(params);
    return R.ok().put("page",page);

  }
  @PostMapping("/addBuyRecord")
  @ApiOperation("添加购买记录")
  public R addBuyRecord(BuyRecord buyRecord){
    boolean result = buyRecordService.save(buyRecord);
    if(result){
      return R.ok("添加购买记录成功！");
    }else{
      return R.error("添加购买记录失败！");
    }
  }

  @PostMapping("/updateBuyRecord")
  @ApiOperation("更新购买记录")
  public R updateBuyRecord(BuyRecord buyRecord){
    boolean result = buyRecordService.updateById(buyRecord);
    if (result){
      return R.ok("修改购买记录成功！");
    }else{
      return R.error("修改购买记录失败！");
    }

  }

  @GetMapping("/deleteBuyRecord")
  @ApiOperation("删除购买记录")
  public R deleteBuyRecord(Long id){
    boolean result = buyRecordService.removeById(id);
      if(result){
        return R.ok("删除购买记录成功！");
      }else{
        return R.error("删除购买记录失败！");
    }
  }



}
