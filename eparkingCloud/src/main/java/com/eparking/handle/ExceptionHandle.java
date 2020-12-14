package com.eparking.handle;
import com.eparking.exception.ActionRspException;
import com.eparking.exception.SessionException;
import com.common.util.ActionRspUtil;
import com.common.util.JsonUtil;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @Author lishuhan
 * @Description:系统统一异常捕捉类
 * @Date Create in 17:272018-5-9
 * @Modified By:
 */
@ControllerAdvice
public class ExceptionHandle {
    private  static final Logger logger= LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handle(Exception e) throws IOException {
        String result="";
        if(e instanceof ActionRspException){
            ActionRspException actionRspException=(ActionRspException)e;
            logger.warn("【业务ERROR】"+actionRspException.getMessage());
            result=JsonUtil.beanToJson(ActionRspUtil.Error(actionRspException.getCode(),actionRspException.getMessage()));
        }else if (e instanceof SessionException){
            SessionException sessionException=(SessionException)e;
            logger.warn("【SESSION_ERROR】"+sessionException.getMessage());
            result=alterMsg();
        }
        else if(e instanceof MyBatisSystemException)
        {
            logger.warn("【MyBatisERROR】"+e.toString());
            result=JsonUtil.beanToJson(ActionRspUtil.Error(-1,"UNKONW_ERROR"));
        }
        else
        {
            logger.warn("【系统ERROR】"+e.toString());
            e.printStackTrace();
            result=JsonUtil.beanToJson(ActionRspUtil.Error(-1,"UNKONW_ERROR"));
        }
            return result;
    }
    private String alterMsg(){
        StringBuilder st=new StringBuilder();
        st.append(" <script src=\"../js/layui/layui.js\"></script>");
        st.append("<script type=\"text/javascript\">");
        st.append("  layui.use( \"layer\", function(){");
        st.append("var layer = layui.layer;");
        st.append("top.window.layer.open({");
        st.append(" content: \"session已失效！请重新登录\"");
        st.append(",yes: function(index, layero){");
        st.append("top.window.location.href=\"../view/getLogin\";");
        st.append("}");
        st.append(",cancel: function(index, layero){ ");
        st.append("top.window.location.href=\"../view/getLogin\";");
        st.append("}");
        st.append("});");
        st.append("});");
        st.append("</script>");
        return st.toString();
    }
   /* @ExceptionHandler(value = Exception.class)
    public String handle(Exception e) throws IOException {
        if(e instanceof SessionException){
            SessionException sessionException=(SessionException)e;
            logger.warn("【SESSION_ERROR】"+sessionException.getMessage());
            if(sessionException.getCode().equals(12))//Session 失效
            {
               *//* logger.warn("【SESSION_ERROR】"+sessionException.getCode());
                ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletResponse response=attributes.getResponse();
                response.sendRedirect("redirect:/view/getLogin");*//*
            }
        }
        return "redirect:/view/getLogin";
    }*/
}
