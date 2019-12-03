package cn.ds.double_live_demo.util;


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
		String encryptPassword = encrypt(password, salt, hashIterations);
		return encryptPassword;
	}

	public static void main(String[] args) {
		try {
			String password = "Gac123456";
			String salt = "OvfEgXei";
//			String salt = RandomUtil.generateRandom(8);
//			System.out.println(salt);
			String encryptPassword = encrypt(password, salt);
			System.out.println(encryptPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
