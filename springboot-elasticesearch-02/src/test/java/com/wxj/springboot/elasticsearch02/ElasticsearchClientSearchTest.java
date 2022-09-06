package com.wxj.springboot.elasticsearch02;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.alibaba.fastjson.JSONObject;
import com.wxj.springboot.elasticsearch02.entity.AhaIndex;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * search api 操作
 */
@Slf4j
@SpringBootTest
public class ElasticsearchClientSearchTest {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private static final String INDEX_NAME = "elasticsearch-client";

    /**
     * 根据 name 查询相应的文档， search api 才是 elasticsearch-client 的优势，
     * 可以看出使用 lambda 大大简化了代码量，
     * 可以与 restHighLevelClient 形成鲜明的对比，但是也有可读性较差的问题，所以 lambda 的基础要扎实
     */
    @Test
    public void testRestClient() throws IOException {

        SearchResponse<Object> search = elasticsearchClient.search(s -> s.index(INDEX_NAME)
                        .query(q ->
                                q.term(t ->
                                        t.field("name").value(v -> v.stringValue("lisi1"))
                                )
                        ),
                Object.class);

        List<Hit<Object>> hits = search.hits().hits();

        for (Hit<Object> hit : search.hits().hits()) {
            Object source = hit.source();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("abc", source);
            log.info("== hit: source: {}, id: {}", hit.source(), hit.id());
            log.info("== jsonObject: {} ", jsonObject);
        }

    }

    // 多条件 返回查询
    @Test
    public void testMultipleCondition() throws IOException {

        SearchRequest request = SearchRequest.of(searchRequest ->
                searchRequest.index(INDEX_NAME).from(0).size(20).sort(s -> s.field(f -> f.field("age").order(SortOrder.Desc)))
                        // 如果有多个 .query 后面的 query 会覆盖前面的 query
                        .query(query ->
                                query.bool(boolQuery ->
                                        boolQuery
                                                // 在同一个 boolQuery 中 must 会将 should 覆盖
                                                .must(must -> must.range(
                                                        e -> e.field("age").gte(JsonData.of("21")).lte(JsonData.of(
                                                                "25"))
                                                ))
                                                .mustNot(mustNot -> mustNot.term(
                                                        e -> e.field("name").value(value -> value.stringValue("lisi1"))
                                                ))
                                                .should(must -> must.term(
                                                        e -> e.field("name").value(value -> value.stringValue("lisi2"))
                                                ))
                                )
                        )

        );

        SearchResponse<AhaIndex> searchResponse = elasticsearchClient.search(request, AhaIndex.class);


        log.info("返回的总条数有：{}", searchResponse.hits().total().value());
        List<Hit<AhaIndex>> hitList = searchResponse.hits().hits();
        for (Hit<AhaIndex> hit : hitList) {
            log.info("== hit: {}, id: {}", hit.source(), hit.id());
        }

    }

    @Test
    public void testMultipleCondition2() throws IOException {
        SearchRequest request = SearchRequest.of(searchRequest ->
                searchRequest.index(INDEX_NAME).from(0).size(20).sort(s -> s.field(f -> f.field("age").order(SortOrder.Desc)))
                        .query(query ->
                                query.bool(boolQuery ->
                                        boolQuery
                                                // 两个 should 连用是没有问题的
                                                .should(must -> must.term(
                                                        e -> e.field("age").value(value -> value.stringValue("22"))
                                                ))
                                                .should(must -> must.term(
                                                        e -> e.field("age").value(value -> value.stringValue("23"))
                                                ))
                                )
                        ));


        SearchResponse<AhaIndex> searchResponse = elasticsearchClient.search(request, AhaIndex.class);


        log.info("返回的总条数有：{}", searchResponse.hits().total().value());
        List<Hit<AhaIndex>> hitList = searchResponse.hits().hits();
        for (Hit<AhaIndex> hit : hitList) {
            log.info("== hit: {}, id: {}", hit.source(), hit.id());
        }

    }

}
