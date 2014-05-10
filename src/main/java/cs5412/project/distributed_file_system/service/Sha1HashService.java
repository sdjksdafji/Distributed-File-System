package cs5412.project.distributed_file_system.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1HashService {

	private static final String HASH_SALT = "CS_5412";

	public static String sha1(String input) {
		String str = input + HASH_SALT;
		MessageDigest mDigest = null;
		try {
			mDigest = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] result = mDigest.digest(str.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}
}
