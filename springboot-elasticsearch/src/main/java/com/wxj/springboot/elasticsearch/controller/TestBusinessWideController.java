package com.wxj.springboot.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.wxj.springboot.elasticsearch.entity.BusinessWideTable;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/businessWide/")
public class TestBusinessWideController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @PostMapping("/bulkDocument")
    public void bulk() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<BusinessWideTable> userList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            BusinessWideTable businessWideTable = new BusinessWideTable();
            businessWideTable.setDocId(Long.valueOf(i))
                    .setSupplierId("supplierId" + i)
                    .setCompanyName("三一test" + i)
                    .setSetTime("2022-8-19")
                    .setContractId("contractId" + i)
                    .setContractName("三一测试合同" + i)
                    .setContractTime("2022-8-19")
                    .setContractCompanyId("supplierId" + i)
                    .setOrderId("orderId" + i)
                    .setOrderName("三一订单" + i)
                    .setOrderTime("2022-8-19")
                    .setOrderContractId("contractId" + i);


            businessWideTable.setDocId(Long.valueOf(i))
                    .setSupplierId("supplierId" + i)
                    .setCompanyName("三一test" + i)
                    .setSetTime("2022-8-19")
                    .setContractId("contractId" + i)
                    .setContractName("三一测试合同" + i)
                    .setContractTime("2022-8-19")
                    .setContractCompanyId("supplierId" + i)
                    .setOrderId("orderId" + i)
                    .setOrderName("三一订单" + i)
                    .setOrderTime("2022-8-19")
                    .setOrderContractId("contractId" + i);

            userList.add(businessWideTable);

        }

        //批量处理
        for (int i = 0; i < userList.size(); i++) {
            //如果是批量更新、删除，调整这里
            bulkRequest.add(new IndexRequest("business_index")
                    .id("" + i)
                    .source(JSON.toJSONString(userList.get(i)), XContentType.JSON));
        }
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("加载完毕");
    }


    @PostMapping("/addDocument")
    public void addDocument() throws IOException {
        //创建对象
        BusinessWideTable businessWideTable = new BusinessWideTable();
        businessWideTable.setDocId(Long.valueOf(1))
                .setSupplierId("supplierId" + 1)
                .setCompanyName("三一test" + 1)
                .setSetTime("2022-8-19")
                .setContractId("contractId" + 1)
                .setContractName("三一测试合同" + 1)
                .setContractTime("2022-8-19")
                .setContractCompanyId("supplierId" + 1)
                .setOrderId("orderId" + 1)
                .setOrderName("三一订单")
                .setOrderTime("2022-8-19")
                .setOrderContractId("contractId" + 1);
        //创建请求
        IndexRequest es_index = new IndexRequest("business_index");
        // 规则 put /es_index/_doc/1
        es_index.id("1");
        //将我们的数据放入请求json
        String jsonUser = JSON.toJSONString(businessWideTable);
        es_index.source(jsonUser, XContentType.JSON);
        //客户端发送请求
        IndexResponse index = restHighLevelClient.index(es_index, RequestOptions.DEFAULT);
        System.out.println("index = " + index);
        System.out.println("indexStatus = " + index.status());
    }
}
