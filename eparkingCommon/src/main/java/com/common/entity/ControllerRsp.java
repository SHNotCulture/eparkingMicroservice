package com.common.entity;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 16:342018-7-13
 * @Modified By:
 */
public class ControllerRsp<T> {
    private Integer code;
    private Integer count;
    private T data;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
