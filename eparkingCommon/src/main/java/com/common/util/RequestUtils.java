package com.common.util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtils {

    private static Logger logger = LoggerFactory.getLogger(RequestUtils.class);
    /**
     * 获取当前登录的用户，若用户未登录，则返回未登录 json
     *
     * @return
     */
    public static  String currentLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            logger.info("用户已登录");
            Object principal = subject.getPrincipals().getPrimaryPrincipal();
           /* if (principal instanceof TCompanyUser) {
                return (TCompanyUser) principal;
            }*/
           return subject.getPrincipals().toString();
        }
        else
        {
            logger.info("用户未登录");
        }
        return null;
    }

    /**
     * 退出登录
     * @return
     */
    public static String logOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        logger.info("用户已退出");
        return "安全退出";
    }
}
