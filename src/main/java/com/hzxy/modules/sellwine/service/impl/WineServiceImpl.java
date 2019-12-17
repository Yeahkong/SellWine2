package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.app.VO.UserFamilyVO;
import com.hzxy.modules.sellwine.dao.WineDao;
import com.hzxy.modules.sellwine.entity.Wine;
import com.hzxy.modules.sellwine.service.WineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 13:24
 * @Description:
 */
@Service("wineService")
@Slf4j
public class WineServiceImpl extends ServiceImpl<WineDao, Wine> implements WineService {



  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    String name =(String)params.get("name");
    Integer capacity = (Integer)params.get("capacity");
    Double unitPrice=(Double)params.get("unitPrice");
    IPage<Wine> page = this.page(
            new Query<Wine>().getPage(params),
            new QueryWrapper<Wine>().like(StringUtils.isNotEmpty(name),"name",name)
                    .like(StringUtils.isNotEmpty(capacity),"capacity",capacity)
                    .like(StringUtils.isNotEmpty(unitPrice),"unitPrice",unitPrice));

    return new PageUtils(page);
  }


}
