package org.madis.iot.veebijuhtimisegaNutikodu.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MAAKODU_ILMAJAAM_DATA")
public class SensorData {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "TEMPERATURE")
	private Double temperature;
	@Column(name = "LIGHTING")
	private Double lighting;
	@Column(name = "DATETIME")
	private Date dateTime;

	public SensorData() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "SensorData [id=" + id + ", temperature=" + temperature + ", lighting=" + lighting + ", dateTime="
				+ dateTime + "]";
	}

}
