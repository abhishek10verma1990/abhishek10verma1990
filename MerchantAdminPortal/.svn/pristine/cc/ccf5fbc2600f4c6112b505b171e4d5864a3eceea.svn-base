package com.npst.upi.portal.merchant.utility;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class enc {
private static String toHex(byte[] bytes) {
StringBuilder buffer = new StringBuilder(bytes.length * 2);
String str;
for (Byte b : bytes) {
str = Integer.toHexString(b);
int len = str.length();
if (len == 8) {
buffer.append(str.substring(6));
} else if (str.length() == 2) {
buffer.append(str);
} else {
buffer.append("0" + str);
}
}
return buffer.toString();
}
private static byte[] fromHex(String text) {
byte arr[] = new byte[text.length() / 2];
for (int i = 0; i < text.length(); i += 2) {
arr[i / 2] = (byte) Integer.parseInt(text.substring(i, i + 2),
16);
}
return arr;
}
public static String encrypt(String text, String key) {
try {
Cipher cipher = Cipher.getInstance("AES");
SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
return toHex(cipher.doFinal(text.getBytes()));
} catch (Exception e) {
return null;
}
}
public static String decrypt(String text, String key) {
try {
Cipher cipher = Cipher.getInstance("AES");
SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
cipher.init(Cipher.DECRYPT_MODE, skeySpec);
return new String(cipher.doFinal(fromHex(text)));
} catch (Exception e) {
return null;
}
}

public static void main(String[] args) {
//	System.out.println(encrypt("9911609922|ABCDEF1234567|1510228077983", "7MERQhZjF4kGbnvXQrHqcDMWu53WbceF"));
//	String statusString  = "117000000000000000000000000000000000000000000000000000000000000000000000000999900000000000000000000000000000000000000000";
//	System.out.println(statusString.substring(48, 60));
//String str = "\"[{\"ResErrMsg\":\"SUCCESS\",\"ResMobileNumber\":\"+917906743578\",\"maskedCardNumber\":\"46877801****5006\",\"cardStatus\":\"0\"},{\"ResErrMsg\":\"SUCCESS\",\"ResMobileNumber\":\"+917906743578\",\"maskedCardNumber\":\"46877801****5096\",\"cardStatus\":\"0\"}]\"";	
//System.out.println(str); 
//System.out.println(str= str.replaceAll("\"\\[", "["));
//System.out.println(str= str.replaceAll("\\]\"", "]"));
	
//	String str = "VD1                00000000000000000000000000000000000000000000000000000000000000000000000023041806000000006";
//    String str1 = str.substring(0, 2);
//    String str2 = str.substring(3, str.length());
//    
//    String finalLimit = str1 + "2" + str2 + "H";
//    System.out.println(str1);
//    System.out.println(str2);
String req = "VENDOR_ID=TEST_USER&METHOD_ID=SAS_LOGIN&RESPONSE_DATE_TIME=06-09-2019+11%3A30%3A16+AM&RESPONSE_DIGEST=DDD254DBC58986F29051BAB\r\n" + 
		"A1F078B220E414E89CABCD7C4A43C59D414555D1B&RESPONSE_ENC=yZ8jiOL3J6tVCUiZw6zoEErgGy39ewemmNARBsMXrhin%2Bj4alnMfmqEIjxGTh0KznrvnsOBRvllxZjy0Vw1RjqDO0TIotv3Qu82GbFZscn4nuZ5l6hotPo57A%2Ba3somihTrG7W3oaTGJ7i8JtgGDhSwvmyw1XWEKiXvXLQzV%2F%2FBEpU\r\n" + 
		"4Bv%2BgJqgN6VoUDRhCuEVJSDc%2FpXhREygfx8kWZbr5xM85almj9%2BI%2B1jj2au6ze1m4hoPgNlSjbI0vnBD4AujYTauucwkGZiMxtQwUNdQ%3D%3D";

Map<String, String> map=loginRequestParsing(req);

//System.out.println("DATA FLOW Normal \n"+ loginRequestParsing(req));

System.out.println("Get all data\n "+map.get("VENDOR_ID")+"\n"+map.get("RESPONSE_ENC")+"\n"+map.get("RESPONSE_DIGEST")+"\n"+map.get("RESPONSE_DATE_TIME")+"\n"+map.get("METHOD_ID"));

}

  public static Map<String,String> loginRequestParsing(final String encodedRequestString){
	  Map<String,String> paramMap = new HashMap<String,String>();
	  try {
		String decodedReqString = URLDecoder.decode(encodedRequestString, "UTF-8");
		System.out.println(decodedReqString);
		StringTokenizer token = new StringTokenizer(decodedReqString,"&");
		while(token.hasMoreTokens()) {
			String tokenString = token.nextToken();
			System.out.println(tokenString);
			String tokenKeyValue[] = tokenString.split("=",2);
			paramMap.put(tokenKeyValue[0], tokenKeyValue[1]);
		}
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
	  return paramMap;
  }
}