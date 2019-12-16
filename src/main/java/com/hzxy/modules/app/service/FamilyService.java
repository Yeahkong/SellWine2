package com.hzxy.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.app.entity.FamilyEntity;
import com.hzxy.modules.app.entity.Ttec;

import java.util.Map;

public interface FamilyService extends IService<FamilyEntity> {


    void deleteBatch(Long[] familyIds);

    PageUtils queryPage(Map<String, Object> params);

    Ttec getBoardNo(long id);

}
