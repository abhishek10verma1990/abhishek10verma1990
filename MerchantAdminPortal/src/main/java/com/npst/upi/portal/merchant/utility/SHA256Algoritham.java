package com.npst.upi.portal.merchant.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SHA256Algoritham
{
	private static final Logger	log	= LoggerFactory.getLogger(SHA256Algoritham.class);
  
  public String sha256(String input)
    throws NoSuchAlgorithmException
  {
    StringBuffer sb = new StringBuffer();
    try
    {
      MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
      byte[] result = mDigest.digest(input.getBytes());
      return bytesToHex(result);
    }
    catch (Exception e)
    {
      this.log.error("sha256", e);
    }
    return sb.toString();
  }
  
  public static String bytesToHex(byte[] hash)
  {
    return DatatypeConverter.printHexBinary(hash);
  }
}
