package com.wxj.springboot.elasticsearch02;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.wxj.springboot.elasticsearch02.entity.AhaIndex;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文档操作
 */
@Slf4j
@SpringBootTest
public class ElasticsearchClientDocumentTest {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private static final String INDEX_NAME = "elasticsearch-client";

    // 添加文档
    @Test
    public void testAddDocument() throws IOException {

        IndexResponse indexResponse = elasticsearchClient.index(indexRequest ->
                indexRequest.index(INDEX_NAME).document(new AhaIndex().setName("wangWu").setAge(21))
        );
        log.info("== response: {}, responseStatus: {}", indexResponse, indexResponse.result());

    }

    // 获取文档信息
    @Test
    public void testGetDocument() throws IOException {
        GetResponse<AhaIndex> getResponse = elasticsearchClient.get(getRequest ->
                getRequest.index(INDEX_NAME).id("HHr17YIBoaZQtnhcWqp-"), AhaIndex.class
        );
        log.info("== document source: {}, response: {}", getResponse.source(), getResponse);
    }

    // 更新文档信息
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateResponse<AhaIndex> updateResponse = elasticsearchClient.update(updateRequest ->
                updateRequest.index(INDEX_NAME).id("HHr17YIBoaZQtnhcWqp-")
                        .doc(new AhaIndex().setName("lisi1").setAge(22)), AhaIndex.class
        );
        log.info("== response: {}, responseStatus: {}", updateResponse, updateResponse.result());
    }

    // 删除文档信息
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteResponse deleteResponse = elasticsearchClient.delete(deleteRequest ->
                deleteRequest.index(INDEX_NAME).id("HHr17YIBoaZQtnhcWqp-")
        );
        log.info("== response: {}, result:{}", deleteResponse, deleteResponse.result());

    }

    // 批量插入文档
    @Test
    public void testBatchInsert() throws IOException {

        List<BulkOperation> bulkOperationList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            AhaIndex ahaIndex = new AhaIndex().setName("lisi" + i).setAge(20 + i);
            bulkOperationList.add(new BulkOperation.Builder().create(e -> e.document(ahaIndex)).build());
        }

        BulkResponse bulkResponse = elasticsearchClient.bulk(bulkRequest ->
                bulkRequest.index(INDEX_NAME).operations(bulkOperationList)
        );

        // 这边插入成功的话显示的是 false
        log.info("== errors: {}", bulkResponse.errors());
    }


    /**
     * 采用lambda 查询
     *
     * @throws IOException
     */
    @Test
    public void testRestClient() throws IOException {

        SearchResponse<AhaIndex> search = elasticsearchClient.search(s -> s.index(INDEX_NAME)
                        .query(q -> q.term(t -> t
                                .field("name")
                                .value(v -> v.stringValue("lisi0"))
                        )),
                AhaIndex.class);

        for (Hit<AhaIndex> hit : search.hits().hits()) {
            log.info("== hit: {}", hit.source());
        }

    }

}
