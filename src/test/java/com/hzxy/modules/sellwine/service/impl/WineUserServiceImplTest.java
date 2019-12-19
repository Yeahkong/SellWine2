package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.modules.sellwine.entity.WineUser;
import com.hzxy.modules.sellwine.service.WineUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class WineUserServiceImplTest {

    @Autowired
    private WineUserService wineUserService;

    @Test
    public void test(){
        WineUser wineUser = wineUserService.getOne(new QueryWrapper<WineUser>().eq("role_id",5)
        .eq("area_id",7),true);

        Assert.assertNotNull(wineUser);

    }

}
