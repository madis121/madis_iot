package org.madis.iot.veebijuhtimisegaNutikodu.models;

import java.util.Date;

import org.madis.iot.utils.Utils;

public class ConfigDto {

	private Boolean heaterSwitch;
	private Boolean lightSwitch;
	private String startTime;
	private String endTime;
	private String temperature;
	private String lighting;
	
	private String timeRegex = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
	
	public ConfigDto() {

	}

	public Boolean getHeaterSwitch() {
		return heaterSwitch;
	}

	public void setHeaterSwitch(Boolean heaterSwitch) {
		this.heaterSwitch = heaterSwitch;
	}

	public Boolean getLightSwitch() {
		return lightSwitch;
	}

	public void setLightSwitch(Boolean lightSwitch) {
		this.lightSwitch = lightSwitch;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getTemperature() {
		return Integer.parseInt(temperature);
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public Integer getLighting() {
		return Integer.parseInt(lighting);
	}

	public void setLighting(String lighting) {
		this.lighting = lighting;
	}
	
	public Date getStartDate() {
		if (Utils.matches(timeRegex, startTime)) {
			String[] hoursMinutes = startTime.split(":");
			return Utils.getDateTime(Integer.parseInt(hoursMinutes[0]), Integer.parseInt(hoursMinutes[1]));
		}
		return null;
	}
	
	public Date getEndDate() {
		if (Utils.matches(timeRegex, endTime)) {
			String[] hoursMinutes = endTime.split(":");
			return Utils.getDateTime(Integer.parseInt(hoursMinutes[0]), Integer.parseInt(hoursMinutes[1]));
		}
		return null;
	}
	
	public Double getTemperatureInt() {
		if (temperature != null && !temperature.equals("")) {
			return Double.parseDouble(temperature);
		}
		return null;
	}
	
	public Double getLightingInt() {
		if (lighting != null && !lighting.equals("")) {
			return Double.parseDouble(lighting);
		}
		return null;
	}

	@Override
	public String toString() {
		return "Config [heaterSwitch=" + heaterSwitch + ", lightSwitch=" + lightSwitch + ", startTime=" + getStartDate()
				+ ", endTime=" + getEndDate() + ", temperature=" + getTemperatureInt() + ", lighting=" + getLightingInt() + "]";
	}
	
}
