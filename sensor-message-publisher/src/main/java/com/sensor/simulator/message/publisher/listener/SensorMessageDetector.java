package com.sensor.simulator.message.publisher.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.sensor.simulator.message.publisher.model.SensorPulishableMessage;
import com.sensor.simulator.message.publisher.service.SensorMessageKafkaProducer;
import com.sensor.simulator.message.publisher.service.SensorMessageValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SensorMessageDetector {
	
	SensorMessageKafkaProducer producer;
	SensorMessageValidator validator;
	
	@Async
	@EventListener
	public void sensorMessageEvent(SensorPulishableMessage sensorMessage) {
		log.info("Received message: {%s}".formatted(sensorMessage));
		var message = validator.validate(sensorMessage);
		producer.send(message);
	}

}
