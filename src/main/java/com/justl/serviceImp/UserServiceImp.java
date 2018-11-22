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
    public List queryUser(String TbName,Map<String,Object> map) {
        List query = userDao.queryUser(TbName,map);
        return query;
    }

    @Override
    public List queryGoodUser(String TbName,Map<String, Object> map) {
        List query = userDao.queryGoodUser(TbName,map);
        return query;
    }

    @Override
    public List selectCQL(String TbName, Map<String, Object> map) {
        List list=userDao.selectCQL(TbName, map);
        return list;
    }


}
