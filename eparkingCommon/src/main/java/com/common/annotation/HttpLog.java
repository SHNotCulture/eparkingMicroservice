package com.common.annotation;

import java.lang.annotation.*;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 21:582018-10-24
 * @Modified By:
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpLog {
    /** 要执行的操作类型比如：add **/
    public String operationType() default "";
    /** 要执行的模块名称如：Carouse **/
    public String modularTypeName() default "";
}
