package com.wxj.springboot.elasticsearch.controller;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/insertData")
public class TestInsertData2ESController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @PostMapping
    @RequestMapping("/toEs")
    public void bulkBusiness(@RequestParam String indexName,
                             @RequestParam int num,
                             HttpServletRequest request,
                             @RequestBody String sourceData) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 0; i < num; i++) {
            //如果是批量更新、删除，调整这里
            bulkRequest.add(new IndexRequest(indexName)
                    .source(sourceData, XContentType.JSON));
        }
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("数据插入完毕");
    }
 

    @PostMapping
    @RequestMapping("/toTheadEs")
    public void ManyThreadsBulkBusiness(@RequestParam String indexName,
                                        @RequestParam int num,
                                        @RequestBody String sourceData) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int a = 0; a < 10; a++) {
            executorService.submit(() -> {
                for (int i = 0; i < num; i++) {
                    //如果是批量更新、删除，调整这里
                    bulkRequest.add(new IndexRequest(indexName)
                            .source(sourceData, XContentType.JSON));
                }
                try {
                    System.out.println(Thread.currentThread().getName() + "开始提交数据");
                    restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
                    System.out.println(Thread.currentThread().getName() + "完成 数据插入");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println("数据插入完毕");
    }

}
