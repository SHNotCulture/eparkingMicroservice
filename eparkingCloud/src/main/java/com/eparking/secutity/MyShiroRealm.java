package com.eparking.secutity;

import com.common.entity.eparkingCloud.TCompanyPark;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.service.CarParkService;
import com.eparking.service.UserService;
import com.common.util.Common;
import com.common.util.JsonUtil;
import com.common.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 16:212018-8-29
 * @Modified By:
 */
public class MyShiroRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    //如果项目中用到了事物，@Autowired注解会使事物失效，可以自己用get方法获取值
    @Autowired
    private UserService userService;
    @Autowired
    private CarParkService carParkService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        //获取当前登录的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = new String( (char[])token.getCredentials());
        TCompanyUser user=new TCompanyUser();
        TCompanyUser userList=new TCompanyUser();
        //云端最顶级账户
        if(name.equals("admin") &&password.equals("eparking2020")){

            userList.setId(0);
            userList.setUserName("系统管理员");
            userList.setUserAccout(name);
            userList.setPassword(password);
            userList.setIsAdmin("0");
            userList.setCompanyId(0);
            String ParkIds="";
            TCompanyPark companyPark=new TCompanyPark();
            List<TCompanyPark> tCompanyParkList=carParkService.getCarPark(companyPark);
            if(tCompanyParkList!=null)
            {
                if(tCompanyParkList.size()>0){
                    for (TCompanyPark park:tCompanyParkList) {
                        ParkIds+=park.getId()+",";
                    }
                    ParkIds=ParkIds.substring(0,ParkIds.length()-1);
                }else{
                    ParkIds="0";
                }
            }
            userList.setParkIds(ParkIds);
        }
        else
        {
            user.setUserAccout(name);
            user.setPassword(MD5Util.MD5Encode(password));
            // 从数据库获取对应用户名密码的用户
            userList = userService.CheckPassword(user);
        }
        if (userList.getId() != null) {
           /* // 用户为禁用状态
            if (userList.getUserEnable() != 1) {
                throw new DisabledAccountException();
            }*/
            logger.info("---------------- Shiro 凭证认证成功 ----------------------");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    name,//用户
                    password,//密码
                    getName()//realm name
            );



            request.getSession().setAttribute(Common.User, JsonUtil.beanToJson(userList));//将当前登录用户信息存入session
            return authenticationInfo;
        }

        throw new UnknownAccountException();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        /*if (principal instanceof TCompanyUser) {
            TCompanyUser userLogin = (TCompanyUser) principal;
            Set<String> roles = roleService.findRoleNameByUserId(userLogin.getId());
            authorizationInfo.addRoles(roles);

            Set<String> permissions = userService.findPermissionsByUserId(userLogin.getId());
            authorizationInfo.addStringPermissions(permissions);
        }*/
        logger.info("---- 获取到以下权限 ----");
        logger.info(authorizationInfo.getStringPermissions().toString());
        logger.info("---------------- Shiro 权限获取成功 ----------------------");
        return authorizationInfo;
    }

}
