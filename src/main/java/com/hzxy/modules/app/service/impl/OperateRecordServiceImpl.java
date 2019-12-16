package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.modules.app.dao.OperateRecordDao;
import com.hzxy.modules.app.entity.OperateRecordEntity;
import com.hzxy.modules.app.service.OperateRecordService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("operateRecordService")
public class OperateRecordServiceImpl extends ServiceImpl<OperateRecordDao, OperateRecordEntity> implements OperateRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OperateRecordEntity> page = this.page(
                new Query<OperateRecordEntity>().getPage(params),
                new QueryWrapper<OperateRecordEntity>()
        );

        return new PageUtils(page);
    }

}