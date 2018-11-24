package com.justl.leancloud;

import com.justl.domain.auto.ReportDomain;

/**
 * 测试被举报信息查询
 *
 * @author buhuaqi
 * @date 2018-11-13 16:15
 */
public class DSReport {
    public static void main(String[] args) {
        ReportDomain reportDomain = new ReportDomain();
//        reportDomain.setdSobjectId("5be8f9264773f700666d35eb");
//        reportDomain.setUserPhoneNumber("15942657084");
//
//        DbClient dbClient = new DbClient();
//        Map<String, Object> map = new HashMap<>();
//        map.put("mobilePhoneNumber", reportDomain.getUserPhoneNumber());
//        List<AVObject> users = dbClient.selectCQL("_User", map);
//
//        map.clear();
//        map.put("source", reportDomain.getdSobjectId());
//        List<AVObject> dynamicStates = dbClient.selectCQL("DynamicState", map);
//
//        System.out.println("用户节操分：" + users.get(0).get("CScore") + "    用户被举报总数：" + users.get(0).get("ReportNum") + "   动态被举报次数" + dynamicStates.get(0).get("ReportNum"));
    }
}
