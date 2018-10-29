package com.justl.utils;

import com.avos.avoscloud.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

    public DbClient() {
        dbInit();
    }


    /**
     * 初始化数据库相关参数
     */
    private void dbInit() {
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

    public AVUser login(String username, String password){
        AVUser avUser = null;
        try {
             avUser = AVUser.logIn(username, password);
        } catch (AVException e) {
            e.printStackTrace();
        }
        return avUser;
    }

    public static void main(String[] args) throws AVException, UnsupportedEncodingException, NoSuchAlgorithmException {
        DbClient dbClient = new DbClient();


        AVUser avUser = AVUser.logIn("123456789", "bhq");
    }


}
