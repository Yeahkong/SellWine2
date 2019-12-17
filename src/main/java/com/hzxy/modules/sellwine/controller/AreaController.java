package com.hzxy.modules.sellwine.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.entity.Area;
import com.hzxy.modules.sellwine.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api("地域 相关的Api")
@RequestMapping("/area")
public class AreaController {

  @Autowired
  private AreaService areaService;

  @GetMapping("/allArea")
  @ApiOperation("获取所有地区数据")
  public R allArea(){
    List<Area> allArea=areaService.list();
    Map resultMap=new HashMap();
    resultMap.put("allArea",allArea);
    return R.ok(resultMap);
  }


  @GetMapping("/page")
  @ApiOperation("获取地区的分页数据")
  public R page(Map<String,Object> params){
    PageUtils page=areaService.qurryPage(params);
    Map resultMap=new HashMap();
    resultMap.put("page",page);
    return R.ok(resultMap);
  }


  @GetMapping("/getOneArea")
  @ApiOperation("根据id获取一个地区的数据")
  public R getOneArea(Long id){
    Area getOneArea=areaService.getById(id);
    Map resultMap=new HashMap();
    resultMap.put("getOneArea",getOneArea);
    return R.ok(resultMap);
  }

  @PostMapping("/addArea")
  @ApiOperation("增加地区")
  public R addArea(Area area){
    Area checkArea = areaService.getOne(new QueryWrapper<Area>()
            .eq(StringUtils.isNotEmpty(area.getCurrentName()),"current_name",area.getCurrentName()));
    if(checkArea==null){
      areaService.save(area);
    }else{
      return R.error("地区已存在！");
    }
    return R.ok();
  }

  @PostMapping("/updateArea")
  @ApiOperation("更新地区")
  public R updateArea(Area area){
    boolean result=areaService.updateById(area);
    if(result){
      return R.ok("更新地区成功！");
    }else{
      return R.error("更新地区失败！");
    }
  }

  @GetMapping("/deleteArea")
  @ApiOperation("删除地区")
  public R deleteArea(Long id){
    boolean result=areaService.removeById(id);
    if(result){
      return R.ok("删除地区成功！");
    }else {
      return R.error("删除地区失败！");
    }

  }



}
