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

	private static Config mainConfig = new Config();
	private static boolean heaterSwitch = false;
	private static boolean lightSwitch = false;

	public static Config getConfig() {
		try {
			Config config = (Config) getEntityManager().createQuery("FROM Config C WHERE C.name = :configName")
					.setParameter("configName", "main").getSingleResult();
			return config;
		} catch (NoResultException e) {
		}
		return null;
	}

	public static void updateConfig(Config config) {
		if (config != null) {
			Config configRef = getConfig();
			Config dbConfig = null;

			if (configRef != null) {
				dbConfig = getEntityManager().find(Config.class, getConfig().getId());
			}

			if (dbConfig != null) {
				getEntityManager().getTransaction().begin();
				dbConfig.setHeaterSwitch(config.getHeaterSwitch());
				dbConfig.setLightSwitch(config.getLightSwitch());
				dbConfig.setStartTime(config.getStartTime());
				dbConfig.setEndTime(config.getEndTime());
				dbConfig.setTemperature(config.getTemperature());
				dbConfig.setLighting(config.getLighting());
				dbConfig.setUpdated(config.getUpdated());
				getEntityManager().getTransaction().commit();
			} else {
				getEntityManager().getTransaction().begin();
				getEntityManager().persist(config);
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

	public static Config getMainConfig() {
		return mainConfig;
	}

	public static void setMainConfig(Config mainConfig) {
		BaseService.mainConfig = mainConfig;
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
