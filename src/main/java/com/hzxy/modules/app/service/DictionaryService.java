package com.hzxy.modules.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.app.entity.Dictionary;
import com.hzxy.modules.app.form.DictionaryForm;

import java.util.List;
import java.util.Map;

public interface DictionaryService extends IService<Dictionary> {

    R save(DictionaryForm dictionaryForm);

    PageUtils queryPage(Map<String,Object> params);

    List<Dictionary> queryMap(Map<String,Object> params);

}
