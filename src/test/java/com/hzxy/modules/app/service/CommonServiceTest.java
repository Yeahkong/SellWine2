package com.hzxy.modules.app.service;

import com.hzxy.common.utils.R;
import com.hzxy.modules.app.form.UserFamilyForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;


    @Test
    public void saveUserFamily() {

       /* UserFamilyForm userFamilyForm = new UserFamilyForm();
        userFamilyForm.setFamilyAddress("苏州市");
        userFamilyForm.setMobile("17702213316");

        R r = commonService.saveUserFamily(userFamilyForm);
*/

        log.info("date long is {}",new Date().getTime());

    }
}
