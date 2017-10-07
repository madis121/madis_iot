package org.madis.iot.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	
	public static Date getDate(int hours, int minutes, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);
		return calendar.getTime();
	}
	
	public static Date getDate(int minutes, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

}
