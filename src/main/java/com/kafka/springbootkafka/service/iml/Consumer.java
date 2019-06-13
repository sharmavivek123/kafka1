/*package com.kafka.springbootkafka.service.iml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) {

        logger.info(String.format("$$ -> Consumed Message -> %s", message));

    }
}*/



package com.kafka.springbootkafka.service.iml;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "user011", groupId = "group_id")
    public void consume(String message) {

        System.out.println(" coming in listener part----");
        logger.info(String.format("$$ -> Consumed Message -> %s", message));


        String bootstarpserver="127.0.0.1:9092";
        String groupId="user011";
        String topic="group_id";


        // create topic
        Properties properties=new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstarpserver);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");


        // create consumer

        KafkaConsumer<String,String> kafkaConsumer=new KafkaConsumer<>(properties);

        // subscribe consumer to our topic
        kafkaConsumer.subscribe(Collections.singleton(topic));


        while (true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String,String> record : records){
                logger.info("key :: "+ record.key() + " value is :: " +record.value());
                logger.info(("partition ::  "+ record.partition()+ "offset::  "+ record.offset()));
            }


        }



    }
}
