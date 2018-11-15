package com.justl.dao;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.justl.utils.DbClient;
import com.justl.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ly 2018-11-12
 */
@Repository
public class UserDao {

    private static Logger logger = LoggerFactory.getLogger(LoginDao.class);

    /**
     * 用户列表查询
     * @return List<AVUser>
     */
    public ListUtils query(String TbName,Map<String,Object> map){
        DbClient dbClient = new DbClient();
        ListUtils user = dbClient.select(TbName,map);
        return user;
    }
}
