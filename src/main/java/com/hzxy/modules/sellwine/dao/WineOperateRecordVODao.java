package com.hzxy.modules.sellwine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzxy.modules.sellwine.VO.WineOperateRecordVO;
import com.hzxy.modules.sellwine.entity.WineOperateRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WineOperateRecordVODao extends BaseMapper<WineOperateRecordVO> {
  List<WineOperateRecordVO> queryPage(IPage<WineOperateRecordVO> page,@Param("opr") WineOperateRecordVO opr);

}
