package com.justl.dao;

import com.avos.avoscloud.AVObject;
import com.google.gson.JsonParser;
import com.justl.domain.auto.ReportDomain;
import com.justl.utils.DbClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author buhuaqi
 * @date 2018-11-12 15:06
 */
@Repository
public class ReportDao {
    private static Logger logger = LoggerFactory.getLogger(ReportDao.class);

    private static DbClient getDbClient() {
        return new DbClient();
    }

    /**
     * 返回被举报用户信息(含聊天截图信息)
     */
    public Map<String, Object> reportedUserChat(ReportDomain reportDomain) {
        DbClient dbClient = getDbClient();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> response = new HashMap<>();
        map.put("mobilePhoneNumber", reportDomain.getUserPhoneNumber());
        //获取举报用户的_User对象
        List<AVObject> users = dbClient.selectCQL("_User", map);

        map.clear();
        map.put("objectId", reportDomain.getReObjectId());
        //获取举报对象
        List<AVObject> files = dbClient.selectCQL("_File", map);

        map.clear();
        map.put("ReportObjectid", reportDomain.getReObjectId());

        List<AVObject> blackRooms = dbClient.selectCQL("BlackRoom", map);
        if (blackRooms.isEmpty()) {
            response.put("chatReportNum", 0);
        } else {
            response.put("chatReportNum", blackRooms.get(0).get("ReportNum"));
        }

        for (AVObject user : users) {
            for (AVObject file : files) {
                String metaData = file.get("metaData").toString();
                String owner = new JsonParser().parse(metaData).getAsJsonObject().get("owner").getAsString();
                if (owner.equals((String) user.getObjectId())) {
                    map.clear();
                    map.put("userId", user.getObjectId());
                    List<AVObject> userProhibit1 = dbClient.selectCQL("UserProhibit", map);
                    AVObject userProhibit = userProhibit1.get(0);

                    response.put("username", user.get("username"));
                    response.put("mobilePhoneNumber", user.get("mobilePhoneNumber"));
                    response.put("cScore", userProhibit.get("Cscore"));
                    response.put("url", files.get(0).get("url"));
                    response.put("totalReportNum", userProhibit.get("ReportNum"));
                    return response;
                }
            }
        }
        return null;
    }

