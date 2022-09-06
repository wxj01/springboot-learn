package com.wxj.springboot.elasticsearch02.service.impl;

import com.wxj.springboot.elasticsearch02.service.FormService;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl implements FormService {
    /**
     * 根据表单编码 获取表单配置信息
     *
     * @param FormCode 表单编码
     * @return
     */
    @Override
    public String ObtainFormConfiguration(String FormCode) {
        return null;
    }
}
