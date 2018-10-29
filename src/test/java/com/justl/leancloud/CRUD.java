package com.justl.leancloud;

import com.justl.utils.DbClient;

/**
 * @author buhuaqi
 * @date 2018-10-29 11:31
 */
public class CRUD {
    public static void main(String[] args) {
        DbClient dbClient = new DbClient();
        dbClient.insertCQL("insert into _User(username, password) values('123456789', 'bhq')");

//        dbClient.deletCQL("test","5bd689009f5454006e266c81");
//        Map map = new HashMap<>();
//        map.put("words","1");
//        map.put("subjectName","hq");
//        AVObject avObject = dbClient.updateCQL("test", "5bd69a68ac502e0061f59151", map);
//        System.out.println(avObject);
    }
}
