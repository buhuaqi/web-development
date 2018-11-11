package com.justl.service;

import com.justl.domain.auto.User;
import com.justl.domain.response.ResponseData;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author buhuaqi
 * @date 2018-10-29 12:52
 */
public interface LoginService {
    /**登录逻辑**/
    Map<String,Object> login(User user);

    /**登出逻辑**/
    ResponseData<String> logOut(HttpSession session);
}
