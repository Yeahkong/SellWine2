package com.hzxy.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.app.entity.AirConditioningEntity;

import java.util.Map;

/**
 * 
 *
 * @author liuningying
 * @email 1591686150@qq.com
 * @date 2019-11-13 09:43:45
 */
public interface AirConditioningService extends IService<AirConditioningEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

