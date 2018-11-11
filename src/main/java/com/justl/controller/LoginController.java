package com.justl.controller;

import com.justl.domain.auto.User;
import com.justl.domain.response.ResponseData;
import com.justl.service.LoginService;
import com.justl.utils.Constants;
import com.justl.utils.WebApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author buhuaqi
 * @date 2018-10-29 12:39
 */
@RestController
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    LoginService loginService;

    @PostMapping("/login")
    public ResponseData<Map<String, Object>> login(@RequestParam String username,@RequestParam String password, HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        Map<String, Object> login = null;
        try {
            logger.info("登录：用户名 {}", username);

            login = loginService.login(user);
            session.setAttribute(Constants.LOGIN_USER, user);
            String id = "JZ_JSESSION="+session.getId();
            login.put("cookie",id);
            logger.info("session:  {}",id);
        } catch (Exception e) {
            logger.error("login error: {}", e.getMessage());
            return WebApiUtils.getResultInfo(null, e.getMessage(), 401);
        }
        return new ResponseData<>(login);
    }

    /**
     * 注销
     */
    @GetMapping("/logout")
    public ResponseData<String> logout(HttpSession session) {
        return loginService.logOut(session);
    }
}
