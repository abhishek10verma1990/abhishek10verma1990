/*package com.npst.upi.portal.merchant.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolationException;

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
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.constant.MerchantRegStatus;
import com.npst.upi.portal.merchant.dao.MerchantsService;
import com.npst.upi.portal.merchant.dto.CSVFileLocationDto;
import com.npst.upi.portal.merchant.dto.ValidateMerchantVPADto;
import com.npst.upi.portal.merchant.dto.MerchantsDto;
import com.npst.upi.portal.merchant.exception.ApplicationException;
import com.npst.upi.portal.merchant.model.UploadedFileqqInfo;
import com.npst.upi.portal.merchant.model.Merchants;
import com.npst.upi.portal.merchant.repo.FileInfoRepository;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.service.MerchantonboardingWebService;
import com.npst.upi.portal.merchant.service.MerchantOnboardingService;
import com.npst.upi.portal.merchant.utility.ApplicationConstants;
import com.npst.upi.portal.merchant.utility.ExcelReadingUtil;

@Service
public class MerchantOnboardingServiceImpl implements MerchantOnboardingService {
	private static final Logger log = LoggerFactory.getLogger(MerchantOnboardingServiceImpl.class);
	@Autowired
	MerchantsRepo vpaBulkUploadRepo;
	@Autowired
	private MerchantsService vpaBulkUploadDao;
	@Autowired
	private FileInfoRepository fileInfoRepository;
	@Autowired
	private MerchantonboardingWebService merchantonboardingWebService;
	@Autowired
	private TaskExecutor taskExecutor;
	private static boolean isDirectoryCreated;
	@Autowired
	MerchantsRepo vpaBulkUploadRepository;
	@Value("${MerchantUploadFilePath}")
	private String merchantUploadFilePath;

	@Value("${MerchantBulkFilePrefix}")
	private String merchantBulkFilePrefix;
	@Value("${MerchantBulkUploadFileDatePatternLength10}")
	private String datePatterns;

	@Value("${MerchantBulkFileNamePostfixRegex}")
	private String fileNamePostFixregex;
	private static String[] fileDatePatternArr;
	@Value("${Vpa_Pattern}")
	private String VPA_PATTERN;
	@Value("${IsCurrentMonthTillDateFileAllow}")
	private String isCurrentMonthTillDateFileAllow;
	@Value("${MaxInprocessMerchantFile}")
	private int maxInprocessFile;
	@Value("${SheduleMerchantBUploadAfterNInProcessFile}")
	private int sheduleAfterNInprocessFile;
	
	
	@Value("${MerchantBFileDashboardDetailStatusIn}")
	private String dashboardStatusString;
	Set<Integer> dashboardStatusInSet;
	public final static String merchantHeader[] = { "merchant_org_name", "merchant_vpa", "merchant_mobile_no",
			"merchant_account_no", "merchant_IFSC_code", "manager_name", "merchant_callback_url", "merchant_address",
			"mcccode", "merchant_pan_number", "org_code", "settlement_bank", "api_bank", "operator_contact_name",
			"operator_mobile_number", "operator_email", "aadhar_number", "branch_code" };

	
	@Value("${merchantvpa.length}")
	private String merchantvpaLength;

	@Value("${merchantvpa.invalid}")
	private String merchantvpaInvalid;
	@Value("${merchantvpa.allfield}")
	private String merchantvpaAllField;
	@Value("${merchantvpa.dublicateentry}")
	private String merchantvpaDublicateEntry;
	@Value("${merchantvpa.exist}")
	private String merchantvpaExist;
	@Value("${merchantvpa.maxlength}")
	private int maxLength;
	@Value("${merchantvpa.minlength}")
	private int minLength;
	@Value("${merchantvpa.mobileno}")
	private String merchantMobileNo;
	@Value("${merchantvpa.merchantAccNo}")
	private String merchantAccNo;
	@Value("${merchantvpa.merchantIFSCCode}")
	private String merchantIFSCCode;
	@Value("${merchantvpa.merchantOrgName}")
	private String merchantOrgName;
	@Value("${merchantvpa.mccCode}")
	private String mccCode;
	@Value("${merchantvpa.merchantPanNumber}")
	private String merchantPanNumber;

	@Override
	public UploadedFileqqInfo saveMerchantFile(MultipartFile multipartFile) throws ApplicationException {
		UploadedFileqqInfo fileInfo = null;
		try {
			if (!isDirectoryCreated) {
				checkAndMakeDirs();
			}
			fileInfo = fileInfoRepository.findFirstByFileNameAndServiceType(multipartFile.getOriginalFilename(),
					Constants.MerchantBulkUploadService);
			if (fileInfo != null && fileInfo.getStatus() == MerchantRegStatus.FILE_INVALID.getStatus()) {
				fileInfo.setTotalRecords(0);
				fileInfo.setSuccessRecords(0);
				fileInfo.setFailureRecords(0);
				fileInfo.setRemarks(null);
				fileInfo.setFileSize(null);
				fileInfo.setInsertDate(null);
				fileInfo.setUpdatedDate(null);
			} else {
				fileInfo = new UploadedFileqqInfo();
			}
			fileInfo.setContentType(multipartFile.getContentType());
			fileInfo.setFileLocation(merchantUploadFilePath + File.separator + multipartFile.getOriginalFilename());
			fileInfo.setFileName(multipartFile.getOriginalFilename());
			fileInfo.setStatus(MerchantRegStatus.FILE_NEW_UPLOADED.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_NEW_UPLOADED.getDescription());
			fileInfo.setInsertDate(new Date());
			fileInfo.setServiceType(Constants.MerchantBulkUploadService);
			String sizeStr = geFileSizeStr(multipartFile.getSize());
			fileInfo.setFileSize(sizeStr);
			fileInfoRepository.save(fileInfo);
			Path path = Paths.get(fileInfo.getFileLocation());
			Files.write(path, multipartFile.getBytes());
			System.out.println("file success fully saved" + fileInfo.getFileLocation());
			if (multipartFile.getContentType().matches(Constants.CsvContentTypeRegex)) {
				System.out.println("inside csv");
				csvMerchantFileIntoDb(multipartFile.getBytes(), fileInfo,false);
			} else if (multipartFile.getContentType().matches(Constants.ExcelContentTypeRegex)) {
				System.out.println("inside excel file");
				excelMerchantFileIntoDb(multipartFile.getBytes(), fileInfo,false);
			} else {
				log.info("invalid file content type and extention");
				fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
				fileInfo.setRemarks(MerchantRegStatus.FILE_INVALID.getDescription());
				fileInfoRepository.save(fileInfo);
			}
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			System.out.println("file already exists in database>>>>>>>>>>>>>>>>>>>>");
			e.printStackTrace();
			log.warn("duplicate file" + e.getMessage());
			throw new ApplicationException(ApplicationConstants.DUBLICATE_FILE_PATTERN.getErrorCode(),
					ApplicationConstants.DUBLICATE_FILE_PATTERN.getErrorDetails());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error:" + e);
			fileInfo.setStatus(MerchantRegStatus.FILE_FAILURE.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_FAILURE.getDescription());
			fileInfoRepository.save(fileInfo);
			throw new ApplicationException(ApplicationConstants.APPLICATION_EXCEPTION.getErrorCode(),
					ApplicationConstants.APPLICATION_EXCEPTION.getErrorDetails());
		}
		return fileInfo;
	}

	@Override
	public void merchantBulkFileProcessingFromShedular(UploadedFileqqInfo fileInfo) {
		try {
			Path path = Paths.get(fileInfo.getFileLocation());
			if (fileInfo.getContentType().matches(Constants.CsvContentTypeRegex)) {
				System.out.println("inside csv");
				csvMerchantFileIntoDb(Files.readAllBytes(path), fileInfo,true);
			} else if (fileInfo.getContentType().matches(Constants.ExcelContentTypeRegex)) {
				System.out.println("inside excel file");
				excelMerchantFileIntoDb(Files.readAllBytes(path), fileInfo,true);
			} else {
				log.info("invalid file content type and extention");
				fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
				fileInfo.setRemarks(MerchantRegStatus.FILE_INVALID.getDescription());
				fileInfoRepository.save(fileInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error:" + e);
			fileInfo.setStatus(MerchantRegStatus.FILE_FAILURE.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_FAILURE.getDescription());
			fileInfoRepository.save(fileInfo);
		}
	}

	@Override
	public boolean isValidFileNameAndExt(String fileName) throws ApplicationException {
		boolean f = false;
		if (fileName != null && !fileName.isEmpty() && fileName.length() > merchantBulkFilePrefix.length() + 10) {
			if (fileName.startsWith(merchantBulkFilePrefix)) {
				String tempStr = fileName.substring(merchantBulkFilePrefix.length(),
						merchantBulkFilePrefix.length() + 10);
				LocalDate fileDate = null;
				if (fileDatePatternArr == null) {
					fileDatePatternArr = datePatterns.split("\\|");
				}
				for (int i = 0; i < fileDatePatternArr.length; i++) {
					try {
						fileDate = LocalDate.parse(tempStr, DateTimeFormatter.ofPattern(fileDatePatternArr[i]));
						if (Pattern.matches(fileNamePostFixregex,
								fileName.substring(merchantBulkFilePrefix.length() + 10))) {
							f = true;
							break;
						}
					} catch (Exception e) {
						log.warn("Invalid File Date Formate Name ie=" + fileName + " =>" + e.getMessage() + ",");
						log.info("Try for another date pattern" + fileName + " =>" + e.getMessage() + ",");

					}
				}
				LocalDate currentMonth = LocalDate.now();
				if (f && currentMonth.compareTo(fileDate) >= 0) {
					f = true;
					if ("YES".equalsIgnoreCase(isCurrentMonthTillDateFileAllow)) {
						f = currentMonth.getYear() == fileDate.getYear()
								&& currentMonth.getMonthValue() == fileDate.getMonthValue();
					}
				} else {
					f = false;
				}
			}
		}
		return f;
	}

	@Override
	public boolean validateVPA(String merchantVpa) throws ApplicationException {
		return merchantVpa != null && merchantVpa.length() > 8 && Pattern.matches(VPA_PATTERN, merchantVpa);
	}
	@Override
	public boolean isAddFileInNextShedule() {
		return fileInfoRepository.countByStatusAndServiceType(MerchantRegStatus.FILE_IN_PROCCESS.getStatus(),
				Constants.MerchantBulkUploadService) > sheduleAfterNInprocessFile;
	}
	@Override
	public boolean isVpaAlreadyInMerchant(String merchantVPA) throws ApplicationException {
	return vpaBulkUploadRepo.countFirstByMerchantVPA(merchantVPA)>0;
	}

	@Override
	public List<CSVFileLocationDto> getAllFiles() throws ApplicationException {

		if (dashboardStatusInSet == null) {
			String[] arr = dashboardStatusString.split(",");
			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < arr.length; i++) {
				set.add(Integer.valueOf(arr[i]));
			}
			dashboardStatusInSet=set;
		}
		List<CSVFileLocationDto> outList = new ArrayList<CSVFileLocationDto>();
		List<UploadedFileqqInfo> resultList = fileInfoRepository
				.getMFileDashboardDetails(Constants.MerchantBulkUploadService, dashboardStatusInSet);
		if (resultList.size() > 0) {
			CSVFileLocationDto out = null;
			for (UploadedFileqqInfo fileInfo : resultList) {
				out = new CSVFileLocationDto();
				out.setRemarks(fileInfo.getRemarks());
				out.setTotalRecord(fileInfo.getTotalRecords());
				out.setFailureRecord(fileInfo.getFailureRecords());
				out.setSuccessRecord(fileInfo.getSuccessRecords());
				out.setCreatedtime(fileInfo.getInsertDate() + "");
				out.setFilename(fileInfo.getFileName());
				out.setId(fileInfo.getFileId());
				out.setStatus(fileInfo.getStatus());
				outList.add(out);
				out = null;

			}
		}
		return outList;
	}

	@Override
	public boolean saveSingleVPA(MerchantsDto vpaBulkUploadDto) throws ApplicationException {
		Merchants vpaBulkUpload = new Merchants();
		vpaBulkUpload.setMerchantOrgName(vpaBulkUploadDto.getMerchantOrgName());
		vpaBulkUpload.setMerchantVPA(vpaBulkUploadDto.getMerchantVPA());
		vpaBulkUpload.setMerchantMobileNo(vpaBulkUploadDto.getMerchantMobileNo());
		vpaBulkUpload.setMerchantAccountNo(vpaBulkUploadDto.getMerchantAccountNo());
		vpaBulkUpload.setMerchantIFSCCode(vpaBulkUploadDto.getMerchantIFSCCode());
		vpaBulkUpload.setCreatedDate(new Date());
		vpaBulkUpload.setManagerName(vpaBulkUploadDto.getManagerName());
		vpaBulkUpload.setMerchantCallbackURL(vpaBulkUploadDto.getMerchantCallbackURL());
		vpaBulkUpload.setMerchantAddress(vpaBulkUploadDto.getMerchantAddress());
		vpaBulkUpload.setMccCode(vpaBulkUploadDto.getMccCode());
		vpaBulkUpload.setMerchantPanNumber(vpaBulkUploadDto.getMerchantPanNumber());
		vpaBulkUpload.setOrgCode(vpaBulkUploadDto.getOrgCode());
		vpaBulkUpload.setSettlementBank(vpaBulkUploadDto.getSettlementBank());
		vpaBulkUpload.setApiBank(vpaBulkUploadDto.getApiBank());
		vpaBulkUpload.setOperatorContactName(vpaBulkUploadDto.getOperatorContactName());
		vpaBulkUpload.setOperatorMobileNumber(vpaBulkUploadDto.getOperatorMobileNumber());
		vpaBulkUpload.setOperatorEmail(vpaBulkUploadDto.getOperatorEmail());
		vpaBulkUpload.setAadharNumber(vpaBulkUploadDto.getAadharNumber());
		vpaBulkUpload.setBranchCode(vpaBulkUploadDto.getBranchCode());
		if (validate(vpaBulkUpload)) {
			if (vpaBulkUploadDao.insert(vpaBulkUpload)) {
				new Thread(() -> {
					merchantonboardingWebService.createSingleMerchantOnboarding(vpaBulkUpload, false);
				});
				return true;
			} else {
				return false;
			}
		} else {
			throw new ApplicationException(ApplicationConstants.INVALID_DATA.getErrorCode(), vpaBulkUpload.getRemark());
		}
	}

	@Override
	public MerchantsDto getSingleVPA(ValidateMerchantVPADto validateMerchantVPADto) throws ApplicationException {
		MerchantsDto vpaBulkUploadDto = null;
		Merchants vpaBulkUpload = vpaBulkUploadRepository
				.findByMerchantVPA(validateMerchantVPADto.getMerchantvpa().toLowerCase().trim());
		if (vpaBulkUpload != null) {
			vpaBulkUploadDto = new MerchantsDto();
			vpaBulkUploadDto.setMerchantOrgName(vpaBulkUpload.getMerchantOrgName());
			vpaBulkUploadDto.setMerchantVPA(vpaBulkUpload.getMerchantVPA());
			vpaBulkUploadDto.setMerchantMobileNo(vpaBulkUpload.getMerchantMobileNo());
			vpaBulkUploadDto.setMerchantAccountNo(vpaBulkUpload.getMerchantAccountNo());
			vpaBulkUploadDto.setMerchantIFSCCode(vpaBulkUpload.getMerchantIFSCCode());
			vpaBulkUploadDto.setCreatedDate(vpaBulkUpload.getCreatedDate());
			vpaBulkUploadDto.setManagerName(vpaBulkUpload.getManagerName());
			vpaBulkUploadDto.setMerchantCallbackURL(vpaBulkUpload.getMerchantCallbackURL());
			vpaBulkUploadDto.setMerchantAddress(vpaBulkUpload.getMerchantAddress());
			vpaBulkUploadDto.setMccCode(vpaBulkUpload.getMccCode());
			vpaBulkUploadDto.setMerchantPanNumber(vpaBulkUpload.getMerchantPanNumber());
			vpaBulkUploadDto.setOrgCode(vpaBulkUpload.getOrgCode());
			vpaBulkUploadDto.setSettlementBank(vpaBulkUpload.getSettlementBank());
			vpaBulkUploadDto.setApiBank(vpaBulkUpload.getApiBank());
			vpaBulkUploadDto.setOperatorContactName(vpaBulkUpload.getOperatorContactName());
			vpaBulkUploadDto.setOperatorMobileNumber(vpaBulkUpload.getOperatorMobileNumber());
			vpaBulkUploadDto.setOperatorEmail(vpaBulkUpload.getOperatorEmail());
			vpaBulkUploadDto.setAadharNumber(vpaBulkUpload.getAadharNumber());
			vpaBulkUploadDto.setBranchCode(vpaBulkUpload.getBranchCode());
			return vpaBulkUploadDto;
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validate(Merchants vpaBulkUpload) {
		if (vpaBulkUpload == null) {
			return false;
		}
		trimAll(vpaBulkUpload);
		String error = null;
		if (vpaBulkUpload.getMerchantAccountNo() == null
				|| vpaBulkUpload.getMerchantAccountNo().equalsIgnoreCase("null")
				|| vpaBulkUpload.getMerchantAccountNo().isEmpty()
				|| vpaBulkUpload.getMerchantAccountNo().length() != 13) {
			error = merchantAccNo;
		} else if (vpaBulkUpload.getMerchantIFSCCode() == null
				|| vpaBulkUpload.getMerchantIFSCCode().equalsIgnoreCase("null")
				|| vpaBulkUpload.getMerchantIFSCCode().isEmpty()
				|| vpaBulkUpload.getMerchantIFSCCode().length() != 11) {
			error = merchantIFSCCode;
		} else if (vpaBulkUpload.getMerchantOrgName() == null
				|| vpaBulkUpload.getMerchantOrgName().equalsIgnoreCase("null")
				|| vpaBulkUpload.getMerchantOrgName().isEmpty()) {
			error = merchantOrgName;
		} else if (vpaBulkUpload.getMccCode() == null || vpaBulkUpload.getMccCode().equalsIgnoreCase("null")
				|| vpaBulkUpload.getMccCode().isEmpty()) {
			error = mccCode;
		} else if (vpaBulkUpload.getMerchantPanNumber() == null
				|| vpaBulkUpload.getMerchantPanNumber().equalsIgnoreCase("null")
				|| vpaBulkUpload.getMerchantPanNumber().isEmpty()
				|| vpaBulkUpload.getMerchantPanNumber().length() != 10) {
			error = merchantPanNumber;
		} else if (vpaBulkUpload.getMerchantMobileNo() == null || vpaBulkUpload.getMerchantMobileNo().isEmpty()
				|| vpaBulkUpload.getMerchantMobileNo().length() < 10) {
			error = merchantMobileNo;
		} else if (vpaBulkUpload.getMerchantVPA().length() > maxLength
				|| vpaBulkUpload.getMerchantVPA().length() < minLength) {
			error = merchantvpaLength;

		} else if (vpaBulkUpload.getMerchantVPA() == null || vpaBulkUpload.getMerchantVPA().trim().isEmpty()
				|| vpaBulkUpload.getMerchantVPA().length() < 8
				|| !Pattern.matches(VPA_PATTERN, vpaBulkUpload.getMerchantVPA())) {
			error = merchantvpaInvalid;
		}
		if (error == null) {
			if (vpaBulkUploadRepo.countFirstByMerchantVPAAndMerchantType(vpaBulkUpload.getMerchantVPA(), vpaBulkUpload.getMerchantType()) > 0) {
				error = "VPA already exists in database; it should be unique";
			}
		}
		if (error != null) {
			vpaBulkUpload.setRemark(error);
			// vpaBulkUploadDao.insertMerchantErrorLogs(vpaBulkUpload,error);
			taskExecutor.execute(() -> {
				vpaBulkUploadDao.insertMerchantErrorLogs(vpaBulkUpload, vpaBulkUpload.getRemark());
			});
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean isValidFileAlreadyInDb(String fileName, int serviceType) {
		UploadedFileqqInfo fileInformation = fileInfoRepository.findFirstByFileNameAndServiceType(fileName, serviceType);
		if (fileInformation != null && fileInformation.getStatus() != MerchantRegStatus.FILE_INVALID.getStatus()) {
			return true;
		}
		return false;
	}

	private void csvMerchantFileIntoDb(final byte[] byteStream, final UploadedFileqqInfo fileInfo,boolean isReqFromShedular) {
		List<CSVRecord> listcsv = new ArrayList<>();
		try {
			fileInfo.setStatus(MerchantRegStatus.FILE_IN_PROCCESS.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_IN_PROCCESS.getDescription());
			fileInfoRepository.save(fileInfo);
			try (Reader reader = new StringReader(new String(byteStream));
					CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
				listcsv = csvParser.getRecords();
			}
			if (listcsv == null || listcsv.size() < 2) {
				fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
				fileInfo.setRemarks("invalid file data,data should be present after header");
				fileInfoRepository.save(fileInfo);
				return;
			} else {
				fileInfo.setTotalRecords(listcsv.size() - 1);
			}

			// header at 0 position
			log.info("csv header=" + listcsv.get(0));
			CSVRecord fileHeaderTemp = listcsv.get(0);
			if (fileHeaderTemp == null || merchantHeader.length != fileHeaderTemp.size()) {
				fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
				fileInfo.setRemarks("invalid header length it shoul be=" + merchantHeader.length);
				fileInfoRepository.save(fileInfo);
				return;
			}
			for (int i = 0; i < merchantHeader.length; i++) {
				if (!merchantHeader[i].equalsIgnoreCase(fileHeaderTemp.get(i))) {
					fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
					fileInfo.setRemarks("invalid header parameter");
					fileInfoRepository.save(fileInfo);
					return;
				}
			}
			if (!isReqFromShedular && isAddFileInNextShedule()) {
				fileInfo.setStatus(MerchantRegStatus.FILE_SHEDULED_FOR_NEXT_PROCESS.getStatus());
				fileInfo.setRemarks(MerchantRegStatus.FILE_SHEDULED_FOR_NEXT_PROCESS.getDescription());
				fileInfoRepository.save(fileInfo);
				return;
			}
			fileInfoRepository.save(fileInfo);
			if(!isReqFromShedular){
				List<CSVRecord> temp = listcsv;
				taskExecutor.execute(()->{csvDataToDb(temp, fileInfo);});
			}else{
				csvDataToDb(listcsv, fileInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("error:" + e);
			fileInfo.setStatus(MerchantRegStatus.FILE_FAILURE.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_FAILURE.getDescription());
			fileInfoRepository.save(fileInfo);
		}
	}

	private void excelMerchantFileIntoDb(final byte[] byteStream, final UploadedFileqqInfo fileInfo,boolean isReqFromShedular) {
		// only for simple and single sheet of .xls or .xlsx work.
		try {
			fileInfo.setStatus(MerchantRegStatus.FILE_IN_PROCCESS.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_IN_PROCCESS.getDescription());
			fileInfoRepository.save(fileInfo);
			Sheet sheet = ExcelReadingUtil.getFirstSheet(byteStream);
			if (sheet == null || sheet.getPhysicalNumberOfRows() < 2) {
				fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
				fileInfo.setRemarks("invalid file data,data should be present after header");
				fileInfoRepository.save(fileInfo);
				return;
			} else {
				fileInfo.setTotalRecords(sheet.getPhysicalNumberOfRows() - 1);
			}
			Row fileHeaderTemp = sheet.getRow(0);
			log.info("excel header=" + fileHeaderTemp);
			if (fileHeaderTemp == null || merchantHeader.length != fileHeaderTemp.getPhysicalNumberOfCells()) {
				fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
				fileInfo.setRemarks("invalid header length it shoul be=" + merchantHeader.length);
				fileInfoRepository.save(fileInfo);
				return;
			}
			for (int i = 0; i < merchantHeader.length; i++) {
				if (!merchantHeader[i].equalsIgnoreCase(
						fileHeaderTemp.getCell(i) == null ? null : fileHeaderTemp.getCell(i).toString())) {
					fileInfo.setStatus(MerchantRegStatus.FILE_INVALID.getStatus());
					fileInfo.setRemarks("invalid header parameter");
					fileInfoRepository.save(fileInfo);
					return;
				}
			}
			if (!isReqFromShedular && isAddFileInNextShedule()) {
				fileInfo.setStatus(MerchantRegStatus.FILE_SHEDULED_FOR_NEXT_PROCESS.getStatus());
				fileInfo.setRemarks(MerchantRegStatus.FILE_SHEDULED_FOR_NEXT_PROCESS.getDescription());
				fileInfoRepository.save(fileInfo);
				return;
			}
			fileInfoRepository.save(fileInfo);
			if (!isReqFromShedular) {
				taskExecutor.execute(()->{excelDataToDb(sheet, fileInfo);});
			}else{
				excelDataToDb(sheet, fileInfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.info("error:" + e);
			fileInfo.setStatus(MerchantRegStatus.FILE_FAILURE.getStatus());
			fileInfo.setRemarks("io exception while reading file");
			fileInfoRepository.save(fileInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("error:" + e);
			fileInfo.setStatus(MerchantRegStatus.FILE_FAILURE.getStatus());
			fileInfo.setRemarks(MerchantRegStatus.FILE_FAILURE.getDescription());
			fileInfoRepository.save(fileInfo);
		}
		return;
	}

	@Override
	public Boolean compareFileContents(String filePath) throws ApplicationException {
		// not yet implemeted
		return null;
	}

	@Override
	public boolean isBusyInProcessing() {
		if (fileInfoRepository.countByStatusAndServiceType(MerchantRegStatus.FILE_IN_PROCCESS.getStatus(),
				Constants.MerchantBulkUploadService) > maxInprocessFile) {
			return true;
		} else
			return false;
	}

	private void excelDataToDb(final Sheet sheet, final UploadedFileqqInfo fileInfo) {
		Set<String> dupl = new HashSet<String>();
		int size = sheet.getPhysicalNumberOfRows();
		Row row = null;
		int successCount = 0;
		// need ExecutorService to insert db
		List<Merchants> todblist = new ArrayList<Merchants>();
		int i = 1;
		for (; i < size; i++) {
			try {
				Merchants ob = new Merchants();
				ob.setCreatedDate(fileInfo.getInsertDate());
				ob.setCsvFileName(fileInfo.getFileName());
				ob.setStatus(MerchantRegStatus.NEW_MERCHANT.getStatus());
				ob.setMerchantType(fileInfo.getFileName().substring(0, 3));
				ob.setState("1");
				row = sheet.getRow(i);
				setExcelData(row, ob);
				if (validate(ob)) {
					if (!dupl.contains(ob.getMerchantVPA())) {
						dupl.add(ob.getMerchantVPA());
						todblist.add(ob);
						if(todblist.size()==200){
							List<Merchants> temp=todblist;
						    todblist = new ArrayList<Merchants>();
							successCount+= vpaBulkUploadDao.insert(temp);
							fileInfo.setSuccessRecords(successCount);
							fileInfo.setFailureRecords(i-successCount);
							fileInfo.setLinePointer(i);
							fileInfoRepository.save(fileInfo);
						}
						continue;
					} else {
						ob.setRemark("duplicate vpa");
						// vpaBulkUploadDao.insertMerchantErrorLogs(ob, ob.getRemark());
						taskExecutor.execute(() -> {
							vpaBulkUploadDao.insertMerchantErrorLogs(ob, ob.getRemark());
						});
					}
				}

			} catch (Exception e) {
				log.info("fileName" + fileInfo.getFileName() + " ,error in line number=" + i + "row=" + row);
				log.info("error:" + e);
			}
		}
		fileInfo.setLinePointer(i);
		successCount+= vpaBulkUploadDao.insert(todblist);
		fileInfo.setStatus(MerchantRegStatus.FILE_SUCCESSFULLY_PROCESSED.getStatus());
		fileInfo.setRemarks(MerchantRegStatus.FILE_SUCCESSFULLY_PROCESSED.getDescription());
		fileInfo.setSuccessRecords(successCount);
		fileInfo.setFailureRecords(fileInfo.getTotalRecords() - fileInfo.getSuccessRecords());
		fileInfo.setUpdatedDate(new Date());
		fileInfoRepository.save(fileInfo);

	}

	private void csvDataToDb(List<CSVRecord> listcsv, final UploadedFileqqInfo fileInfo) {
		Set<String> dupl = new HashSet<String>();
		int size = listcsv.size();
		CSVRecord row = null;
		// need ExecutorService to insert db
		int successCount = 0;
		List<Merchants> todblist = new ArrayList<Merchants>();
		int i = 1;
		for (; i < size; i++) {
			try {
				Merchants ob = new Merchants();
				ob.setCreatedDate(fileInfo.getInsertDate());
				ob.setCsvFileName(fileInfo.getFileName());
				ob.setStatus(MerchantRegStatus.NEW_MERCHANT.getStatus());
				row = listcsv.get(i);
				setCsvData(row, ob);
				if (validate(ob)) {
					if (!dupl.contains(ob.getMerchantVPA())) {
						dupl.add(ob.getMerchantVPA());
						todblist.add(ob);
						if(todblist.size()==200){
							List<Merchants> temp=todblist;
							List<Merchants> upiswirch = temp;
						    todblist = new ArrayList<Merchants>();
							successCount+= vpaBulkUploadDao.insert(temp);
							fileInfo.setSuccessRecords(successCount);
							fileInfo.setFailureRecords(i-successCount);
							fileInfo.setLinePointer(i);
							fileInfoRepository.save(fileInfo);
							
							for(VpaBulkUpload vpaBulkUpload : upiswirch) {
								merchantonboardingWebService.createSingleMerchantOnboarding(vpaBulkUpload, false);
							}
							
						}
						continue;
					} else {
						ob.setRemark("duplicate vpa");
						// vpaBulkUploadDao.insertMerchantErrorLogs(ob,
						// ob.getRemark());
						taskExecutor.execute(() -> {
							vpaBulkUploadDao.insertMerchantErrorLogs(ob, ob.getRemark());
						});
					}
				}
			} catch (Exception e) {
				log.info("fileName" + fileInfo.getFileName() + " ,error in line number=" + i + "row =" + row);
				log.info("error:" + e);
			}
		}
		fileInfo.setLinePointer(i);
		successCount+= vpaBulkUploadDao.insert(todblist);
		fileInfo.setStatus(MerchantRegStatus.FILE_SUCCESSFULLY_PROCESSED.getStatus());
		fileInfo.setRemarks(MerchantRegStatus.FILE_SUCCESSFULLY_PROCESSED.getDescription());
		fileInfo.setSuccessRecords(successCount);
		fileInfo.setFailureRecords(fileInfo.getTotalRecords() - fileInfo.getSuccessRecords());
		fileInfo.setUpdatedDate(new Date());
		fileInfoRepository.save(fileInfo);
	}

	private void setCsvData(CSVRecord row, Merchants ob) {
		// merchant_org_name
		ob.setMerchantOrgName(row.get(0));
		// merchant_vpa
		ob.setMerchantVPA(row.get(1));
		// merchant_mobile_no
		ob.setMerchantMobileNo(row.get(2));
		// merchant_account_no
		ob.setMerchantAccountNo(row.get(3));
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
		ob.setMerchantPanNumber(row.get(9));
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
	}

	private void setExcelData(Row row, Merchants ob) {
		// merchant_org_name
		ob.setMerchantOrgName(getString(row.getCell(0)));
		// merchant_vpa
		ob.setMerchantVPA(getString(row.getCell(1)));
		// merchant_mobile_no
		ob.setMerchantMobileNo(getString(row.getCell(2)));
		// merchant_account_no
		ob.setMerchantAccountNo(getString(row.getCell(3)));
		// merchant_IFSC_code
		ob.setMerchantIFSCCode(getString(row.getCell(4)));
		// manager_name
		ob.setManagerName(getString(row.getCell(5)));
		// merchant_callback_url
		ob.setMerchantCallbackURL(getString(row.getCell(6)));
		// merchant_address
		ob.setMerchantAddress(getString(row.getCell(7)));
		// mcccode
		ob.setMccCode(getString(row.getCell(8)));
		// merchant_pan_number
		ob.setMerchantPanNumber(getString(row.getCell(9)));
		// org_code
		ob.setOrgCode(getString(row.getCell(10)));
		// settlement_bank
		ob.setSettlementBank(getString(row.getCell(11)));
		// api_bank
		ob.setApiBank(getString(row.getCell(12)));
		// operator_contact_name
		ob.setOperatorContactName(getString(row.getCell(13)));
		// operator_mobile_number
		ob.setOperatorMobileNumber(getString(row.getCell(14)));
		// operator_email
		ob.setOperatorEmail(getString(row.getCell(15)));
		// aadhar_number
		ob.setAadharNumber(getString(row.getCell(16)));
		// branch_code
		ob.setBranchCode(getString(row.getCell(17)));
	}

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

	private static void trimAll(Merchants vpaBulkUpload) {
		String temp = null;
		if (vpaBulkUpload != null) {
			if (vpaBulkUpload.getAadharNumber() != null) {
				vpaBulkUpload.setAadharNumber(vpaBulkUpload.getAadharNumber().trim());
			}
			if (vpaBulkUpload.getApiBank() != null) {
				vpaBulkUpload.setApiBank(vpaBulkUpload.getApiBank().trim());
			}
			if (vpaBulkUpload.getBranchCode() != null) {
				vpaBulkUpload.setBranchCode(vpaBulkUpload.getBranchCode().trim());
				if (vpaBulkUpload.getBranchCode().length() < 4) {
					temp = "0000" + vpaBulkUpload.getBranchCode();
					vpaBulkUpload.setBranchCode(temp.substring(temp.length() - 4));

				}
			}

			if (vpaBulkUpload.getManagerName() != null) {
				vpaBulkUpload.setManagerName(vpaBulkUpload.getManagerName().trim());
			}
			if (vpaBulkUpload.getMccCode() != null) {
				vpaBulkUpload.setMccCode(vpaBulkUpload.getMccCode().trim());
				if (vpaBulkUpload.getMccCode().length() < 4) {
					temp = "0000" + vpaBulkUpload.getMccCode();
					vpaBulkUpload.setMccCode(temp.substring(temp.length() - 4));
				}

			}
			if (vpaBulkUpload.getMerchantAccountNo() != null) {
				vpaBulkUpload.setMerchantAccountNo(vpaBulkUpload.getMerchantAccountNo().trim());
				if (vpaBulkUpload.getMerchantAccountNo().length() < 13) {
					temp = "0000" + vpaBulkUpload.getMerchantAccountNo();
					if (temp.length() >= 13) {
						vpaBulkUpload.setMerchantAccountNo(temp.substring(temp.length() - 13));
					}
				}

			}
			if (vpaBulkUpload.getMerchantAddress() != null) {
				vpaBulkUpload.setMerchantAddress(vpaBulkUpload.getMerchantAddress().trim());
			}
			if (vpaBulkUpload.getMerchantCallbackURL() != null) {
				vpaBulkUpload.setMerchantCallbackURL(vpaBulkUpload.getMerchantCallbackURL().trim());
			}
			if (vpaBulkUpload.getMerchantIFSCCode() != null) {
				vpaBulkUpload.setMerchantIFSCCode(vpaBulkUpload.getMerchantIFSCCode().trim().toUpperCase());
			}
			if (vpaBulkUpload.getMerchantMobileNo() != null) {
				vpaBulkUpload.setMerchantMobileNo(vpaBulkUpload.getMerchantMobileNo().trim());
			}
			if (vpaBulkUpload.getMerchantOrgName() != null) {
				vpaBulkUpload.setMerchantOrgName(vpaBulkUpload.getMerchantOrgName().trim());
			}
			if (vpaBulkUpload.getMerchantPanNumber() != null) {
				vpaBulkUpload.setMerchantPanNumber(vpaBulkUpload.getMerchantPanNumber().trim());
			}
			if (vpaBulkUpload.getMerchantVPA() != null) {
				vpaBulkUpload.setMerchantVPA(vpaBulkUpload.getMerchantVPA().trim().toLowerCase());
			}
			if (vpaBulkUpload.getOperatorContactName() != null) {
				vpaBulkUpload.setOperatorContactName(vpaBulkUpload.getOperatorContactName().trim());
			}
			if (vpaBulkUpload.getOperatorEmail() != null) {
				vpaBulkUpload.setOperatorEmail(vpaBulkUpload.getOperatorEmail().trim());
			}
			if (vpaBulkUpload.getOperatorMobileNumber() != null) {
				vpaBulkUpload.setOperatorMobileNumber(vpaBulkUpload.getOperatorMobileNumber().trim());
			}
			if (vpaBulkUpload.getOrgCode() != null) {
				vpaBulkUpload.setOrgCode(vpaBulkUpload.getOrgCode().trim());
			}
			if (vpaBulkUpload.getSettlementBank() != null) {
				vpaBulkUpload.setSettlementBank(vpaBulkUpload.getSettlementBank().trim());
			}
		}

	}

	private synchronized void checkAndMakeDirs() throws ApplicationException {
		try {
			File file = new File(merchantUploadFilePath);
			if (file.exists() || file.mkdirs()) {
				merchantUploadFilePath = file.getAbsolutePath();
				isDirectoryCreated = true;
			} else {
				// make directory from current path with name
				// MerchantUploadFilePath
				file = new File("MerchantUploadFilePath/");
				if (file.exists() || file.mkdirs()) {
					merchantUploadFilePath = file.getAbsolutePath();
					isDirectoryCreated = true;
				} else {
					log.error("unable to create Directory ie:" + merchantUploadFilePath);
					throw new ApplicationException(ApplicationConstants.APPLICATION_EXCEPTION.getErrorCode(),
							ApplicationConstants.APPLICATION_EXCEPTION.getErrorDetails());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error while creating directory:" + merchantUploadFilePath);
			throw new ApplicationException(ApplicationConstants.APPLICATION_EXCEPTION.getErrorCode(),
					ApplicationConstants.APPLICATION_EXCEPTION.getErrorDetails());
		}
	}

	private String geFileSizeStr(long size) {
		String sizeStr = "";
		if (size < 1024) {
			sizeStr = size + " bytes";
		} else if (size < 1024000) {
			sizeStr = (size / 1024) + " kb";
		} else {
			sizeStr = (size / (1024 * 1024)) + " mb";
		}
		return sizeStr;
	}

	

}
*/