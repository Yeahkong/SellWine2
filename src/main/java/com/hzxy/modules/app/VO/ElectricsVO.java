package com.hzxy.modules.app.VO;

import lombok.Data;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-18 11:23
 * @Description:
 */
@Data
public class ElectricsVO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String electricNo;

    private String electricStatus;

    private String electricStatusDetail;

    private String electricName;

    private String electricTypeId;

    private String bindStatus;

}
