package com.wxj.springboot.redis.controller;

import com.wxj.springboot.redis.filter.RedisBloomFilter;
import com.wxj.springboot.redis.utils.BloomFilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName BloomFilterController.java
 * @Description TODO
 * @createTime 2021年09月17日 18:15:00
 */
@Controller
public class BloomFilterController {

    @Autowired
    RedisBloomFilter redisBloomFilter;

    @Autowired
    BloomFilterHelper bloomFilterHelper;

    @ResponseBody
    @RequestMapping("/add")
    public String addBloomFilter(@RequestParam("orderNum") String orderNum){
        try{
            redisBloomFilter.addByBloomFilter(bloomFilterHelper,"bloom",orderNum);
        }catch (Exception e){
            e.printStackTrace();
            return "添加失败";
        }
        return "添加成功";
    }


    @ResponseBody
    @RequestMapping("/check")
    public boolean checkBloomFilter(@RequestParam ("orderNum") String orderNum) {

        boolean b = redisBloomFilter.includeByBloomFilter(bloomFilterHelper, "bloom", orderNum);

        return b;
    }
}
