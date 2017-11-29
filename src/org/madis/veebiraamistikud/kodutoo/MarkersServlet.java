package org.madis.veebiraamistikud.kodutoo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.iot.utils.Utils;
import org.madis.veebiraamistikud.kodutoo.models.Marker;
import org.madis.veebiraamistikud.kodutoo.service.MarkerService;

@WebServlet(
		name="markersServlet",
		description="Servlet for posting marker data",
		urlPatterns={"/veebiraamistikud/markersServlet"}
)
public class MarkersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MarkerService markerService = new MarkerService();

	public MarkersServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = Utils.readRequest(request);
		String[] dataSplits = data.split(",");
		Marker marker = new Marker(Double.parseDouble(dataSplits[1]), Double.parseDouble(dataSplits[2]));
		
		if (dataSplits[0].equals("insert")) {
			markerService.insertMarker(marker);
		} else if (dataSplits[0].equals("delete")) {
			markerService.deleteMarker(marker);
		}
	}

}
