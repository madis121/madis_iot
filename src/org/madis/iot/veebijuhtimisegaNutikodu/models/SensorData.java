package org.madis.iot.veebijuhtimisegaNutikodu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@Entity
@Table(name = "MAAKODU_ILMAJAAM_DATA")
public class SensorData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "TEMPERATURE")
	private Double temperature;
	
	@Column(name = "LIGHTING")
	private Double lighting;
	
	@Column(name = "DATETIME")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dateTime;

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

	public DateTime getDateTime() {
		return dateTime.withZone(DateTimeZone.forID(Constants.TIMEZONE_HELSINKI));
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime.withZone(DateTimeZone.forID(Constants.TIMEZONE_HELSINKI));
	}

	@Override
	public String toString() {
		return "SensorData [id=" + id + ", temperature=" + temperature + ", lighting=" + lighting + ", dateTime="
				+ dateTime + "]";
	}

}
