package org.madis.iot.veebijuhtimisegaNutikodu.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.madis.iot.veebijuhtimisegaNutikodu.models.Config;

public class BaseService {

	public static final String PERSISTENCE_UNIT = "persistenceUnit";

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();

	private static Config config = new Config();
	private static boolean heaterSwitch = false;
	private static boolean lightSwitch = false;

	public static Config getDbConfig() {
		try {
			Config c = (Config) getEntityManager().createQuery("FROM Config C WHERE C.name = :configName")
					.setParameter("configName", "main").getSingleResult();
			return c;
		} catch (NoResultException e) {
		}
		return null;
	}

	public static void updateDbConfig(Config c) {
		if (c != null) {
			Config dbConfig = null;

			if (getDbConfig() != null) {
				dbConfig = getEntityManager().find(Config.class, getDbConfig().getId());
				getEntityManager().getTransaction().begin();
				dbConfig.setHeaterSwitch(c.getHeaterSwitch());
				dbConfig.setLightSwitch(c.getLightSwitch());
				dbConfig.setStartTime(c.getStartTime());
				dbConfig.setEndTime(c.getEndTime());
				dbConfig.setTemperature(c.getTemperature());
				dbConfig.setLighting(c.getLighting());
				dbConfig.setUpdated(c.getUpdated());
				getEntityManager().getTransaction().commit();
			} else {
				getEntityManager().getTransaction().begin();
				getEntityManager().persist(c);
				getEntityManager().getTransaction().commit();
			}
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}

	public static Config getConfig() {
		return config;
	}

	public static boolean isHeaterSwitch() {
		return heaterSwitch;
	}

	public static void setHeaterSwitch(boolean heaterSwitch) {
		BaseService.heaterSwitch = heaterSwitch;
	}

	public static boolean isLightSwitch() {
		return lightSwitch;
	}

	public static void setLightSwitch(boolean lightSwitch) {
		BaseService.lightSwitch = lightSwitch;
	}

}
