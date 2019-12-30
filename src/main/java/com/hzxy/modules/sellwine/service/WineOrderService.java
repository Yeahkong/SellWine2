package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.VO.WineOrderVO;
import com.hzxy.modules.sellwine.entity.WineOrder;

import java.util.Map;

public interface WineOrderService extends IService<WineOrder> {
//  public Page<WineOrderVO> queryPage(Map<String,Object> params);
//  public IPage<WineOrderVO> queryPage(Page<WineOrderVO> page);
  public IPage<WineOrder> queryPage(Page<WineOrder> pager);
}
