package com.study.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.study.model.TFdSignDissReportModel;
import com.study.result.PageParam;
import com.study.result.ResultView;
import com.study.service.TFdSignDissReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 家医解约报告 前端控制器
 * </p>
 *
 * @author wsm
 * @since 2020-07-29
 */
@Api(tags = "家医解约报告 前端控制器")
@Validated
@RestController
@RequestMapping("/tFdSignDissReportModel")
public class TFdSignDissReportController {

    @Autowired
    private TFdSignDissReportService tFdSignDissReportService;

    @ApiOperation(value = "查询全部", notes = "")
    @GetMapping("/authority/getAll")
    public ResultView getAll() {
        List<TFdSignDissReportModel> models = tFdSignDissReportService.list();
        return ResultView.success(models);
    }

    @ApiOperation(value = "分页条件查询", notes = "提交参数：{\"pageIndex\":1,\"pageSize\":10,\"sort\":\"name-desc\",\"condition\":\"{\'orgCode\':\'\'}\"}")
    @PostMapping("/authority/getPage")
    public ResultView getPage(@RequestBody PageParam pageParam) {
        IPage iPage = tFdSignDissReportService.getPage(pageParam);
        return ResultView.success(iPage);
    }

    @ApiOperation(value = "根据id查询", notes = "")
    @GetMapping("/authority/getById/{id}")
    public ResultView getById(@PathVariable String id) {
        TFdSignDissReportModel model = tFdSignDissReportService.getById(id);
        return ResultView.success(model);
    }

    @ApiOperation(value = "新增", notes = "")
    @PostMapping("/authority_button/add")
    public ResultView add(@RequestBody TFdSignDissReportModel model) {
        Date date = new Date();
//        model.setId("wsm" + UUID.randomUUID().toString().substring(0,25));
        model.setCreateTime(date);
        tFdSignDissReportService.save(model);
        return ResultView.success(model);
    }

    @ApiOperation(value = "修改", notes = "")
//    @PostMapping("/authority_button/update")
    public ResultView update(@RequestBody TFdSignDissReportModel model) {
        Date date = new Date();
        tFdSignDissReportService.updateById(model);
        return ResultView.success(model);
    }

    @ApiOperation(value = "根据id删除", notes = "")
//    @DeleteMapping("/authority_button/deleteById")
    public ResultView deleteById(@RequestParam String id) {
        tFdSignDissReportService.removeById(id);
        return ResultView.success();
    }

    @ApiOperation(value = "根据ids删除", notes = "")
//    @DeleteMapping("/authority_button/deleteByIds")
    public ResultView deleteByIds(@RequestParam String[] ids) {
        tFdSignDissReportService.removeByIds(Arrays.asList(ids));
        return ResultView.success();
    }

    @ApiOperation(value = "一般社康审核解约", notes = "")
    @GetMapping(value = "/auditOrgReport")
    public ResultView auditOrgReport(@RequestParam @ApiParam("报告id") @NotEmpty(message = "报告id不能为空") String reportId, @RequestParam @ApiParam("审核人") @NotEmpty(message = "审核人不能为空") String auditBy) {
        tFdSignDissReportService.auditOrgReport(reportId, auditBy);
        return ResultView.success();
    }

    @ApiOperation(value = "区域社康审核解约", notes = "")
    @GetMapping(value = "/auditParentOrgReport")
    public ResultView auditParentOrgReport(@RequestParam @ApiParam("报告ids") @NotEmpty(message = "报告id不能为空") String[] reportIds, @RequestParam @ApiParam("审核人") @NotEmpty(message = "审核人不能为空") String auditBy) {
        tFdSignDissReportService.auditParentOrgReport(reportIds, auditBy);
        return ResultView.success();
    }
}

