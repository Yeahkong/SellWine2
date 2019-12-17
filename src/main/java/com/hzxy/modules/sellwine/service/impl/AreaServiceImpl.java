package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.dao.AreaDao;
import com.hzxy.modules.sellwine.entity.Area;
import com.hzxy.modules.sellwine.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("areaService")
@Slf4j
public class AreaServiceImpl extends ServiceImpl<AreaDao, Area> implements AreaService {


  @Override
  public PageUtils qurryPage(Map<String, Object> params) {
    Long parentId=(Long)params.get("parentId");
    String currentName=(String)params.get("currentName");
    Integer currentLevel=(Integer)params.get("currentLevel");
    IPage<Area> page= this.page(new Query<Area>().getPage(params),new QueryWrapper<Area>()
            .like(StringUtils.isNotEmpty(parentId),"parent_Id",parentId)
            .eq(StringUtils.isNotEmpty(currentName),"currentName",currentName)
            .eq(StringUtils.isNotEmpty(currentLevel),"current_level",currentLevel));
    return new PageUtils(page);
  }
}
