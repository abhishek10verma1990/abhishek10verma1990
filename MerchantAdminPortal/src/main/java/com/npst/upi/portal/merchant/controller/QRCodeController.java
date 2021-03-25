/**
 * 
 */
package com.npst.upi.portal.merchant.controller;

import java.io.File;
import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.QRCodeRequest;
import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.service.QrCodeService;

@RestController
@RequestMapping(Constants.BASEURL)
public class QRCodeController {
	
	@Autowired
	private QrCodeService qrCodeService;
	
	private static final Logger log = LoggerFactory.getLogger(QRCodeController.class);
	
	@RequestMapping(Constants.GENERATE_QR)
	public ResponseEntity<Object> generateQRCode(@RequestParam String branchcode) {
		log.info("Generate Qr called... : {}", branchcode);
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg(), "");
		try {
			QRCodeRequest qrcoderequest=new QRCodeRequest();
			qrcoderequest.setBranchcode(branchcode);
			qrcoderequest.setQrtype("UQR");
			qrCodeService.generateQr(qrcoderequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : {}", e);
		}
		
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
		
	}
	
	@RequestMapping(Constants.DOWNLOAD_QR)
	public ResponseEntity<Object> downloadQRCode(@RequestBody QRCodeRequest qrcoderequest) {
		log.info("downloadQr called... : {}", qrcoderequest);
		
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg(), "");
		try {
			File file = new File(qrCodeService.downloadQr(qrcoderequest));
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					.contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length()).body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : {}", e);
		}
		
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
		
	}
//SAS QR GENERATE	
	
	@RequestMapping(Constants.GENERATE_SASQR)
	public ResponseEntity<Object> generateSASQRCode(@RequestParam String qrcoderequest) {
		log.info("Generate Qr called... : {}", qrcoderequest);
		String isQr = null;
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg(), "");
		try {
			isQr = qrCodeService.generateSASQr(qrcoderequest);
			System.out.println("is QR in used "+isQr);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : {}", e);
		}
			return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);

		
		
	}
	@SuppressWarnings({ "unused", "unlikely-arg-type" })
	@RequestMapping(Constants.DOWNLOAD_SASQR)
	public ResponseEntity<Object> downloadSASQRCode(@RequestParam(name = "qrcoderequest", required = false)String qrcoderequest,@RequestParam(name = "branchcode", required = false) String branchcode) {
		log.info("downloadQr called... : {}", qrcoderequest);
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg(), "");
		try {
			
			String filepath=qrCodeService.downloadSASQr(qrcoderequest,branchcode);
			if(filepath==null) {
				ApiResponseDto apiResponseDtofail = new ApiResponseDto(ResponseStatus.MERCHANT_QR_DETAILS_NOT_EXIST.getStatus(), ResponseStatus.MERCHANT_QR_DETAILS_NOT_EXIST.getStatus_code(), ResponseStatus.MERCHANT_QR_DETAILS_NOT_EXIST.getStatus_msg(), "");
				return new ResponseEntity<>(apiResponseDtofail, HttpStatus.OK);
			}
			else {
				File file = new File(filepath);
				InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
						.contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length()).body(resource);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : {}", e);
		}
		
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
		
	}

}
