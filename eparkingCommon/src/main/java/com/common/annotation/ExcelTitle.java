package com.common.annotation;

import java.lang.annotation.*;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 14:512018-11-13
 * @Modified By:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelTitle {
    String value() default "";
    int order();
}
