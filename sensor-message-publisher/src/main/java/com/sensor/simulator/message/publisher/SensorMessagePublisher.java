package com.sensor.simulator.message.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.sensor.simulator.message.publisher.model.SensorPulishableMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SensorMessagePublisher {
	
	ApplicationEventPublisher applicationEventPublisher;
	public void publish(SensorPulishableMessage sensorMessage) {
		applicationEventPublisher.publishEvent(sensorMessage);
	}
	
}
