//package com.wxj.springboot.mybatisplus.result;
//
//import com.wxj.springboot.mybatisplus.ResultCode;
//import lombok.Data;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author wxj
// * @version 1.0.0
// * @ClassName R.java
// * @Description TODO
// * @createTime 2022年07月01日 21:01:00
// */
////统一结果返回类
//@Data
//public class R {
//
//    private Boolean success;  //成功状态
//
//    public Integer code;    //状态码
//
//    public String message;   //返回信息
//
//    private Map<String,Object> data=new HashMap<String,Object>();  //返回数据
//
//    private R(){} //私有化，只能用给出的静态方法
//
//    //成功静态方法
//    public static R ok(){
//        R r=new R();
//        r.setSuccess(true);
//        r.setCode(ResultCode.SUCCESS);
//        r.setMessage("成功");
//        return r;
//    }
//
//    //失败静态方法
//    public static R error(){
//        R r=new R();
//        r.setSuccess(false);
//        r.setCode(ResultCode.ERROR);
//        r.setMessage("失败");
//        return r;
//    }
//
//    //this代表这个对象
//
//    //链式编程 R.ok().code().message()
//
//    public R success(Boolean success){
//        this.setSuccess(success);
//        return this;
//    }
//
//    public R message(String message){
//        this.setMessage(message);
//        return this;
//    }
//
//    public R code(Integer code){
//        this.setCode(code);
//        return this;
//    }
//
//    public R data(String key,Object value){
//        this.data.put(key,value);
//        return this;
//    }
//
//    public R data(Map<String,Object> map){
//        this.setData(map);
//        return this;
//    }
//
//}