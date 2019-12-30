package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.modules.sellwine.VO.WineExceptionVO;
import com.hzxy.modules.sellwine.entity.WineException;

public interface WineExceptionVOService extends IService<WineExceptionVO> {
  public IPage<WineExceptionVO> queryPage(IPage<WineExceptionVO> page, WineExceptionVO wineExceptionVO);
}
