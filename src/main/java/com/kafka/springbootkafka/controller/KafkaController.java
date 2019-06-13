package com.kafka.springbootkafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.springbootkafka.model.Student;
import com.kafka.springbootkafka.service.iml.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;


@RestController
 @RequestMapping(value = "/kafka")
public class KafkaController {

    ObjectMapper objectMapper=new ObjectMapper();


@Autowired
Producer producer;
    @PostMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);
        return message;
    }

@RequestMapping(method = RequestMethod.POST,value = "/add")
    public void postdata(@RequestBody Student student) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(student);
       producer.sendMessage(value);
    System.out.println("successfully data send to kafka producer");

}

}