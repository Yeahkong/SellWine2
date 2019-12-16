package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.modules.app.dao.ElectricsDao;
import com.hzxy.modules.app.entity.Electrics;
import com.hzxy.modules.app.service.ElectricsService;
import org.springframework.stereotype.Service;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-17 14:46
 * @Description:
 */
@Service("electricsService")
public class ElectricsServiceImpl extends ServiceImpl<ElectricsDao, Electrics> implements ElectricsService {
}
