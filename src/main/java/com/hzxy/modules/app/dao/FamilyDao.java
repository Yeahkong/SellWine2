package com.hzxy.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzxy.modules.app.VO.UserFamilyVO;
import com.hzxy.modules.app.entity.FamilyEntity;
import com.hzxy.modules.app.entity.Ttec;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FamilyDao extends BaseMapper<FamilyEntity> {

    List<UserFamilyVO> getUserFamily( @Param("params")Map<String,Object> params);

    Ttec getBoardNo(long id);

}
