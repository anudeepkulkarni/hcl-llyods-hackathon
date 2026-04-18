package com.llyods.payments.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

	// Secret key for AES encryption
	private static final String SECRET_KEY = "jocata_secretkey"; 

	// Encrypt data using AES
	public static String encrypt(String data) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedData = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encryptedData);
	}

	// Decrypt data using AES
	public static String decrypt(String encryptedData) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
		return new String(decryptedData);
	}
}
