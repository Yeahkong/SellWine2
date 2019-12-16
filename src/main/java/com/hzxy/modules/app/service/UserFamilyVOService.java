package com.hzxy.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.app.VO.UserFamilyVO;

import java.util.List;
import java.util.Map;

public interface UserFamilyVOService extends IService<UserFamilyVO> {

    List<UserFamilyVO> getUserFamily(Map<String ,Object> params);

    PageUtils queryPage(Map<String,Object> params);

}
