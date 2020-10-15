package com.study.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.model.FindReportResidentPageReq;
import com.study.model.FindReportResidentPageVo;
import com.study.service.TFdSignDissResidentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 解决居民明细 前端控制器
 * </p>
 *
 * @author wsm
 * @since 2020-07-29
 */
@Api(tags = "解决居民明细 前端控制器")
@RestController
@RequestMapping("/tFdSignDissResidentModel")
public class TFdSignDissResidentController {

    @Autowired
    private TFdSignDissResidentService tFdSignDissResidentService;

    @ApiOperation(value = "分页查询解约居民明细页面")
    @PostMapping("/findReportResidentPage")
    public Page findReportResidentPage(@RequestBody @Valid FindReportResidentPageReq req) {
        Page<FindReportResidentPageVo> Page = tFdSignDissResidentService.findReportResidentPage(req);
        return Page;
    }

}

