package org.madis.iot.veebijuhtimisegaNutikodu.rules;

import java.util.Date;

import org.madis.iot.utils.Utils;

public class Controls {

	public static boolean processTemperatureData(Boolean heaterSwitch, Double temperatureCheck, Double currentTemperature,
			Date startTime, Date endTime) {
		Date date = new Date();
		String[] start = Utils.getTimeFromDate(startTime).split(":");
		String[] end = Utils.getTimeFromDate(endTime).split(":");
		
		if (start != null && end != null) {
			Integer startHours = Integer.parseInt(start[0]);
			Integer startMinutes = Integer.parseInt(start[1]);
			Integer endHours = Integer.parseInt(end[0]);
			Integer endMinutes = Integer.parseInt(end[1]);

			if (heaterSwitch) {
				if (temperatureCheck > currentTemperature) {
					if (date.after(Utils.getDateTime(startHours, startMinutes))
							&& date.before(Utils.getDateTime(endHours, endMinutes))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean processLightingData(Boolean lightSwitch, Double lightingCheck, Double currentLighting,
			Date startTime, Date endTime) {
		Date date = new Date();
		String[] start = Utils.getTimeFromDate(startTime).split(":");
		String[] end = Utils.getTimeFromDate(endTime).split(":");

		if (start != null && end != null) {
			Integer startHours = Integer.parseInt(start[0]);
			Integer startMinutes = Integer.parseInt(start[1]);
			Integer endHours = Integer.parseInt(end[0]);
			Integer endMinutes = Integer.parseInt(end[1]);
			
			if (lightSwitch) {
				if (lightingCheck > currentLighting) {
					if (date.after(Utils.getDateTime(startHours, startMinutes))
							&& date.before(Utils.getDateTime(endHours, endMinutes))) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
