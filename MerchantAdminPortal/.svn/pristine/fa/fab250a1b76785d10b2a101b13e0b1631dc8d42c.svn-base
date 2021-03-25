package com.npst.upi.portal.merchant.utility;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class MarchantCryptoUtil {
	// temporary
	private static final String algo = "AES/ECB/PKCS5PADDING";
	private static String key_ = "1234567890123456";

	public static String getBase64StringEncrypted(String plain, String key) {
		if (plain == null)
			return null;
		key = key_;
		String out = null;
		SecretKeySpec sks = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algo);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
			e2.printStackTrace();
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, sks);
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		}
		try {
			final byte[] byt = cipher.doFinal(plain.getBytes());
			out = Base64.getEncoder().encodeToString(byt);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return out;
	}

	public static String decryptedString(byte[] cipherbyt, String key) {
		if (cipherbyt == null)
			return null;
		key = key_;
		String out = null;
		SecretKeySpec sks = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algo);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.DECRYPT_MODE, sks);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		try {
			out = new String(cipher.doFinal(cipherbyt), "utf-8");
			System.out.println("decrypt=" + out);
		} catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return out;
	}
}
