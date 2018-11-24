package com.justl.controller;

import com.justl.domain.auto.ReportDomain;
import com.justl.domain.response.ResponseData;
import com.justl.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author buhuaqi
 * @date 2018-11-12 14:47
 */
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    ReportService reportService;

    /**
     * 查询动态举报用户信息
     */
    @PostMapping("/dyinfo")
    public ResponseData<Map<String, Object>> dynamicReportInfo(@RequestBody ReportDomain reportDomain) {
        return new ResponseData<>(reportService.dynamicReportQuery(reportDomain));
    }

    /**
     * 动态举报逻辑
     */
    @PostMapping("/dynamic")
    public ResponseData<Map<String, Object>> dynamicReport(@RequestBody ReportDomain reportDomain) {
        return new ResponseData<>(reportService.dynamicReport(reportDomain));
    }

    /**
     * 查询聊天举报用户信息
     */
    @PostMapping("/chatinfo")
    public ResponseData<Map<String, Object>> chatReportInfo(@RequestBody ReportDomain reportDomain) {
        return new ResponseData<>(reportService.chatReportQuery(reportDomain));
    }

    /**
     * 聊天举报逻辑
     */
    @PostMapping("/chat")
    public ResponseData<Map<String, Object>> chatReport(@RequestBody ReportDomain reportDomain) {
        return new ResponseData<>(reportService.chatReport(reportDomain));
    }
}
