package com.hzxy.modules.sellwine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzxy.modules.sellwine.entity.WineUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WineUserDao extends BaseMapper<WineUser> {
}
