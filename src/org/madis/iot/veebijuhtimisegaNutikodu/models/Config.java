package org.madis.iot.veebijuhtimisegaNutikodu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "MAAKODU_ILMAJAAM_CONFIG")
public class Config {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "heater_switch")
	private Boolean heaterSwitch;
	
	@Column(name = "light_switch")
	private Boolean lightSwitch;
	
	@Column(name = "start_time")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime startTime;
	
	@Column(name = "end_time")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime endTime;
	
	@Column(name = "temperature")
	private Double temperature;
	
	@Column(name = "lighting")
	private Double lighting;
	
	@Column(name = "updated")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updated;

	public Config() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getLighting() {
		return lighting;
	}

	public void setLighting(Double lighting) {
		this.lighting = lighting;
	}

	public DateTime getUpdated() {
		return updated;
	}

	public void setUpdated(DateTime updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", name=" + name + ", heaterSwitch=" + heaterSwitch + ", lightSwitch=" + lightSwitch
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", temperature=" + temperature + ", lighting="
				+ lighting + ", updated=" + updated + "]";
	}

}
