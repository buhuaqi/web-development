package com.justl.serviceImp;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.justl.dao.UserDao;
import com.justl.service.UserService;
import com.justl.utils.ListUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImp implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public ListUtils query(Map<String,Object> map) {
        ListUtils query = userDao.query("_User",map);
        return query;
    }


}
