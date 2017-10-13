package org.madis.iot.veebijuhtimisegaNutikodu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.iot.utils.Utils;
import org.madis.iot.veebijuhtimisegaNutikodu.models.SensorData;
import org.madis.iot.veebijuhtimisegaNutikodu.service.SensorDataService;

@WebServlet(
		name="sensorDataServlet",
		description="Servlet for getting existing sensor data",
		urlPatterns={"/nutikodu/sensorDataServlet"}
)
public class SensorDataServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public SensorDataServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		List<SensorData> sensorData = SensorDataService.getSensorData();
		ArrayList<Object> list = new ArrayList<>(sensorData);
		String json = Utils.listToJsonArray(list, "sensorData");
		writer.write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
