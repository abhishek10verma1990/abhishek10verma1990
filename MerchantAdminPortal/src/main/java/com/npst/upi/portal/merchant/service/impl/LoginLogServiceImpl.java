package com.npst.upi.portal.merchant.service.impl;

import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npst.upi.portal.merchant.dto.LoginRequestDto;
import com.npst.upi.portal.merchant.dto.SASResp;
import com.npst.upi.portal.merchant.entity.Loginlog;
import com.npst.upi.portal.merchant.entity.SASInfo;
import com.npst.upi.portal.merchant.entity.SASLoginHistory;
import com.npst.upi.portal.merchant.repo.LoginLogRepo;
import com.npst.upi.portal.merchant.repo.SASInfoRepo;
import com.npst.upi.portal.merchant.repo.SASLoginHistoryReps;
import com.npst.upi.portal.merchant.service.LoginLogService;
import com.npst.upi.portal.merchant.utility.Utility;

@Service
public class LoginLogServiceImpl implements LoginLogService{
	private static final Logger log = LoggerFactory.getLogger(LoginLogServiceImpl.class);

	@Autowired
	LoginLogRepo loginlog;
	
	@Autowired
	SASInfoRepo sasInfoRepo;
	
	
	@Autowired
	SASLoginHistoryReps sasloginhistory;
	
	@Override
	public void saveLoginAudit(LoginRequestDto loginRequestDto,String tokenId) {
		log.info("Inside saveLoginAudit method ");
		
		
		Loginlog loginLog=null;
		loginLog=loginlog.findByLoginToken(tokenId);
		if(loginLog==null) {
			loginLog=new Loginlog();
			loginLog.setChannel(loginRequestDto.getChannel());
			loginLog.setClientip(loginRequestDto.getClientip());
			loginLog.setCreated_on(loginRequestDto.getCreated_on());
			loginLog.setRequest(loginRequestDto.getRequest());
			loginLog.setStatus(loginRequestDto.getStatus());
			loginLog.setLoginToken(tokenId);
			loginlog.save(loginLog);
		}
		else {
			loginLog.setModified_on(loginRequestDto.getModified_on());
			loginLog.setResponse(loginRequestDto.getResponse());
			loginlog.save(loginLog);
		}
	}
	@Override
	public void saveSASLoginAudit(String data, String stNo) {
		log.info("Inside saveSASLoginAudit method ");
		SASInfo sasInfo=null;
		String desdata = data;
		String [] temp=data.trim().split("~");
		sasInfo=sasInfoRepo.findByStNo(stNo);
		if(sasInfo==null) {
			sasInfo=new SASInfo();
			sasInfo.setRequest(data);
			sasInfo.setUsername(temp[1]);
			sasInfo.setDescCode(temp[3]);
			sasInfo.setStNo(temp[0]);
			sasInfo.setResponse(temp[12]);
			sasInfo.setDpcd(temp[2]);
			sasInfoRepo.save(sasInfo);
			log.info("DATA saved In SASLoginAudit table For {} ",stNo);

		}
		else {
			sasInfo.setDpcd(temp[2]);
			sasInfo.setResponse(temp[12]);
			sasInfoRepo.save(sasInfo);
			log.info("DATA Updated In SASLoginAudit table For {} and DP will be {}  ",stNo,temp[2]);
		}
	}

	@Override
	public void sasLogInHistory(String data, String stNo) {
		log.info("Inside sasLogInHistory method ");

		SASLoginHistory sasInfohis=null;
		String desdata = data;
		String [] temp=data.trim().split("~");
		sasInfohis=new SASLoginHistory();
		sasInfohis.setCreated_on(temp[12]);
		sasInfohis.setRequest(data);
		sasInfohis.setUsername(temp[1]);
		sasInfohis.setDescCode(temp[3]);
		sasInfohis.setStNo(temp[0]);
		sasInfohis.setResponse(temp[12]);
		sasInfohis.setDpcd(temp[2]);
		sasloginhistory.save(sasInfohis);
		log.info("DATA saved In SASLOGINHistory table For {} ",stNo);
	}
}
