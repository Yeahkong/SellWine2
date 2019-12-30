package com.hzxy.modules.sellwine.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.VO.WineExceptionVO;
import com.hzxy.modules.sellwine.entity.WineException;
import com.hzxy.modules.sellwine.service.WineExceptionService;
import com.hzxy.modules.sellwine.service.WineExceptionVOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/wineException")
@Api("异常表 相关的api")
public class WineExceptionController {
 @Autowired
  private WineExceptionVOService wineExceptionVOService;


  @GetMapping("/page")
  @ApiOperation("获取异常记录的分页数据")
  public R page(@RequestParam Map<String,Object> params){

    int page= Integer.valueOf(params.get("page").toString());
    int limit=Integer.valueOf(params.get("limit").toString());
    WineExceptionVO wineExceptionVO=new WineExceptionVO();

    String trueName= (String) params.get("trueName");
    String mobileNo=(String) params.get("mobileNo");
    String areaName=(String) params.get("areaName");
    String equipmentNo=(String) params.get("equipmentNo");
    wineExceptionVO.setTrueName(trueName);
    wineExceptionVO.setMobileNo(mobileNo);
    wineExceptionVO.setAreaName(areaName);
    wineExceptionVO.setEquipmentNo(equipmentNo);
    IPage<WineExceptionVO> page1=wineExceptionVOService.queryPage(new Page<>(page,limit),wineExceptionVO);
    PageUtils pageUtils=new PageUtils(page1);
    return R.ok(pageUtils);
  }
}
