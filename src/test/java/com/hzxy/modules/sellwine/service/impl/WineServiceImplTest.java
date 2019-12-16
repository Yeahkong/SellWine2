package com.hzxy.modules.sellwine.service.impl;

import com.hzxy.modules.sellwine.service.WineService;
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
public class WineServiceImplTest {

    @Autowired
    private WineService wineService;

    @Test
    public void test(){
        int count = wineService.count();

        log.info("count is {}",count);
    }

}
