package com.wxj.springboot.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/1/10 0010 13:05
 */
@RestController
@Slf4j
public class kafkaController {

        @Autowired
        private KafkaTemplate<Object, Object> template;

        @GetMapping("/send/{input}")
        public void sendFoo(@PathVariable String input) {
            this.template.send("topic_input", input);
        }
        @KafkaListener(id = "webGroup", topics = "topic_input")
        public void listen(String input) {
            log.info("input value: {}" , input);
        }

}