package com.sensor.simulator.message.publisher.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.sensor.simulator.message.publisher.model.SensorPulishableMessage;
import lombok.SneakyThrows;

@Component
public class SensorMessageDetector {
	
	@Async
	@EventListener
	public void sensorMessageEvent(SensorPulishableMessage sensorMessage) {
		System.out.println(sensorMessage);
		/*Going to be saved into DB and also kafka*/
	}

}
