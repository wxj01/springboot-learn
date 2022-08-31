package com.wxj.springboot.elasticsearch02;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证 es 查询 、排序、分页
 */
@Slf4j
@SpringBootTest
public class ElasticsearchQueryTest {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public static final String INDEX_NAME_01 = "elasticsearch-client";
    public static final String INDEX_NAME_02 = "tweet";
    public static final String INDEX_NAME_03 = "tweet2";
    public static final String INDEX_NAME_04 = "tweet3";


    /**
     * 页码
     */
    private int pageNum = 4;

    /**
     * 单页条数
     */
    private int pageSize = 2;

    /**
     * 多索引联查，获取docId,
     *
     * @throws IOException
     */
    @Test
    public void testQueryData() throws IOException {

        List<String> indexList = new ArrayList<>();
        indexList.add(INDEX_NAME_02);
        indexList.add(INDEX_NAME_03);
        indexList.add(INDEX_NAME_04);

        SearchResponse<Object> search = elasticsearchClient.search(s -> s.index(indexList)
                        // 查询
                        .query(q -> q.matchPhrase(v -> v.field("topic").query("elasticsearch")))
                        // 获取docId
                        .source(o -> o.filter(f -> f.includes("topic", "tweet", "date")))
                        // 根据docId 排序
                        .sort(so -> so.field(fi -> fi.field("date").order(SortOrder.Desc)))
                        .from((pageNum - 1) * pageSize)
                        .size(pageSize)
                , Object.class

        );

        for (Hit<Object> hit : search.hits().hits()) {
            log.info("== hit: {}", hit.source());
        }


        //
    }

}
