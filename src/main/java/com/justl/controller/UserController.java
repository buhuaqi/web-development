package com.justl.controller;

import com.avos.avoscloud.AVObject;
import com.justl.domain.auto.BlackRoom;
import com.justl.domain.auto.User;
import com.justl.domain.response.ResponseData;
import com.justl.service.UserService;
import com.justl.utils.ListUtils;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.http.protocol.ResponseDate;
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

    //小黑屋查询
    @PostMapping("/blackRoom")
    @ResponseBody
    public ResponseData queryBlackRoom(@RequestBody BlackRoom blackRoom){
        //封装查询条件
        Map<String, Object> params =pojoToMap(blackRoom,blackRoom.getClass());
        List<AVObject> list=userService.selectCQL("BlackRoom",params);
        return new ResponseData(list);
    }

    /**
     *普通用户查询
     * @param user
     * @return
     */
    @PostMapping("/comlist")
    @ResponseBody
    public  ResponseData queryUser(@RequestBody User user){
        Map<String, Object> params=pojoToMap(user,user.getClass());
        params.put("Promoters",false);
        params.put("Buyer",false);
        params.put("VIP",false);
        List list= userService.queryUser("_User",params);
        ListUtils listUtils=new ListUtils(list.size(),list);
        return new ResponseData(listUtils);
    }
    @PostMapping("/goodlist")
    @ResponseBody
    public  ResponseData queryGoodUser(@RequestBody User user){
        Map<String, Object> params=pojoToMap(user,user.getClass());
        params.remove("VIP");
        params.remove("Buyer");
        params.remove("Promoters");
        List list= userService.queryGoodUser("_User",params);
        ListUtils listUtils=new ListUtils(list.size(),list);
        return new ResponseData(listUtils);
    }


    private static Map pojoToMap(Object o,Class clazz){
         Map<String, Object> params = new HashMap<>();
        //pojo转map
        Field fields[] = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object obj = field.get(clazz.cast(o));
                if(obj!=null){
                    params.put(field.getName(), obj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

}
