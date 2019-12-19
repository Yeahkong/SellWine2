package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
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

        Object userName = params.get("userName");
        Object mobileNo = params.get("mobileNo");

        IPage<WineUser> page = this.page(
                new Query<WineUser>().getPage(params),
                new QueryWrapper<WineUser>().like(StringUtils.isNotEmpty(userName),"user_name",userName)
                .like(StringUtils.isNotEmpty(mobileNo),"mobile_no",mobileNo)
        ) ;


        return new PageUtils(page);
    }

}
