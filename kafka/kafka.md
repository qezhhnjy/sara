# spring boot kafka
`spring boot` `kafka`

---
## 1. 最简易的spring boot kafka应用

- 首先需要官网下载并解压kafka软件.
- 其次使用spring init 搭建一个项目.
- 相关依赖,对于基础使用来说,只需要这一个,kafka本身也有更多的一些依赖.

        <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
		</dependency>
- 生产者
    ```java
    @Component
    @EnableScheduling
    @Slf4j
    public class ProducerFirst {

        @Resource
        private KafkaTemplate<String, String> kafka;

        /**
        * 定时任务
        */
        @Scheduled(cron = "*/1 * * * * ?")
        public void send() {
            Person fuzy = new Person(1, "fuzy", "901116", "15868176918", "fzyszsz@163.com", LocalDateTime.now());

            ListenableFuture<SendResult<String, String>> future = kafka.send("test", fuzy.toString());
            future.addCallback(ProducerFirst::success, ProducerFirst::failed);
        }

        private static void success(SendResult<String, String> result) {
            log.info(String.valueOf(result));
        }

        private static void failed(Throwable t) {
            log.error(t.getLocalizedMessage(),t);
            log.error(t.getMessage(),t);
        }
    }

    @Data
    @AllArgsConstructor
    class Person {
        private int           id;
        private String        name;
        private String        password;
        private String        phone;
        private String        email;
        private LocalDateTime time;
    }
    ```

- 消费者

    ```java
    @Component
    @Slf4j
    public class ConsumerFirst {

        @KafkaListener(topics = {"test"}, groupId = "consumer")
        public void receive(String message) {
            log.info("receive: {}", message);
        }

        @KafkaListener(topics = "test", groupId = "listen")
        public void listen(ConsumerRecord<?, ?> record) {
            log.info("kafka key: {}", record.key());
            log.info("kafka value: {}", record.value());
        }
    }
    ```

>**启动kafka,然后运行该项目,就完成了最简易的kafka使用代码,所有配置都使用了系统的默认配置.**

[Spring Boot和Kafka实战自定义复杂配置示例](https://www.jdon.com/50853)