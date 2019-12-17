package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.entity.Wine;
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
    public void test() {
        int count = wineService.count();

        log.info("count is {}", count);
    }

    @Test
    public void test1() {
        Wine getWine = wineService.getById(1);
        log.info(String.valueOf(getWine));

    }

    @Test
    public void test2() {
        Wine wine = new Wine();
        wine.setId((long) 2);
        wine.setCapacity(10);
        wine.setName("劲酒");
        wine.setRemark("劲酒虽好，可不要贪杯哦！");
        wine.setUnitPrice(10.0);
        log.info(String.valueOf(wine));
    }

    @Test
    public void test3() {
        boolean result = wineService.removeById(3);
        log.info(String.valueOf(result));
    }

    @Test
    public void test4() {
        Wine wine=new Wine();
        Wine checkIsExist = wineService.getOne(new QueryWrapper<Wine>()
                .eq(StringUtils.isNotEmpty(wine.getName()), "name", wine.getName()));
        if (checkIsExist == null) {
            //添加酒
            wineService.save(wine);
            log.info("添加酒成功");
        } else {
            log.info("酒已存在");
        }

    }
}
