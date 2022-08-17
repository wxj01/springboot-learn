package com.wxj.springboot.specificfeature;


/**
 * specific feature 上下文
 */
public class FeatureContext {

    private static final ThreadLocal<String> featureThreadLocal = new ThreadLocal();

    public FeatureContext() {
    }

    public static String get(){
        return featureThreadLocal.get();
    }

    public static void set(String featureName){
        featureThreadLocal.set(featureName);
    }

    public static void destroy(){
        featureThreadLocal.remove();
    }

}
