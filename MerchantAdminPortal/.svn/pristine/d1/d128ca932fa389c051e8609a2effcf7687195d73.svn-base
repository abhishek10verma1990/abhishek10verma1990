package com.npst.upi.portal.merchant.utility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelReadingForMasterData {

	// public static void main(String[] a) {
	// Integer ignoreColumn = 0;
	// try {
	// Workbook wb = ExcelReadingUtil.excelFileReading("simple.xlsx");
	// System.out.println(wb);
	//
	// Map<String, Set<String>> map = null;
	// map = getSimpleTableMapOfColumnNameAsKeyAndValueAsSets(wb, ignoreColumn);
	// System.out.println("output>>>>>>>>>>>>>>>>>>>>>>>>");
	// System.out.println(map);
	//
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	// }
	// }

	public static Map<String, Set<String>> getSimpleTableMapOfColumnNameAsKeyAndValueAsSets(Workbook wb,
			Integer ignoreColumn) throws Exception {
		Map<String, Set<String>> outMap = new HashMap();
		Map<String, Set<String>> destinationMap = null;
		Sheet sheet = null;
		int i = 0, n = wb.getNumberOfSheets();
		while (i < n) {
			sheet = wb.getSheetAt(i++);
			System.out.println("sheet Name=" + sheet.getSheetName());
			destinationMap = addSimpleTableExcelDataFromSheetToMap(sheet, ignoreColumn);
			System.out.println("destinationMap=>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(destinationMap);
			if (destinationMap != null && destinationMap.size() > 0)
				addInDestinationMap(destinationMap, outMap);
		}
		return outMap;
	}

	public static Map<String, Set<String>> addSimpleTableExcelDataFromSheetToMap(Sheet sheet, Integer ignoreColumn) {
		Map<String, Set<String>> map_destination = new HashMap<>();
		Map<Integer, String> indexAndHeaderName_header = new HashMap<>();
		Map<Integer, String> tempRowCellValue_source = new HashMap<>();

		if (sheet != null) {
			try {
				Row row = null;
				indexAndHeaderName_header = getStringHeader(sheet.getRow(0));
				Iterator<Row> itr_row = sheet.iterator();
				while (itr_row.hasNext()) {
					row = itr_row.next();
					if (row.getRowNum() == 0)
						continue;
					tempRowCellValue_source = getIndexAndStringValue(row);
					if (tempRowCellValue_source == null || tempRowCellValue_source.size() == 0) {
						continue;
					} else {
						addToDestinationMap(tempRowCellValue_source, indexAndHeaderName_header, map_destination);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (map_destination.size() > 0 && ignoreColumn != null && ignoreColumn >= 0) {
			String key = indexAndHeaderName_header.get(ignoreColumn);
			if (key != null)
				map_destination.remove(key);

		}
		return map_destination;
	}

	public static Map<String, Set<String>> getMapOfColumnNameAsKeyAndValueAsSets(Sheet sheet) {
		Map<String, Set<String>> map = new HashMap<>();
		System.out.println("not define");
		if (sheet != null) {

			for (Row row : sheet) {
				if (row == null)
					continue;
				for (Cell cell : row) {
					System.out.println(cell);
				}
			}
		}

		return map;
	}

	public static Map<Integer, String> getIndexAndStringValue(Row row) {
		Map<Integer, String> indexValueMap = new HashMap<>();
		String s = null;
		if (row != null) {
			try {
				System.out.println("row index=" + row.getRowNum());
				for (Cell cell : row) {
					if ((s = getStringIfPresent(ExcelReadingUtil.getObject(cell))) != null) {
						indexValueMap.put(cell.getColumnIndex(), s);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return indexValueMap;
	}

	public static Map<Integer, String> getStringHeader(Row firstRow) throws Exception {
		Map<Integer, String> headerMap = new HashMap<>();
		String s = null;
		if (firstRow != null) {
			System.out.println("row index=" + firstRow.getRowNum());
			if (firstRow.getRowNum() == 0) {
				for (Cell cell : firstRow) {
					if ((s = getStringIfPresent(ExcelReadingUtil.getObject(cell))) != null) {
						headerMap.put(cell.getColumnIndex(), s);
					}
				}
			} else {
				System.out.println("header column name should be 1 row");
			}

		}
		if (headerMap.size() == 0)
			System.out.println("String header not found");
		return headerMap;
	}

	public static String getStringIfPresent(Object ob) {
		String s = null;
		System.out.println("ob=" + ob);
		if (ob instanceof String) {
			s = ob.toString();
		}
		return s;
	}

	private static void addToDestinationMap(Map<Integer, String> sourceMap, Map<Integer, String> headerMap,
			Map<String, Set<String>> destinationMap) {
		Set<String> set = null;
		if (sourceMap != null && sourceMap.size() > 0 && headerMap != null && headerMap.size() > 0
				&& destinationMap != null) {
			for (Map.Entry<Integer, String> entry : sourceMap.entrySet()) {
				String columnName = headerMap.get(entry.getKey());

				if (destinationMap.get(columnName) == null) {
					set = new HashSet<String>(10);
					set.add(entry.getValue());
					destinationMap.put(columnName, set);
				} else {
					destinationMap.get(columnName).add(sourceMap.get(entry.getKey()));
				}
			}
		} else {
			System.out.println("source OR destinationMap empty or null");
		}

	}

	private static void addInDestinationMap(Map<String, Set<String>> sourceMap, Map<String, Set<String>> destinationMap)
			throws Exception {
		Set<String> set = null;
		if (sourceMap != null && sourceMap.size() > 0 && destinationMap != null) {
			for (Map.Entry<String, Set<String>> entry : sourceMap.entrySet()) {
				if (destinationMap.get(entry.getKey()) == null) {
					set = new HashSet<>();
					set.addAll(entry.getValue());
					destinationMap.put(entry.getKey(), set);
				} else {
					destinationMap.get(entry.getKey()).addAll(entry.getValue());
				}

			}
		} else {
			System.out.println("source OR destinationMap empty or null");
		}

	}

}
