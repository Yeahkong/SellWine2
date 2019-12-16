package com.hzxy.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzxy.modules.app.entity.Ttec;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TtecDao extends BaseMapper<Ttec> {
  boolean updateTtec(Ttec ttec);


}
