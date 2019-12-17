package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.entity.Area;

import java.util.Map;


public interface AreaService extends IService<Area> {
  PageUtils qurryPage(Map<String,Object> params);
}
