package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.entity.WineRole;

import java.util.Map;

public interface WineRoleService extends IService<WineRole> {
    PageUtils queryPage(Map<String,Object> params);
}
