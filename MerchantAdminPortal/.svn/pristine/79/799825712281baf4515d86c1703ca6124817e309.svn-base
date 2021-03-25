package com.npst.upi.portal.merchant.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.persistence.Query;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.npst.upi.portal.merchant.dto.AccountSearchRequestDto;
import com.npst.upi.portal.merchant.dto.Login;
import com.npst.upi.portal.merchant.dto.MerchantKYC_Details;
import com.npst.upi.portal.merchant.dto.PaginationMetaData;
import com.npst.upi.portal.merchant.dto.SASResp;
import com.npst.upi.portal.merchant.dto.SimpleDto;
import com.npst.upi.portal.merchant.dto.User;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.service.MerchantsService;


public class Utility {
	private static final Logger log = LoggerFactory.getLogger(Utility.class);
	
	private static Integer DEFAULT_PAGE=0;
	
	private static Integer MIN_PAGE_SIZE=2;

	private static Integer MAX_PAGE_SIZE=5000;

	private static Integer DEFAULT_PAGE_SIZE=5;

	private static Properties prop;

	private static final String PROPFILENAME = "config.properties";
	
	@Autowired
	MerchantsService merchantserv;


	public static String getStringFromKey(String json, String key) throws JSONException {
		String outJson = null;
		JSONObject jsonObject = new JSONObject(json);
		outJson = jsonObject.getString(key);
		System.out.println("outjson=" + outJson);
		return outJson;
	}

	@SuppressWarnings("deprecation")
	public static void changeRefDate(Date startDate, Date endDate) {
		log.info("inside");
		if (startDate != null && endDate != null) {
			startDate.setHours(0);
			startDate.setMinutes(0);
			startDate.setSeconds(0);
			endDate.setHours(23);
			endDate.setMinutes(59);
			endDate.setSeconds(59);
		}
	}

	public static PaginationMetaData getPageInfo(Page<?> p) {
		PaginationMetaData out = new PaginationMetaData();
		if (p != null) {
			// "last": true,
			out.setLast(p.isLast());
			// "totalPages": 5,
			out.setTotalPages(p.getTotalPages());
			// "totalElements": 18,
			out.setTotalElements(p.getTotalElements());
			// "first": false,
			out.setFirst(p.isFirst());
			// "sort": null,
			out.setSort(p.getSort());
			// "numberOfElements": 2,
			out.setNumberOfElements(p.getNumberOfElements());
			// "size": 4,
			out.setSize(p.getSize());
			// "number": 4
			out.setNumber(p.getNumber());
			out.setMaxPageLimit(100);
		}
		return out;
	}

	public static Date formateDate(String yyyy_MM_dd) throws Exception {
		try {
			Long l = Long.parseLong(yyyy_MM_dd);
			return new Date(l);

		} catch (NumberFormatException e) {
			try {
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				String[] yyyy_MM_dd_ = yyyy_MM_dd.substring(0, 10).split("-");
				String yyyy = yyyy_MM_dd_[0];
				String MM = yyyy_MM_dd_[1];
				String dd = yyyy_MM_dd_[2];
				return df.parse(MM + "/" + dd + "/" + yyyy);
			} catch (Exception e1) {
				
				throw new Exception("Date invalid");
			}
		}

	}

	
	public static MerchantKYC_Details getReqJson(String reqStr) {
		log.trace("reqStr to convert in ReqJson {}", reqStr);
		MerchantKYC_Details reqJson = new MerchantKYC_Details();
		// Gson gson = new GsonBuilder().serializeNulls().create();
		Gson gson = new Gson();
		reqJson = gson.fromJson(reqStr, reqJson.getClass());
		if (null == reqJson) {
			reqJson = new MerchantKYC_Details();
		}
		log.trace("returning  reqJson {}", reqJson);
		return reqJson;
	}
	
	
	
	
	public static PaginationMetaData getPageInfo(int page, int size, long totalElements, Sort sort) throws Exception {
		PaginationMetaData out = new PaginationMetaData();
		log.info("inside");
		if (size > 0 && page >= 0 && totalElements >= 0) {
			int totalPages = (int) (totalElements / size);
			int r = (int) totalElements % size;
			if (r > 0)
				totalPages += 1;
			int numberOfElementsInCurrentPage = 0;
			if (totalPages > 0) {
				if (page < totalPages - 1)
					numberOfElementsInCurrentPage = size;
				else if (page == totalPages - 1) {
					if (r == 0)
						numberOfElementsInCurrentPage = size;
					else
						numberOfElementsInCurrentPage = r;
				}
			}
			// "last": true,
			out.setLast((page >= totalPages - 1 ? true : false));
			// "totalPages": 5,
			out.setTotalPages(totalPages);
			// "totalElements": 18,
			out.setTotalElements(totalElements);
			// "first": false,
			out.setFirst((page == 0 ? true : false));
			// "sort": null,
			out.setSort(sort);
			// "numberOfElements": 2,
			out.setNumberOfElements(numberOfElementsInCurrentPage);
			// "size": 4,
			out.setSize(size);
			// "number": 4
			out.setNumber(page);
		} else {
			String msg = "invalid paramter :size(>0) =" + size + " ,page(>=0) =" + page + " ,totalElements(>=0)="
					+ totalElements;
			log.warn(msg);
			throw new Exception(msg);
		}
		return out;
	}

