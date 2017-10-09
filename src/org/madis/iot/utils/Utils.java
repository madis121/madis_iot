package org.madis.iot.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.madis.iot.veebijuhtimisegaNutikodu.models.Constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utils {

	public static void appendToFile(String fileName, String fileContent) {
		Writer fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			// fileWriter = new OutputStreamWriter(new FileOutputStream(fileName), UTF-8);	
			// fileWriter = new FileWriter(file.getAbsoluteFile(), true);
			fileWriter = new FileWriter(fileName, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(fileContent + "\n");
			System.out.println("FileUtils.appendToFile() done");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getTimeFromDate(Date date) {
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}
	
	public static String getCurrentTime() {
		return getTimeFromDate(new Date());
	}
	
	public static Date getDateTime(int hours, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object consumeJson(String input, Class clazz) {
		Gson gson = new GsonBuilder().create();
		Object object = gson.fromJson(input, clazz);
		return object;
	}
	
	public static boolean matches(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	public static String readRequest(HttpServletRequest request) {
		String data = null;
		
		if (request != null) {
			try {
				BufferedReader reader = request.getReader();
				StringBuilder stringBuilder = new StringBuilder();
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					stringBuilder.append(line);
				}
				
				String dataReq = stringBuilder.toString();
				
				if (dataReq != null && !dataReq.trim().equals("")) {
					data = dataReq;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public static HashMap<String, Double> readSensorRequest(HttpServletRequest request) {
		HashMap<String, Double> dataMap = new HashMap<>();
		
		if (request != null) {
			try {
				BufferedReader reader = request.getReader();
				StringBuilder stringBuilder = new StringBuilder();
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					stringBuilder.append(line);
				}
				
				String dataReq = stringBuilder.toString();
				String temperature = request.getParameter("temperature");
				String lighting = request.getParameter("lighting");
				
				if (dataReq != null && !dataReq.trim().equals("")) {
					String[] split = dataReq.split(",");
					
					if (split.length == 2) {
						dataMap.put(Constants.TEMPERATURE, Double.parseDouble(split[0]));
						dataMap.put(Constants.LIGHTING, Double.parseDouble(split[1]));
					}
				} else if (temperature != null || lighting != null) {
					if (temperature != null && !temperature.trim().equals("")) {
						dataMap.put(Constants.TEMPERATURE, Double.parseDouble(temperature));
					}
					if (lighting !=  null && !lighting.trim().equals("")) {
						dataMap.put(Constants.LIGHTING, Double.parseDouble(lighting));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dataMap;
	}

}
