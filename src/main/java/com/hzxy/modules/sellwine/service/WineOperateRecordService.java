package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.modules.sellwine.entity.WineOperateRecord;

public interface WineOperateRecordService extends IService<WineOperateRecord> {

  IPage<WineOperateRecord> queryPage(IPage<WineOperateRecord> page,WineOperateRecord wineOperateRecord);

}

