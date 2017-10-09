package org.madis.iot.veebijuhtimisegaNutikodu.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MAAKODU_ILMAJAAM_CONFIG")
public class Config {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "HEATER_SWITCH")
	private Boolean heaterSwitch;
	@Column(name = "LIGHT_SWITCH")
	private Boolean lightSwitch;
	@Column(name = "START_TIME")
	private Date startTime;
	@Column(name = "END_TIME")
	private Date endTime;
	@Column(name = "TEMPERATURE")
	private Double temperature;
	@Column(name = "LIGHTING")
	private Double lighting;
	@Column(name = "UPDATED")
	private Date updated;

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", name=" + name + ", heaterSwitch=" + heaterSwitch + ", lightSwitch=" + lightSwitch
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", temperature=" + temperature + ", lighting="
				+ lighting + ", updated=" + updated + "]";
	}

}
