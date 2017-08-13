package com.youu.youu.restapi.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.youu.youu.manager.bean.TUser;
import com.youu.youu.restapi.service.UserService;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token（就是浏览器传的参数），通过currentUser.login(token)过来的
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        String username = uToken.getUsername();
        String password = new String(uToken.getPassword());
        //从数据库中获取用户名和密码
        TUser tUser = userService.getManagerByLoginacc(username);
        if (tUser == null) {
            throw new UnknownAccountException("用户不存在");
        }
        //账户作为盐值
        ByteSource salt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo info = null ;// new SimpleAuthenticationInfo("thruman", "ab494cf7106503071f6b7636b7f31379", getName());
        info = new SimpleAuthenticationInfo(tUser.getLoginacct(), 
        		tUser.getPassword(), salt, getName());
        System.out.println("info"+info);
        return info;
    }
    public static void main(String[] args) {
        String hashAl = "MD5";
        Object crObject = "123456";
        Object saltObject = ByteSource.Util.bytes("youu");
        Object resultObject = new SimpleHash(hashAl, crObject, saltObject, 6);
        System.out.println(resultObject);
    }
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return null;
	}

}
