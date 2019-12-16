package com.hzxy.modules.app.dao;

import com.hzxy.modules.app.entity.UserFamilyEntity;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserFamilyDaoTest {

    @Autowired
    private UserFamilyDao userFamilyDao;

    @Test
    public void test(){
        ArrayList<Long> list = new ArrayList<Long>();
        list.add(19L);
        list.add(20L);
        list.add(30L);
        List<UserFamilyEntity> result = userFamilyDao.selectBatchIds(list);
        //int result = userFamilyDao.deleteById(12);
        log.info("result size is {}",result.size());
    }

}