    /**
     * 聊天举报逻辑
     */
    public void chatReport(ReportDomain reportDomain) {
        DbClient dbClient = getDbClient();
        Map<String, Object> map = new HashMap<>();
        map.put("ReportObjectid", reportDomain.getReObjectId());
        //获取举报对象(BlackRoom表)
        List<AVObject> reportUsers = dbClient.selectCQL("BlackRoom", map);
        map.clear();
        //查询_User表被举报对象
        map.put("iOATH", reportDomain.getiOATH());
        List<AVObject> users = dbClient.selectCQL("_User", map);
        //查询UserProhibit对象
        map.clear();
        map.put("userId", users.get(0).getObjectId());
        AVObject userProhibit = dbClient.selectCQL("UserProhibit", map).get(0);

        map.clear();
        //为空插入动态信息，_User表ReportNum加1
        if (reportUsers.isEmpty()) {
            //查询举报用户信息
            map.put("iOATH", reportDomain.getiOATH());
            AVObject user = dbClient.selectCQL("_User", map).get(0);
            if (user == null) {
                logger.warn("举报用户不存在");
            }
            dbClient.insertCQL("insert into BlackRoom(ReportType,Sex,Name,mobilePhoneNumber,ReportObjectid,ReportNum) values(" +
                    "'文件'," + "'" + user.get("Sex") + "'" + "," + "'" + user.get("Name") + "'" + "," + "'" + reportDomain.getUserPhoneNumber() + "'" + "," + "'" + reportDomain.getReObjectId() + "'" + ",1" + ")");

            map.clear();
            map.put("ReportNum", (int) userProhibit.get("ReportNum") + 1);
            dbClient.updateCQL("UserProhibit", userProhibit.getObjectId(), map);
        } else {
            //获取被举报用户信息(_User表)
            map.put("iOATH", reportDomain.getiOATH());
            AVObject user = dbClient.selectCQL("_User", map).get(0);
            //获取动态被举报次数(BlackRoom表)
            int dsReportNum = (int) reportUsers.get(0).get("ReportNum");
            if (dsReportNum < 9) {
                logger.info("被举报用户图片举报次数小于9");
                //UserProhibit表ReportNum加1
                map.clear();
                map.put("ReportNum", (int) userProhibit.get("ReportNum") + 1);
                dbClient.updateCQL("UserProhibit", userProhibit.getObjectId(), map);
                //BlackRoom表加1
                map.clear();
                map.put("ReportNum", (int) reportUsers.get(0).get("ReportNum") + 1);
                dbClient.updateCQL("BlackRoom", reportUsers.get(0).getObjectId(), map);
            } else if (dsReportNum == 9) {
                //动态举报数等于9
                logger.info("被举报用户动态举报次数等于9");
                map.clear();
                map.put("Shield", Boolean.TRUE);
                dbClient.updateCQL("BlackRoom", reportUsers.get(0).getObjectId(), map);
                //BlackRoom表加1
                map.clear();
                map.put("ReportNum", (int) reportUsers.get(0).get("ReportNum") + 1);
                dbClient.updateCQL("BlackRoom", reportUsers.get(0).getObjectId(), map);
                //UserProhibit表ReportNum加1
                map.clear();
                map.put("ReportNum", (int) userProhibit.get("ReportNum") + 1);
                dbClient.updateCQL("UserProhibit", userProhibit.getObjectId(), map);
                //用户节操分减10
                map.clear();
                map.put("Cscore", (int) userProhibit.get("Cscore") - 10);
                dbClient.updateCQL("UserProhibit", userProhibit.getObjectId(), map);

                //获取修改后的user对象
                map.clear();
                map.put("iOATH", reportDomain.getiOATH());
                AVObject afterUser = dbClient.selectCQL("_User", map).get(0);
                //获取修改后的UserProhibit对象
                map.clear();
                map.put("userId", afterUser.getObjectId());
                AVObject afterUserProhibit = dbClient.selectCQL("UserProhibit", map).get(0);

                int cScore = (int) afterUserProhibit.get("Cscore");
                if (cScore > 0 && cScore < 60) {
                    logger.info("被举报用户节操分数：{}", cScore);
                    //暂时封禁
                    map.clear();
                    map.put("Prohibition", "1");
                    dbClient.updateCQL("UserProhibit", afterUserProhibit.getObjectId(), map);
                    logger.info("被举报用户暂时封禁");
                } else if (cScore <= 0) {
                    //永久封禁
                    map.clear();
                    map.put("Prohibition", "2");
                    dbClient.updateCQL("UserProhibit", user.getObjectId(), map);
                    logger.info("被举报用户永久封禁");
                }
            }
        }
    }

    /**
     * 返回被举报用户信息(含动态信息)
     */
    public Map<String, Object> reportedUser(ReportDomain reportDomain) {
        DbClient dbClient = getDbClient();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> response = new HashMap<>();
        map.put("mobilePhoneNumber", reportDomain.getUserPhoneNumber());
        //获取举报用户的_User对象
        List<AVObject> users = dbClient.selectCQL("_User", map);

        map.clear();
        map.put("objectId", reportDomain.getReObjectId());
        //获取举报动态对象
        List<AVObject> dynamicStates = dbClient.selectCQL("DynamicState", map);

        map.clear();
        map.put("ReportObjectid", reportDomain.getReObjectId());

        List<AVObject> blackRooms = dbClient.selectCQL("BlackRoom", map);
        if (blackRooms.isEmpty()) {
            response.put("dSReportNum", 0);
        } else {
            response.put("dSReportNum", blackRooms.get(0).get("ReportNum"));
        }

        for (AVObject user : users) {
            for (AVObject dynamicState : dynamicStates) {
                if (((String) dynamicState.get("source")).equals((String) user.getObjectId())) {
                    map.clear();
                    map.put("userId", user.getObjectId());
                    List<AVObject> userProhibit1 = dbClient.selectCQL("UserProhibit", map);
                    AVObject userProhibit = userProhibit1.get(0);

                    response.put("username", user.get("username"));
                    response.put("mobilePhoneNumber", user.get("mobilePhoneNumber"));
                    response.put("cScore", userProhibit.get("Cscore"));
                    response.put("text", dynamicStates.get(0).get("text"));
                    response.put("totalReportNum", userProhibit.get("ReportNum"));
                    return response;
                }
            }
        }
        return null;
    }

