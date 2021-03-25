package com.npst.upi.portal.merchant.utility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelReadingUtil {

	public static Workbook excelFileReading(String xlsFilePath) throws Exception {
		Workbook wb = null;
		try {
			System.out.println(xlsFilePath);
			File f = new File(xlsFilePath);

			if (xlsFilePath != null && f.exists() && f.isFile()) {
				try (InputStream in = new FileInputStream(f);) {
					if (xlsFilePath.endsWith(".xlsx")) {
						wb = new XSSFWorkbook(in); // 2007Impl for .xlsx Excel
					} else if (xlsFilePath.endsWith(".xls")) {
						wb = new HSSFWorkbook(in);// 2003Impl for .xls Excel
					} else {
						System.out.println("this is not a valid .xls or .xlsx file ,ie=" + xlsFilePath);
						throw new Exception("this is not a valid .xls or .xlsx file ,ie=" + xlsFilePath);
					}

				}
			} else {
				System.out.println("invalid file or directory,ie.=" + xlsFilePath);
				throw new Exception("invalid file or directory ,ie.=" + xlsFilePath);
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		System.out.println("workbook=" + wb);
		return wb;
	}

	public static Set<Sheet> excelGetSheets(final String xlsFilePath) throws Exception {
		Set<Sheet> sheetSet = null;
		try {
			Workbook wb = excelFileReading(xlsFilePath);
			if (wb != null) {
				int j = wb.getNumberOfSheets();
				if (wb != null && j > 0) {
					sheetSet = new HashSet<Sheet>();
					for (int i = 0; i < j; i++) {
						sheetSet.add(wb.getSheetAt(i));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return sheetSet;
	}

	public static Set<Sheet> excelGetSheets(final byte[] xlsBytes) throws Exception {
		Set<Sheet> sheetSet = null;
		try {
			Workbook wb = excelFileReading(xlsBytes);
			if (wb != null) {
				int j = wb.getNumberOfSheets();
				if (wb != null && j > 0) {
					sheetSet = new HashSet<Sheet>();
					for (int i = 0; i < j; i++) {
						sheetSet.add(wb.getSheetAt(i));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return sheetSet;
	}

	public static Sheet getFirstSheet(final byte[] byteStream) throws Exception {
		Sheet sheet = null;
		try {
			Workbook wb = excelFileReading(byteStream);
			if (wb != null) {
				Iterator<Sheet> itr = wb.sheetIterator();
				{
					while (itr.hasNext()) {
						sheet = itr.next();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return sheet;
	}

	public static Workbook excelFileReading(final InputStream inputStream) throws Exception {
		Workbook wb = null;
		try {
			System.out.println("try to read using .xlsx");
			wb = new XSSFWorkbook(inputStream); // 2007Impl for .xlsx Excel
		} catch (Exception e) {
			System.out.println("it seems not a .xlsx data or invalid file");
			System.out.println("try to read using .xls");
			try {
				wb = new HSSFWorkbook(inputStream); // 2003Impl for .xls Excel
			} catch (Exception ex) {
				System.out.println("it seems not a .xls data or its not a valid .xls or .xlsx file format");
				e.printStackTrace();
				ex.printStackTrace();
				throw e;
			}
		}
		return wb;

	}

	public static Workbook excelFileReading(final byte[] bytesStream) throws Exception {
		Workbook wb = null;
		try {
			try (InputStream inputStream = new ByteArrayInputStream(bytesStream);) {
				wb = excelFileReading(inputStream);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return wb;

	}

	public static Object getObject(Cell cell) {
		Object ob = null;
		try {
			if (cell != null) {
				System.out.println("column index=" + cell.getColumnIndex());
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					ob = cell.getRichStringCellValue().getString();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					ob = cell.getNumericCellValue();
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					ob = cell.getBooleanCellValue();
					break;
				case Cell.CELL_TYPE_FORMULA:
					ob = cell.getCellFormula();
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				case Cell.CELL_TYPE_ERROR:
					ob = cell.getErrorCellValue();
					break;
				default:
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ob;

	}
}

/*
 * 1. The Apache API Basics There are two main prefixes which you will encounter
 * when working with Apache POI:
 * 
 * HSSF: denotes the API is for working with Excel 2003 and earlier.
 * 
 * XSSF: denotes the API is for working with Excel 2007 and later.
 * 
 * And to get started the Apache POI API, you just need to understand and use
 * the following 4 interfaces:
 * 
 * Workbook: high level representation of an Excel workbook. Concrete
 * implementations are: HSSFWorkbook and XSSFWorkbook. Sheet: high level
 * representation of an Excel worksheet. Typical implementing classes are
 * HSSFSheet and XSSFSheet. Row: high level representation of a row in a
 * spreadsheet. HSSFRow and XSSFRow are two concrete classes. Cell: high level
 * representation of a cell in a row. HSSFCell and XSSFCell are the typical
 * implementing classes.
 */