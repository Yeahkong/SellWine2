package com.hzxy.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.modules.app.VO.PatternsVO;
import com.hzxy.modules.app.entity.Patterns;

import java.util.List;

public interface PatternsService extends IService<Patterns> {

    List<PatternsVO> getPatternsByFamilyId(Long familyId);

}
