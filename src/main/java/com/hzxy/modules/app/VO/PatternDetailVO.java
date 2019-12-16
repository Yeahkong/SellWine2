package com.hzxy.modules.app.VO;

import lombok.Data;

import java.util.List;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-22 17:13
 * @Description:  模式详细信息
 */
@Data
public class PatternDetailVO {

    private Long patternId;

    private String patternName;

    private String currentStatus;

    private List<PatternTemp> patternTempList;


}
