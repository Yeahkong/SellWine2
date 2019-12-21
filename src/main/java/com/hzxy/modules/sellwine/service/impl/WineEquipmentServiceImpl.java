package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.dao.WineEquipmentDao;
import com.hzxy.modules.sellwine.entity.WineEquipment;
import com.hzxy.modules.sellwine.service.WineEquipmentService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-19 14:01
 * @Description:
 */
@Service("wineEquipmentService")
public class WineEquipmentServiceImpl extends ServiceImpl<WineEquipmentDao, WineEquipment> implements WineEquipmentService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object onLineStatus = params.get("onLineStatus");
        Object equipmentNo = params.get("equipmentNo");
        Object userId = params.get("userId");
        Object status = params.get("status");

        IPage<WineEquipment> page = this.page(new Query<WineEquipment>().getPage(params),
                new QueryWrapper<WineEquipment>()
        .eq(StringUtils.isNotEmpty(onLineStatus),"on_line_status",onLineStatus)
        .eq(StringUtils.isNotEmpty(userId),"user_id",userId)
        .eq(StringUtils.isNotEmpty(status),"status",status)
        .like(StringUtils.isNotEmpty(equipmentNo),"equipment_no",equipmentNo));


        return new PageUtils(page);
    }
}
