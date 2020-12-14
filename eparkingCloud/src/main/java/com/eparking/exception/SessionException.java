package com.eparking.exception;

import com.eparking.enums.ActionRspEnum;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 17:542018-11-21
 * @Modified By:
 */
public class SessionException extends RuntimeException {
    private  Integer code;

    public SessionException(ActionRspEnum actionRspEnum) {
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
