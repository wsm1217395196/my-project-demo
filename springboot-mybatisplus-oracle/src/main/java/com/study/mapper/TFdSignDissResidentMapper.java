package com.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.model.FindReportResidentPageReq;
import com.study.model.FindReportResidentPageVo;
import com.study.model.TFdSignDissResidentModel;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 解决居民明细 Mapper 接口
 * </p>
 *
 * @author wsm
 * @since 2020-07-29
 */
public interface TFdSignDissResidentMapper extends BaseMapper<TFdSignDissResidentModel> {

    Page<FindReportResidentPageVo> findReportResidentPage(Page<FindReportResidentPageVo> page, @Param("req") FindReportResidentPageReq req);

}
