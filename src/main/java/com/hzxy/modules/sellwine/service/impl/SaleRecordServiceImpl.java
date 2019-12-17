package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.dao.SaleRecordDao;
import com.hzxy.modules.sellwine.entity.SaleRecord;
import com.hzxy.modules.sellwine.service.SaleRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("saleRecordService")
@Slf4j
public class SaleRecordServiceImpl extends ServiceImpl<SaleRecordDao, SaleRecord> implements SaleRecordService {
  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    Long equipmentId = (Long)params.get("equipmentId");
    String payWay=(String)params.get("payWay");
    String payStatus=(String)params.get("payStatus");
    IPage<SaleRecord> page=this.page(new Query<SaleRecord>().getPage(params),
            new QueryWrapper<SaleRecord>()
            .like(StringUtils.isNotEmpty(equipmentId),"equipment_id",equipmentId)
            .eq(StringUtils.isNotEmpty(payWay),"pay_way",payWay)
            .eq(StringUtils.isNotEmpty(payStatus),"pay_status",payStatus));


    return new PageUtils(page);
  }
}
