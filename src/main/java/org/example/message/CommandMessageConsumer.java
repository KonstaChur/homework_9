package org.example.message;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.command.message.ProcessMessageICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class CommandMessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(CommandMessageConsumer.class.getName());
    private final KafkaConsumer<String, String> consumer;

    public CommandMessageConsumer(String bootstrapServer, String groupId, String topic) {
        Properties props = consumerProps(bootstrapServer, groupId);
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(List.of(topic));
    }

    public void poll(long millis) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(millis));

        for (ConsumerRecord<String, String> record : records) {
            var commandMessage = record.value();
            logger.info("Key: {}, Value: {}", record.key(), commandMessage);
            logger.info("Partition: {}, Offset: {}", record.partition(), record.offset());
            new ProcessMessageICommand(commandMessage).execute();
        }
    }

    private Properties consumerProps(String bootstrapServer, String groupId) {
        String deserializer = StringDeserializer.class.getName();
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, deserializer);
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return properties;
    }
}
