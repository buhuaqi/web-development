package com.justl.leancloud;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.justl.utils.DbClient;

/**
 * @author buhuaqi
 * @date 2018-10-29 11:31
 */
public class CRUD {
    public static void main(String[] args) throws AVException {
        DbClient dbClient = new DbClient();
//        dbClient.insertCQL("insert into _User(username, password,Admin) values('123', '123' ,'justdgement')");

//        dbClient.deletCQL("test", "5bd689009f5454006e266c81");
//        Map map = new HashMap<>();
//        map.put("words","1");
//        map.put("subjectName","hq");
//        AVObject avObject = dbClient.updateCQL("test", "5bd69a68ac502e0061f59151", map);
//        System.out.println(avObject);
        AVObject withoutData = AVObject.createWithoutData("_User", "Admin");
        withoutData.fetch();


    }
}
