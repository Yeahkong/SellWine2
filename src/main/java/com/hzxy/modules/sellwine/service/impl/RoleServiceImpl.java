package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.modules.sellwine.dao.RoleDao;
import com.hzxy.modules.sellwine.entity.Role;
import com.hzxy.modules.sellwine.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 14:56
 * @Description:
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
}
