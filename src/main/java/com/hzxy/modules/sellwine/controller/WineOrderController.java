package com.hzxy.modules.sellwine.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.VO.WineOrderVO;
import com.hzxy.modules.sellwine.entity.Wine;
import com.hzxy.modules.sellwine.entity.WineEquipment;
import com.hzxy.modules.sellwine.entity.WineOrder;
import com.hzxy.modules.sellwine.entity.WineUser;
import com.hzxy.modules.sellwine.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wineOrder")
@Api("订单 相关的Api")
public class WineOrderController  {

  @Autowired
  private WineOrderService wineOrderService;
  @Autowired
  private WineOrderVOService wineOrderVOService;
  @Autowired
  private WineEquipmentService wineEquipmentService;
  @Autowired
  private WineUserService wineUserService;
  @Autowired
  private WineService wineService;


  @GetMapping("/allWineOrder")
  @ApiOperation("获取全部售酒订单")
  public R allWineOrder(){
    List<WineOrder> wineOrderList = wineOrderService.list();
    if(wineOrderList!=null && wineOrderList.size()>0){
      List<WineOrderVO> wineOrderVOS=new ArrayList<>();
      for(WineOrder wineOrder:wineOrderList) {
        WineOrderVO wineOrderVO = convertor(wineOrder);
        wineOrderVOS.add(wineOrderVO);
      }
      return R.ok(wineOrderVOS);
    }else{
      return R.error("表数据为空！");
    }

  }

  public WineOrderVO convertor(WineOrder wineOrder){
    WineOrderVO wineOrderVO=new WineOrderVO();
    BeanUtils.copyProperties(wineOrder,wineOrderVO);
    WineEquipment wineEquipment=wineEquipmentService.getById(wineOrder.getEquipmentId());
    if(wineOrder.getEquipmentId()!=null) {
      wineOrderVO.setEquipmentNo(wineEquipment.getEquipmentNo());
      WineUser wineUser = wineUserService.getById(wineEquipment.getUserId());
      wineOrderVO.setUserName(wineUser.getUserName());
      wineOrderVO.setTrueName(wineUser.getTrueName());
      Wine wine = wineService.getById(wineEquipment.getWineId());
      wineOrderVO.setWineName(wine.getWineName());
    }
    return wineOrderVO;
  }

  @GetMapping("/page")
  @ApiOperation("获取售酒订单分页数据")
  public R page(@RequestParam Map<String,Object> params){



    int page=Integer.valueOf(params.get("page").toString());
    int size=Integer.valueOf(params.get("limit").toString());
    WineOrderVO order=new WineOrderVO();

    String mobileNo=(String)params.get("mobileNo");
    String trueName=(String)params.get("trueName");
    String wineName=(String)params.get("wineName");
    Date buyTime = (Date) params.get("buyTime");
    String equipmentNo = (String) params.get("equipmentNo");
    order.setMobileNo(mobileNo);
    order.setTrueName(trueName);
    order.setEquipmentNo(equipmentNo);
    order.setWineName(wineName);
    order.setBuyTime(buyTime);
    IPage<WineOrderVO> page1 =wineOrderVOService.queryPage(new Page<>(page, size),order);
    PageUtils pageUtils=new PageUtils(page1);
    return R.ok(pageUtils);
  }




}