	public static long getOffSet(int page, int size) throws Exception {
		log.info("inside");

		if (page < 0 || size <= 0)
			throw new Exception("invalid page no or page size");
		if (page == 0)
			return 0;
		else
			return page * size;

	}

	public static long getCount(List<Object[]> long_list) throws Exception {
		log.info("inside");
		long l = 0;
		if (long_list != null) {
			for (Object[] ob : long_list) {
				for (int i = 0; i < ob.length; i++) {
					if (ob[i] instanceof Long) {
						l += Long.parseLong(ob[i].toString());
					} else if (ob[i] instanceof BigInteger) {
						BigInteger bi = (BigInteger) ob[i];
						l += bi.longValue();
					} else {
						String msg = "object type not match ie=" + ob[0].getClass().getTypeName();
						log.info(msg);
						throw new Exception(msg);
					}
				}
			}
		}
		return l;
	}

	public static Date getDateOfObjofDate(Object ob) throws Exception {
		log.info("inside");
		Date date = null;
		if (ob != null) {
			if (ob instanceof Date)
				date = (Date) ob;
			else if (ob instanceof String) {
				String s = (String) ob;
				date = getDatefromStringTS(s);
			} else {
				log.info("invalid Date object");
				throw new Exception("invalid date object");
			}
		}
		return date;

	}

	@SuppressWarnings("deprecation")
	public static Date getDatefromStringTS(String dateTS) throws Exception {
		// 2017-08-10 10:10:10
		// 2017-08-10T10:10:10
		// 2017-11-22T15:52:57+05:30
		log.info("inside");
		Date date = null;
		if (dateTS != null) {
			date = formateDate(dateTS);
			if (date != null) {
				try {
					String hh_mm_ss[] = dateTS.substring(11, 19).split(":");
					date.setHours(Integer.parseInt(hh_mm_ss[0]));
					date.setMinutes(Integer.parseInt(hh_mm_ss[1]));
					date.setSeconds(Integer.parseInt(hh_mm_ss[2]));
				} catch (Exception e) {
					log.info("time part missing from date String");
				}
			}
		}
		return date;
	}

	public static void setPageAndSize(SimpleDto dto, int page, int size) throws Exception {
		log.info("inside");
		if (dto != null) {
			dto.setPage(page < 0 ? DEFAULT_PAGE : page);
			dto.setSize((size < MIN_PAGE_SIZE) ? DEFAULT_PAGE_SIZE : (size > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : size));
		}

	}

	public static Set<String> getCollectionSet(String... strs) {
		log.info("in");
		log.info("strs=" + strs);
		Set<String> set = new HashSet<String>();
		if (strs != null) {
			log.info("string[] length=" + strs.length);
			for (String s : strs) {
				if (s == null)
					continue;
				set.add(s);
			}
		}
		log.info("strs set=" + set);
		log.info("out");
		return set;
	}

	public static void setParams(Query q, Object... params) throws Exception {
		log.info("in");
		log.info("params=" + params);
		int i = 1;
		if (q != null) {
			for (Object p : params) {
				q.setParameter(i++, p);
			}
		}
		log.info("out");
	}
	
