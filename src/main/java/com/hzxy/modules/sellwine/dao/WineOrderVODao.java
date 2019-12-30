package com.hzxy.modules.sellwine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzxy.modules.sellwine.VO.WineOrderVO;
import com.hzxy.modules.sellwine.entity.WineOrder;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WineOrderVODao extends BaseMapper<WineOrderVO> {
  List<WineOrderVO> queryPage(IPage<WineOrderVO> page, @Param("order") WineOrderVO order);


}
