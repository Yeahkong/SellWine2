package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.VO.WineOrderVO;
import com.hzxy.modules.sellwine.entity.WineOrder;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface WineOrderVOService extends IService<WineOrderVO> {
  IPage<WineOrderVO> queryPage(IPage<WineOrderVO> page,WineOrderVO wineOrderVO);


}
