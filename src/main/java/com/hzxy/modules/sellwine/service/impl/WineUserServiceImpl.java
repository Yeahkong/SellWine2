package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.dao.WineUserDao;
import com.hzxy.modules.sellwine.entity.WineUser;
import com.hzxy.modules.sellwine.service.WineUserService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 14:42
 * @Description:
 */
@Service("wineUserService")
public class WineUserServiceImpl extends ServiceImpl<WineUserDao, WineUser> implements WineUserService {


    @Override
    public PageUtils queryPage(Map<String,Object> params){
        return null;
    }

}
