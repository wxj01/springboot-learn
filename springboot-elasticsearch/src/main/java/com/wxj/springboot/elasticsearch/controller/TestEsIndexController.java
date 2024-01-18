package com.wxj.springboot.elasticsearch.controller;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class TestEsIndexController {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引 request  put test_index
     */
    @PostMapping("/createIndex")
    public void createIndex() throws IOException {
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("test_index");
        // 2、执行请求
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("createIndexResponse = " + createIndexResponse);
    }

    /**
     * 判断索引是否存在
     *
     * @throws IOException
     */
    @GetMapping("/existIndex")
    public void existIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        boolean exists = restHighLevelClient.indices().exists(request.indices("test_index"), RequestOptions.DEFAULT);
        System.out.println("exists = " + exists);
    }

    /**
     * 测试删除索引
     */
    @DeleteMapping("/deleteIndex")
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("test_index");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println("delete = " + delete);
    }
}


