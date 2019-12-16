package com.hzxy.modules.tio.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.modules.tio.dao.SysTioDao;
import com.hzxy.modules.tio.entity.SysTio;
import com.hzxy.modules.tio.service.SysTioService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Program SmartHome
 * @Package com.hzxy.modules.tio.service.impl
 * @ClassName SysTioServiceImpl
 * @Author liuningying
 * @Date 2019-09-05 11:10
 */
@Service("sysTioService")
public class SysTioServiceImpl extends ServiceImpl<SysTioDao, SysTio> implements  SysTioService{
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysTio> page = this.page(
                new Query<SysTio>().getPage(params)
        );
        return new PageUtils(page);
    }
}
