/**
 * 
 */
package com.npst.upi.portal.merchant.core;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.npst.upi.portal.merchant.constant.MerchantRegStatus;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.entity.UploadedFileInfo;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.repo.UploadedFileInfoRepo;
import com.npst.upi.portal.merchant.service.MerchantsService;
import com.npst.upi.portal.merchant.utility.ExcelReadingUtil;
import com.npst.upi.portal.merchant.utility.FileContentType;
import com.npst.upi.portal.merchant.utility.MerchantRegErrorCode;

/**
 * @author Rahul Chaudhary
 *
 */
@Component
public class PersistMerchantRegistrationData implements PersistMerchantRegistrationDataI {

	private static final Logger log = LoggerFactory.getLogger(PersistMerchantRegistrationData.class);
	@Autowired
	private MerchantsRepo merchantsRepo;

	@Autowired
	private MerchantsService merchantsService;

	private static final String MERCHANTS_HEADER_ARR[] = { "MERCHANT_ORG_NAME", "MERCHANT_VPA", "MERCHANT_MOBILE_NO",
			"MERCHANT_ACCOUNT_NO", "MERCHANT_IFSC_CODE", "MANAGER_NAME", "MERCHANT_CALLBACK_URL", "MERCHANT_ADDRESS",
			"Merchant category Code (MCC)", "MERCHANT_PAN_NUMBER", "ORG_CODE", "SETTLEMENT_BANK", "API_BANK", "OPERATOR_CONTACT_NAME",
			"OPERATOR_MOBILE_NUMBER", "OPERATOR_EMAIL", "AADHAR_NUMBER", "DP_CODE" };

	@Autowired
	private UploadedFileInfoRepo uploadedFileInfoRepo;

	@Value("${Vpa_Pattern}")
	private String VPA_REG_EX;

	@Override
	public void persist(byte[] data, UploadedFileInfo uploadedfileInfo) {
		if (uploadedfileInfo.getContentType().matches(FileContentType.CSV.getType())) {
			persistCsvMerchantData(data, uploadedfileInfo);
		} else if (uploadedfileInfo.getContentType().matches(FileContentType.EXCEL.getType())) {
			persistExcelMerchantData(data, uploadedfileInfo);
		} else {
			uploadedfileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
			uploadedfileInfo.setRemarks(MerchantRegStatus.FILE_INVALID.getDescription());
		}
	}

	@Override
	public String fileType() {
		return "";
	}

	private void persistCsvMerchantData(final byte[] byteStream, final UploadedFileInfo fileInfo) {
		log.info("Processing the CSV file ");
		List<CSVRecord> listcsv = new ArrayList<>();
		try {
			fileInfo.setStatus(MerchantRegStatus.FILE_IN_PROCCESS.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_IN_PROCCESS.getDescription());
			log.trace("Merchant file processing {} ", fileInfo);
			uploadedFileInfoRepo.save(fileInfo);
			try (Reader reader = new StringReader(new String(byteStream));
					CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
				listcsv = csvParser.getRecords();
			}
			if (listcsv == null || listcsv.size() < 2) {
				fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
				fileInfo.setRemarks("Invalid file data, data should be present after header");
				uploadedFileInfoRepo.save(fileInfo);
				return;
			} else {
				fileInfo.setTotalCount(listcsv.size() - 1);
			}
			// header at 0 position
			log.info("csv header= {}", listcsv.get(0));
			CSVRecord fileHeaderTemp = listcsv.get(0);
			if (fileHeaderTemp == null || MERCHANTS_HEADER_ARR.length != fileHeaderTemp.size()) {
				fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
				fileInfo.setRemarks("Invalid header length it should be=" + MERCHANTS_HEADER_ARR.length);
				uploadedFileInfoRepo.save(fileInfo);
				return;
			}
			for (int i = 0; i < MERCHANTS_HEADER_ARR.length; i++) {
				if (!MERCHANTS_HEADER_ARR[i].equalsIgnoreCase(fileHeaderTemp.get(i))) {
					fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
					fileInfo.setRemarks("invalid header parameter");
					uploadedFileInfoRepo.save(fileInfo);
					return;
				}
			}
			uploadedFileInfoRepo.save(fileInfo);
			csvDataToDb(listcsv, fileInfo);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("error : {}", e);
			fileInfo.setStatus(MerchantRegStatus.FILE_FAILURE.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_FAILURE.getDescription());
			uploadedFileInfoRepo.save(fileInfo);
		}
	}

