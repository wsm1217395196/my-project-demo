package com.study.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author wsm
 * @date 2020-07-29
 **/
@Data
@ApiModel("分页查询解约居民明细请求体")
public class FindReportResidentPageReq {

    @ApiModelProperty(value = "报告id")
    @NotEmpty(message = "报告id不能为空")
    private String reportId;

    @ApiModelProperty(value = "机构编码")
    private String orgCode;

    @ApiModelProperty(value = "团队id")
    private String teamId;
}
