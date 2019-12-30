package com.hzxy.modules.sellwine.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.VO.WineOperateRecordVO;
import com.hzxy.modules.sellwine.service.WineOperateRecordVOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/wineOperateRecord")
@Api("操作记录 相关的api")

public class WineOperateRecordController {

  @Autowired
  private WineOperateRecordVOService wineOperateRecordVOService;

  @GetMapping("page")
  @ApiOperation("操作记录表的分页查询")
  public R page(@RequestParam Map<String,Object> params){
    int page=Integer.valueOf(params.get("page").toString());
    int limit=Integer.valueOf(params.get("limit").toString());
    WineOperateRecordVO wineOperateRecordVO=new WineOperateRecordVO();
    String trueName=(String)params.get("trueName");
    String mobileNo=(String)params.get("mobileNo");
    String equipmentNo=(String)params.get("equipmentNo");
    String areaName =(String)params.get("areaName");
    wineOperateRecordVO.setAreaName(areaName);
    wineOperateRecordVO.setTrueName(trueName);
    wineOperateRecordVO.setMobileNo(mobileNo);
    wineOperateRecordVO.setEquipmentNo(equipmentNo);
    IPage<WineOperateRecordVO> page1=wineOperateRecordVOService.queryPage(new Page<>(page,limit),wineOperateRecordVO);
    PageUtils pageUtils=new PageUtils(page1);
    return R.ok(pageUtils);
  }

}
