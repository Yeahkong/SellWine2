package com.hzxy.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.app.entity.UserFamilyEntity;

import java.util.Map;

public interface UserFamilyService extends IService<UserFamilyEntity> {

     void deleteBatch(Long[] userFamilyIds);



}
