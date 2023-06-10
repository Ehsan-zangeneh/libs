package com.sensor.simulator.message.publisher.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.sensor.simulator.message.publisher.model.SensorPulishableMessage;

import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "kafka.channel.configs")
@Setter
public class SensorMessageKafkaProducer {
	
	String bootstrapServer;
	String topicName;
	
	public void send(SensorPulishableMessage sensorMessage) {
		System.out.println("send to server {%s}".formatted(bootstrapServer));
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, sensorMessage.toString());
		send(producerRecord);
	}
	
	private void send(ProducerRecord<String, String> producerRecord) {
		KafkaProducer<String, String> producer = new KafkaProducer<>(getkafkaConfigProperties());
		producer.send(producerRecord);
		producer.flush();
		producer.close();
	}
	

	
	private Properties getkafkaConfigProperties( ) {
		Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, "3");
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        return properties;
    
	}


}
