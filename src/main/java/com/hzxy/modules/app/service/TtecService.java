package com.hzxy.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.app.entity.Ttec;

import java.util.Map;


public interface TtecService extends IService<Ttec> {

    PageUtils queryPage(Map<String,Object> params);

    void deleteBatchIds(String[] cneis);

    boolean updateTtec(Ttec ttec);

}
