package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.entity.SaleRecord;

import java.util.Map;

public interface SaleRecordService extends IService<SaleRecord> {

  PageUtils queryPage(Map<String,Object> params);

}
