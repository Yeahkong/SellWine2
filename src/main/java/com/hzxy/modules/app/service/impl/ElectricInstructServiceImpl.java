package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.modules.app.dao.ElectricInstructDao;
import com.hzxy.modules.app.entity.ElectricInstruct;
import com.hzxy.modules.app.service.ElectricInstructService;
import org.springframework.stereotype.Service;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-22 16:24
 * @Description:
 */
@Service("electricInstructService")
public class ElectricInstructServiceImpl extends ServiceImpl<ElectricInstructDao, ElectricInstruct> implements ElectricInstructService {
}
