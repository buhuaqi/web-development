package com.justl.serviceImp;

import com.justl.dao.ReportDao;
import com.justl.domain.auto.ReportDomain;
import com.justl.exception.SysPrivilegeException;
import com.justl.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author buhuaqi
 * @date 2018-11-12 15:02
 */
@Service("reportService")
public class ReportServiceImp implements ReportService {
    private static Logger logger = LoggerFactory.getLogger(ReportServiceImp.class);

    @Resource
    ReportDao reportDao;

    @Override
    public Map<String, Object> dynamicReportQuery(ReportDomain reportDomain) {
        if (null == reportDomain) {
            logger.error("请求参数为空");
            throw new SysPrivilegeException("请求参数为空");
        }
        Map<String, Object> map = reportDao.reportedUser(reportDomain);
        return map;
    }

    @Override
    public Map dynamicReport(ReportDomain reportDomain) {
        if (null == reportDomain) {
            logger.error("请求参数为空");
            throw new SysPrivilegeException("请求参数为空");
        }
        reportDao.report(reportDomain);
        Map<String, Object> map = reportDao.reportedUser(reportDomain);
        return map;
    }

    @Override
    public Map chatReportQuery(ReportDomain reportDomain) {
        if (null == reportDomain) {
            logger.error("请求参数为空");
            throw new SysPrivilegeException("请求参数为空");
        }
        Map<String, Object> map = reportDao.reportedUserChat(reportDomain);
        return map;
    }

    @Override
    public Map chatReport(ReportDomain reportDomain) {
        if (null == reportDomain) {
            logger.error("请求参数为空");
            throw new SysPrivilegeException("请求参数为空");
        }
        reportDao.chatReport(reportDomain);
        reportDao.reportedUserChat(reportDomain);
        Map<String, Object> map = reportDao.reportedUserChat(reportDomain);
        return map;
    }
}
