package org.madis.iot.contextlistener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.madis.iot.veebijuhtimisega.nutikodu.models.Config;
import org.madis.iot.veebijuhtimisega.nutikodu.service.BaseService;

@WebListener
public class Init implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent e) {
		Config config = BaseService.getConfig();
		Config dbConfig = BaseService.getDbConfig();
		
		if (dbConfig != null) {
			config.setId(dbConfig.getId());
			config.setName(dbConfig.getName());
			config.setHeaterSwitch(dbConfig.getHeaterSwitch());
			config.setLightSwitch(dbConfig.getLightSwitch());
			config.setStartTime(dbConfig.getStartTime());
			config.setEndTime(dbConfig.getEndTime());
			config.setTemperature(dbConfig.getTemperature());
			config.setLighting(dbConfig.getLighting());
			config.setUpdated(dbConfig.getUpdated());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		BaseService.getEntityManager().close();
		BaseService.getEntityManagerFactory().close();
	}

}
