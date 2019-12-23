package com.hzxy.modules.sellwine.service.impl;

import com.hzxy.common.utils.JsonUtil;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.sellwine.entity.WineSellRecord;
import com.hzxy.modules.sellwine.service.WineSellRecordService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class WineSellRecordServiceImplTest {

    @Autowired
    private WineSellRecordService wineSellRecordService;

    @Test
    public void queryPage() {

        Map<String,Object> params = new HashMap<>();

        params.put("pageNo",1);
        params.put("pageSize",10);

        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);

        List<Long> wineIds = new ArrayList<>();
        wineIds.add(1L);
        wineIds.add(2L);

        params.put("userIds",userIds);
        params.put("wineIds",wineIds);

        PageUtils pageUtils = wineSellRecordService.queryPage(params);

        List<WineSellRecord> wineSellRecords = (List<WineSellRecord>)pageUtils.getList();

        log.info("wineSellRecords  is ", JsonUtil.toJson(wineSellRecords));

    }
}
