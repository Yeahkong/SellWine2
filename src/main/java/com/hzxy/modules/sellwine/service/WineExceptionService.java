package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.modules.sellwine.entity.WineException;

public interface WineExceptionService extends IService<WineException> {
  public IPage<WineException> queryPage(IPage<WineException> page,WineException wineException);
}
