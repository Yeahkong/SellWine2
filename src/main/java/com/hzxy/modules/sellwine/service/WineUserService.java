package com.hzxy.modules.sellwine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.entity.WineUser;

import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 14:42
 * @Description:
 */
public interface WineUserService extends IService<WineUser> {

    PageUtils queryPage(Map<String,Object> params);

}
