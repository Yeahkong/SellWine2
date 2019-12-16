package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.modules.app.VO.PatternsVO;
import com.hzxy.modules.app.dao.PatternsDao;
import com.hzxy.modules.app.entity.Patterns;
import com.hzxy.modules.app.service.PatternsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-22 14:16
 * @Description:
 */
@Service("patternsService")
public class PatternsServiceImpl extends ServiceImpl<PatternsDao, Patterns> implements PatternsService {
    @Override
    public List<PatternsVO> getPatternsByFamilyId(Long familyId) {
        Map condition = new HashMap<>();
        condition.put("familyId",familyId);
        List<Patterns> queryResult = baseMapper.getPatternsBy(condition);

        List<PatternsVO> result = new ArrayList<>();

        if(queryResult!=null && queryResult.size()>0){
            for(Patterns patterns : queryResult){
                PatternsVO  patternsVO = new PatternsVO();
                patternsVO.setPatternId(patterns.getId());
                patternsVO.setPatternName(patterns.getPatternName());
                patternsVO.setPatternStatus(patterns.getCurrentStatus());

                result.add(patternsVO);
            }
            return result;
        }

        return null;
    }
}
