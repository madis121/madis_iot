package org.madis.iot.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.madis.iot.veebijuhtimisegaNutikodu.models.Constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class Utils {

	public static void appendToFile(String fileName, String fileContent) {
		Writer fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			// fileWriter = new OutputStreamWriter(new
			// FileOutputStream(fileName), UTF-8);
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

	public static String getCurrentTime() {
		DateTime dt = getCurrentDateTime();
		String time = dt.hourOfDay().getAsText() + ":" + dt.minuteOfHour().getAsText() + ":"
				+ dt.secondOfMinute().getAsText();
		return time;
	}

	public static DateTime getCurrentDateTime() {
		return new DateTime(DateTimeZone.forID(Constants.TIMEZONE_HELSINKI));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object consumeJson(String input, Class clazz) {
		Gson gson = new GsonBuilder().create();
		Object object = gson.fromJson(input, clazz);
		return object;
	}

	public static String objectToJson(Object object) {
		Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new JsonSerializer<DateTime>() {
			@Override
			public JsonElement serialize(DateTime json, Type typeOfSrc, JsonSerializationContext context) {
				return new JsonPrimitive(ISODateTimeFormat.dateTime()
						.print(json.withZone(DateTimeZone.forID(Constants.TIMEZONE_HELSINKI))));
			}
		}).create();
		return gson.toJson(object);
	}

	public static String listToJsonArray(ArrayList<Object> list, String arrayName) {
		Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new JsonSerializer<DateTime>() {
			@Override
			public JsonElement serialize(DateTime json, Type typeOfSrc, JsonSerializationContext context) {
				return new JsonPrimitive(ISODateTimeFormat.dateTime()
						.print(json.withZone(DateTimeZone.forID(Constants.TIMEZONE_HELSINKI))));
			}
		}).create();

		JsonArray jsonArray = gson.toJsonTree(list).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add(arrayName, jsonArray);
		return jsonObject.toString();
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
					if (lighting != null && !lighting.trim().equals("")) {
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
