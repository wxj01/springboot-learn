package com.wxj.springboot.elasticsearch02.service;

import java.util.List;

/**
 * 列表查询
 */
public interface SearchService {


    /**
     * 列表 查询
     *
     * @param searchContent
     * @return
     */
    List<String> search(String searchContent);


    /**
     * 获取列表查询表单配置信息
     *
     * @param formCode 表单编码
     * @return
     */
    String obtainFormInfo(String formCode);

    /**
     * 解析表单信息，获取表单实体，
     * 通过实体获取索引信息
     *
     * @param object 表单信息
     * @return
     */
    List<String> obtainIndex(Object object);


    /**
     * 根据输入查询，获取docId 信息,去重、排序和分页
     *
     * @param searchContent 查询内容
     * @param indexList     索引
     * @param pageSize      页面大小
     * @param pageNum       页码
     * @return
     */
    List<String> obtainDocIdList(String searchContent, List<String> indexList, int pageSize, int pageNum);


    /**
     * 根据docId 查询index，获取数据
     *
     * @param docId
     * @param indexName
     * @param fieldNames 需要获取的字段
     * @return
     */
    List<Object> obtainResultByDocId(String docId, String indexName, List<String> fieldNames);


    /**
     * 根据索引名获取要查的字段
     *
     * @param indexName
     * @return
     */
    List<String> obtainFieldNamesByIndex(String indexName);


    /**
     * 将获取的结果数据 ，进行拼接返回
     *
     * @param responseList 查询的结果
     * @return
     */
    String spliceResult(List<Object> responseList);

}
