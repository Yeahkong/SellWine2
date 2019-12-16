package com.hzxy.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-16 09:36
 * @Description:
 */
@Data
@ApiModel("房屋家电表单")
public class RoomElectricForm {

    @ApiModelProperty("家庭id")
    private Long familyId;

    @ApiModelProperty("房屋id")
    private Long roomId;

    @ApiModelProperty("电器类别id")
    private Long electricTypeId;

    @ApiModelProperty("电器No")
    private String electricNo;

    @ApiModelProperty("电器名称")
    private String electricName;

}
