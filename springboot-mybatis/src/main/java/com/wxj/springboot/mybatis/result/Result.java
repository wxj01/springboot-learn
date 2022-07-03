package com.wxj.springboot.mybatis.result;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Result.java
 * @Description 结果类
 * @createTime 2022年07月02日 13:14:00
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result() {
        super();
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
