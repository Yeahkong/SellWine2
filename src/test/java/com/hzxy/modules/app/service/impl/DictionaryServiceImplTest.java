package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.api.Assert;
import com.hzxy.common.utils.JsonUtil;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.app.entity.Dictionary;
import com.hzxy.modules.app.form.DictionaryForm;
import com.hzxy.modules.app.service.DictionaryService;
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

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DictionaryServiceImplTest {

    @Autowired
    DictionaryService dictionaryService;

    @Test
    public void save() {

        DictionaryForm dictionaryForm = new DictionaryForm();
        dictionaryForm.setCreateBy(1L);
        dictionaryForm.setDictType("电器类型");
        dictionaryForm.setDictName("灯");
        dictionaryForm.setDescription("测试数据");

        R r = dictionaryService.save(dictionaryForm);

        log.info("r is {}", JsonUtil.toJson(r));

    }

    @Test
    public void queryPage() {
        Map map = new HashMap<>();
        PageUtils pageUtils = dictionaryService.queryPage(map);
        List list = pageUtils.getList();

        log.info("list size is {}",list.size());
    }


    @Test
    public void queryMap(){
        Map map = new HashMap();
        //map.put("dictType","房间类型");
        List<Dictionary> result = dictionaryService.queryMap(map);
        log.info("result size is {}",result.size());
    }

}
