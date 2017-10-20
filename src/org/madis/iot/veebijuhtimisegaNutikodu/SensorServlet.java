package org.madis.iot.veebijuhtimisegaNutikodu;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.iot.utils.Utils;
import org.madis.iot.veebijuhtimisegaNutikodu.models.Config;
import org.madis.iot.veebijuhtimisegaNutikodu.models.Constants;
import org.madis.iot.veebijuhtimisegaNutikodu.models.SensorData;
import org.madis.iot.veebijuhtimisegaNutikodu.rules.Controls;
import org.madis.iot.veebijuhtimisegaNutikodu.service.BaseService;
import org.madis.iot.veebijuhtimisegaNutikodu.service.SensorDataService;

@WebServlet(
		name="sensorServlet",
		description="Servlet for posting sensor data",
		urlPatterns={"/nutikodu/sensorServlet"}
)
public class SensorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public SensorServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Config config = BaseService.getConfig();
		HashMap<String, Double> dataMap = Utils.readSensorRequest(request);
		Double temperature = dataMap.get(Constants.TEMPERATURE);
		Double lighting = dataMap.get(Constants.LIGHTING);
		
		SensorData sensorData = new SensorData();
		sensorData.setTemperature(temperature);
		sensorData.setLighting(lighting);
		sensorData.setDateTime(Utils.getCurrentDateTime());
		SensorDataService.insertSensorData(sensorData);
		System.out.println(Utils.getCurrentTime() + " [SensorServlet]   Temperature: " + temperature + ", lighting: " + lighting);
		
		BaseService.setHeaterSwitch(Controls.processTemperatureData(config.getHeaterSwitch(), config.getTemperature(), temperature, config.getStartTime(), config.getEndTime()));
		BaseService.setAutomaticLightSwitch(Controls.processLightingData(config.getLighting(), lighting, config.getStartTime(), config.getEndTime()));
		BaseService.setManualLightSwitch(Controls.processLightingData(config.getLightSwitch()));
	}

}
