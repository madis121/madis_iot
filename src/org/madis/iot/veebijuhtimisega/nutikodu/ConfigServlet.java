package org.madis.iot.veebijuhtimisega.nutikodu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.iot.utils.Utils;
import org.madis.iot.veebijuhtimisega.nutikodu.models.Config;
import org.madis.iot.veebijuhtimisega.nutikodu.models.ConfigDto;
import org.madis.iot.veebijuhtimisega.nutikodu.service.BaseService;

@WebServlet(
		name="configServlet",
		description="Servlet for posting config",
		urlPatterns={"/nutikodu/configServlet"}
)
public class ConfigServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public ConfigServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = Utils.readRequest(request);
		ConfigDto confDto = (ConfigDto) Utils.consumeJson(data, ConfigDto.class);
		Config config = BaseService.getConfig();
		
		if (config.getName() == null || !config.getName().equals("main")) {
			config.setName(new String("main"));
		}
		config.setHeaterSwitch(confDto.getHeaterSwitch());
		config.setLightSwitch(confDto.getLightSwitch());
		config.setStartTime(confDto.getStartDate());
		config.setEndTime(confDto.getEndDate());
		config.setTemperature(confDto.getTemperatureInt());
		config.setLighting(confDto.getLightingInt());
		config.setUpdated(Utils.getCurrentDateTime());
		BaseService.updateDbConfig(config);
		
		System.out.println(Utils.getCurrentTime() + " [ConfigServlet]   Updated config: " + config.toString());
	}

}
