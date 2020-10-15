package com.study.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author wsm
 * @date 2020-07-29
 **/
@Data
@ApiModel("分页查询解约居民明细结构")
public class FindReportResidentPageVo {

    @TableField("ID")
    private String id;

//    @ApiModelProperty(value = "报告id")
//    @TableField("REORT_ID")
//    private String reortId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "团队名称")
    private String teamName;

    @ApiModelProperty(value = "居民姓名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "性别")
    @TableField("SEX")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    @TableField("AGE")
    private String age;

    @ApiModelProperty(value = "人群分类")
    @TableField("CROWD_TYPE")
    private String crowdType;

    @ApiModelProperty(value = "户籍类型")
    @TableField("RESIDENT_TYPE")
    private Integer residentType;

    @ApiModelProperty(value = "电话")
    @TableField("TEL")
    private String tel;

    @ApiModelProperty(value = "地址")
    @TableField("ADDRESS")
    private String address;

    @ApiModelProperty(value = "解约原因")
    @TableField("DISS_REASON")
    private String dissReason;


    @ApiModelProperty(value = "操作人")
    @TableField("OPERATOR")
    private String operator;

    @ApiModelProperty(value = "操作时间")
    @TableField("OPERATOR_TIME")
    private Date operatorTime;
}
