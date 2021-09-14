package com.wxj.springboot.learn.controller;

import com.wxj.springboot.learn.bean.TestArray;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/9/14 0014 10:53
 */
@RestController
public class ParamArrController {

    @GetMapping("/testArrayStr")
    public Map<String,Object> testArrayStr(@RequestParam("arrayStr") String arrayStr){
        String[] split = arrayStr.split(",");
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("array",arrayStr);
        return  resultMap;
    }


    @GetMapping("/testArrayStr2")
    public Map<String,Object> testArrayStr(int [] numArray,String[] strArray){
        System.out.println(numArray.length);
        System.out.println(strArray[0]);
        System.out.println(strArray.length);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("numArray",numArray);
        resultMap.put("strArray",strArray);
        return resultMap;
    }


    @PostMapping("/testArray2")
    public Map<String,Object> testArray2(@RequestBody TestArray testArray){
        System.out.println(testArray.getNumArray().length);
        System.out.println(testArray.getStrArray()[0]);
        System.out.println(testArray.getStrArray().length);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("numArray",testArray.getNumArray());
        resultMap.put("strArray",testArray.getStrArray());
        return resultMap;
    }


    @ResponseBody
    @PostMapping("/testArray3")
    public String testArray3(@RequestBody Map params){

        List<Integer> list = (List<Integer>) params.get("array");

        return list.toString();
    }


}