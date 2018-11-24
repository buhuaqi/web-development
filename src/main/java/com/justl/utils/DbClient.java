package com.justl.utils;

import com.avos.avoscloud.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库链接客户端
 *
 * @author buhuaqi
 * @date 2018-10-29 10:38
 */
public class DbClient {

    private static final Logger logger = LoggerFactory.getLogger(DbClient.class);



    /**
     * 11-15 ly修改 初始化参数移动到静态块中
     * 初始化数据库相关参数
     */
    static {
        Properties props = ConfigUtils.getProps("conf.properties");
        AVOSCloud.initialize(props.getProperty("AppId"), props.getProperty("AppKey"), props.getProperty("MasterKey"));
        AVOSCloud.setDebugLogEnabled(true);
    }

    /**
     * 插入单个对象
     */
    public boolean insertCQL(String insertCQL) {
        try {
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(insertCQL);
        } catch (Exception e) {
            logger.error("插入数据出现异常，Exception： {}", LogbackUtil.expection2Str(e));
        }
        return true;
    }

    /**
     * 删除单个对象
     */
    public boolean deletCQL(String tbName, String objectId) {
        try {
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery("delete from " + tbName + " where objectId=?", objectId);
        } catch (Exception e) {
            logger.error("删除数据出现异常，Exception： {}", LogbackUtil.expection2Str(e));
        }
        return true;
    }

    /**
     * 修改单个对象
     */
    public AVObject updateCQL(String tbName, String objectId, Map<String, Object> map) {
        AVObject avObject = null;
        try {
            avObject = AVObject.createWithoutData(tbName, objectId);

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                avObject.put(entry.getKey(), entry.getValue());
            }

            avObject.save();
        } catch (AVException e) {
            logger.error("修改数据出现异常，Exception： {}", LogbackUtil.expection2Str(e));
        }
        return avObject;
    }

    /**
     * 优质客户查询
     */
    public List<? extends AVObject> queryGoodUser(String TbName, Map<String, Object> map){
        List<? extends AVObject> list  = null;
        StringBuilder cql=new StringBuilder("select * from "+TbName+" where ( VIP = true or Promoters = true or Buyer =true ) ");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
           cql.append(" and " + entry.getKey() +" = " +"'"+ entry.getValue()+"'");
        }
        try {
            System.out.println(cql);
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString());
            list  =avCloudQueryResult.getResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
         return list;
    }
    /**
     * 查询语句
     *
     * @return
     */
    public List<AVObject> selectCQL(String TbName, Map<String, Object> map) {
        List<AVObject> avObjects = null;
        AVQuery<AVObject> avQuery = new AVQuery<>(TbName);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            avQuery.whereEqualTo(entry.getKey(), entry.getValue());
        }
        try {
            avObjects = avQuery.find();
        } catch (AVException e) {
            e.printStackTrace();
        }

        return avObjects;
    }

    /**
     * 登陆
     */
    public AVUser login(String username, String password){
        AVUser avUser = null;
        try {
            avUser = AVUser.logIn(username, password);
        } catch (AVException e) {
            e.printStackTrace();
        }
        return avUser;
    }

}
