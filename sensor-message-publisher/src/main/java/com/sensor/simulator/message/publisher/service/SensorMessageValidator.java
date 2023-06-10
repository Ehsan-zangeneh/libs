package com.sensor.simulator.message.publisher.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.sensor.simulator.message.publisher.model.EnrichedSensorMessage;
import com.sensor.simulator.message.publisher.model.SensorPulishableMessage;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Component
@ConfigurationProperties(prefix ="sensor.message")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Slf4j
public class SensorMessageValidator {
	
	double temperatureMin;
	double temperatureMax;
	double oxygenMin;
	double oxygenMax;
	double lightMin;
	double lightMax;

	public EnrichedSensorMessage validate(SensorPulishableMessage sensorMessage) {
		boolean isMessageProblematic = checkForProblematicMetrics(sensorMessage);
		return EnrichedSensorMessage.builder()
				.light(sensorMessage.getLight())
				.temperature(sensorMessage.getTemperature())
				.oxygen(sensorMessage.getOxygen())
				.problematic(isMessageProblematic)
				.build();
	}
	
	private boolean checkForProblematicMetrics(SensorPulishableMessage sensorMessage) {
		if(sensorMessage.getLight() < lightMin || sensorMessage.getLight() > lightMax) {
			log.debug("light must be in the range %s-%s, but it is %s "
					.formatted(lightMin, lightMax, sensorMessage.getLight()));
			return true;
		}
		if(sensorMessage.getOxygen() < oxygenMin || sensorMessage.getOxygen() > oxygenMax) {
			log.debug("oxygen must be in the range %s-%s, but it is %s "
					.formatted(oxygenMin, oxygenMax, sensorMessage.getOxygen()));
			return true;
		}
		if(sensorMessage.getTemperature() < temperatureMin || sensorMessage.getTemperature() > temperatureMax) {
			log.debug("temperature must be in the range %s-%s, but it is %s "
					.formatted(temperatureMin, temperatureMax, sensorMessage.getTemperature()));
			return true;
		}
		return false;
	}
	
}
