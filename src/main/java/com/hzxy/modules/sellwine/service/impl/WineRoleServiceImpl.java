package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.dao.WineRoleDao;
import com.hzxy.modules.sellwine.entity.WineRole;
import com.hzxy.modules.sellwine.service.WineRoleService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 13:55
 * @Description:
 */
@Service("wineRoleService")
public class WineRoleServiceImpl extends ServiceImpl<WineRoleDao, WineRole> implements WineRoleService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object roleId = params.get("roleId");

        IPage<WineRole> page = this.page(new Query<WineRole>().getPage(params),
                new QueryWrapper<WineRole>().eq(StringUtils.isNotEmpty(roleId),"role_id",Long.parseLong(roleId.toString())));

        return new PageUtils(page);
    }
}
