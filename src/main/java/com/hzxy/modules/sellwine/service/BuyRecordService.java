package com.hzxy.modules.sellwine.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.entity.BuyRecord;

import java.util.Map;

public interface BuyRecordService extends IService<BuyRecord> {

  PageUtils queryPage(Map<String,Object> params);

}