    /**
     * 动态举报逻辑
     */
    public void report(ReportDomain reportDomain) {
        DbClient dbClient = getDbClient();
        Map<String, Object> map = new HashMap<>();
        map.put("ReportObjectid", reportDomain.getReObjectId());
        //获取举报对象(BlackRoom表)
        List<AVObject> reportUsers = dbClient.selectCQL("BlackRoom", map);
        map.clear();
        //查询_User表被举报对象
        map.put("iOATH", reportDomain.getiOATH());
        List<AVObject> users = dbClient.selectCQL("_User", map);
        //查询UserProhibit对象
        map.clear();
        map.put("userId", users.get(0).getObjectId());
        AVObject userProhibit = dbClient.selectCQL("UserProhibit", map).get(0);

        map.clear();
        //为空插入动态信息，_User表ReportNum加1
        if (reportUsers.isEmpty()) {
            //查询举报用户信息
            map.put("iOATH", reportDomain.getiOATH());
            AVObject user = dbClient.selectCQL("_User", map).get(0);
            if (user == null) {
                logger.warn("举报用户不存在");
            }
            dbClient.insertCQL("insert into BlackRoom(ReportType,Sex,Name,mobilePhoneNumber,ReportObjectid,ReportNum) values(" +
                    "'动态'," + "'" + user.get("Sex") + "'" + "," + "'" + user.get("Name") + "'" + "," + "'" + reportDomain.getUserPhoneNumber() + "'" + "," + "'" + reportDomain.getReObjectId() + "'" + ",1" + ")");

            map.clear();
            map.put("ReportNum", (int) userProhibit.get("ReportNum") + 1);
            dbClient.updateCQL("UserProhibit", userProhibit.getObjectId(), map);
        } else {
            //获取被举报用户信息(_User表)
            map.put("iOATH", reportDomain.getiOATH());
            AVObject user = dbClient.selectCQL("_User", map).get(0);
            //获取动态被举报次数(BlackRoom表)
            int dsReportNum = (int) reportUsers.get(0).get("ReportNum");
            if (dsReportNum < 9) {
                logger.info("被举报用户动态举报次数小于9");
                //UserProhibit表ReportNum加1
                map.clear();
                map.put("ReportNum", (int) userProhibit.get("ReportNum") + 1);
                dbClient.updateCQL("UserProhibit", userProhibit.getObjectId(), map);
                //BlackRoom表加1
                map.clear();
                map.put("ReportNum", (int) reportUsers.get(0).get("ReportNum") + 1);
                dbClient.updateCQL("BlackRoom", reportUsers.get(0).getObjectId(), map);
            } else if (dsReportNum == 9) {//动态举报数等于9
                logger.info("被举报用户动态举报次数等于9");
                map.clear();
                map.put("Shield", Boolean.TRUE);
                dbClient.updateCQL("BlackRoom", reportUsers.get(0).getObjectId(), map);
                //BlackRoom表加1
                map.clear();
                map.put("ReportNum", (int) reportUsers.get(0).get("ReportNum") + 1);
                dbClient.updateCQL("BlackRoom", reportUsers.get(0).getObjectId(), map);
                //UserProhibit表ReportNum加1
                map.clear();
                map.put("ReportNum", (int) userProhibit.get("ReportNum") + 1);
                dbClient.updateCQL("UserProhibit", userProhibit.getObjectId(), map);
                //用户节操分减10
                map.clear();
                map.put("Cscore", (int) userProhibit.get("Cscore") - 10);
                dbClient.updateCQL("UserProhibit", userProhibit.getObjectId(), map);

                //获取修改后的user对象
                map.clear();
                map.put("iOATH", reportDomain.getiOATH());
                AVObject afterUser = dbClient.selectCQL("_User", map).get(0);
                //获取修改后的UserProhibit对象
                map.clear();
                map.put("userId", afterUser.getObjectId());
                AVObject afterUserProhibit = dbClient.selectCQL("UserProhibit", map).get(0);

                int cScore = (int) afterUserProhibit.get("Cscore");
                if (cScore > 0 && cScore < 60) {
                    logger.info("被举报用户节操分数：{}", cScore);
                    //暂时封禁
                    map.clear();
                    map.put("Prohibition", "1");
                    dbClient.updateCQL("UserProhibit", afterUserProhibit.getObjectId(), map);
                } else if (cScore <= 0) {
                    //永久封禁
                    map.clear();
                    map.put("Prohibition", "2");
                    dbClient.updateCQL("UserProhibit", user.getObjectId(), map);
                }
            }
        }
    }
}
