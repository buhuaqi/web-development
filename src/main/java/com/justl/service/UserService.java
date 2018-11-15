package com.justl.service;

import com.avos.avoscloud.AVObject;
import com.justl.utils.ListUtils;

import java.util.List;
import java.util.Map;

public interface UserService {

    ListUtils query(Map<String,Object> map);
}
