package com.hzxy.modules.sellwine.VO;

import com.hzxy.modules.sellwine.entity.WineArea;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 15:24
 * @Description:
 */
@Data
public class WineAreaVO {

    private Long id;

    private Long parentId;

    private String currentName;

    private Integer currentLevel;

    private List<WineAreaVO> children=new ArrayList<>();

}
