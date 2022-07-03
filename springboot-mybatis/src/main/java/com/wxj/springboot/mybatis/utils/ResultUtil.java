package com.wxj.springboot.mybatis.utils;

import com.wxj.springboot.mybatis.myenum.ResultEnum;
import com.wxj.springboot.mybatis.result.Result;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName ResultUtil.java
 * @Description TODO
 * @createTime 2022年07月02日 13:59:00
 */
public class ResultUtil {
    /**成功且带数据**/
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    /**成功但不带数据**/
    public static Result success(){

        return success(null);
    }
    /**失败**/
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
