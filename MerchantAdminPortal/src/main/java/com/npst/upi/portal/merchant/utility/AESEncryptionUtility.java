package com.npst.upi.portal.merchant.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESEncryptionUtility {
	public static final String secretKeys = "cb_B@leNQ@)!(ApI";
	public static final String keyIV = "CB_$@Lt_K(y_2019";

	
	private static SecretKeySpec secretKey;
	
	   public static final int AES_KEY_SIZE = 256;
	   public static final int GCM_IV_LENGTH = 12;
	   public static final int GCM_TAG_LENGTH = 16;
	private static byte[] key;
	private static final Logger log = LoggerFactory.getLogger(AESEncryptionUtility.class);



/*	public static String encryptAES(byte[] strToEncrypt, String secret) {
		try {
	        byte[] IV = new byte[GCM_IV_LENGTH];
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
	        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey,gcmParameterSpec);
			byte[] cipherText = cipher.doFinal(strToEncrypt);
			return Base64.getEncoder().encodeToString(cipher.doFinal(cipherText));
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error :{}", e);
		}
		return null;
	}*/
	public static void main(String[] args) {
		String originalString = "{\"Dpcd\":\"792\",\"Account\":\"0473111033572\"}";
		String encryptedString = "io1VybntU9Q5dTD/pNOdEHvCVArSQAXM/65hY7FYrx2n+HMek3MJmcN1lYSXAR34A9gDzxdg+Ns=";
		//io1VybntU9Q5dTD/pNOdEHvCVArSQAXM/65hY7FYrx2n+HMek3MJmcN1lYSXAR34A9gDzxdg+Ns=
		//String decryptedString = AESEncryptionUtility.decrypt(encryptedString, AESEncryptionUtility.secretKeys);

		//System.out.println("original String---->" + originalString);
		//System.out.println("encrypted String ----------->" + encryptedString);
		//System.out.println("decrypted String------------>" + decryptedString);
		try {
			String encriptedSt= encryptIV(originalString.getBytes(),setKey(secretKeys),keyIV.getBytes());
		System.out.println("Resp*"+encriptedSt);
			
			String OrignalResp= decryptVI(encryptedString,setKey(secretKeys),keyIV.getBytes());

			System.out.println("Resp Orignal DECRIPT*"+OrignalResp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public static String encryptIV(byte[] plaintext, SecretKey key, byte[] IV) throws Exception
	    {
	        // Get Cipher Instance
	        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
	        
	        // Create SecretKeySpec
	        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
	        
	        // Create GCMParameterSpec
	        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
	        
	        // Initialize Cipher for ENCRYPT_MODE
	        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
	        
	        // Perform Encryption
	        byte[] cipherText = cipher.doFinal(plaintext);
	        
	        return Base64.getEncoder().encodeToString(cipherText);
	    }
	 
	 
	 
	 public static String decryptVI(String encriptedSt, SecretKey key, byte[] IV) {
			try {
				
				Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
				GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
				
				 cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
				return new String(cipher.doFinal(Base64.getDecoder().decode(encriptedSt.getBytes())));
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("error :{}", e);
			}
			return null;
		}

	public static SecretKey setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			log.error("error :{}", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error("error :{}", e);
		}
		return secretKey;
	}
}
