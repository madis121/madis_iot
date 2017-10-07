package org.madis.iot.veebijuhtimisegaNutikodu;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.iot.utils.Utils;
import org.madis.iot.veebijuhtimisegaNutikodu.rules.Rules;

@WebServlet(
		name="rulesServlet",
		description="Rules for lighting/temperature rules",
		urlPatterns={"/rulesServlet"}
)
public class RulesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private double sensorInput = 0;
       
    public RulesServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(Boolean.toString(Rules.getLightingRules(sensorInput)) + "," + Rules.getTemperatureRules());
		System.out.println("[rulesServlet] Lightning switch: " + Rules.getLightingRules(sensorInput) + ", " + Utils.getTimeFromDate(new Date()));
		System.out.println("[rulesServlet] Wanted temperature: " + Rules.getTemperatureRules() + ", " + Utils.getTimeFromDate(new Date()));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
