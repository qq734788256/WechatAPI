package com.wx.base.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
	
	private static final String passwordKey = "Think*#7370#";
	
	private static final String infoKey = "vip!@#0311";
	
	private static final String messageKey = "msg!@#0311";
	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	private static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	        random.setSeed(password.getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	private static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	        random.setSeed(password.getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 加密方法
	 * 
	 * @param content
	 *            加密内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String doEncrypt(String content, String password) {
		byte[] encryptResult = encrypt(content, password);
		return parseByte2HexStr(encryptResult);
	}

	/**
	 * 解密方法
	 * 
	 * @param encryptResultStr
	 *            加密后的结果
	 * @param password
	 *            解密密码
	 * @return
	 */
	public static String doDecipher(String encryptResultStr, String password) {
		byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
		byte[] decryptResult = decrypt(decryptFrom, password);
		return new String(decryptResult);
	}
	/**
	 * 获取用户密码
	 * @param password
	 * @return
	 */
	public static String getUserPassword(String password){
		return doEncrypt(password, passwordKey);
	}
	/**
	 * 验证密码是否正确
	 * @param info
	 * @param param
	 * @return
	 */
	public static boolean validatePassword(String info,String param){
		String realPassword =  doDecipher(info, passwordKey);
		return (realPassword.equals(param));
	}
	/**
	 * 用户token加密
	 * @param userInfo
	 * @return
	 */
	public static String getUserToken(String userInfo){
		return doEncrypt(userInfo, infoKey);
	}
	/**
	 * 用户token解密
	 * @param info
	 * @return
	 */
	public static String getUserInfoByToken(String info){
		return doDecipher(info, infoKey);
	}
	/**
	 * 获取短信token
	 * @param messageInfo
	 * @return
	 */
	public static String getMessageToken(String messageInfo){
		return doEncrypt(messageInfo, messageKey);
	}
	/**
	 * 解析短信token
	 * @param token
	 * @return
	 */
	public static String getMessageInfo(String token){
		return doDecipher(token, messageKey);
	}
	public static void main(String[] args) {
		System.out.println(getUserPassword("manager_active!"));
		System.out.println(validatePassword("FAA7C75BC2966BC3D0F3A3A7BB399C76", "admin*#7370#"));
	}
}
