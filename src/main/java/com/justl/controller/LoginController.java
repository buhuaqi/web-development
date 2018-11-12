package com.justl.controller;

import com.justl.domain.auto.User;
import com.justl.domain.response.ResponseData;
import com.justl.utils.WebApiUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登陆
 * @author buhuaqi
 * @date 2018-10-29 12:39
 */
@RestController
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ResponseBody
    @PostMapping("/login")
    public ResponseData<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> login = new HashMap<>();

        logger.info("登录：用户名 {}", user.getUsername());
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (AuthenticationException ae) {
                logger.error("登录失败: {}" + ae.getMessage());
                return WebApiUtils.getResultInfo(null, ae.getMessage(), 401);
            }
        }
        login.put("username", currentUser.getPrincipal());
        login.put("session", currentUser.getSession());

        return new ResponseData<>(login);
    }
}
