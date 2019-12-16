package com.hzxy.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzxy.modules.app.VO.UserFamilyVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-29 13:06
 * @Description:
 */
@Mapper
public interface UserFamilyVODao extends BaseMapper<UserFamilyVO> {

    List<UserFamilyVO> getUserFamily(@Param("params") Map<String,Object> params);

}
