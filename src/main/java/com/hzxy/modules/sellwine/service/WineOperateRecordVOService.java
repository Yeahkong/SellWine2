package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.modules.sellwine.VO.WineOperateRecordVO;
import com.hzxy.modules.sellwine.entity.WineOperateRecord;

public interface WineOperateRecordVOService extends IService<WineOperateRecordVO> {

  IPage<WineOperateRecordVO> queryPage(IPage<WineOperateRecordVO> page, WineOperateRecordVO wineOperateRecordVO);

}

