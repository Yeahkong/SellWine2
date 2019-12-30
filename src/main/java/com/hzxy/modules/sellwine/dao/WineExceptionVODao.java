package com.hzxy.modules.sellwine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzxy.modules.sellwine.VO.WineExceptionVO;
import com.hzxy.modules.sellwine.entity.WineException;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface WineExceptionVODao extends BaseMapper<WineExceptionVO> {
  /*@Param("ex")里面的参数和对象名称一致*/
  List<WineExceptionVO> queryPage(IPage<WineExceptionVO> page,@Param("ex") WineExceptionVO ex);
}
