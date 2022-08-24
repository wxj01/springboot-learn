package com.wxj.springboot.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.wxj.springboot.elasticsearch.entity.FundWideTable;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
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
@RequestMapping("/fundWide")
public class TestFundWideController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @PostMapping("/bulkDocument")
    public void bulk() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<FundWideTable> userList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            FundWideTable fundWideTable = new FundWideTable();
            fundWideTable.setDocId(Long.valueOf(i)).setPaymentRequestFormId("paymentRequestFormId"+i).setPaymentRequestFormName("三一付款申请单"+i)
                    .setBillId("billId"+i).setBillName("三一票据"+i).setBillTime("2022-8-20").setBillPaymentRequestFormId("paymentRequestFormId"+i)
                    .setWaterId("waterId"+i).setWaterInfo("三一流水详情"+i).setWaterTime("2022-8-20").setWaterBillId("billId"+i);


            userList.add(fundWideTable);

        }

        //批量处理
        for (int i = 0; i < userList.size(); i++) {
            //如果是批量更新、删除，调整这里
            bulkRequest.add(new IndexRequest("fund_index")
                    .id("" + i)
                    .source(JSON.toJSONString(userList.get(i)), XContentType.JSON));
        }
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("加载完毕");
    }


}
