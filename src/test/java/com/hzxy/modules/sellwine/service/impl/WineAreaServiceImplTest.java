package com.hzxy.modules.sellwine.service.impl;

import com.hzxy.common.utils.JsonUtil;
import com.hzxy.modules.sellwine.VO.WineAreaVO;
import com.hzxy.modules.sellwine.entity.WineArea;
import com.hzxy.modules.sellwine.service.WineAreaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class WineAreaServiceImplTest {

    @Autowired
    private WineAreaService wineAreaService;

    @Test
    public void queryWineAreaBy() {
       log.info("wineArea is {}",JsonUtil.toJson(getTreeNodes(1L)));
    }

    public  int fibonacci(int n) {
        if (n == 1 || n == 2) {     // 递归终止条件
            return 1;       // 简单情景
        }
        return fibonacci(n - 1) + fibonacci(n - 2); // 相同重复逻辑，缩小问题的规模

    }

    public WineAreaVO getTreeNodes(Long id){
        WineArea wineArea = wineAreaService.getById(id);
        WineAreaVO wineAreaVO = new WineAreaVO();
        BeanUtils.copyProperties(wineArea,wineAreaVO);
        List<WineArea> wineAreas = wineAreaService.queryWineAreaBy(id);

        for(WineArea tempData : wineAreas){
            WineAreaVO wineAreaVO1 = getTreeNodes(tempData.getId());
            wineAreaVO.getChildren().add(wineAreaVO1);
        }
        return wineAreaVO;
    }
}
