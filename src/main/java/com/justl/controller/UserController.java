package com.justl.controller;

import com.avos.avoscloud.AVObject;
import com.justl.domain.auto.User;
import com.justl.domain.response.ResponseData;
import com.justl.service.UserService;
import com.justl.utils.ListUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户查询controller
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    //查询用户列表
    @PostMapping("/list")
    @ResponseBody
    public  ResponseData query(@RequestBody  User user){
        Map<String, Object> params = new HashMap<>();
        ListUtils query=null;
        if(user==null){
            query=userService.query(params);
        }
        //pojo转map
        Field fields[] = user.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object obj = field.get(user);
               if(obj!=null){
                   params.put(field.getName(), obj);
               }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        query=userService.query(params);
       return new ResponseData(query);
    }
}
