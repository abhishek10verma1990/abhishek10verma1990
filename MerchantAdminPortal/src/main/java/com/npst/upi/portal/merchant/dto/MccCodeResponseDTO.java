package com.npst.upi.portal.merchant.dto;


import java.util.List;

import com.npst.upi.portal.merchant.entity.MccCode;

public class MccCodeResponseDTO {

private static final long serialVersionUID = 1L;
	
	private List<MccCode> mccCodes;

	public MccCodeResponseDTO(List<MccCode> mccCodes) {
		super();
		this.mccCodes = mccCodes;
	}

	public List<MccCode> getMccCodes() {
		return mccCodes;
	}

	public void setMccCodes(List<MccCode> mccCodes) {
		this.mccCodes = mccCodes;
	}

	
	

}
