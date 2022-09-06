package com.wxj.springboot.elasticsearch02.service;

public interface FormService {

    /**
     * 根据表单编码 获取表单配置信息
     *
     * @param FormCode 表单编码
     * @return
     */
    String ObtainFormConfiguration(String FormCode);
}
