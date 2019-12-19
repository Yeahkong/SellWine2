package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.dao.WineDao;
import com.hzxy.modules.sellwine.entity.Wine;
import com.hzxy.modules.sellwine.service.WineService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-19 15:27
 * @Description:
 */
@Service("wineService")
public class WineServiceImpl extends ServiceImpl<WineDao, Wine> implements WineService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Object wineName = params.get("wineName");
        IPage<Wine> page = this.page(new Query<Wine>().getPage(params),
                new QueryWrapper<Wine>().like(StringUtils.isNotEmpty(wineName),"wine_name",wineName));

        return new PageUtils(page);
    }
}
