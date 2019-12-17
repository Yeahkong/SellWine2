package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.modules.sellwine.VO.WineAreaVO;
import com.hzxy.modules.sellwine.dao.WineAreaDao;
import com.hzxy.modules.sellwine.entity.WineArea;
import com.hzxy.modules.sellwine.service.WineAreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 14:48
 * @Description:
 */
@Service("wineAreaService")
public class WineAreaServiceImpl extends ServiceImpl<WineAreaDao, WineArea> implements WineAreaService {

    @Override
    public List<WineArea> queryWineAreaBy(Long id) {
       return baseMapper.selectList(new QueryWrapper<WineArea>().eq("parent_id",id));
    }

    @Override
    public WineAreaVO getTreeData(Long id) {
        WineArea wineArea = baseMapper.selectById(id);
        WineAreaVO wineAreaVO = new WineAreaVO();
        BeanUtils.copyProperties(wineArea,wineAreaVO);
        List<WineArea> wineAreas = queryWineAreaBy(id);

        for(WineArea tempData : wineAreas){
            WineAreaVO wineAreaVO1 = getTreeData(tempData.getId());
            wineAreaVO.getChildren().add(wineAreaVO1);
        }
        return wineAreaVO;
    }
}