	public static String getProperty(String propName) {
		String propValue = null;
		FileInputStream fs = null;
		try {
			if (null == prop) {
				log.info("Loading ... ... ", PROPFILENAME);
				prop = new Properties();

				String osName = System.getProperty(Constants.CONST_OS_NAME);
				if (osName.toLowerCase().contains(Constants.CONST_OS_WIN)) {
					prop.load(fs = new FileInputStream(Constants.CONST_FILE_PATH_WIN + PROPFILENAME));
				} else {
					prop.load(fs = new FileInputStream(Constants.CONST_FILE_PATH_LINUX + PROPFILENAME));
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			System.exit(0);
		} finally {
			try {
				if (null != fs) {
					fs.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		propValue = prop.getProperty(propName);

		log.trace("[" + propName + "]=[" + propValue + "]");
		return (null == propValue) ? Constants.CONST_BLANK : propValue.trim();
	}	

	public static String getStringZeroPaddingBetPreAndNum(String prefix, int num, int length) {
		String out = null;
		StringBuilder sf = new StringBuilder(10);

		if (prefix != null && !prefix.isEmpty()) {
			out = prefix + num;
			if (out.length() == length) {
				return out;
			}
			sf.append(prefix);
			int l = length - out.length();
			while (l >= 0) {
				sf.append("0");
				l--;
			}
			sf.append(num);
			return sf.toString();
		}

		return out;

	}

	private static final String OS = System.getProperty("os.name").toLowerCase();

	private static final int SEP = 0;

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	public static Object getObjectFromJson(String jsonString, Object readInobj) throws IOException {
		Object out = null;
		out = new ObjectMapper().readValue(jsonString, readInobj.getClass());
		return out;
	}

	public static String getJsonFromObject(Object obj) throws JsonProcessingException {
		String outJson = null;
		outJson = new ObjectMapper().writeValueAsString(obj);
		return outJson;
	}

	public static String getObjectFromObject(Object obj, Object dest) throws JsonProcessingException {
		String outJson = null;
		outJson = new ObjectMapper().writeValueAsString(obj);
		return outJson;
	}
	
	public static String getJSONStr(Object req) {
		if (null == req) { return ""; }
		log.trace("return req[", req.toString(), "]");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(req);
		log.trace("return jsonStr[", jsonStr, "]");
		return jsonStr;
	}

	//public String createVpa(String  custId, String accountNo) {
	//	String vpa = "";
		// vpa = accountNo.substring(0, 5);
		//Merchants acno = merchantserv.findBymerchantAccountNo(accountNo);
		//String p = "";
		//try {
			//if(accountNo != null && accountNo != "") {
					//List<String> vp = acno.getMerchantVpa();
					//if(!vp.isEmpty()) {
						//if(vp.size()!=0) {
							//int suffix = vp.size();
							//String s = vpa.concat("_"+(++suffix)+"@cnrb");
						//	    p = custId.concat(s);
					//		System.out.println(p);
							
				//		}	
					//}else{
						 //p = vpa.concat(custId);
					//}
				//}
			
	//	}catch(Exception e) {
		//	e.printStackTrace();
		//}
		//return p;
	//}
	
	public String generateIfsc(String branchcode) {
		String str = "CANA";
		String ifsc = "";
		try {
			if(branchcode != null && branchcode != "") {
				ifsc = str.concat(branchcode);
				System.out.println(ifsc);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ifsc;
	}
	
	public void response() {
		Login login = new Login();
		login.setSession_id("CHOICE201809140000000169");
		login.setRequest_id("POS201809140000000169");
		login.setStatus("SUCESS");
		login.setDigest("3D3E71A784004990BEB2EB6C366036C42C6D3D2147FE8188D23CEBB99013091F");
		
		User user = new User();
		user.setStaff_no("109897");
		user.setName("NAGARAJAN R");
		user.setDpcd("0401");
		user.setDesignation_code("1237");
		user.setDesignation("System Admin");
		user.setBranch_name("HEAD OFFICE BENGALURU");
		user.setSection("2603");
		user.setWing_code("9");
		user.setSection("Department of Information Teachnology");
		user.setWing("Dept of IT Wing");
		user.setLogin_date("14-SEP-2018 02:46 PM");
		user.setOffice_type_code("1");
		user.setOffice_type("HEAD OFFICE");
		user.setRole("2");
	}
	
	public static void main(String[] arr) {
		Utility u = new Utility();
		//u.createVpa("76312983789", "2653217836289");
		u.generateIfsc("87326498374");
	}

	public static AccountSearchRequestDto getReqJSONDate(String message) {
		log.trace("reqStr to convert in ReqJson {}", message);
		AccountSearchRequestDto reqJson = new AccountSearchRequestDto();
		// Gson gson = new GsonBuilder().serializeNulls().create();
		Gson gson = new Gson();
		reqJson = gson.fromJson(message, reqJson.getClass());
		if (null == reqJson) {
			reqJson = new AccountSearchRequestDto();
		}
		log.trace("returning  reqJson {}", reqJson);
		return reqJson;
	
	}

	public static SASResp getRespJSONDate(String response) {
		log.trace("reqStr to convert in ReqJson {}", response);
		SASResp sasresp=new SASResp();
		Gson gson = new Gson();
		sasresp = gson.fromJson(response, sasresp.getClass());

		if (null == sasresp) {
			sasresp = new SASResp();
		}
		log.trace("returning  reqJson {}", sasresp);
		return sasresp;
	}
	
	
	
	
}
