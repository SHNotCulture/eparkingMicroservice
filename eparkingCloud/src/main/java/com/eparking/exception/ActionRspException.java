package com.eparking.exception;

import com.eparking.enums.ActionRspEnum;

/**
 * @Author lishuhan
 * @Description:系统统一返回异常类
 * @Date Create in 17:242018-5-9
 * @Modified By:
 */
public class ActionRspException extends RuntimeException {
    private  Integer code;

    public ActionRspException(ActionRspEnum actionRspEnum) {
        super(actionRspEnum.getMessage());
        this.code = actionRspEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
