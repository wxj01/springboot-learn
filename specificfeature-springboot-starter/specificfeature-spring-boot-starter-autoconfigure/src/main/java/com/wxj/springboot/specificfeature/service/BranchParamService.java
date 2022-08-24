package com.wxj.springboot.specificfeature.service;

public interface BranchParamService {

    /**
     * 从referer 中获取 分支的参数
     * @param referer
     * @return
     */
    String getBranchParam(String referer);
}
