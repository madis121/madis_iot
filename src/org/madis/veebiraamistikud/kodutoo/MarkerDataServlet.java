package org.madis.veebiraamistikud.kodutoo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.veebiraamistikud.kodutoo.models.Marker;
import org.madis.veebiraamistikud.kodutoo.service.MarkerService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet(
		name="markerDataServlet",
		description="Servlet for getting existing markers data",
		urlPatterns={"/veebiraamistikud/markerDataServlet"}
)
public class MarkerDataServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private MarkerService markerService = new MarkerService();
       
    public MarkerDataServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		List<Marker> markers = markerService.getMarkers();
		Gson gson = new GsonBuilder().create();
		JsonArray jsonArray = gson.toJsonTree(markers).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("markers", jsonArray);
		writer.write(jsonObject.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
