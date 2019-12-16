package com.hzxy.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.app.entity.AccessTokenEntity;

import java.util.Map;

/**
 * 
 *
 * @author liuningying
 * @email 1591686150@qq.com
 * @date 2019-11-23 15:20:38
 */
public interface AccessTokenService extends IService<AccessTokenEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

