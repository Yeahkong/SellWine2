package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.modules.app.dao.UserFamilyDao;
import com.hzxy.modules.app.entity.UserFamilyEntity;
import com.hzxy.modules.app.service.UserFamilyService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-29 15:49
 * @Description:
 */
@Service
public class UserFamilyServiceImpl extends ServiceImpl<UserFamilyDao, UserFamilyEntity> implements UserFamilyService {
    @Override
    public void deleteBatch(Long[] userFamilyIds) {
        baseMapper.deleteBatchIds(Arrays.asList(userFamilyIds));
    }
}
