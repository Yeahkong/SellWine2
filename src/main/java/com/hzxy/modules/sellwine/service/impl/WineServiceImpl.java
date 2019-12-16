package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.modules.sellwine.dao.WineDao;
import com.hzxy.modules.sellwine.entity.Wine;
import com.hzxy.modules.sellwine.service.WineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 13:24
 * @Description:
 */
@Service("wineService")
@Slf4j
public class WineServiceImpl extends ServiceImpl<WineDao, Wine> implements WineService {
}
