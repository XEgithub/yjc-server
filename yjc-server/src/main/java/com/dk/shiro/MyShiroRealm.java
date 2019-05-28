package com.dk.shiro;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro-demo
 *
 * @author 张浩伟
 * @version 1.01 2017年12月04日
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

//    @Autowired
//    private AdminService adminService;
//
//    @Autowired
//    private RoleService roleService;

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * 经测试：本例中该方法的调用时机为需授权资源被访问时
     * 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);
        //到数据库查是否有此对象
//        Admin admin = adminService.findByUsername(loginName);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        if(admin !=null){
//            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
//            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//            //用户的角色集合
//            List<Role> roleList = adminService.getRoles(admin);
//            Set<String> set = new HashSet<String>();
//            for (Role role : roleList) {
//                set.add(role.getRolename());
//                info.addStringPermissions(roleService.getPermission(role.getId()));
//            }
//            info.setRoles(set);
//            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
//
////            List<SysRole> roleList=admin.getRoles();
////            for (SysRole role : roleList) {
////                info.addStringPermissions(role.getPermissionsName());
////            }
//            return info;
//        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;

        log.info("验证当前Subject时获取到token为：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));

        //查出是否有此用户
//        Admin admin = adminService.findByUsername(token.getUsername());
//        if(admin !=null){
//            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验ByteSource.Util.bytes(admin.getCredentialsSalt())
//            return new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), ByteSource.Util.bytes(admin.getCredentialsSalt()), getName());
//        }
        return null;
    }
}
