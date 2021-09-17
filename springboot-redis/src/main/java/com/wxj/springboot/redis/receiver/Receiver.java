package com.wxj.springboot.redis.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Receiver.java
 * @Description TODO
 * @createTime 2021年09月17日 17:07:00
 */
@Component
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);


    /**
     * 接收到消息的方法，message就是指从主题获取的消息，主题配置在RedisMessageListener配置类做配置
     * @param message
     */
    public void receiveMessage(String message) {
//        TestService testService=new TestService();
        //testService.getData();
        LOGGER.info("Received <" + message + ">");

    }
}
