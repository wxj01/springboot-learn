package com.wxj.springboot.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.wxj.springboot.elasticsearch.entity.BFPTable;
import com.wxj.springboot.elasticsearch.entity.BusinessWideTable;
import com.wxj.springboot.elasticsearch.entity.FundWideTable;
import com.wxj.springboot.elasticsearch.entity.PayableWideTable;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAddData {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private int startNum = 2; //350000
    private int endNum = 3; //350000

    @Test
    public void testAddBusiness() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<BusinessWideTable> userList = new ArrayList<>();
        int num = 2;
        for (int i = startNum; i < endNum; i++) {
            BusinessWideTable businessWideTable = new BusinessWideTable();
            //同一个供应 ，同一个合同，不同的订单
            //一个供应商 (test-1) ，一个合同(三一测试合同-1),该合同10个订单 三一订单1~10
            // test-1 -> 三一测试合同-1 -> 三一订单1~10
            businessWideTable.setDocId(Long.valueOf(i))
                    .setSupplierId("supplierId-" + i / 6)
                    .setCompanyName("test-" + i / 6)
                    .setSetTime("2022-8-19")
                    .setContractId("contractId-" + i / 5)
                    .setContractName("三一测试合同-" + i / 5)
                    .setContractTime("2022-8-19")
                    .setContractCompanyId("supplierId-" + i / 6)
                    .setOrderId("orderId-" + i / 4)
                    .setOrderName("三一订单-" + i / 3)
                    .setOrderTime("2022-8-19")
                    .setOrderContractId("contractId-" + i / 2);

            userList.add(businessWideTable);
//            if (i % 50000 == 0) {
//                addData2Es(bulkRequest, userList, "test_business_index");
//                userList.clear();
//            }
        }

        addData2Es(bulkRequest, userList, "test_business_index");
    }


    @Test
    public void testAddPayable() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<PayableWideTable> userList = new ArrayList<>();
        int num = 2;
        for (int i = startNum; i < endNum; i++) {
            PayableWideTable payableWideTable = new PayableWideTable();

            // 一个订单 orderId1 ，对应一个发票清单 ， 一个清单 对应一个发票， 一个发票对应多个建议
            //       三一订单1 ->  三一发票清单-1 ->  invoiceId-1 -> 三一付款建议内容-1 ~20
            payableWideTable
                    .setDocId(Long.valueOf(i))
                    .setOrderId("orderId-" + i / 2)
                    .setOrderName("三一订单-" + i / 3)
                    .setInvoiceListId("InvoiceListId-" + i / 4)
                    .setInvoiceListName("三一发票清单-" + i / 5)
                    .setInvoiceListTime("2022-8-20")
                    .setInvoiceId("invoiceId-" + i / 6)
                    .setInvoiceNo("invoiceNo-" + i / 7)
                    .setInvoiceCode("sany" + i)
                    .setInvoiceDate("2022-8-20")
                    .setInvoiceInvoiceListId("InvoiceListId-" + i / 8)
                    .setPaymentProposalsId("paymentProposalsId-" + i)
                    .setPaymentProposalsName("三一付款建议内容-" + i)
                    .setPaymentProposalsMoney(1000 * i + Math.random() * 100)
                    .setPaymentProposalsTime("2022-8-20")
                    .setPaymentProposalsInvoiceId("invoiceId-" + i / 6);


            userList.add(payableWideTable);

        }
        addData2Es(bulkRequest, userList, "test_payable_index");

    }


    @Test
    public void testAddFund() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<FundWideTable> userList = new ArrayList<>();
        int num = 2;
        for (int i = startNum; i < endNum; i++) {
            FundWideTable fundWideTable = new FundWideTable();
            // 一个付款清单 ，对一个票据 ，一个票据对应多个流水
            //三一付款建议内容-1  -> 三一付款申请单-1 -> 三一票据-1 ->  三一流水详情-1~30
//            fundWideTable.setDocId(Long.valueOf(i))
//
//                    .setPaymentProposalsId("paymentProposalsId-" + i / 2)
//                    .setPaymentProposalsName("三一付款建议内容-" + i / 3)
//
//                    .setPaymentRequestFormId("paymentRequestFormId-" + i / 3)
//                    .setPaymentRequestFormName("三一付款申请单-" + i / 4)
//                    .setBillId("billId-" + i)
//                    .setBillName("三一票据-" + i)
//                    .setBillTime("2022-8-20")
//                    .setBillPaymentRequestFormId("paymentRequestFormId-" + i / 5)
//                    .setWaterId("waterId-" + i)
//                    .setWaterInfo("三一流水详情-" + i)
//                    .setWaterTime("2022-8-20")
//                    .setWaterBillId("billId-" + i);

            fundWideTable.setDocId(Long.valueOf(i))

                    .setPaymentProposalsId("paymentProposalsId-" + i / 2)
                    .setPaymentProposalsName("三一付款建议内容-" + i / 3)

                    .setPaymentRequestFormId("paymentRequestFormId-" + i / 3)
                    .setPaymentRequestFormName("三一付款申请单-" + i / 4)
                    .setBillId("billId-" + i)
                    .setBillName("三一票据-" + i)
                    .setBillTime("2022-8-20")
                    .setBillPaymentRequestFormId("paymentRequestFormId-" + i / 5)
                    .setWaterId("waterId-" + i)
                    .setWaterInfo("三一流水详情-" + i)
                    .setWaterTime("2022-8-20")
                    .setWaterBillId("billId-" + i);

            userList.add(fundWideTable);

//            if (i % 10000 == 0) {
//                addData2Es(bulkRequest, userList, "test_fund_index");
//            }

        }
        addData2Es(bulkRequest, userList, "test_fund_index");


    }


    @Test
    public void testBFPData() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<BFPTable> userList = new ArrayList<>();
        for (int i = startNum; i < endNum; i++) {
            BFPTable bfpTable = new BFPTable();

//            private String supplierId;  // 供应商id
//            private String contractId; // 合同主键
//            private String orderId; // 订单id
//
//            private String InvoiceListId;  // 发票清单id
//            private String invoiceId; // 发票id
//            private String paymentProposalsId; // 付款建议id
//
//            private String paymentRequestFormId; //付款申请单id
//            private String billId; // 票据id
//            private String waterId;  // 流水id

            bfpTable.setDocId(Long.valueOf(i))
                    .setSupplierId("supplierId" + i / 512)
                    .setContractId("contractId" + i / 256)
                    .setOrderId("orderId" + i / 128)

                    .setInvoiceListId("invoiceListId" + i / 64)
                    .setInvoiceId("invoiceId" + i / 32)
                    .setPaymentProposalsId("paymentProposalsId" + i / 16)

                    .setPaymentRequestFormId("paymentRequestFormId" + i / 8)
                    .setBillId("billId" + i / 4)
                    .setWaterId("waterId" + i / 2);

            userList.add(bfpTable);

        }
        addData2Es(bulkRequest, userList, "test_bfp_index");
    }

    @Test
    public void queryEsData() throws IOException {
//        SearchRequest es_index = new SearchRequest("es_index");
        // 一次从三个索引中查询数据
        SearchRequest es_index = new SearchRequest("test_business_index", "test_payable_index", "test_fund_index");
        //使用搜索条件构造器，构造搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //查询条件，使用queryBuilders 实现
        //精准查询
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("docId", 1);
        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery("三一");
        
        // 匹配所有
//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        searchSourceBuilder.query(multiMatchQuery);
        //只获取docId
        searchSourceBuilder.fetchSource("docId", null);
//        searchSourceBuilder.query(termQueryBuilder);

        //分页
        searchSourceBuilder.from(); // 默认 0
        searchSourceBuilder.size(2000); // 默认 10

//        List<SortBuilder<?>> sorts

        searchSourceBuilder.sort("companyName", SortOrder.ASC);
//        searchSourceBuilder.sort("invoiceCode", SortOrder.DESC);
//        searchSourceBuilder.sort("billId", SortOrder.DESC);


        es_index.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(es_index, RequestOptions.DEFAULT);
        System.out.println("search.getHits() = " + JSON.toJSONString(search.getHits()));
        System.out.println("==============================");
        Set<String> set = new HashSet<>();
        Set<Integer> docIdSet = new HashSet();
        for (SearchHit hits : search.getHits().getHits()) {
            System.out.println(hits.getSourceAsString());
            set.add(hits.getSourceAsString());
            docIdSet.add((Integer) hits.getSourceAsMap().get("docId"));
            System.out.println("hits.getSourceAsString() = " + hits.getSourceAsString());
        }
        System.out.println("set中去重docId集合:" + set);

//        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
//                25, 26, 27, 28, 29, 30
        System.out.println("docIdSet中去重docId集合:" + docIdSet);

        // 重中间关系表通过docId 查询
        SearchRequest bftSearchRequest = new SearchRequest("test_bfp_index");
        //使用搜索条件构造器，构造搜索条件
        SearchSourceBuilder bftSearchSourceBuilder = new SearchSourceBuilder();
        bftSearchSourceBuilder.query(QueryBuilders.termsQuery("docId", new ArrayList<>(docIdSet)));

        //从中间表中获取前十条
        bftSearchSourceBuilder.from();
        bftSearchSourceBuilder.size();


        bftSearchRequest.source(bftSearchSourceBuilder);
        SearchResponse bftSearch = restHighLevelClient.search(bftSearchRequest, RequestOptions.DEFAULT);
        System.out.println("bftSearch.getHits() = " + JSON.toJSONString(bftSearch.getHits()));
        for (SearchHit hits : bftSearch.getHits().getHits()) {
            System.out.println("hits.getSourceAsString() = " + hits.getSourceAsString());
        }
        System.out.println("关系表 test_btf_index 查询 完");

        //通过关系表反查 三个宽表的数据


    }

    @Test
    public void ArraysAsList() throws IOException {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30};
        List<Integer> integers = Arrays.asList(1, 2, 3);
        Set set = new HashSet();
        set.add(1);
        set.add(3);
        set.add(5);
        List arrayList = new ArrayList(set);
        System.out.println(arrayList);

        SearchRequest es_index = new SearchRequest("test_bfp_index");
        //使用搜索条件构造器，构造搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
