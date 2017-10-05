package org.madis.iot.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileUtils {

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

}
