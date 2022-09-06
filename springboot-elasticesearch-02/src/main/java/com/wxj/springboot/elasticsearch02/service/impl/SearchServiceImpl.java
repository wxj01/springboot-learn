package com.wxj.springboot.elasticsearch02.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.alibaba.fastjson.JSONObject;
import com.wxj.springboot.elasticsearch02.service.FormService;
import com.wxj.springboot.elasticsearch02.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {


    public static final String DOC_ID = "docId";
    public static final String FULL_NAME = "full_name";
    public static final String FORM_CODE = "form_code";

    @Resource
    private SearchService searchService;

    @Resource
    private FormService formService;

    @Resource
    private ElasticsearchClient elasticsearchClient;

    /**
     * 根据索引名获取要查的字段
     *
     * @param indexName
     * @return
     */
    @Override
    public List<String> obtainFieldNamesByIndex(String indexName) {
        return null;
    }

    /**
     * @param colName    主键对应的字段名称
     * @param colValue   主键对应值
     * @param indexName  索引名称
     * @param fieldNames 索引需要查询的字段
     * @return
     */
    @Override
    public Object obtainResultById(String colName, String colValue, String indexName, List<String> fieldNames) {
        try {
            SearchResponse<Object> search = elasticsearchClient.search(s -> s.index(indexName)
                            .query(q -> q.term(t -> t.field(colName).value(v -> v.stringValue(colValue))))
                            .source(o -> o.filter(f -> f.includes(fieldNames)))
                    , Object.class);
            // 根据主键的去查询，最多只有一条数据
            for (Hit<Object> hit : search.hits().hits()) {
                Object source = hit.source();
                return source;
            }
        } catch (IOException e) {
            log.info("==== 异常：{}", e);
            e.printStackTrace();
        }

        // docId 中没有 对应的主键，但是 索引对应的字段还要展示，值都是空，这时不能直接返回空，
        // 需要返回一个字段值为空的 字符串
        return null;
    }

    /**
     * 列表 查询
     *
     * @param searchContent
     * @return
     */
    @Override
    public List<String> search(String searchContent) {
        //获取表单
        String formInfo = searchService.obtainFormInfo(FORM_CODE);
        //通过表单获取索引
        List<String> indexNameList = searchService.obtainIndex(formInfo);
        // 根据查询内容获取 docId ,去重、排序、分页
        List<String> docIdList = searchService.obtainDocIdList("三一", indexNameList, 2, 10);

        List<Object> resultList = new ArrayList<>();


        //根据docId 去查index
        docIdList.forEach(docId -> {
            //
            // 1、拿到一个docId 如何确定 哪些索引
            // 2、获取单个索引 需要查询的 字段
            // 3、索引和对应的字段 获取数据
            List<String> fieldNames = searchService.obtainFieldNamesByIndex("indexName");
            //获取index 的 字段值
            // 索引 和  需要查询的字段 要对应上
            List<Object> indexName01 = searchService.obtainResultByDocId(docId, "indexName01", fieldNames);
            List<Object> indexName02 = searchService.obtainResultByDocId(docId, "indexName02", fieldNames);
            List<Object> indexName03 = searchService.obtainResultByDocId(docId, "indexName03", fieldNames);

            Map<String, Object> map = new LinkedHashMap<>();
            map.put("indexName01", indexName01);
            map.put("indexName02", indexName02);
            map.put("indexName03", indexName03);

            // 拼接各领域的字段
            resultList.add(searchService.spliceResult(map));
        });

        return null;
    }

    /**
     * 获取列表查询表单配置信息
     */
    @Override
    public String obtainFormInfo(String formCode) {
        return formService.ObtainFormConfiguration(formCode);
    }

    /**
     * 解析表单信息，获取表单实体，
     * 通过实体获取索引信息
     *
     * @param object 表单信息
     * @return
     */
    @Override
    public List<String> obtainIndex(Object object) {
        return null;
    }

    /**
     * 根据输入查询，获取docId 信息,去重、排序和分页
     *
     * @param searchContent 根据 查询内容
     * @return
     */
    @Override
    public List<String> obtainDocIdList(String searchContent, List<String> indexList, int pageSize, int pageNum) {

        List<String> docIdList = new ArrayList<>();

        SearchResponse<Object> search = null;
        try {
            search = elasticsearchClient.search(s -> s.index(indexList)
                            // 查询
                            .query(q -> q.matchPhrase(v -> v.field(FULL_NAME).query(searchContent)))
                            // 获取docId
                            .source(o -> o.filter(f -> f.includes(DOC_ID)))
                            //去重  1、keyword类型 可以
                            .collapse(c -> c.field(DOC_ID))
                            // 根据docId 排序
                            .sort(so -> so.field(fi -> fi.field(DOC_ID).order(SortOrder.Desc)))
                            .from((pageNum - 1) * pageSize)
                            .size(pageSize)
                    , Object.class

            );
        } catch (IOException e) {
            log.info("==== 异常：{}", e);
            e.printStackTrace();
        }
        for (Hit<Object> hit : search.hits().hits()) {
            Object source = hit.source();
            log.info("== hit:{}", hit.source());
            String docId = (String) ((LinkedHashMap) hit.source()).get("docId");
            docIdList.add(docId);
        }

        return docIdList;
    }

    /**
     * 根据docId 查询index，获取数据,可能是一条，可能是多条
     *
     * @param docId
     * @return
     */
    @Override
    public List<Object> obtainResultByDocId(String docId, String indexName, List<String> fieldNames) {
        try {
            SearchResponse<Object> search = elasticsearchClient.search(s -> s.index(indexName)
                            .query(q -> q.term(t -> t.field(DOC_ID).value(v -> v.stringValue(docId))))
                            .source(o -> o.filter(f -> f.includes(fieldNames)))
                    , Object.class);
            // 没有查到数据
            if (search.hits().hits().size() == 0) {

            }
            List<Object> resultList = new ArrayList<>();
            for (Hit<Object> hit : search.hits().hits()) {
                Object source = hit.source();
                resultList.add(source);
            }
            return resultList;
        } catch (IOException e) {
            log.info("==== 异常：{}", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将获取的结果数据 ，进行拼接返回
     *
     * @param spliceDataMap
     * @return
     */
    @Override
    public Object spliceResult(Map<String, Object> spliceDataMap) {
        // 列表的一条完整的数据
        JSONObject jsonObject = new JSONObject();

        // 从map 中获取索引 及  数据
        // 商1应3资5  商1应5资3  商5应1资3
        // 需要判断 哪个领域的数据最多
        //根据docId中的各索引对应的主键去查，就可以一个docId 对应到 一条数据

        // 拼接的时候还有一个顺序的问题
        for (String key : spliceDataMap.keySet()) {
            jsonObject.put(key, spliceDataMap.get(key));
        }

        return jsonObject;
    }
}
