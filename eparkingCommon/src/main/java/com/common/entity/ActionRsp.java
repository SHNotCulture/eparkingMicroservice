package com.common.entity;

/**
 * @Author lishuhan
 * @Description:系统统一返回格式
 * @Date Create in 17:522018-5-31
 * @Modified By:
 */
public class ActionRsp<T> {
    private Integer code;
    private T result;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ActionRsp{" +
                "code=" + code +
                ", result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
