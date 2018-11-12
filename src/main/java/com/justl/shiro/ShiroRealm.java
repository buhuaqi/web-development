package com.justl.shiro;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.justl.dao.LoginDao;
import com.justl.domain.auto.User;
import com.justl.utils.DbClient;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author buhuaqi
 * @date 2018-11-12 9:44
 */

public class ShiroRealm extends AuthorizingRealm {
    private DbClient dbClient = new DbClient();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object principal = principalCollection.getPrimaryPrincipal();

        Set<String> roles = new HashSet<>();
        Map map = new HashMap<String,Object>();
        map.put("username",principal);

        List<AVObject> users = dbClient.selectCQL("_User", map);
        for (AVObject user : users) {
            if("admin".equals((String) user.get("Admin"))){
                roles.add("admin");
            }else if("justdgement".equals((String) user.get("Admin"))){
                roles.add("justdgement");
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LoginDao loginDao = new LoginDao();
        User user = new User();
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        user.setUsername(upToken.getUsername());
        user.setPassword(String.valueOf(upToken.getPassword()));
        String pswd = String.valueOf(upToken.getPassword());
        AVUser loginUser = loginDao.queryCQL("_User", user);

        if (loginUser != null) {
            return new SimpleAuthenticationInfo(upToken.getUsername(),upToken.getPassword(),getName());
        }else{
            throw new UnknownAccountException("用户认证失败！");
        }

    }
}
