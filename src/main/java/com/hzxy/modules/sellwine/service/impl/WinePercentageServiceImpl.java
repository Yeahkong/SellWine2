package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.modules.sellwine.dao.WinePercentageDao;
import com.hzxy.modules.sellwine.entity.WinePercentage;
import com.hzxy.modules.sellwine.service.WinePercentageService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 17:11
 * @Description:
 */
@Service("winePercentageService")
public class WinePercentageServiceImpl extends ServiceImpl<WinePercentageDao, WinePercentage> implements WinePercentageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<WinePercentage> page = this.page(new Query<WinePercentage>().getPage(params));

        return new PageUtils(page);
    }
}
