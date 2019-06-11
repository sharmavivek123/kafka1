package com.kafka.springbootkafka.controller;

import com.kafka.springbootkafka.service.iml.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
 @RequestMapping(value = "/kafka")
public class KafkaController {


@Autowired
Producer producer;
    @PostMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);
        return message;
    }


}