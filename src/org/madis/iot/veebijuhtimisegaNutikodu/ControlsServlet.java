package org.madis.iot.veebijuhtimisegaNutikodu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.iot.utils.Utils;
import org.madis.iot.veebijuhtimisegaNutikodu.service.BaseService;

@WebServlet(
		name="controlsServlet",
		description="Getting data for heater status and light status",
		urlPatterns={"/nutikodu/controlsServlet"}
)
public class ControlsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ControlsServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		boolean heaterSwitch = BaseService.isHeaterSwitch();
		boolean lightSwitch = BaseService.isLightSwitch();

		writer.write(Boolean.toString(heaterSwitch) + "," + Boolean.toString(lightSwitch));
		System.out.println(Utils.getCurrentTime() + "[ControlsServlet]   Heater status: " + heaterSwitch + ", light status: " + lightSwitch);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
