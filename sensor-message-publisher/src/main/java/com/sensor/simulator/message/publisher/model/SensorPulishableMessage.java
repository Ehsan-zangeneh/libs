package com.sensor.simulator.message.publisher.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SensorPulishableMessage {
	double temperature;
	double oxygen;
	double light;
}
