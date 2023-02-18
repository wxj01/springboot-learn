package com.wxj.springboot.elasticsearch02;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsAggregate;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TopHitsAggregate;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxj.springboot.elasticsearch02.utils.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public static final String INDEX_NAME_05 = "tweet4";


    /**
     * 页码
     */
    private int pageNum = 1;

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
//        indexList.add(INDEX_NAME_02);
//        indexList.add(INDEX_NAME_03);
//        indexList.add(INDEX_NAME_04);
        indexList.add(INDEX_NAME_05);

        SearchResponse<Object> search = elasticsearchClient.search(s -> s.index(indexList)
                        // 查询
                        .query(q -> q.matchPhrase(v -> v.field("topic").query("elasticsearch")))
                        // 获取docId
                        .source(o -> o.filter(f -> f.includes("date")))
                        //去重  1、keyword类型 可以  2、text 不行验证
                        .collapse(c -> c.field("date"))
                        // 根据docId 排序
                        .sort(so -> so.field(fi -> fi.field("date").order(SortOrder.Desc)))
                        .from((pageNum - 1) * pageSize)
                        .size(pageSize)
                , Object.class

        );

        for (Hit<Object> hit : search.hits().hits()) {
            log.info("== hit: {}", hit.source());
            log.info("== search.hits().hits():{} ", search.hits().hits());
            // 拿到docId 
            String date = (String) ((LinkedHashMap) hit.source()).get("date");
            // 拿着docId 去 index 中查 商务数据
            SearchResponse<Object> date1 = elasticsearchClient.search(s -> s.index(INDEX_NAME_02)
                            .query(q -> q.term(t -> t.field("date").value(v -> v.stringValue(date))))
                            //要查哪些字段
                            .source(o -> o.filter(f -> f.includes("topic", "name", "date")))
                    , Object.class);

            // 拿着docId 去 index 中查  应付数据
            SearchResponse<Object> date2 = elasticsearchClient.search(s -> s.index(INDEX_NAME_03)
                            .query(q -> q.term(t -> t.field("date").value(v -> v.stringValue(date))))
                            //要查哪些字段
                            .source(o -> o.filter(f -> f.includes("topic", "about", "date")))
                    , Object.class);

            // 拿着docId 去 index 中 查 资金数据
            SearchResponse<Object> date3 = elasticsearchClient.search(s -> s.index(INDEX_NAME_04)
                            .query(q -> q.term(t -> t.field("date").value(v -> v.stringValue(date))))
                            //要查哪些字段
                            .source(o -> o.filter(f -> f.includes("topic", "comments", "date")))
                    , Object.class);


            //从哪儿个索引中查

            //查完

            // 数据拼接
            List<Hit<Object>> hits = date1.hits().hits();
            Hit<Object> objectHit = hits.get(0);
            Object source = objectHit.source();

            // 先假定 只有一条数据
            // 问题1： 一个领域中没有查到数据，属性名称要展示，只是 数据为空，该如何处理
            // 问题2：一个领域中docId 查出多条，一个领域中docId 查出一条，另一个领域docId 查出，多条
            Object source1 = date1.hits().hits().get(0).source();
            Object source2 = date2.hits().hits().get(0).source();
            Object source3 = date3.hits().hits().get(0).source();

            System.out.println("source1 = " + source1);
            Object o = JSON.toJSON(source1);
            System.out.println("o = " + o);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sw", source1);
            jsonObject.put("yf", source2);
            jsonObject.put("zj", source3);

            System.out.println(jsonObject);

            EsUtils.SpliceString(date1, date2, date3);
        }


    }

    @Test
    public void testMany() throws IOException {
        SearchResponse<Object> search = elasticsearchClient.search(s -> s.index("xxx")
                        .query(q -> q.bool(b -> b
                                .must(m -> m.term(t -> t.field("年度").value(v -> v.stringValue("年度"))))
                                .must(m -> m.term(t -> t.field("是否有效").value(v -> v.stringValue("有效"))))
                                .should(sd -> sd.matchPhrase(m -> m.field("full_name").query("搜索内容")))
                        ))
                        .source(o -> o.filter(f -> f.includes("列表展示列")))
                        .sort(so -> so.field(f -> f.field("排序字段").order(SortOrder.Desc)))
                        .from(0)
                        .size(10)
                , Object.class);

        List<Object> list = new ArrayList<>();
        for (Hit<Object> hit : search.hits().hits()) {
            Object source = hit.source();
            Object o = JSON.toJSON(source);
            list.add(o);
        }
        return;
    }


    @Test
    public void testQuery() throws IOException {
        List list = new ArrayList();
        List<Map<String, Object>> orderMap = new ArrayList<>();
        SearchResponse<Object> search = elasticsearchClient.search(s -> s.index("xxx")
                        .query(q -> q.match(m -> m.field("aa")))
                        .fields(list)
                        .aggregations("composite_cnt",
                                agg -> agg
                                        .terms(t -> t.field("").size(100).script(sc -> sc.inline(in -> in.source(
                                                "String a = params._source[\"mjahr_yf\"]; String b = params" +
                                                        "._source[\"mblnr_yf\"]; emit(a + \"#\" + b);"))))
                                        .aggregations("bucketSort", sub -> sub
                                                .bucketSort(b -> b.from(0).size(5)))

                        )
                , Object.class);
    }


    @Test
    public void testAgg8() throws IOException {
        // 执行查询
        SearchResponse<Map> searchResponse = elasticsearchClient.search(srBuilder -> srBuilder
                        .index("employees")
                        //top_hits示例：指定size，不同工种中，年纪最大的3个员工的具体信息
                        .aggregations("jobs", aggregationBuilder -> aggregationBuilder
                                //获取 job的分类信息
                                .terms(termsAggregationBuilder -> termsAggregationBuilder
                                        .field("job.keyword")

                                )//嵌套聚合
                                .aggregations("old_employee", subAggregationBuilder -> subAggregationBuilder
                                        .topHits(topHitsAggregationBuilder -> topHitsAggregationBuilder
                                                .size(3)
                                                .sort(sortOptionsBuilder -> sortOptionsBuilder
                                                        .field(fieldSortBuilder -> fieldSortBuilder
                                                                .field("age").order(SortOrder.Desc)))
                                        )
                                )

                        )

                , Map.class);

        //解析查询结果
        System.out.println(searchResponse);
        System.out.println("花费的时长：" + searchResponse.took());

        //获取聚合结果
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        System.out.println("aggregations：" + aggregations);
        StringTermsAggregate jobs = aggregations.get("jobs").sterms();//注意类型

        List<StringTermsBucket> bucketList = jobs.buckets().array();
        for (StringTermsBucket bucket : bucketList) {
            System.out.println("key：" + bucket.key());
            System.out.println("docCount：" + bucket.docCount());
            //嵌套的信息
            TopHitsAggregate old_employee = bucket.aggregations().get("old_employee").topHits();
            List<Hit<JsonData>> hits = old_employee.hits().hits();
            System.out.println("    old_employee.hits().total().value():" + old_employee.hits().total().value());
            for (Hit<JsonData> hit : hits) {
                System.out.println("    hit.source():" + hit.source().toString());
            }

        }

    }

}
