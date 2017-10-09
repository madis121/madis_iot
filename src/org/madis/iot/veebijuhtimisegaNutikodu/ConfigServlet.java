package org.madis.iot.veebijuhtimisegaNutikodu;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.iot.utils.Utils;
import org.madis.iot.veebijuhtimisegaNutikodu.models.Config;
import org.madis.iot.veebijuhtimisegaNutikodu.models.ConfigDto;
import org.madis.iot.veebijuhtimisegaNutikodu.service.BaseService;

@WebServlet(
		name="configServlet",
		description="Servlet for posting config",
		urlPatterns={"/configServlet"}
)
public class ConfigServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public ConfigServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request != null) {
			String data = Utils.readRequest(request);
			ConfigDto confDto = (ConfigDto) Utils.consumeJson(data, ConfigDto.class);
			Config config = BaseService.getMainConfig();
			
			if (config.getName() == null || !config.getName().equals("main")) {
				config.setName(new String("main"));
			}
			config.setHeaterSwitch(confDto.getHeaterSwitch());
			config.setLightSwitch(confDto.getLightSwitch());
			config.setStartTime(confDto.getStartDate());
			config.setEndTime(confDto.getEndDate());
			config.setTemperature(confDto.getTemperatureInt());
			config.setLighting(confDto.getLightingInt());
			config.setUpdated(new Date());
			BaseService.updateConfig(config);
			
			System.out.println("[ConfigServlet] " + Utils.getCurrentTime() + " Updated config: " + config.toString());
		}
	}

}
