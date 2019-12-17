package com.hzxy.modules.sellwine.controller;

import com.hzxy.modules.sellwine.entity.BuyRecord;
import com.hzxy.modules.sellwine.service.BuyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class BuyRecordControllerTest {
  @Autowired
  private BuyRecordService buyRecordService;




  @Test
  public void allBuyRecord(){
    List<BuyRecord> allBuyRecord = buyRecordService.list();
    log.info(String.valueOf(allBuyRecord));
  }

}
