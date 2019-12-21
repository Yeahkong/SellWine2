package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.entity.WinePercentage;

import java.util.Map;

public interface WinePercentageService extends IService<WinePercentage> {

    PageUtils queryPage(Map<String,Object> params);

}
