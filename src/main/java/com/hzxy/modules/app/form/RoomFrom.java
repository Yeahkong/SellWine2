package com.hzxy.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Program SmartHome
 * @Package com.hzxy.modules.app.form
 * @ClassName RoomFrom
 * @Author liuningying
 * @Date 2019-11-09 10:00
 */
@Data
@ApiModel("房间表单")
public class RoomFrom {
    @ApiModelProperty("家庭id")
    private Long familyId;

    @ApiModelProperty("房间id")
    private String roomId;

    @ApiModelProperty("房间名称")
    private String roomName;
}
