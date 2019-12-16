package com.hzxy.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.Query;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.app.dao.TtecDao;
import com.hzxy.modules.app.entity.Ttec;
import com.hzxy.modules.app.service.TtecService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;


/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-12 09:09
 * @Description:
 */
@Service("ttecService")
public class TtecServiceImpl extends ServiceImpl<TtecDao, Ttec>  implements TtecService {
    @Override
    public void deleteBatchIds(String[] cneis) {
        baseMapper.deleteBatchIds(Arrays.asList(cneis));
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String cnei = (String)params.get("cnei");
        String scmId = (String)params.get("scmId");
        String netType = (String)params.get("netType");
        String onLineStatus = (String)params.get("onLineStatus");
        // 主控板id  联网方式 在线状态
        IPage<Ttec> page = this.page(
                new Query<Ttec>().getPage(params),
                new QueryWrapper<Ttec>().like(StringUtils.isNotEmpty(cnei),"cnei",cnei)
                .like(StringUtils.isNotEmpty(scmId),"scm_id",scmId)
                .eq(StringUtils.isNotEmpty(netType),"net_type",netType)
                .eq(StringUtils.isNotEmpty(onLineStatus),"on_line_status",(onLineStatus))
        );

        return new PageUtils(page);
    }
    @Override
    public boolean updateTtec(Ttec ttec){
        return baseMapper.updateTtec(ttec);
    }
}
