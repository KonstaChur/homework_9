package org.example.message.message;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.game_objects.UObjectWithId;
import org.example.game_objects.spaceship.DescribableByIdSpaceshipsObject;
import org.example.game_objects.spaceship.SpaceshipObject;
import org.example.message.CommandMessageConsumer;
import org.example.message.CommandMessageDto;
import org.example.queue.Games;
import org.example.queue.LinkedListCommandQueue;
import org.example.service.CommandQueueOperatingService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Testcontainers
class CommandMessageConsumerITCase extends IoCAbstractTest {
    @Container
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));

    private ObjectMapper objectMapper = new ObjectMapper();
    private Games games = Games.getInstance();
    private UObjectWithId spaceships = DescribableByIdSpaceshipsObject.getInstance();

    @Test
    void shouldInterpretMoveLinearCommand() throws JsonProcessingException, ExecutionException, InterruptedException {
        // given
        var commandMessageConsumer = new CommandMessageConsumer(
                kafka.getBootstrapServers(),
                "test-group",
                "test-topic");

        var gameId = UUID.randomUUID();
        games.setGameQueue(gameId.toString(), LinkedListCommandQueue.getInstance());
        var spaceshipId = UUID.randomUUID();
        var spaceship = new SpaceshipObject();
        spaceship.setProperty("Position", new double[]{12, 5});
        spaceship.setProperty("Velocity", 3);
        double[] expectedPosition = new double[]{15, 5};

        spaceships.setProperty(spaceshipId.toString(), spaceship);
        var commandMessage = new CommandMessageDto(
                gameId.toString(),
                spaceshipId.toString(),
                "MoveLinearCommand",
                new JSONObject()
        );

        // when
        createProducer().send(
                new ProducerRecord<>(
                        "test-topic",
                        "key",
                        objectMapper.writeValueAsString(commandMessage)
                )
        ).get();
        Thread.sleep(1000);
        commandMessageConsumer.poll(1500);
        var queueOperatingService = new CommandQueueOperatingService();
        queueOperatingService.process();
        Thread.sleep(1000);

        // then
        double[] actualPosition = (double[]) spaceship.getProperty("Position");
        assertArrayEquals(expectedPosition, actualPosition, 0);
    }

    private KafkaProducer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "kafka-test");
        return new KafkaProducer<>(props);
    }
}