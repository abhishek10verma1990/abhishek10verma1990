package com.npst.upi.portal.merchant.utility;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.npst.upi.portal.merchant.dto.MerchantKYC_Details;
import com.npst.upi.portal.merchant.dto.SASResp;

public class EncAESGSM {
	public static final String secretKeys = "cb_B@leNQ@)!(ApI";
	public static final String keyIV = "CB_$@Lt_K(y_2019";

	
	
	   public static final int AES_KEY_SIZE = 256;
	   public static final int GCM_IV_LENGTH = 12;
	   public static final int GCM_TAG_LENGTH = 16;
	private static final Logger log = LoggerFactory.getLogger(EncAESGSM.class);


	/*public static void main(String[] args) {
		
		MerchantKYC_Details regResponse=null;
		//String originalString = "{\"Dpcd\":\"792\",\"Account\":\"0473111033572\"}";
		
		
		//"yZ8jiOL3J6tVCUiZw6zoEErgGy39ewemmNARBsMXrhin+j4alnMfmqEIjxGTh0KznrvnsOBRvllxZjy0Vw1RjqDO0TIotv3Qu82GbFZscn4nuZ5l6hotPo57A+a3somihTrG7W3oaTGJ7i8JtgGDhSwvmyw1XWEKiXvXLQzV//BEpU4Bv+gJqgN6VoUDRhCuEVJSDc/pXhREygfx8kWZbr5xM85almj9+I+1jj2au6ze1m4hoPgNlSjbI0vnBD4AujYTauucwkGZiMxtQwUNdQ==";
		String encryptedString = 	 "yZ8jiOL3J6tVCUiZw6zoEErgGy39ewemmNARBsMXrhin+j4alnMfmqEIjxGTh0KznrvnsOBRvllxZjy0Vw1RjqDO0TIotv3Qu82GbFZscn4nuZ5l6hotPo57A+a3somihTrG7W3oaTGJ7i8JtgGDhSwvmyw1XWEKiXvXLQzV//BEpU4Bv+gJqgN6VoUDRhCuEVJSDc/pXhREygfx8kWZbr5xM85almj9+I+1jj2au6ze1m4hoPgNlSjbI0vnBD4AujYTauucwkGZiMxtQwUNdQ==";
		
		String RESP="{\"VENDOR_ID\":\"TEST_USER\",\"METHOD_ID\":\"001\",\"RESPONSE_CODE\":\"1\",\"RESPONSE_ENC\":\"qtQz+pXNLq1OEV2Sz7uday6XD0+ODRfF8bwBHdYl2w6t6Q1Zhm0Joqs1sTXsmWSM+8q+/pZduWxyczegQQBMncfCuzogtpnTuNSBbl5XZHUx2aETtndfIoNhFPituouph0Sh623qYjOFiU1/0WLtjCg090JSXgJo6HrmK0ufqeES+1Zl2/pz8lxJGIRHRxCkX1JGDKzoQW0puWObhjTrBtEGV9omymOh9oa1nj2bssrU1AZuorcXjD+3BSuIdhUtWgcOnScBXRT8ZgOn6BqZEenmmNVHDhdtt1Ptz57g0vXlL9Ew5nAB1x9mPQ18NuY90qYijxL4CKPDuhFzZ1zw+8VrC+Y+CPnDF0eUo6jcvGe54HYF5DWFReyuR2XkZ9t5TyrpV2WgrVT3lRvkh+Fc+e32rDb7Lx5I9DSOFvaPJE2Ia8MA3Mpsq5grnPr8gB19uYPgxKPjivyPrwh97wkKtrpglxhTSHexqLO31AjuEMCy5iw8zvZD7m0Pxz4Xu1aRmzFTEg0FgIBomoQ/Tkm4CH7VtuKyJeTN7mwD1lHYzMbRw2oQKALIXv1WvwNQ8AEJl/PZzdxoKLeOoZyH/CeY20G3fNYUSbn3z3fTS0ivxkeIK68c5EY59OxC8oKmfi9Cvh8OzpjNwXf7qXt0O/3FPfLVr+3CpkxdYr/mSrFsYt8jQg58ALfPgjPrAWL0pFopoRvjVxsSYBaGty5oaVzV7mHk8xhO7U/cwm82eteflmaNOJ3Pr6vrQQri5P7RLyjo+/tl4mvXJcdvC6ZEN16N9aDc7lZOHzSv9nH8iBES67264KzODplRO/bWd4oKCdi6evMxfzRIGvfo51bjGinBqg5DXzuTsIDyH16W6+VhIudHh3WGvkvmXiUKipXwW/oKdcxA5WRgtKO5b2CBqfukDAvieG/mE7Wc2AIYy77I4yn3PEDqvBbfXblu17jjOLYRcyX7mZNLC6MoYjhBLIW6S9+J3p66oNlB6c3Mo56Ne4aCkBod6um09orZbuMW0DI9zIdvTmJGtFxj5oAkveHNYxquzfl2skdUKZVqMxeOtWtUIyeW1Q==\",\"RESPONSE_DIGEST\":\"E243EA8DF1CBF15649BB534802B2A943A9F54A46391F64915C216F2BBD3A58FF\",\"RESPONSE_DATE_TIME\":\"2019-08-31T12:17:03.4146119+05:30\"}";
		try {
		//	String encriptedSt= encryptIV(originalString.getBytes(),secretKeys.getBytes(),keyIV.getBytes());
		//System.out.println("Resp*"+encriptedSt);
			
			String OrignalResp= decryptVI(encryptedString, secretKeys.getBytes(),keyIV.getBytes());
System.out.println("RESPONCE SAS"+OrignalResp);
			
			Gson gson = new Gson();
			List<MerchantKYC_Details> clicks = Arrays.asList(gson.fromJson(OrignalResp, MerchantKYC_Details[].class));
			
			
			
			//MerchantKYC_Details cricketer = mapper.readValue(OrignalResp, MerchantKYC_Details.class);

			SASResp data = new Gson().fromJson(RESP, SASResp.class);
			
			System.out.println(data.getRESPONSE_CODE());
			System.out.println(data.getVENDOR_ID());
			System.out.println(data.getRESPONSE_ENC());



			//regResponse=Utility.getReqJson(OrignalResp);
			//List<MerchantKYC_Details> ppl2 = Arrays.asList(mapper.readValue(OrignalResp, MerchantKYC_Details[].class));

			for(MerchantKYC_Details model : clicks) {
	            System.out.println(model.getCOD_CUST_ID());
	        }

			
			
			//System.out.println("*************"+ppl2);

			
			System.out.println("Resp Orignal DECRIPT*"+OrignalResp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	 public static String encryptIV(byte[] plaintext, byte[] key, byte[] IV) throws Exception
	    {
	        // Get Cipher Instance
	        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
	        
	        // Create SecretKeySpec
	        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
	        
	        // Create GCMParameterSpec
	        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
	        
	        // Initialize Cipher for ENCRYPT_MODE
	        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
	        
	        // Perform Encryption
	        byte[] cipherText = cipher.doFinal(plaintext);
	        
	        return Base64.getEncoder().encodeToString(cipherText);
	    }
	 
	 
	 
	 public static String decryptVI(String encriptedSt, byte[] key, byte[] IV) {
			try {
				
				Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
				GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
			    cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
				return new String(cipher.doFinal(Base64.getDecoder().decode(encriptedSt.getBytes())));
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("error :{}", e);
			}
			return null;
		}

	 public static Key getKey(byte[] key) {
		return new SecretKeySpec(key, "AES");
	 }
	 
	
	 public static  String bytesToHex(byte[] bytes) {
		    // bytesToHex is used to convert output from Cipher.
		    // cipher.update can return null, which is equivalent to returning
		    // no plaitext rsp. ciphertext.
		    if (bytes == null) {
		      return "";
		    }
		    String chars = "0123456789abcdef";
		    StringBuilder result = new StringBuilder(2 * bytes.length);
		    for (byte b : bytes) {
		      // convert to unsigned
		      int val = b & 0xff;
		      result.append(chars.charAt(val / 16));
		      result.append(chars.charAt(val % 16));
		    }
		    return result.toString();
		  }
	 
	 public static  byte[] hexToBytes(String hex) throws IllegalArgumentException {
		    if (hex.length() % 2 != 0) {
		      throw new IllegalArgumentException("Expected a string of even length");
		    }
		    int size = hex.length() / 2;
		    byte[] result = new byte[size];
		    for (int i = 0; i < size; i++) {
		      int hi = Character.digit(hex.charAt(2 * i), 16);
		      int lo = Character.digit(hex.charAt(2 * i + 1), 16);
		      if ((hi == -1) || (lo == -1)) {
		        throw new IllegalArgumentException("input is not hexadecimal");
		      }
		      result[i] = (byte) (16 * hi + lo);
		    }
		    return result;
		  }
	 

	 
	 
public static byte [] encryptData(String key, byte [] data, byte[] iv) throws NoSuchPaddingException,
     NoSuchAlgorithmException,
     InvalidAlgorithmParameterException,
     InvalidKeyException,
     BadPaddingException,
     IllegalBlockSizeException, InvalidKeySpecException {

 //Prepare the nonce
 SecureRandom secureRandom = new SecureRandom();

 //Noonce should be 12 bytes
/* byte[] iv = new byte[12];
 secureRandom.nextBytes(iv);*/

