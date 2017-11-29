package org.madis.iot.veebijuhtimisega.nutikodu.service;

import java.util.List;

import org.madis.iot.veebijuhtimisega.nutikodu.models.SensorData;

public class SensorDataService extends BaseService {

	@SuppressWarnings("unchecked")
	public static List<SensorData> getSensorData() {
		List<SensorData> list = getEntityManager().createQuery("FROM SensorData").getResultList();
		return list;
	}
	
	public static void insertSensorData(SensorData sensorData) {
		if (sensorData != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(sensorData);
			getEntityManager().getTransaction().commit();
		}
	}
	
}
