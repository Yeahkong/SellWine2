package com.hzxy.modules.app.dao;

import com.hzxy.modules.app.entity.RoomElectric;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RoomElectricDaoTest {

    @Autowired
    private RoomElectricDao roomElectricDao;

    @Test
    public void test(){
        RoomElectric roomElectric = new RoomElectric();
        roomElectric.setFamilyId(1L);
        roomElectric.setRoomId(1L);
        roomElectric.setElectrics("6_7");

        int result = roomElectricDao.insert(roomElectric);
        log.info("result is {}",result);
    }

}
