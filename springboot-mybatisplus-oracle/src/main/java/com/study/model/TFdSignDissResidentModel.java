package com.study.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 解决居民明细
 * </p>
 *
 * @author wsm
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_FD_SIGN_DISS_RESIDENT")
@ApiModel(value="TFdSignDissResidentModel对象", description="解决居民明细")
public class TFdSignDissResidentModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;

    @ApiModelProperty(value = "报告id")
    @TableField("REORT_ID")
    private String reortId;

    @ApiModelProperty(value = "机构编码")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "团队id")
    @TableField("TEAM_ID")
    private String teamId;

    @ApiModelProperty(value = "居民id")
    @TableField("PATIENT_ID")
    private String patientId;

    @ApiModelProperty(value = "身份证")
    @TableField("IDCARD")
    private String idcard;

    @ApiModelProperty(value = "居民姓名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "性别")
    @TableField("SEX")
    private Double sex;

    @ApiModelProperty(value = "年龄")
    @TableField("AGE")
    private String age;

    @ApiModelProperty(value = "电话")
    @TableField("TEL")
    private String tel;

    @ApiModelProperty(value = "地址")
    @TableField("ADDRESS")
    private String address;

    @ApiModelProperty(value = "人群分类")
    @TableField("CROWD_TYPE")
    private String crowdType;

    @ApiModelProperty(value = "户籍类型")
    @TableField("RESIDENT_TYPE")
    private Double residentType;

    @ApiModelProperty(value = "解约原因")
    @TableField("DISS_REASON")
    private String dissReason;

    @ApiModelProperty(value = "解约时间")
    @TableField("DISS_TIME")
    private LocalDateTime dissTime;

    @ApiModelProperty(value = "操作人")
    @TableField("OPERATOR")
    private String operator;

    @ApiModelProperty(value = "操作时间")
    @TableField("OPERATOR_TIME")
    private LocalDateTime operatorTime;

    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATE_BY")
    private String createBy;


}
