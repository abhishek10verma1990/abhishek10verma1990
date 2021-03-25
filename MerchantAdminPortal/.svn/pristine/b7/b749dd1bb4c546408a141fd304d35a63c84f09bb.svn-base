package com.npst.upi.portal.merchant.utility;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESSecurity
{
  private static Logger logger = LoggerFactory.getLogger(AESSecurity.class);
  private static String dbKey = "1234567891234567";
  
  public String encrypt(String input, String key)
  {
    byte[] crypted = null;
    try
    {
      SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(1, skey);
      crypted = cipher.doFinal(input.getBytes());
    }
    catch (Exception e)
    {
      logger.error("AESSecurity_encrypt", e);
    }
    return new String(Base64.encodeBase64(crypted));
  }
  
  public static String decrypt(String input, String key)
  {
    byte[] output = null;
    try
    {
      SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(2, skey);
      output = cipher.doFinal(Base64.decodeBase64(input));
    }
    catch (Exception e)
    {
      logger.error("AESSecurity_decrypt", e);
    }
    return new String(output);
  }
  
  public String dbDecrypt(String input)
  {
    byte[] output = null;
    try
    {
      SecretKeySpec skey = new SecretKeySpec(dbKey.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(2, skey);
      output = cipher.doFinal(Base64.decodeBase64(input));
    }
    catch (Exception e)
    {
      logger.error("AESSecurity_decrypt", e);
    }
    return new String(output);
  }
  public static void main(String args[]) {
	  decrypt("HELOOOO",dbKey);
  }
}
