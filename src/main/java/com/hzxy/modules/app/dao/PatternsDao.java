package com.hzxy.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzxy.modules.app.entity.Patterns;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-22 14:10
 * @Description:
 */
@Mapper
public interface PatternsDao extends BaseMapper<Patterns> {

    List<Patterns> getPatternsBy(@Param("params") Map<String,Object> params);

}
