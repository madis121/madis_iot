package org.madis.iot.veebijuhtimisegaNutikodu.rules;

import org.joda.time.DateTime;
import org.madis.iot.utils.Utils;

public class Controls {

	public static boolean processTemperatureData(Boolean heaterSwitch, Double temperatureCheck,
			Double currentTemperature, DateTime startTime, DateTime endTime) {
		DateTime dateTime = Utils.getCurrentDateTime();

		if (startTime != null && endTime != null) {
			if (heaterSwitch) {
				if (temperatureCheck > currentTemperature) {
					if (dateTime.isAfter(startTime) && dateTime.isBefore(endTime)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean processLightingData(Double lightingCheck, Double currentLighting,
			DateTime startTime, DateTime endTime) {
		DateTime dateTime = Utils.getCurrentDateTime();

		if (startTime != null && endTime != null) {
			if (lightingCheck > currentLighting) {
				if (dateTime.isAfter(startTime) && dateTime.isBefore(endTime)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean processLightingData(Boolean lightSwitch) {
		if (lightSwitch) {
			return true;
		}
		return false;
	}

}
