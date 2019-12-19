package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.entity.WineEquipment;

import java.util.Map;

public interface WineEquipmentService extends IService<WineEquipment> {

    PageUtils queryPage(Map<String,Object> params);

}