	private void persistExcelMerchantData(final byte[] byteStream, final UploadedFileInfo fileInfo) {
		// only for simple and single sheet of .xls or .xlsx work.
		log.info("Processing the .xls file ");
		try {
			fileInfo.setStatus(MerchantRegStatus.FILE_IN_PROCCESS.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_IN_PROCCESS.getDescription());
			uploadedFileInfoRepo.save(fileInfo);
			log.info("Merchant file  saving in db", fileInfo);
			Sheet sheet = ExcelReadingUtil.getFirstSheet(byteStream);
			if (sheet == null || sheet.getPhysicalNumberOfRows() < 2) {
				fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
				fileInfo.setRemarks("Invalid file data,data should be present after header");
				uploadedFileInfoRepo.save(fileInfo);
				log.info("Merchant file saving in db {} ", fileInfo);
				return;
			} else {
				fileInfo.setTotalCount(sheet.getPhysicalNumberOfRows() - 1);
			}
			Row fileHeaderTemp = sheet.getRow(0);
			if (fileHeaderTemp == null || MERCHANTS_HEADER_ARR.length != fileHeaderTemp.getPhysicalNumberOfCells()) {
				fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
				fileInfo.setRemarks("invalid header length it shoul be=" + MERCHANTS_HEADER_ARR.length);
				uploadedFileInfoRepo.save(fileInfo);
				return;
			}
			for (int i = 0; i < MERCHANTS_HEADER_ARR.length; i++) {
				if (!MERCHANTS_HEADER_ARR[i].equalsIgnoreCase(
						fileHeaderTemp.getCell(i) == null ? null : fileHeaderTemp.getCell(i).toString())) {
					fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
					fileInfo.setRemarks("Invalid header parameter");
					uploadedFileInfoRepo.save(fileInfo);
					return;
				}
			}
			uploadedFileInfoRepo.save(fileInfo);
			log.info("Merchant file saved in db {} ", fileInfo);
			excelDataToDb(sheet, fileInfo);
		} catch (IOException e) {
			log.info("Something went worng on Merchant file saved in db {} ", e);
			fileInfo.setStatus(MerchantRegStatus.FILE_FAILURE.getStatus());
			fileInfo.setRemarks("io exception while reading file");
			uploadedFileInfoRepo.save(fileInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Something went worng on Merchant file saved in db {} ", e);
			fileInfo.setStatus(MerchantRegStatus.FILE_FAILURE.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_FAILURE.getDescription());
			uploadedFileInfoRepo.save(fileInfo);
		}
		return;
	}

	private void excelDataToDb(final Sheet sheet, final UploadedFileInfo fileInfo) {

		log.info("Processing Merchant file data {}", fileInfo);
		Set<String> dupl = new HashSet<>();
		int size = sheet.getPhysicalNumberOfRows();
		Row row = null;
		int successCount = 0;
		List<Merchants> todblist = new ArrayList<>();
		int i = 1;
		for (; i < size; i++) {
			try {
				Merchants merchant = new Merchants();
				merchant.setCreatedAt(fileInfo.getCreatedAt());
				merchant.setFileName(fileInfo.getFileName());
				merchant.setUpdatedAt(fileInfo.getUpdatedAt());
				merchant.setMerchantType(fileInfo.getFileName().substring(0, 3));
				row = sheet.getRow(i);
				log.info("Total row file data {}", row);
				mapExcelData(row, merchant);
				merchant.setStatus(MerchantRegStatus.SUCCESS_STATE_1.getStatus());
				if (validate(merchant)) {
					if (!dupl.contains(merchant.getMerchantVPA())) {
						dupl.add(merchant.getMerchantVPA());
						todblist.add(merchant);
						if (todblist.size() == 200) {
							List<Merchants> temp = todblist;
							todblist = new ArrayList<>();
							successCount += merchantsService.insert(temp);
							fileInfo.setSuccessCount(successCount);
							fileInfo.setFailureCount(i - successCount);
							fileInfo.setLinePointer(i);
							uploadedFileInfoRepo.save(fileInfo);
						}
						continue;
					} else {
						merchant.setRemarks("Duplicate VPA");
						merchant.setErrorCode(MerchantRegErrorCode.DUPLICATE_VPA.getCode());
						merchant.setStatus(MerchantRegStatus.FAILED.getStatus());
						merchantsService.insertMerchantErrorLogs(merchant,
								MerchantRegErrorCode.DUPLICATE_VPA.getCode());
					}
				}

			} catch (Exception e) {
				log.info("FileName : {}, error in line number : {}, row : {}", fileInfo.getFileName(), i, row);
				log.info("Error : {}", e);
			}
		}
		fileInfo.setLinePointer(i);
		successCount += merchantsService.insert(todblist);
		fileInfo.setStatus(MerchantRegStatus.FILE_SUCCESSFULLY_PROCESSED.getStatus());
		fileInfo.setRemarks(MerchantRegStatus.FILE_SUCCESSFULLY_PROCESSED.getDescription());
		fileInfo.setSuccessCount(successCount);
		fileInfo.setFailureCount(fileInfo.getTotalCount() - fileInfo.getSuccessCount());
		fileInfo.setUpdatedAt(LocalDateTime.now());
		uploadedFileInfoRepo.save(fileInfo);

	}

	private void csvDataToDb(List<CSVRecord> listcsv, final UploadedFileInfo fileInfo) {
		Set<String> dupl = new HashSet<>();
		int size = listcsv.size();
		CSVRecord row = null;
		int successCount = 0;
		List<Merchants> todblist = new ArrayList<>();
		int i;
		for (i = 1; i < size; i++) {
			try {
				Merchants merchant = new Merchants();
				merchant.setCreatedDate(fileInfo.getCreatedAt());
				merchant.setUpdatedAt(fileInfo.getUpdatedAt());
				merchant.setFileName(fileInfo.getFileName());
				merchant.setStatus(MerchantRegStatus.SUCCESS_STATE_1.getStatus());
				merchant.setMerchantType(fileInfo.getFileName().substring(0, 3));// BQR or UQR
				// merchant.setUniqueMid(Util.generateUniqueMId("CANB"));
				row = listcsv.get(i);
				mapCsvData(row, merchant);
				if (validate(merchant)) {
					if (!dupl.contains(merchant.getMerchantVPA())) {
						dupl.add(merchant.getMerchantVPA());
						todblist.add(merchant);
						if (todblist.size() == 200) {
							List<Merchants> temp = todblist;
							todblist = new ArrayList<>();
							successCount += merchantsService.insert(temp);
							fileInfo.setSuccessCount(successCount);
							fileInfo.setFailureCount(i - successCount);
							fileInfo.setLinePointer(i);
							uploadedFileInfoRepo.save(fileInfo);

						}
						continue;
					} else {
						merchantsService.insertMerchantErrorLogs(merchant,
								MerchantRegErrorCode.DUPLICATE_VPA.getCode());
					}
				}
			} catch (Exception e) {
				log.info("FileName : {}, error in line number : {}, row : {}", fileInfo.getFileName(), i, row);
				log.info("Error : {}", e);
			}
		}
		fileInfo.setLinePointer(i);
		successCount += merchantsService.insert(todblist);
		fileInfo.setStatus(MerchantRegStatus.FILE_SUCCESSFULLY_PROCESSED.getStatus());
		fileInfo.setRemarks(MerchantRegStatus.SUCCESS_MERCHANT.getDescription());
		fileInfo.setSuccessCount(successCount);
		fileInfo.setFailureCount(fileInfo.getTotalCount() - fileInfo.getSuccessCount());
		fileInfo.setUpdatedAt(LocalDateTime.now());
		uploadedFileInfoRepo.save(fileInfo);
	}

	private void mapCsvData(CSVRecord row, Merchants ob) {
		// merchant_org_name
		ob.setMerchantOrgName(row.get(0));
		// merchant_vpa
		ob.setMerchantVPA(row.get(1));
		// merchant_mobile_no
		ob.setMerchantMobileNo(row.get(2));
		// merchant_account_no
		ob.setMerchantAccountNo(row.get(3).trim());
		// merchant_IFSC_code
		ob.setMerchantIFSCCode(row.get(4));
		// manager_name
		ob.setManagerName(row.get(5));
		// merchant_callback_url
		ob.setMerchantCallbackURL(row.get(6));
		// merchant_address
		ob.setMerchantAddress(row.get(7));
		// mcccode
		ob.setMccCode(row.get(8));
		// merchant_pan_number
		ob.setPanCard(row.get(9));
		// org_code
		ob.setOrgCode(row.get(10));
		// settlement_bank
		ob.setSettlementBank(row.get(11));
		// api_bank
		ob.setApiBank(row.get(12));
		// operator_contact_name
		ob.setOperatorContactName(row.get(13));
		// operator_mobile_number
		ob.setOperatorMobileNumber(row.get(14));
		// operator_email
		ob.setOperatorEmail(row.get(15));
		// aadhar_number
		ob.setAadharNumber(row.get(16));
		// branch_code
		ob.setBranchCode(row.get(17));
		ob.setMerchantCode(row.get(8));
		ob.setName(row.get(13));
		ob.setUniqueMid("MER" + row.get(1).substring(0, row.get(1).lastIndexOf("@")));
		ob.setContactNo(row.get(2));
		ob.setMerchantType("UQR");

	}

	private void mapExcelData(Row row, Merchants merchant) {
		// merchant_org_name
		log.info("Total row file data for saving data {}", row);
		if(getString(row.getCell(0)).length()>16) {
			merchant.setMerchantOrgName(getString(row.getCell(0)).substring(0, 15));
		}
		else {
			merchant.setMerchantOrgName(getString(row.getCell(0)));
		}
		// merchant_vpa
		merchant.setMerchantVPA(getString(row.getCell(2))+".timepay@cosmos");
		// merchant_mobile_no
		merchant.setMerchantMobileNo(getString(row.getCell(2)));
		// merchant_account_no
		merchant.setMerchantAccountNo(getString(row.getCell(3)).trim());
		// merchant_IFSC_code
		merchant.setMerchantIFSCCode(getString(row.getCell(4)));
		// manager_name
		merchant.setManagerName(getString(row.getCell(5)));
		// merchant_callback_url
		merchant.setMerchantCallbackURL(getString(row.getCell(6)));
		// merchant_address
		merchant.setMerchantAddress(getString(row.getCell(7)));
		// mcccode
		merchant.setMccCode(getString(row.getCell(8)));
		// merchant_pan_number
		merchant.setPanCard(getString(row.getCell(9)));
		// org_code
		merchant.setOrgCode(getString(row.getCell(10)));
		// settlement_bank
		merchant.setSettlementBank(getString(row.getCell(11)));
		// api_bank
		merchant.setApiBank(getString(row.getCell(12)));
		// operator_contact_name
		merchant.setOperatorContactName(getString(row.getCell(13)));
		// operator_mobile_number
		merchant.setOperatorMobileNumber(getString(row.getCell(14)));
		// operator_email
		merchant.setOperatorEmail(getString(row.getCell(15)));
		// aadhar_number
		merchant.setAadharNumber(getString(row.getCell(16)));
		// branch_code
		merchant.setBranchCode(getString(row.getCell(17)));
		merchant.setActive(true);
		// Merchant code
		merchant.setMerchantCode(getString(row.getCell(8)));
		merchant.setName(getString(row.getCell(13)));
		merchant.setUniqueMid("MER" + merchant.getMerchantVPA().substring(0, merchant.getMerchantVPA().lastIndexOf("@")));
		merchant.setContactNo(getString(row.getCell(2)));
		merchant.setUpdatedAt(merchant.getUpdatedAt());
		merchant.setMerchantType("UQR");
	}

	@SuppressWarnings("deprecation")
	private String getString(Cell cell) {
		String ob = null;
		if (cell != null) {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				ob = cell.getRichStringCellValue().getString();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				ob = new BigDecimal(cell.getNumericCellValue()).toPlainString();
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				ob = String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				ob = String.valueOf(cell.getCellFormula());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				ob = "";
			} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
				ob = String.valueOf(cell.getErrorCellValue());
			}
		}
		return ob;

	}

	private static void trimAll(Merchants merchant) {
		if (merchant != null) {
			if (!StringUtils.isEmpty(merchant.getAadharNumber())) {
				merchant.setAadharNumber(merchant.getAadharNumber().trim());
			}
			if (!StringUtils.isEmpty(merchant.getApiBank())) {
				merchant.setApiBank(merchant.getApiBank().trim());
			}
		/*	if (!StringUtils.isEmpty(merchant.getBranchCode())) {
				merchant.setBranchCode(merchant.getBranchCode().trim());
				if (merchant.getBranchCode().length() < 4) {
					String temp = "0000" + merchant.getBranchCode();
					merchant.setBranchCode(temp.substring(temp.length() - 4));
				}
			}*/

			if (!StringUtils.isEmpty(merchant.getManagerName())) {
				merchant.setManagerName(merchant.getManagerName().trim());
			}
			/*if (!StringUtils.isEmpty(merchant.getMccCode())) {
				merchant.setMccCode(merchant.getMccCode().trim());
				if (merchant.getMccCode().length() < 4) {
					String temp = "0000" + merchant.getMccCode();
					merchant.setMccCode(temp.substring(temp.length() - 4));
				}

			}
			if (!StringUtils.isEmpty(merchant.getMerchantAccountNo())) {
				merchant.setMerchantAccountNo(merchant.getMerchantAccountNo().trim());
				if (merchant.getMerchantAccountNo().length() < 13) {
					String temp = "0000" + merchant.getMerchantAccountNo();
					if (temp.length() >= 13) {
						merchant.setMerchantAccountNo(temp.substring(temp.length() - 13));
					}
				}

			}*/
			if (!StringUtils.isEmpty(merchant.getMerchantAddress())) {
				merchant.setMerchantAddress(merchant.getMerchantAddress().trim());
			}
			if (!StringUtils.isEmpty(merchant.getMerchantCallbackURL())) {
				merchant.setMerchantCallbackURL(merchant.getMerchantCallbackURL().trim());
			}
			if (!StringUtils.isEmpty(merchant.getMerchantIFSCCode())) {
				merchant.setMerchantIFSCCode(merchant.getMerchantIFSCCode().trim().toUpperCase());
			}
			if (!StringUtils.isEmpty(merchant.getMerchantMobileNo())) {
				merchant.setMerchantMobileNo(merchant.getMerchantMobileNo().trim());
			}
			if (!StringUtils.isEmpty(merchant.getMerchantOrgName())) {
				merchant.setMerchantOrgName(merchant.getMerchantOrgName().trim());
			}
			if (!StringUtils.isEmpty(merchant.getMerchantPanNumber())) {
				merchant.setMerchantPanNumber(merchant.getMerchantPanNumber().trim());
			}
			if (!StringUtils.isEmpty(merchant.getMerchantVPA())) {
				merchant.setMerchantVPA(merchant.getMerchantVPA().trim().toLowerCase());
			}
			if (!StringUtils.isEmpty(merchant.getOperatorContactName())) {
				merchant.setOperatorContactName(merchant.getOperatorContactName().trim());
			}
			if (!StringUtils.isEmpty(merchant.getOperatorEmail())) {
				merchant.setOperatorEmail(merchant.getOperatorEmail().trim());
			}
			if (!StringUtils.isEmpty(merchant.getOperatorMobileNumber())) {
				merchant.setOperatorMobileNumber(merchant.getOperatorMobileNumber().trim());
			}
			if (!StringUtils.isEmpty(merchant.getOrgCode())) {
				merchant.setOrgCode(merchant.getOrgCode().trim());
			}
			if (!StringUtils.isEmpty(merchant.getSettlementBank())) {
				merchant.setSettlementBank(merchant.getSettlementBank().trim());
			}
		}

	}

	private boolean validate(Merchants merchant) {
		log.info("starting validate method start for saving DB data",merchant);
		if (merchant == null) {
			return false;
		}
		trimAll(merchant);
		String errorcode = "";
		merchant.setMerchantPanNumber(merchant.getPanCard());
		if (StringUtils.isEmpty(merchant.getMerchantAccountNo())) {
			errorcode = MerchantRegErrorCode.ACC_NO_INVALID.getCode();
		} else if (StringUtils.isEmpty(merchant.getMerchantIFSCCode())
				|| merchant.getMerchantIFSCCode().length() != 11) {
			errorcode = MerchantRegErrorCode.IFSC_CODE_INVALID.getCode();
		} else if (StringUtils.isEmpty(merchant.getMerchantOrgName())) {
			errorcode = MerchantRegErrorCode.ORG_NAME_INVALID.getCode();
		} else if (StringUtils.isEmpty(merchant.getMccCode())) {
			errorcode = MerchantRegErrorCode.MCC_CODE_INVALID.getCode();
		} else if (StringUtils.isEmpty(merchant.getPanCard()) || merchant.getPanCard().length() != 10) {
			merchant.setPanCard("DUMMY1111A");
			merchant.setMerchantPanNumber(merchant.getPanCard());
			// errorcode = MerchantRegErrorCode.PAN_NUMBER_INVALID.getCode();
		} else if (StringUtils.isEmpty(merchant.getMerchantMobileNo())
				|| merchant.getMerchantMobileNo().length() < 10) {
			errorcode = MerchantRegErrorCode.MOBILE_NO_INVALID.getCode();
		} /*else if (StringUtils.isEmpty(merchant.getMerchantVPA())
				|| !Pattern.matches(VPA_REG_EX, merchant.getMerchantVPA())) {
			errorcode = MerchantRegErrorCode.VPA_INVALID.getCode();
		}*/
		log.info("value of error code is",errorcode);
		if (errorcode == null) {
			if (merchantsRepo.countFirstByMerchantVPAAndMerchantType(merchant.getMerchantVPA(),
					merchant.getMerchantType()) > 0) {
				errorcode = MerchantRegErrorCode.DUPLICATE_VPA.getCode();
			}
		}
		if (!StringUtils.isEmpty(errorcode)) {
			merchant.setRemarks(errorcode);
			merchant.setErrorCode(errorcode);
			merchant.setStatus(MerchantRegStatus.FAILED.getStatus());
			merchantsService.insertMerchantErrorLogs(merchant, merchant.getRemarks());
			return false;
		} else {
			return true;
		}

	}

}
