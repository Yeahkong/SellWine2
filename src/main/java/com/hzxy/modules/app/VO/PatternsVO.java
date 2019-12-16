package com.hzxy.modules.app.VO;

import lombok.Data;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-22 14:26
 * @Description:
 */
@Data
public class PatternsVO {
    /**
     * 模式id
     */
    private Long patternId;
    /**
     * 模式名称
     */
    private String patternName;
    /**
     * 模式当前状态
     */
    private String patternStatus;

}
