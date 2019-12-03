package cn.ds.double_live_demo.util;

import java.security.MessageDigest;

public class PasswordUtil {

	/**
	 * 密码盐加密
	 * 
	 * @param password 原密码
	 * @param salt     密码盐值
	 * @return
	 */
	public static String encrypt(String password, String salt) {
		String encryptPassword = encrypt(password, salt, 10);
		return encryptPassword;
	}

	/**
	 * 密码盐加密
	 * 
	 * @param password       原密码
	 * @param salt           密码盐值
	 * @param hashIterations 加密次数
	 * @return
	 */
	public static String encrypt(String password, String salt, int hashIterations) {
		if (hashIterations <= 0) {
			hashIterations = 1;
		}
		try {
			password = password + salt;
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes("UTF8"));
			byte message[] = messageDigest.digest();
			String result = "";
			for (int i = 0; i < message.length; i++) {
				result += Integer.toHexString((0x000000FF & message[i]) | 0xFFFFFF00).substring(6);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static void main(String[] args) {
		try {
			String password = "Gac123456";
			String salt = "OvfEgXei";
//			String salt = RandomUtil.generateRandom(8);
//			System.out.println(salt);
			String encryptPassword = encrypt(password, salt);
			System.out.println(encryptPassword);
			System.out.println(salt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
