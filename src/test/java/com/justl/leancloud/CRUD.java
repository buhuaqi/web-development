package com.justl.leancloud;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.justl.domain.auto.ReportDomain;
import com.justl.utils.DbClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author buhuaqi
 * @date 2018-10-29 11:31
 */
public class CRUD {
    private static Logger logger = LoggerFactory.getLogger(CRUD.class);

    public static void main(String[] args) throws AVException {
        DbClient dbClient = new DbClient();
        //插入数据
//        dbClient.insertCQL("insert into _User(username, password,Admin) values('justdgement', 'justdgement' ,'justdgement')");

//        dbClient.deletCQL("test", "5bd689009f5454006e266c81");
        Map map = new HashMap<>();
//        map.put("words","1");
//        map.put("subjectName","hq");
//        AVObject avObject = dbClient.updateCQL("test", "5bd69a68ac502e0061f59151", map);
//        System.out.println(avObject);
//        AVObject withoutData = AVObject.createWithoutData("_User", "Admin");
//        withoutData.fetch();
//        map.put("url", "http://lc-bh76dzdr.cn-n1.lcfile.com/7s6F5AN080HMPJNhUyPkHzD");
//        List<AVObject> blackRoom = dbClient.selectCQL("_File", map);
//        dbClient.insertCQL("insert into BlackRoom(ReportType,Sex,Name,mobilePhoneNumber, ReportObjectid,ReportNum) values('动态','男','AA','18098357895','5bf61ae244d904005fcb33f6',1)");
//        map.put("ReportNum", 3);
//        dbClient.updateCQL("_User", "5bf619ad67f356005f69077f", map);

//        JsonObject asJsonObject = new JsonParser().parse("{\"owner\":\"5aa684c1ac502e007bfbb1b1\",\"size\":695693,\"_checksum\":\"b358ff6e8b5f2e8d8edca74305ed9392\"}").getAsJsonObject();
        ReportDomain reportDomain = new ReportDomain();
        reportDomain.setiOATH(25768);
        reportDomain.setUserPhoneNumber("18611611625");
        reportDomain.setReObjectId("5b4c73a4ee920a003c1a5a0d");

        map.clear();
        map.put("objectId", reportDomain.getReObjectId());

        AVObject fileAvObject = (AVObject) dbClient.selectCQL("_File", map).get(0);
        String metaData = fileAvObject.get("metaData").toString();
        //解析是否屏蔽图片
        JsonObject metaDataJsonObject = new JsonParser().parse(metaData).getAsJsonObject();
        if (!metaData.contains("shield")) {
            metaDataJsonObject.addProperty("shield", Boolean.TRUE);
        } else {
            metaDataJsonObject.remove("shield");
            metaDataJsonObject.addProperty("shield", Boolean.TRUE);
        }
        metaData = metaDataJsonObject.toString();
        map.clear();
        map.put("metaData", metaData);
        dbClient.updateCQL("_File", reportDomain.getReObjectId(), map);

    }
}
