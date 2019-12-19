package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.entity.Wine;

import java.util.Map;

public interface WineService extends IService<Wine> {

    PageUtils queryPage(Map<String,Object> params);

}