//        TermsQueryBuilder termQueryBuilder = QueryBuilders.termsQuery("docId", arr);
        TermsQueryBuilder termQueryBuilder = QueryBuilders.termsQuery("docId", new ArrayList(set));

//        boolQueryBuilder.must(QueryBuilders.termsQuery("JWHDZBM", jwhdzbm));
        searchSourceBuilder.query(termQueryBuilder);

        //分页
        searchSourceBuilder.from(); // 默认 0
        searchSourceBuilder.size(1000); // 默认 10


        es_index.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(es_index, RequestOptions.DEFAULT);
        System.out.println("search.getHits() = " + JSON.toJSONString(search.getHits()));
        System.out.println("==============================");
        for (SearchHit hits : search.getHits().getHits()) {
            System.out.println("hits.getSourceAsString() = " + hits.getSourceAsString());
        }


    }


    @Test
    public void queryEsDataByStep() throws IOException {
        // 一次从三个索引中查询数据
        SearchRequest es_index = new SearchRequest("test_business_index", "test_payable_index", "test_fund_index");
        //使用搜索条件构造器，构造搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //查询条件，使用queryBuilders 实现
        //精准查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("docId", 1);
        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery("三一");


//        searchSourceBuilder.query(multiMatchQuery);
        searchSourceBuilder.query(termQueryBuilder);

        //分页
        searchSourceBuilder.from(); // 默认 0
        searchSourceBuilder.size(50); // 默认 10

        //高度
        HighlightBuilder highlightBuilder = new HighlightBuilder(); //高亮构造器
        highlightBuilder.field("username"); //设置高亮字段
        highlightBuilder.requireFieldMatch(false); // 关闭多个高亮
        highlightBuilder.preTags("<span style='color:red'>"); //设置前缀
        highlightBuilder.postTags("</span>"); //设置后缀
        searchSourceBuilder.highlighter(highlightBuilder);

        es_index.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(es_index, RequestOptions.DEFAULT);
        System.out.println("search.getHits() = " + JSON.toJSONString(search.getHits()));
        System.out.println("==============================");
        for (SearchHit hits : search.getHits().getHits()) {
            System.out.println("hits.getSourceAsString() = " + hits.getSourceAsString());
        }
    }


    private void addData2Es(BulkRequest bulkRequest, List userList, String indexName) throws IOException {
        //批量处理
        for (int i = 0; i < userList.size(); i++) {
            //如果是批量更新、删除，调整这里
            bulkRequest.add(new IndexRequest(indexName)
//                    .id("" + UUID.)
                    .source(JSON.toJSONString(userList.get(i)), XContentType.JSON));
        }
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("加载完毕");
    }
}
