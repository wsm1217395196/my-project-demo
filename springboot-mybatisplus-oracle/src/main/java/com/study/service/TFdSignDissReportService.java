package com.study.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.exception.MyRuntimeException;
import com.study.mapper.TFdSignDissReportMapper;
import com.study.model.TFdSignDissReportModel;
import com.study.result.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 家医解约报告 服务实现类
 * </p>
 *
 * @author wsm
 * @since 2020-07-29
 */
@Service
public class TFdSignDissReportService extends ServiceImpl<TFdSignDissReportMapper, TFdSignDissReportModel> {

    @Autowired
    private TFdSignDissReportMapper tFdSignDissReportMapper;

    public IPage getPage(PageParam pageParam) {
        int pageIndex = pageParam.getPageIndex();
        int pageSize = pageParam.getPageSize();
        String sort = pageParam.getSort();
        JSONObject jsonObject = JSONObject.parseObject(pageParam.getCondition());
        String orgCode = jsonObject.getString("orgCode");

        QueryWrapper qw = new QueryWrapper();
        if (!StringUtils.isEmpty(orgCode)) {
            qw.eq("ORG_CODE", orgCode);
        }

        IPage<TFdSignDissReportModel> page = new Page<>();
        if (pageIndex != 0 && pageSize != 0) {
            page = new Page(pageIndex, pageSize);
        }
        page = tFdSignDissReportMapper.selectPage(page, qw);
        page = tFdSignDissReportMapper.getPage(page, orgCode);
        return page;
    }

    @Transactional
    public void auditOrgReport(String reportId, String auditBy) {
        List<TFdSignDissReportModel> vos = findIdByIds(new String[]{reportId});
        if (vos.size() == 0) {
            throw new MyRuntimeException("该社康审核解约报告不存在");
        }
        TFdSignDissReportModel fdSignDissReport = vos.get(0);
        if (fdSignDissReport.getAuditState().equals(TFdSignDissReportModel.AuditState.REVIEWED)) {
            throw new MyRuntimeException("该社康审核解约报告已审核过了，无需再次审核");
        }
        Date date = new Date();
        fdSignDissReport.setAuditState(TFdSignDissReportModel.AuditState.REVIEWED);
        fdSignDissReport.setAuditTime(date);
        fdSignDissReport.setAuditBy(auditBy);
        this.updateById(fdSignDissReport);
    }

    @Transactional
    public void auditParentOrgReport(String[] reportIds, String auditBy) {
        List<TFdSignDissReportModel> vos = findIdByIds(reportIds);
        if (vos.size() != reportIds.length) {
            throw new MyRuntimeException("选择的社康审核解约报告部分不存在");
        }
        boolean auditState = vos.stream().anyMatch(fdSignDissReport -> fdSignDissReport.getAuditState().equals(TFdSignDissReportModel.AuditState.NOT_REVIEWED));
        if (auditState) {
            throw new MyRuntimeException("选择的社康审核解约报告部分一般社康未审核");
        }
        boolean parentAuditState = vos.stream().anyMatch(fdSignDissReport -> fdSignDissReport.getParentAuditState().equals(TFdSignDissReportModel.AuditState.REVIEWED));
        if (parentAuditState) {
            throw new MyRuntimeException("选择的社康审核解约报告部分区域社康已审核");
        }
        Date date = new Date();
        vos.forEach(fdSignDissReport -> {
            fdSignDissReport.setParentAuditState(TFdSignDissReportModel.AuditState.REVIEWED);
            fdSignDissReport.setParentAuditTime(date);
            fdSignDissReport.setParentAuditBy(auditBy);
        });
        this.updateBatchById(vos);
    }

    public List<TFdSignDissReportModel> findIdByIds(String[] ids) {
        QueryWrapper qw = new QueryWrapper();
        qw.in("id", ids);
        qw.select("id,audit_state,parent_audit_state");
        List<TFdSignDissReportModel> list = this.list(qw);
        return list;
    }
}
