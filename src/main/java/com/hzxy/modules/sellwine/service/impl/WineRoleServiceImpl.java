package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.modules.sellwine.dao.WineRoleDao;
import com.hzxy.modules.sellwine.entity.WineRole;
import com.hzxy.modules.sellwine.service.WineRoleService;
import org.springframework.stereotype.Service;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 13:55
 * @Description:
 */
@Service("wineRoleService")
public class WineRoleServiceImpl extends ServiceImpl<WineRoleDao, WineRole> implements WineRoleService {
}
