package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.DateUtils;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.dao.WineSellRecordDao;
import com.hzxy.modules.sellwine.entity.WineSellRecord;
import com.hzxy.modules.sellwine.service.WineSellRecordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-21 13:23
 * @Description:
 */
@Service("wineSellRecordService")
public class WineSellRecordServiceImpl extends ServiceImpl<WineSellRecordDao, WineSellRecord> implements WineSellRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Object userIds = params.get("userIds");
        Object wineIds = params.get("wineIds");
        Object beginPayTime = params.get("beginPayTime");
        Object endPayTime = params.get("endPayTime")==null ? DateUtils.getDateNow():params.get("endPayTime");
        List<Long> userIdsForQuery = new ArrayList<>();
        if(userIds!=null){
            userIdsForQuery = (List<Long>)userIds;
        }
        List<Long> wineIdsForQuery = new ArrayList<>();
        if(wineIds!=null){
            wineIdsForQuery = (List<Long>)wineIds;
        }

        IPage<WineSellRecord> page = this.page(new Query<WineSellRecord>().getPage(params),
                new QueryWrapper<WineSellRecord>()
        .in(userIds!=null ,"user_id",userIdsForQuery)
        .in(wineIds!=null,"wine_id",wineIdsForQuery)
        .between(StringUtils.isNotEmpty(beginPayTime),"pay_time",beginPayTime,endPayTime));

        return new PageUtils(page);
    }


}
