package org.madis.iot.veebijuhtimisegaNutikodu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.iot.utils.Utils;

@WebServlet(
		name="sensorServlet",
		description="Servlet for posting sensor data",
		urlPatterns={"/sensorServlet"}
)
public class SensorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String FILE_DIR = "data.txt";
	
	private ArrayList<String> collectedData = new ArrayList<>();

    public SensorServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request != null) {
			BufferedReader reader = request.getReader();
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			
			String dataReq = stringBuilder.toString();
			String dataParam = request.getParameter("data");
			String data = null;
			
			if (dataReq != null && !dataReq.trim().equals("")) {
				data = dataReq;
			} else if (dataParam != null && !dataParam.trim().equals("")) {
				data = dataParam;
			}
			
			if (data != null) {
				collectedData.add(data);
				System.out.println("[sensorServlet] Pushed: " + data);
				Utils.appendToFile(FILE_DIR, data);
			}
			
			System.out.println("[sensorServlet] Currently stored: " + Arrays.toString(collectedData.toArray()));
		}
	}

}
