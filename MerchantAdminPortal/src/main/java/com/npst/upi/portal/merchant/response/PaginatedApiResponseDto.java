package com.npst.upi.portal.merchant.response;

import java.util.List;

import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.PaginationMetaData;
/**
 * 
 * @author Rahul Chaudhary
 *
 */
public class PaginatedApiResponseDto extends ApiResponseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean pagingSupport;
	private PaginationMetaData paginationMetaData;

	public PaginatedApiResponseDto(String status, String status_code, String status_msg) {
		super(status, status_code, status_msg);
	}
	
	public PaginatedApiResponseDto(String status, String status_code, String status_msg, boolean pagingSupport, List<com.npst.upi.portal.merchant.dto.MerchantsTxn> response, PaginationMetaData pageInfo,
			Integer maxPageLimit) {
		super(status, status_code, status_msg, response);
		this.pagingSupport=pagingSupport;
		this.paginationMetaData=pageInfo;
	}

	public boolean isPagingSupport() {
		return pagingSupport;
	}

	public void setPagingSupport(boolean pagingSupport) {
		this.pagingSupport = pagingSupport;
	}

	public PaginationMetaData getPaginationMetaData() {
		return paginationMetaData;
	}

	public void setPaginationMetaData(PaginationMetaData pageInfo) {
		this.paginationMetaData = pageInfo;
	}	

}
