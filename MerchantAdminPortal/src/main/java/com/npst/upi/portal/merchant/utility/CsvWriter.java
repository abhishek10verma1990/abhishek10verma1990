package com.npst.upi.portal.merchant.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvWriter {
	private BufferedWriter writer;
	private CSVPrinter csvPrinter;
	private String filePath;
	

	public String getFilePath() {
		return filePath;
	}

	public CsvWriter(String absoluteCsvFileName) {
		try {
			writer = Files.newBufferedWriter(Paths.get(absoluteCsvFileName));
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
			filePath=absoluteCsvFileName;
		} catch (Exception e) {
			e.printStackTrace();
			filePath=null;
		}
	}

	public CsvWriter(String folder, String csvFileName) {
		try {
			folder = checkAndMkdir(folder);
			filePath = folder +(csvFileName.endsWith(".csv")|| csvFileName.endsWith(".CSV")?csvFileName:csvFileName+".csv");
			writer = Files.newBufferedWriter(Paths.get(filePath));
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
			
		} catch (Exception e) {
			filePath=null;
			e.printStackTrace();
		}
	}

	public void doWrite(List<Object[]> list) throws IOException {
		{
			for (Object[] ob : list) {
				csvPrinter.printRecord(ob);
			}
		}
	}

	public void doWrite(Object[] ob) throws IOException {
		{
			csvPrinter.printRecord(ob);
		}
	}

	public void doFinish() {

		if (csvPrinter != null) {
			try {
				csvPrinter.flush();
				csvPrinter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String checkAndMkdir(String folderName) {
		String path = "temp";
		try {
			if (folderName == null) {
				folderName = path;
			}
			File file = new File(folderName);
			if (file.exists()) {
				path = file.getAbsolutePath();
			} else {
				if (file.mkdirs()) {
					path = file.getAbsolutePath();
				} else {
					new File(path).mkdirs();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return path + File.separator;
	}
}
