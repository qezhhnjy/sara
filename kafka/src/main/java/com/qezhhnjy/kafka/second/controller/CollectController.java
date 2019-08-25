package com.qezhhnjy.kafka.second.controller;

import com.qezhhnjy.kafka.second.message.Message;
import com.qezhhnjy.kafka.second.message.PracticalAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author fuzy
 * create time 19-5-17-下午10:09
 */
@RestController
@RequestMapping("/kafka")
@Slf4j
public class CollectController {

    @Resource
    private KafkaTemplate<String, Object> kafka;
    @Value("${tpd.topic-name}")
    private String                        topicName;
    @Value("${tpd.messages-per-request}")
    private int                           count;

    @PostMapping(value = "/send")
    public String receive(@RequestBody Message message) {
        try {
            message.setTime(Instant.now().getEpochSecond());
            message.setDate(LocalDate.now());
            log.info("receive kafka message:{}", message);
            kafka.send("advice-topic", "key", message);
            return "send kafka success";
        } catch (Exception e) {
            return "send kafka failed";
        }
    }

    @PostMapping(value = "/hello")
    public String hello() {
        try {
            CountDownLatch latch = new CountDownLatch(count);
            IntStream.range(0, count).forEach(i -> {
                latch.countDown();
                kafka.send(topicName, String.valueOf(i), new PracticalAdvice("A Practical Advice", i));
            });
            latch.await(60L, TimeUnit.SECONDS);
            log.info("all message received");
            return "hello success";
        } catch (Exception e) {
            return "hello failed";
        }
    }

}

