package com.hzxy.modules.sellwine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzxy.modules.sellwine.entity.Wine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WineDao extends BaseMapper<Wine> {
}
