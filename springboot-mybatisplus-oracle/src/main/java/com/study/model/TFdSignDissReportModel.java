package com.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 家医解约报告
 * </p>
 *
 * @author wsm
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_FD_SIGN_DISS_REPORT")
@ApiModel(value="TFdSignDissReportModel对象", description="家医解约报告")
public class TFdSignDissReportModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    @TableId(type = IdType.UUID)
    private String id;

    //    @ApiModelProperty(value = "t_fd_sign_task.id")
    @TableField("TASK_ID")
    private String taskId;

    //    @ApiModelProperty(value = "机构编码")
    @TableField("ORG_CODE")
    private String orgCode;

    //    @ApiModelProperty(value = "签约总数")
    @TableField("SIGN_NUM")
    private Integer signNum;

    //    @ApiModelProperty(value = "解约总数")
    @TableField("DISS_NUM")
    private Integer dissNum;

    //    @ApiModelProperty(value = "未签约数")
    @TableField("UNSIGN_NUM")
    private Integer unsignNum;

    //    @ApiModelProperty(value = "迁出本辖区数")
    @TableField("OUT_REGION_NUM")
    private Integer outRegionNum;

    //    @ApiModelProperty(value = "连续一年无法取得联系数")
    @TableField("LOSE_CONT_NUM")
    private Integer loseContNum;

    //    @ApiModelProperty(value = "连续一年拒绝服务数")
    @TableField("REFUSED_CONT_NUM")
    private Integer refusedContNum;

    //    @ApiModelProperty(value = "主动解约数")
    @TableField("PERSON_DISS_NUM")
    private Integer personDissNum;

    //    @ApiModelProperty(value = "档案迁移数")
    @TableField("OUT_FILES_NUM")
    private Integer outFilesNum;

    //    @ApiModelProperty(value = "到期解约数")
    @TableField("EXPIRE_NUM")
    private Integer expireNum;

    //    @ApiModelProperty(value = "其它解约数")
    @TableField("OTHER_NUM")
    private Integer otherNum;

    //    @ApiModelProperty(value = "一般社康审核状态 0：未审核 1：已审核")
    @TableField("AUDIT_STATE")
    private Integer auditState;

    public interface AuditState{
        /**
         * 0：未审核
         */
        Integer NOT_REVIEWED = 0;
        /**
         * 1：已审核
         */
        Integer REVIEWED = 1;
    }

    //    @ApiModelProperty(value = "区域社康审核状态  0：未审核 1：已审核")
    @TableField("PARENT_AUDIT_STATE")
    private Integer parentAuditState;

    @TableField("CREATE_BY")
    private String createBy;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("AUDIT_BY")
    private String auditBy;

    @TableField("AUDIT_TIME")
    private Date auditTime;

    @TableField("PARENT_AUDIT_BY")
    private String parentAuditBy;

    @TableField("PARENT_AUDIT_TIME")
    private Date parentAuditTime;

}
