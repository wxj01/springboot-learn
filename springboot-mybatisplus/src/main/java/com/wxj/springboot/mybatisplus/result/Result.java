//package com.wxj.springboot.mybatisplus.result;
//
//import lombok.Data;
//
///**
// * @author wxj
// * @version 1.0.0
// * @ClassName Result.java
// * @Description 返回结果类
// * @createTime 2022年06月29日 23:35:00
// */
//@Data
//public class Result {
//
//    private Integer code;
//    private String msg;
//    private String message;
//    private Object data;
//
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//    public  Result ok(Object t){
//        this.setCode(1000);
//        this.setMsg("成功");
//        this.setMessage("成功");
//        this.setData(t);
//        return this;
//    }
//
//    public Result fail(String msg){
//        this.setCode(1001);
//        this.setMsg(msg);
//        this.setMessage(msg);
//        return this;
//    }
//
//
//    public Result() {
//    }
//
//    public Result(Integer code, String msg) {
//        this.code = code;
//        this.msg = msg;
//        this.setMessage(msg);
//    }
//
//    public Result(Integer code, String msg, Object data) {
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//        this.setMessage(msg);
//    }
//
//}
//
