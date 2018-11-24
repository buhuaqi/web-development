package com.justl.service;

import com.justl.domain.auto.ReportDomain;

import java.util.Map;

/**
 * @author buhuaqi
 * @date 2018-11-12 15:00
 */
public interface ReportService {
    /**
     * 动态举报查询
     **/
    Map dynamicReportQuery(ReportDomain reportDomain);

    /**
     * 动态举报
     */
    Map dynamicReport(ReportDomain reportDomain);

    /**
     * 聊天举报查询
     **/
    Map chatReportQuery(ReportDomain reportDomain);

    /**
     * 聊天举报
     */
    Map chatReport(ReportDomain reportDomain);
}
