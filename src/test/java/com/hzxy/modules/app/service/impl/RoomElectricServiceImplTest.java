package com.hzxy.modules.app.service.impl;

import com.hzxy.modules.app.entity.RoomElectric;
import com.hzxy.modules.app.service.RoomElectricService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RoomElectricServiceImplTest {

    @Autowired
    private RoomElectricService roomElectricService;

    @Test
    public void test(){
        RoomElectric roomElectric = new RoomElectric();
        roomElectric.setId(2L);
        roomElectric.setElectrics("6_8");
        roomElectric.setFamilyId(1L);
        roomElectric.setRoomId(2L);

        boolean result = roomElectricService.saveOrUpdate(roomElectric);
        log.info("result is {}",result);
    }

    @Test
    public void testSelect(){
        Map<String,Object> condition = new HashMap<>();
        condition.put("family_id",1L);
        condition.put("room_id",1L);
        List<Map<String,Object>> result = roomElectricService.getRoomElectrics(condition);

        log.info("result size is {}",result.size());

    }

}
