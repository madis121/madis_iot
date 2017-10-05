package org.madis.iot;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.madis.iot.utils.FileUtils;

@WebServlet(
		name="testServlet",
		description="Servlet made for testing",
		urlPatterns={"/testServlet"}
)
public class PostServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String FILE_DIR = "data.txt";
	private ArrayList<String> collectedData = new ArrayList<>();

    public PostServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		PrintWriter writer = response.getWriter();
//		writer.append("<html>Test servlet</html>");
//		writer.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ArrayList<String> collectedData = new ArrayList<>();
		BufferedReader reader = request.getReader();
		
		if (request != null) {
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			
			String data = stringBuilder.toString();
			collectedData.add(data);
			System.out.println("Pushed: " + data);
			System.out.println("Currently stored: " + Arrays.toString(collectedData.toArray()));
			
			FileUtils.appendToFile(FILE_DIR, data);
		}
	}

}
