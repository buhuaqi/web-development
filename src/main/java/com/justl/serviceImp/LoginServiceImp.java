package com.justl.serviceImp;

import com.avos.avoscloud.AVUser;
import com.justl.dao.LoginDao;
import com.justl.domain.auto.User;
import com.justl.domain.response.ResponseData;
import com.justl.exception.SysPrivilegeException;
import com.justl.service.LoginService;
import com.justl.utils.WebApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author buhuaqi
 * @date 2018-10-29 12:54
 */
@Service("loginService")
public class LoginServiceImp implements LoginService {
    private static final String TB_NAME = "_User";

    private static Logger logger = LoggerFactory.getLogger(LoginServiceImp.class);

    @Resource
    LoginDao loginDao;

    @Override
    public Map<String, Object> login(User user) {
        // 参数校验
        User bakUser = user;
        if (null == user) {
            logger.error("登录参数为空");
            throw new SysPrivilegeException("登录参数为空");
        }

        //查询数据库
        AVUser avUser = loginDao.queryCQL(TB_NAME, user);

        //返回给前端的信息
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("user", avUser);
        return tmp;
    }

    /**
     * 退出操作
     */
    public ResponseData<String> logOut(HttpSession session) {
        session.invalidate();
        return WebApiUtils.getResultInfo("用户退出成功");
    }
}
