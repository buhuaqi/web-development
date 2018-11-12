package com.justl.serviceImp;

import com.avos.avoscloud.AVUser;
import com.justl.dao.ReportDao;
import com.justl.domain.auto.User;
import com.justl.exception.SysPrivilegeException;
import com.justl.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
    public Map<String, Object> dynamicReport(User user) {
        Map<String, Object> map = new HashMap<>();
        if (null == user) {
            logger.error("参数为空");
            throw new SysPrivilegeException("参数为空");
        }
        AVUser avUser = reportDao.reportedUser(user);
        map.put("userName", avUser.get("username"));
        map.put("objectId", avUser.get("objectId"));
        map.put("mobilePhoneNumber", avUser.get("mobilePhoneNumber"));
        map.put("cScore", avUser.get("CScore"));
        map.put("reportNum", avUser.get("ReportNum"));

        return map;
    }
}
