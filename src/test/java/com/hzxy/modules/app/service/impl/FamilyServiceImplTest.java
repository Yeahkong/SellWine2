package com.hzxy.modules.app.service.impl;

import com.hzxy.common.utils.JsonUtil;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.app.VO.UserFamilyVO;
import com.hzxy.modules.app.service.FamilyService;
import com.hzxy.modules.app.service.UserFamilyVOService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FamilyServiceImplTest {

    @Autowired
    UserFamilyVOService userFamilyVOService;

    @Test
    public void getUserFamily() {
        Map<String,Object> condition = new HashMap<>();
        condition.put("userId",10);
        List<UserFamilyVO> list = userFamilyVOService.getUserFamily(condition);
        log.info("list size  is {}", list.size());
    }

    @Test
    public void queryPage(){
        Map condition = new HashMap();
        condition.put("familyName","晓辉");
        condition.put("familyAddress","杭州");
        PageUtils pageUtils = userFamilyVOService.queryPage(condition);


        log.info("totalCount is {}",pageUtils.getTotalCount());
    }

    @Test
    public void queryMap(){
        Map condition = new HashMap();
        condition.put("family_name","晓辉的家");
        List<UserFamilyVO> list = new ArrayList<UserFamilyVO>(userFamilyVOService.listByMap(condition));
        UserFamilyVO result = list.stream().max(Comparator.comparing(UserFamilyVO::getFamilyAddress)).get();
        log.info("list size is {}",list.size());
        log.info("result familyAddress is {}",result.getFamilyAddress());
    }

    public static void main(String[] args) {
        String str = "FJLX0001";
        log.info(str.substring(str.length()-4));
        Integer result = Integer.parseInt(str.substring(str.length()-3));
        log.info("result is {}",result);
    }
}
