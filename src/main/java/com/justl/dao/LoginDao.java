package com.justl.dao;

import com.avos.avoscloud.AVUser;
import com.justl.domain.auto.User;
import com.justl.exception.SysPrivilegeException;
import com.justl.utils.DbClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author buhuaqi
 * @date 2018-10-29 13:01
 */
@Repository
public class LoginDao {

    private static Logger logger = LoggerFactory.getLogger(LoginDao.class);
    private boolean flag;

    public AVUser queryCQL(String tbName, User user) {

        DbClient dbClient = new DbClient();
        AVUser login = dbClient.login(user.getUsername(), user.getPassword());

        if (login!=null){
            return login;
        }else {
            logger.error("用户名或密码错误 user{}");
            throw new SysPrivilegeException("用户名或密码错误");
        }

    }
}
