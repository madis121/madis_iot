package org.madis.iot.veebijuhtimisegaNutikodu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.iot.utils.Utils;
import org.madis.iot.veebijuhtimisegaNutikodu.models.Config;
import org.madis.iot.veebijuhtimisegaNutikodu.service.BaseService;

@WebServlet(
		name="configDataServlet",
		description="Servlet for getting existing config data",
		urlPatterns={"/nutikodu/configDataServlet"}
)
public class ConfigDataServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ConfigDataServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Config config = BaseService.getConfig();
		writer.write(Utils.objectToJson(config));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
