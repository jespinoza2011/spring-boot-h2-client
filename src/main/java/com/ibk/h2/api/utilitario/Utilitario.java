package com.ibk.h2.api.utilitario;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Utilitario {


	private static final int KEY_LENGTH = 128;
	private static final String KEY_DESC = "AES";
	private static final String KEY_ALGORITHM = "AES/CBC/PKCS5Padding";

	public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_DESC);
		keyGenerator.init(n);
		SecretKey key = keyGenerator.generateKey();
		return key;
	}

	public static IvParameterSpec generateIv() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	public static String encrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		IvParameterSpec ivParameterSpec = generateIv();
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(KEY_LENGTH), ivParameterSpec);
		byte[] cipherText = cipher.doFinal(input.getBytes());
		return Base64.getEncoder().encodeToString(cipherText);
	}

	public static String decrypt(String cipherText)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		IvParameterSpec ivParameterSpec = generateIv();
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, generateKey(KEY_LENGTH), ivParameterSpec);
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}
}