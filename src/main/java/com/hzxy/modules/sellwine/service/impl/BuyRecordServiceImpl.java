package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.dao.BuyRecordDao;
import com.hzxy.modules.sellwine.entity.BuyRecord;
import com.hzxy.modules.sellwine.service.BuyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("buyRecordService")
@Slf4j
public class BuyRecordServiceImpl extends ServiceImpl<BuyRecordDao, BuyRecord> implements BuyRecordService {
  @Override
  public PageUtils queryPage(Map<String, Object> params) {
  Long roleId=(Long)params.get("roleId");
  Long wineId = (Long)params.get("wineId");
  Double discount =(Double)params.get("discount");
  String payWay=(String)params.get("payWay");
  String payStatus=(String)params.get("payStatus");
  IPage<BuyRecord> page=this.page(new Query<BuyRecord>()
                  .getPage(params),new QueryWrapper<BuyRecord>()
                  .like(StringUtils.isNotEmpty(roleId),"role_id",roleId)
                  .eq(StringUtils.isNotEmpty(wineId),"wine_id",wineId)
                  .eq(StringUtils.isNotEmpty(discount),"discount",discount)
                  .eq(StringUtils.isNotEmpty(payWay),"pay_way",payWay)
                  .eq(StringUtils.isNotEmpty(payStatus),"pay_status",payStatus));
    return new PageUtils(page);
  }
}
