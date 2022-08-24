package com.wxj.springboot.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.wxj.springboot.elasticsearch.entity.User;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TestEsDocumentController {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 添加文档（如果没有索引自己创建）
     *
     * @throws IOException
     */
    @PostMapping("/addDocument")
    public void addDocument() throws IOException {
        //创建对象
        User user = new User("test", 18, new Date());
        //创建请求
        IndexRequest es_index = new IndexRequest("es_index");
        // 规则 put /es_index/_doc/1
        es_index.id("1");
        //将我们的数据放入请求json
        String jsonUser = JSON.toJSONString(user);
        es_index.source(jsonUser,XContentType.JSON);
        //客户端发送请求
        IndexResponse index = restHighLevelClient.index(es_index, RequestOptions.DEFAULT);
        System.out.println("index = " + index);
        System.out.println("indexStatus = " + index.status());
    }

    /**
     * 获取文档
     *
     * @throws IOException
     */
    @GetMapping("/getDocument")
    public void getDocument() throws IOException {
        // get /index/_doc/1
        GetRequest es_index = new GetRequest("es_index", "1");
        GetResponse documentFields = restHighLevelClient.get(es_index, RequestOptions.DEFAULT);
        System.out.println("documentFields.getSourceAsString() = " + documentFields.getSourceAsString());
        System.out.println("getResponse = " + documentFields);
    }

    /**
     * 更新文档
     *
     * @throws IOException
     */
    @PostMapping("/updateDocument")
    public void updateDocument() throws IOException {
        // post /index/_doc/1/_update
        UpdateRequest es_index = new UpdateRequest("es_index", "1");
        //修改
        User test02 = new User("test02", 19, new Date());
        String s = JSON.toJSONString(test02);
        es_index.doc(s, XContentType.JSON);

        UpdateResponse update = restHighLevelClient.update(es_index, RequestOptions.DEFAULT);
        System.out.println("update = " + update);
    }


    /**
     * 删除文档
     *
     * @throws IOException
     */
    @DeleteMapping("/deleteDocument")
    public void deleteDocument() throws IOException {
        // delete /index/_doc/1
        DeleteRequest es_index = new DeleteRequest("es_index", "1");
        DeleteResponse delete = restHighLevelClient.delete(es_index, RequestOptions.DEFAULT);
        System.out.println("delete.status() = " + delete.status());
    }

    /**
     * 批量添加
     *
     * @throws IOException
     */
    @PostMapping("/bulkDocument")
    public void bulk() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<User> userList = new ArrayList<>();
        userList.add(new User("user01", 3, new Date()));
        userList.add(new User("user02", 4, new Date()));
        userList.add(new User("user03", 5, new Date()));
        userList.add(new User("user04", 6, new Date()));
        userList.add(new User("user05", 7, new Date()));

        //批量处理
        for (int i = 0; i < userList.size(); i++) {
            //如果是批量更新、删除，调整这里
            bulkRequest.add(new IndexRequest("es_index")
                    .id("" + i + 1)
                    .source(JSON.toJSONString(userList.get(i)), XContentType.JSON));
        }
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }


    /**
     *  查询
     * @throws IOException
     */
    @GetMapping("/search")
    public void search() throws IOException {
        SearchRequest es_index = new SearchRequest("es_index");
        //使用搜索条件构造器，构造搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //查询条件，使用queryBuilders 实现
        //精准查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("userName", "age");

        // 匹配所有
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        searchSourceBuilder.query(termQueryBuilder);

        //分页
        searchSourceBuilder.from(); // 默认 0
        searchSourceBuilder.size(); // 默认 10

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

}
