package com.justl.dao;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.justl.domain.auto.User;
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

    /**
     * 返回被举报用户信息
     */
    public AVUser reportedUser(User user) {
        DbClient dbClient = new DbClient();
        Map<String, Object> map = new HashMap<>();
        map.put("objectId", user.getObjectId());

        List<AVObject> users = dbClient.selectCQL("_User", map);
        for (AVObject reportedUser : users) {

            return (AVUser) reportedUser;
        }
        return null;
    }
}
