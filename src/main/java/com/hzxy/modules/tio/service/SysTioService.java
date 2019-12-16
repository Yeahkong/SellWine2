package com.hzxy.modules.tio.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.tio.entity.SysTio;

import java.util.Map;

/**
 * @Program SmartHome
 * @Package com.hzxy.modules.tio.service
 * @ClassName SysTioService
 * @Author liuningying
 * @Date 2019-09-05 11:01
 */

public interface SysTioService extends IService<SysTio> {
    PageUtils queryPage(Map<String, Object> params);

}
