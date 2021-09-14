package com.wxj.springboot.jta.dbconfig;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName DataSourceContextHolder.java
 * @Description TODO
 * @createTime 2021年09月14日 21:21:00
 */
public class DataSourceContextHolder {

    private static final  ThreadLocal<String> contextHolder
            = new ThreadLocal<>();

    // 设置数据源名
    public static void setDataSourceKey(String dbName){
        contextHolder.set(dbName);
    }

    //获取数据源名
    public static String getDataSourceKey(){
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDataSourceKey() {
        contextHolder.remove();
    }
}


