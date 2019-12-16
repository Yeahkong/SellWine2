package com.hzxy.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.app.entity.OperateRecordEntity;

import java.util.Map;

/**
 * 
 *
 * @author liuningying
 * @email 1591686150@qq.com
 * @date 2019-12-02 15:57:55
 */
public interface OperateRecordService extends IService<OperateRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

