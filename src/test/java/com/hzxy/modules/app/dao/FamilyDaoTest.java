package com.hzxy.modules.app.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzxy.modules.app.VO.UserFamilyVO;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FamilyDaoTest {

    @Autowired
    FamilyDao familyDao;


    @Test
    public void test(){

        Map<String,Object> condition = new HashMap<>();
        condition.put("userId",10);
        List<UserFamilyVO> list = familyDao.getUserFamily(condition);

        log.info("list size is {}",list.size());

    }
}
