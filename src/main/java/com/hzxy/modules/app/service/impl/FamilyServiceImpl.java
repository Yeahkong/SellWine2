package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.modules.app.dao.FamilyDao;
import com.hzxy.modules.app.entity.FamilyEntity;
import com.hzxy.modules.app.entity.Ttec;
import com.hzxy.modules.app.service.FamilyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-25 14:06
 * @Description:
 */
@Service("familyService")
public class FamilyServiceImpl extends ServiceImpl<FamilyDao, FamilyEntity> implements FamilyService {

    @Override
    public void deleteBatch(Long[] familyIds) {
        this.removeByIds(Arrays.asList(familyIds));
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String  familyName = (String)params.get("familyName");
        String  familyAddress = (String)params.get("familyAddress");


        IPage<FamilyEntity> page = this.page(
                new Query<FamilyEntity>().getPage(params),
                new QueryWrapper<FamilyEntity>().like(StringUtils.isNotBlank(familyName),"family_name",familyName)
                        .like(StringUtils.isNotBlank(familyAddress),"family_address",familyAddress));


        return new PageUtils(page);
    }
    @Override
    public Ttec getBoardNo(long id){
        return baseMapper.getBoardNo(id);
    }
}
