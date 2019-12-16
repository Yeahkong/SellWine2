package com.hzxy.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.modules.app.entity.RoomElectric;

import java.util.List;
import java.util.Map;

public interface RoomElectricService extends IService<RoomElectric> {

    List<Map<String,Object>> getRoomElectrics(Map<String,Object> params);

    RoomElectric getRoomElectric(Map<String,Object> params);

}
