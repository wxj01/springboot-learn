package com.wxj.springboot.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.wxj.springboot.elasticsearch.entity.PayableWideTable;
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
@RequestMapping("/payableWide")
public class TestPayableWideController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @PostMapping("/bulkDocument")
    public void bulk() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<PayableWideTable> userList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            PayableWideTable payableWideTable = new PayableWideTable();
            payableWideTable.setDocId(Long.valueOf(i)).setInvoiceListId("InvoiceListId" + i).setInvoiceListName(
                    "三一发票清单" + i).setInvoiceListTime("2022-8-20")
                    .setInvoiceId("invoiceId" + i).setInvoiceNo("invoiceNo" + i).setInvoiceCode("sany" + i).setInvoiceDate("2022-8-20").setInvoiceInvoiceListId("InvoiceListId" + i)
                    .setPaymentProposalsId("paymentProposalsId" + i).setPaymentProposalsName("三一付款建议内容" + i).setPaymentProposalsMoney(1000 * i + Math.random() * 100)
                    .setPaymentProposalsTime("2022-8-20").setPaymentProposalsInvoiceId("invoiceId" + i);


            userList.add(payableWideTable);

        }

        //批量处理
        for (int i = 0; i < userList.size(); i++) {
            //如果是批量更新、删除，调整这里
            bulkRequest.add(new IndexRequest("payable_index")
                    .id("" + i)
                    .source(JSON.toJSONString(userList.get(i)), XContentType.JSON));
        }
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("加载完毕");
    }


}
