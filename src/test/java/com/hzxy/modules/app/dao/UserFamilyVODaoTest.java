package com.hzxy.modules.app.dao;

import com.hzxy.common.utils.JsonUtil;
import com.hzxy.modules.app.VO.UserFamilyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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
public class UserFamilyVODaoTest {

    @Autowired
    UserFamilyVODao userFamilyVODao;

    @Test
    public void test(){

        Map<String,Object> condition = new HashMap<>();
        condition.put("userId",10);
        condition.put("familyName","晓辉");

        List<UserFamilyVO> list = userFamilyVODao.getUserFamily(condition);

        log.info("list size is {}",list.size());
        for(UserFamilyVO familyVO : list){
            log.info("userFamilyVO is {}",JsonUtil.toJson(familyVO));
        }

    }


}
