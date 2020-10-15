package com.study.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.study.model.TFdSignDissReportModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 家医解约报告 Mapper 接口
 * </p>
 *
 * @author wsm
 * @since 2020-07-29
 */
public interface TFdSignDissReportMapper extends BaseMapper<TFdSignDissReportModel> {

    IPage<TFdSignDissReportModel> getPage(IPage<TFdSignDissReportModel> page, @Param("corCode") String orgCode);
}
