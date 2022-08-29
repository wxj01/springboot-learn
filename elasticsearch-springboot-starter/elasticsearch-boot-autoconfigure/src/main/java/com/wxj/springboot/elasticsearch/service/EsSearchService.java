package com.wxj.springboot.elasticsearch.service;

public interface EsSearchService {

    /**
     * @param searchContent 查询内容
     * @param indexName     查询索引
     * @return 返回字符串结果
     */
    public String search(String searchContent, String... indexName);


}
