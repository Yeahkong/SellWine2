package com.hzxy.modules.sellwine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzxy.modules.sellwine.entity.WineException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WineExceptionDao extends BaseMapper<WineException> {
  List<WineException> queryPage(IPage<WineException> page,WineException wineException);
}
