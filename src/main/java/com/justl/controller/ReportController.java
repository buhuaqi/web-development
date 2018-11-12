package com.justl.controller;

import com.justl.domain.auto.User;
import com.justl.domain.response.ResponseData;
import com.justl.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 查询被举报用户信息
     */
    @GetMapping("/info")
    public ResponseData<Map<String, Object>> dynamicReport(String objectId) {
        User user = new User();
        user.setObjectId(objectId);
        Map map = reportService.dynamicReport(user);
        return new ResponseData<>(map);
    }

//    /**
//     * 查询被举报用户节操分数，被举报次数
//     */
//    public ResponseData<> reportDetails(@RequestBody User user) {
//
//        return null;
//    }
}
