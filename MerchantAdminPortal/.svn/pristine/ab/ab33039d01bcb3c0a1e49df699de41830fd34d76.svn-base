package com.npst.upi.portal.merchant.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class UtilityCheck {
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String FILE_HEADER = "Mobile Nos.";

	public static boolean checkDateIsNotEmptyOrNull(Date incomingDate) {
		if (incomingDate != null) {
			return true;
		}
		return false;
	}

	public static boolean checkDateIsNotEmptyOrNull(String incomingDate) {
		if (incomingDate != null) {
			return true;
		}
		return false;
	}

	public static boolean checkStringIsNotEmptyOrNull(String incomingString) {

		if (incomingString != null) {
			incomingString = incomingString.trim();
			if (incomingString != "" && !incomingString.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static String checkUPIProviderListIsActiveOrINActive(Integer incomingStatus) {
		String status = "INACTIVE";
		if (incomingStatus != null && incomingStatus != 0) {
			status = "ACTIVE";
		}
		return status;
	}

	public static boolean writeCSVFile(List<String> negativeMobileNumber,String fileName) {
		FileWriter fileWriter = null;
		boolean flag = false;
		try {
			fileWriter = new FileWriter(fileName+".csv");
			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			// Write a new student object list to the CSV file
			for (String negativeMobNo : negativeMobileNumber) {
				fileWriter.append(negativeMobNo);
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			System.out.println("CSV file was created successfully !!!");
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			return flag;
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
		return flag;
	}
}
