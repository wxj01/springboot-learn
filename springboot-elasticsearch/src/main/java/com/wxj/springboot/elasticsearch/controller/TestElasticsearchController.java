//package com.wxj.springboot.elasticsearch.controller;
//
//import org.elasticsearch.action.bulk.BulkItemResponse;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//
///**
// * @author wxj
// * @version 1.0.0
// * @ClassName TestElasticsearchController.java
// * @Description TODO
// * @createTime 2021年09月18日 21:33:00
// */
//
//@RestController
//public class TestElasticsearchController {
//
//    @Resource
//    private RestHighLevelClient restHighLevelClient;
//
//    /*
//        添加
//     */
//    @GetMapping("addDoc")
//    public void add() throws IOException {
//        /**
//         * 向ES中的索引beijing下的type类型中添加一天文档
//         */
//        IndexRequest indexRequest = new IndexRequest("beijing","user","11");
//        indexRequest.source("{\"name\":\"齐天大圣孙悟空\",\"age\":685,\"bir\":\"1685-01-01\",\"introduce\":\"花果山水帘洞美猴王齐天大圣孙悟空是也！\"," +
//                "\"address\":\"花果山\"}", XContentType.JSON);
//        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//        System.out.println(indexResponse.status());
//    }
//
//
//    /*
//        删除
//     */
//    @PostMapping("deleteDoc")
//    public void deleteDoc() throws IOException {
//        // 我们把特朗普删除了
//        DeleteRequest deleteRequest = new DeleteRequest("beijing","user","rYBNG3kBRz-Sn-2f3ViU");
//        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
//        System.out.println(deleteResponse.status());
//    }
//
//    /*
//        更新
//     */
//    @PostMapping("updateDoc")
//    public void updateDoc() throws IOException {
//        UpdateRequest updateRequest = new UpdateRequest("beijing","user","p4AtG3kBRz-Sn-2fMFjj");
//        updateRequest.doc("{\"name\":\"调皮捣蛋的hardy\"}",XContentType.JSON);
//        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
//        System.out.println(updateResponse.status());
//    }
//
//    @PostMapping("bulkUpdate")
//    public void bulkUpdate() throws IOException {
//        BulkRequest bulkRequest = new BulkRequest();
//        // 添加
//        IndexRequest indexRequest = new IndexRequest("beijing", "user", "13");
//        indexRequest.source("{\"name\":\"天蓬元帅猪八戒\",\"age\":985,\"bir\":\"1685-01-01\",\"introduce\":\"天蓬元帅猪八戒因调戏嫦娥被贬下凡\",\"address\":\"高老庄\"}", XContentType.JSON);
//        bulkRequest.add(indexRequest);
//
//        // 删除
//        DeleteRequest deleteRequest01 = new DeleteRequest("beijing", "user", "pYAtG3kBRz-Sn-2fMFjj");
//        DeleteRequest deleteRequest02 = new DeleteRequest("beijing", "user", "uhTyGHkBExaVQsl4F9Lj");
//        DeleteRequest deleteRequest03 = new DeleteRequest("beijing", "user", "C8zCGHkB5KgTrUTeLyE_");
//        bulkRequest.add(deleteRequest01);
//        bulkRequest.add(deleteRequest02);
//        bulkRequest.add(deleteRequest03);
//
//        // 修改
//        UpdateRequest updateRequest = new UpdateRequest("beijing", "user", "10");
//        updateRequest.doc("{\"name\":\"炼石补天的女娲\"}", XContentType.JSON);
//        bulkRequest.add(updateRequest);
//
//        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
//        BulkItemResponse[] items = bulkResponse.getItems();
//        for (BulkItemResponse item : items) {
//            System.out.println(item.status());
//        }
//    }
//
//
//    /*
//        查询
//     */
//    public void search() throws IOException {
//        //创建搜索对象
//        SearchRequest searchRequest = new SearchRequest("christy");
//        //搜索构建对象
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery())//执行查询条件
//                .from(0)//起始条数
//                .size(10)//每页展示记录
//                .postFilter(QueryBuilders.matchAllQuery()) //过滤条件
//                .sort("age", SortOrder.DESC);//排序
//
//        //创建搜索请求
//        searchRequest.types("user").source(searchSourceBuilder);
//
//        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        System.out.println("符合条件的文档总数: "+searchResponse.getHits().getTotalHits());
//        System.out.println("符合条件的文档最大得分: "+searchResponse.getHits().getMaxScore());
//        SearchHit[] hits = searchResponse.getHits().getHits();
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsMap());
//        }
//    }
//
//}
