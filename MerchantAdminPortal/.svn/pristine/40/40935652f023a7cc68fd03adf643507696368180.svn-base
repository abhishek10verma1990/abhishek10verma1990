package com.npst.upi.portal.merchant.service.impl;

import java.io.InputStream;

import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.npst.upi.portal.merchant.service.ReadExcelDataService;
import com.npst.upi.portal.merchant.utility.ExcelReadingForMasterData;
import com.npst.upi.portal.merchant.utility.ExcelReadingUtil;


@Service
public class ReadExcelDataServiceImpl implements ReadExcelDataService {

	@Override
	public Map<String, Set<String>> getMasterDataFromExcelInputStream(InputStream inputStream, Integer ignoreColumn)
			throws Exception {
		Map<String, Set<String>> outMap = null;
		try {
			System.out.println("going to get workbook");
			Workbook wb = ExcelReadingUtil.excelFileReading(inputStream);
			System.out.println("going to getdata from each sheet in workbook");
			outMap = ExcelReadingForMasterData.getSimpleTableMapOfColumnNameAsKeyAndValueAsSets(wb, ignoreColumn);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return outMap;
	}

}
