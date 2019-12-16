package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.modules.app.dao.AirConditioningDao;
import com.hzxy.modules.app.entity.AirConditioningEntity;
import com.hzxy.modules.app.service.AirConditioningService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("airConditioningService")
public class AirConditioningServiceImpl extends ServiceImpl<AirConditioningDao, AirConditioningEntity> implements AirConditioningService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AirConditioningEntity> page = this.page(
                new Query<AirConditioningEntity>().getPage(params),
                new QueryWrapper<AirConditioningEntity>()
        );

        return new PageUtils(page);
    }

}