 //Prepare your key/password
 SecretKey secretKey = generateSecretKey(key, iv);


 Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
 GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);

 //Encryption mode on!
 cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

 //Encrypt the data
 byte [] encryptedData = cipher.doFinal(data);

 //Concatenate everything and return the final data
 ByteBuffer byteBuffer = ByteBuffer.allocate(4 + iv.length + encryptedData.length);
 byteBuffer.putInt(iv.length);
 byteBuffer.put(iv);
 byteBuffer.put(encryptedData);
 return byteBuffer.array();
}


public static byte [] decryptData(String key, byte [] encryptedData) 
        throws NoSuchPaddingException, 
        NoSuchAlgorithmException, 
        InvalidAlgorithmParameterException, 
        InvalidKeyException, 
        BadPaddingException, 
        IllegalBlockSizeException, 
        InvalidKeySpecException {


    //Wrap the data into a byte buffer to ease the reading process
    ByteBuffer byteBuffer = ByteBuffer.wrap(encryptedData);

    int noonceSize = byteBuffer.getInt();

    //Make sure that the file was encrypted properly
    if(noonceSize < 12 || noonceSize >= 16) {
        throw new IllegalArgumentException("Nonce size is incorrect. Make sure that the incoming data is an AES encrypted file.");
    }
    byte[] iv = new byte[noonceSize];
    byteBuffer.get(iv);

    //Prepare your key/password
    SecretKey secretKey = generateSecretKey(key, iv);

    //get the rest of encrypted data
    byte[] cipherBytes = new byte[byteBuffer.remaining()];
    byteBuffer.get(cipherBytes);

    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
    GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);

    //Encryption mode on!
    cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

    //Encrypt the data
    return cipher.doFinal(cipherBytes);

}
public static SecretKey generateSecretKey(String password, byte [] iv) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeySpec spec = new PBEKeySpec(password.toCharArray(), iv, 65536, 128); // AES-128
    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] key = secretKeyFactory.generateSecret(spec).getEncoded();
    return new SecretKeySpec(key, "AES");
}
}