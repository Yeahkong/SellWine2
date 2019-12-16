package com.hzxy.modules.job.service.impl;

import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.job.service.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ScheduleJobServiceImplTest {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Test
    public void queryPage() {
        Map map = new HashMap<>();
        map.put("beanname","testTask");
        PageUtils pageUtils = scheduleJobService.queryPage(map);
        log.info("pageSize is : {}",pageUtils.getPageSize());
    }
}
