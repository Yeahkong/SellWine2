package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.app.dao.RoomElectricDao;
import com.hzxy.modules.app.entity.Dictionary;
import com.hzxy.modules.app.entity.RoomElectric;
import com.hzxy.modules.app.service.DictionaryService;
import com.hzxy.modules.app.service.RoomElectricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-15 16:23
 * @Description:
 */
@Service("roomElectricService")
public class RoomElectricServiceImpl extends ServiceImpl<RoomElectricDao, RoomElectric> implements RoomElectricService {

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public List<Map<String,Object>> getRoomElectrics(Map<String, Object> params) {
        String familyId = (String)params.get("familyId");
        String roomId = (String)params.get("roomId");

        List<Map<String, Object>> queryResult = baseMapper.selectMaps(new QueryWrapper<RoomElectric>()
        .eq(StringUtils.isNotEmpty(familyId),"family_id",familyId)
        .eq(StringUtils.isNotEmpty(roomId),"room_id",roomId));

        if(queryResult!=null && queryResult.size()>0){
            String electrics = (String)queryResult.get(0).get("electrics");
            if(StringUtils.isNotEmpty(electrics)){
                List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
                String[] dictIds = electrics.split("_");
                for(String dictId : dictIds){
                    Dictionary dictionary = dictionaryService.getById(dictId);
                    if(dictionary!=null){
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",dictionary.getId());
                        map.put("name",dictionary.getDictName());
                        result.add(map);
                    }
                }
                return result;
            }
        }
        return null;
    }
    @Override
    public RoomElectric getRoomElectric(Map<String,Object> params){
        List<RoomElectric> roomElectrics = baseMapper.selectByMap(params);
        if(roomElectrics!=null && roomElectrics.size()>0){
            return baseMapper.selectByMap(params).get(0);
        }else{
            return null;
        }

    }

}
