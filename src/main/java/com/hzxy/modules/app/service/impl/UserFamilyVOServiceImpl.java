package com.hzxy.modules.app.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.app.VO.UserFamilyVO;
import com.hzxy.modules.app.dao.UserFamilyVODao;
import com.hzxy.modules.app.service.UserFamilyVOService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-29 15:37
 * @Description:
 */
@Service
public class UserFamilyVOServiceImpl extends ServiceImpl<UserFamilyVODao, UserFamilyVO> implements UserFamilyVOService {


    @Override
    public List<UserFamilyVO> getUserFamily(Map<String, Object> params) {
        return baseMapper.getUserFamily(params);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String  familyName = (String)params.get("familyName");
        String  familyAddress = (String)params.get("familyAddress");
        String mobile = (String)params.get("mobile");

        IPage<UserFamilyVO> page = this.page(
                new Query<UserFamilyVO>().getPage(params),
                new QueryWrapper<UserFamilyVO>().like(StringUtils.isNotEmpty(familyName),"family_name",familyName)
                        .like(StringUtils.isNotEmpty(familyAddress),"family_address",familyAddress)
                        .like(StringUtils.isNotEmpty(mobile),"mobile",mobile));

        return new PageUtils(page);
    }
}
