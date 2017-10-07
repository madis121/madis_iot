package org.madis.iot.veebijuhtimisegaNutikodu.rules;

import java.util.Date;

import org.madis.iot.utils.Utils;

public class Rules {

	public static String getTemperatureRules() {
		Date date = new Date();
		
		if (date.after(Utils.getDate(15, 0, 0)) && date.before(Utils.getDate(15, 59, 0))) {
			return "18";
		} else if (date.after(Utils.getDate(16, 0, 0)) && date.before(Utils.getDate(16, 59, 0))) {
			return "19";
		}
		return "20";
	}
	
	public static boolean getLightingRules(double sensorInput) {
		Date date = new Date();
		
		if (date.after(Utils.getDate(15, 0, 0)) && date.before(Utils.getDate(15, 59, 0))) {
			if (sensorInput >= 20) {
				return false;
			}
			return true;
		} else if (date.after(Utils.getDate(16, 0, 0)) && date.before(Utils.getDate(16, 59, 0))) {
			if (sensorInput >= 20) {
				return false;
			}
			return true;
		}
		
		return false;
	}
	
}
