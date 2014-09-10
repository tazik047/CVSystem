package ua.nure.pi.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for hashing string values.
 * 
 * @author Volodymyr_Semrkov
 */
public class Hashing {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * Hashing string
	 * 
	 * @param str
	 *            String value.
	 * @return Hashing string value.
	 */
	public static String hash(String str) {
		MessageDigest digest;
		StringBuilder hexString = new StringBuilder();
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new IllegalStateException("Cannot hash password");
		}
		for (byte d : digest.digest()) {
			hexString.append(getFirstHexDigit(d)).append(getSecondHexDigit(d));
		}
		return hexString.toString();
	}

	/**
	 * String salting.
	 * 
	 * @param str
	 *            - String for salting.
	 * @param salt
	 *            - Salt.
	 * @return Hashing string value.
	 */
	public static String salt(String str, String salt) {
		return Hashing.hash(Hashing.hash(str) + salt);
	}

	/**
	 * Get first digit.
	 * 
	 * @param x
	 *            Byte value.
	 * @return Char value.
	 */
	private static char getFirstHexDigit(byte x) {
		return HEX_DIGITS[(0xFF & x) / 16];
	}

	/**
	 * Get second digit.
	 * 
	 * @param x
	 *            Byte value.
	 * @return Char value.
	 */
	private static char getSecondHexDigit(byte x) {
		return HEX_DIGITS[(0xFF & x) % 16];
	}
}
