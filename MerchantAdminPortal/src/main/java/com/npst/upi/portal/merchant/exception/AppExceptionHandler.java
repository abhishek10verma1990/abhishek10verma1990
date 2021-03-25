package com.npst.upi.portal.merchant.exception;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.ResponseStatus;

/**
 * 
 * @author Rahul Chaudhary
 *
 */
@RestControllerAdvice
public class AppExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleExceptions(Exception exception) {
		exception.printStackTrace();
		logger.info("App Exception : {}", exception);
		ApiResponseDto apiresponse = new ApiResponseDto(ResponseStatus.ERROR.getStatus(),
				ResponseStatus.ERROR.getStatus_code(), ResponseStatus.ERROR.getStatus_msg(), "");

		return new ResponseEntity<>(apiresponse, HttpStatus.OK);
	}

	@ExceptionHandler(FileSizeLimitExceededException.class)
	public ResponseEntity<Object> handleFileExceptions(FileSizeLimitExceededException exception) {
		exception.printStackTrace();
		logger.info("App Exception : {}", exception);
		ApiResponseDto apiresponse = new ApiResponseDto(ResponseStatus.ERROR.getStatus(),
				ResponseStatus.ERROR.getStatus_code(), ResponseStatus.ERROR.getStatus_msg(), "");

		return new ResponseEntity<>(apiresponse, HttpStatus.OK);
	}
}