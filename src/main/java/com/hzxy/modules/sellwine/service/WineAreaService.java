package com.hzxy.modules.sellwine.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.modules.sellwine.VO.WineAreaVO;
import com.hzxy.modules.sellwine.entity.WineArea;

import java.util.List;

public interface WineAreaService extends IService<WineArea> {

    List<WineArea> queryWineAreaBy(Long id);

    WineAreaVO getTreeData(Long id);

}
