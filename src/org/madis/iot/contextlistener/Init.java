package org.madis.iot.contextlistener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.madis.iot.veebijuhtimisegaNutikodu.models.Config;
import org.madis.iot.veebijuhtimisegaNutikodu.service.BaseService;

@WebListener
public class Init implements ServletContextListener {

	Config config = BaseService.getMainConfig();
	
	@Override
	public void contextInitialized(ServletContextEvent e) {
		Config dbConfig = BaseService.getConfig();
		
		if (dbConfig != null) {
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
