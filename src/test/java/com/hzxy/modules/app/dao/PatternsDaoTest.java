package com.hzxy.modules.app.dao;

import com.hzxy.modules.app.entity.Patterns;
import lombok.extern.slf4j.Slf4j;
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
public class PatternsDaoTest {

    @Autowired
    private PatternsDao patternsDao;

    @Test
    public void test(){
        Map condition = new HashMap<>();
        condition.put("familyId",1L);

        List<Patterns> result = patternsDao.getPatternsBy(condition);

        log.info("result size is {}",result.size());

    }

}
