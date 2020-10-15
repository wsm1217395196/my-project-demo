package com.study.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mapper.TFdSignDissResidentMapper;
import com.study.model.FindReportResidentPageReq;
import com.study.model.FindReportResidentPageVo;
import com.study.model.TFdSignDissResidentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 解决居民明细 服务实现类
 * </p>
 *
 * @author wsm
 * @since 2020-07-29
 */
@Service
public class TFdSignDissResidentService extends ServiceImpl<TFdSignDissResidentMapper, TFdSignDissResidentModel> {

    @Autowired
    private TFdSignDissResidentMapper tFdSignDissResidentMapper;

    public Page<FindReportResidentPageVo> findReportResidentPage(FindReportResidentPageReq req) {
        Page<FindReportResidentPageVo> page = new Page<>(3, 5);
        page = tFdSignDissResidentMapper.findReportResidentPage(page, req);
        return page;
    }
}
