package com.hzxy.modules.sellwine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzxy.modules.sellwine.entity.WineOperateRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WineOperateRecordDao extends BaseMapper<WineOperateRecord> {
  List<WineOperateRecord> queryPage(IPage<WineOperateRecord> page,WineOperateRecord wineOperateRecord);

}
