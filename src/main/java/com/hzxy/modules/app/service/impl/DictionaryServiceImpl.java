package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.*;
import com.hzxy.modules.app.VO.UserFamilyVO;
import com.hzxy.modules.app.dao.DictionaryDao;
import com.hzxy.modules.app.entity.Dictionary;
import com.hzxy.modules.app.form.DictionaryForm;
import com.hzxy.modules.app.service.DictionaryService;
import com.hzxy.modules.app.utils.MathUtils;
import com.hzxy.modules.app.utils.PinYinUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-11 15:42
 * @Description:
 */
@Service("dictionaryService")
@Slf4j
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, Dictionary> implements DictionaryService {

    @Override
    public R save(DictionaryForm dictionaryForm) {
        try{
            String dictType = dictionaryForm.getDictType();
            Map condition = new HashMap<>();
            condition.put("dict_type",dictType);
            List<Dictionary> queryResult = baseMapper.selectByMap(condition);
            Dictionary dictionary = new Dictionary();
            log.info("dictionaryForm is {}",JsonUtil.toJson(dictionaryForm));
            BeanUtils.copyProperties(dictionaryForm,dictionary);
            log.info("dictionary is {}",JsonUtil.toJson(dictionary));
            if(queryResult!=null && queryResult.size()>0){
                // 获取最大编码
                Dictionary existDictionary = queryResult.stream().max(Comparator.comparing(Dictionary::getDictCode)).get();
                String existDictCode = existDictionary.getDictCode();
                Integer maxCode = Integer.parseInt(existDictCode.substring(existDictCode.length()-4));
                String strMaxCode = MathUtils.getMaxCode(maxCode);
                String pinYinType = PinYinUtils.converterToFirstSpell(dictionaryForm.getDictType());
                dictionary.setDictCode(pinYinType+strMaxCode);
                dictionary.setCreateTime(DateUtils.getDateNow());

                baseMapper.insert(dictionary);
            }else{
                String dictCode = PinYinUtils.converterToFirstSpell(dictionaryForm.getDictType())+"0001";
                dictionary.setDictCode(dictCode);
                dictionary.setCreateTime(DateUtils.getDateNow());
                baseMapper.insert(dictionary);
            }
        }catch (Exception e){
            return R.error(e.getMessage());
        }

        return R.ok();
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String dictType = (String)params.get("dictType");

        IPage<Dictionary> page = this.page(
                new Query<Dictionary>().getPage(params),
                new QueryWrapper<Dictionary>().like(StringUtils.isNotEmpty(dictType),"dict_type",dictType));

        return new PageUtils(page);
    }

    @Override
    public List<Dictionary> queryMap(Map<String, Object> params) {

        String dictType = (String)params.get("dictType");

        List<Dictionary> result = baseMapper.selectList(new QueryWrapper<Dictionary>()
        .eq(StringUtils.isNotEmpty(dictType),"dict_type",dictType));

        return result;
    }


}
