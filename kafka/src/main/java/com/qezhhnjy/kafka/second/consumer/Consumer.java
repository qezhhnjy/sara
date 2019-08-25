package com.qezhhnjy.kafka.second.consumer;

import com.qezhhnjy.kafka.second.message.PracticalAdvice;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.StreamSupport;

/**
 * @author fuzy
 */
@Component
@Slf4j
public class Consumer {

    @Resource
    private KafkaProperties kafkaProperties;
    @Value("${tpd.topic-name}")
    private String          topicName;
    @Value("${tpd.group-name")
    private String          groupName;

//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>(
//                kafkaProperties.buildConsumerProperties()
//        );
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                JsonDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG,
//                "tpd-loggers");
//
//        return props;
//    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    // String Consumer Configuration
    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new StringDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerStringContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }

    // Byte Array Consumer Configuration

    @Bean
    public ConsumerFactory<String, byte[]> byteArrayConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new ByteArrayDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaListenerByteArrayContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(byteArrayConsumerFactory());
        return factory;
    }

    @KafkaListener(topics = "${tpd.topic-name}", clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory")
    public void listenAsObject(ConsumerRecord<String, PracticalAdvice> record, @Payload PracticalAdvice payload) {
        log.info("Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}", record.key(),
                typeIdHeader(record.headers()), payload, record.toString());
    }

    @KafkaListener(topics = "${tpd.topic-name}", clientIdPrefix = "string", containerFactory = "kafkaListenerStringContainerFactory")
    public void listenAsString(ConsumerRecord<String, String> record, @Payload String payload) {
        log.info("Logger 2 [String] received key {}: Type [{}] | Payload: {} | Record: {}", record.key(),
                typeIdHeader(record.headers()), payload, record.toString());
    }

    @KafkaListener(topics = "${tpd.topic-name}", clientIdPrefix = "byte", containerFactory = "kafkaListenerByteArrayContainerFactory")
    public void listenAsByteArray(ConsumerRecord<String, byte[]> record, @Payload byte[] payload) {
        log.info("Logger 3 [ByteArray] received key {}: Type [{}] | Payload: {} | Record: {}", record.key(),
                typeIdHeader(record.headers()), payload, record.toString());
    }

    private static String typeIdHeader(Headers headers) {
        return StreamSupport.stream(headers.spliterator(), false)
                .filter(header -> "__TypeId__".equals(header.key()))
                .findFirst().map(header -> new String(header.value()))
                .orElse("N/A");
    }


    // ------简单测试

    @KafkaListener(topics = {"${tpd.topic-name}"})
    public void receive(PracticalAdvice message) {
        log.info("receive: {}", message);
    }
//
//    @KafkaListener(topics = {"${tpd.topic-name}"}, groupId = "consumer")
//    public void third(String message) {
//        log.info("third receive: {}", message);
//    }
//
//    @KafkaListener(topics = {"${tpd.topic-name}"}, groupId = "listen")
//    public void listen(ConsumerRecord<String, Object> record) {
//        log.info("kafka value: {}", record.value());
//    }
}
