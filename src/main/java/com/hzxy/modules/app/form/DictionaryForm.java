package com.hzxy.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-11 15:45
 * @Description:
 */
@Data
@ApiModel("字典表单")
public class DictionaryForm {

    @ApiModelProperty("类型")
    private String dictType;

    @ApiModelProperty("中文名称")
    private String dictName;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("创建者id")
    private Long createBy;

}
