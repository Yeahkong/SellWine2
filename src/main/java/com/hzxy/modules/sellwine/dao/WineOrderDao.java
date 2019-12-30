package com.hzxy.modules.sellwine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzxy.modules.sellwine.VO.WineOrderVO;
import com.hzxy.modules.sellwine.entity.WineOrder;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WineOrderDao extends BaseMapper<WineOrder> {
//  List<WineOrderVO> queryPage(Page<WineOrderVO> page);
//  IPage<WineOrderVO> queryPage(@Param("page") Page<WineOrderVO> page);
IPage<WineOrder> queryPage(@Param("page") Page<WineOrder> page);

}
