package com.justl.service;

import com.avos.avoscloud.AVObject;
import com.justl.utils.ListUtils;

import java.util.List;
import java.util.Map;

public interface UserService {

    List queryUser(String TbName,Map<String,Object> map);

    List queryGoodUser(String TbName,Map<String,Object> map);

    List selectCQL(String TbName, Map<String, Object> map);
}
