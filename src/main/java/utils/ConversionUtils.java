package utils;


import java.util.Random;

public class ConversionUtils {


	public static String convertNullObjToString(Object value) {
		return value == null ? "" : String.valueOf(value);
	}

	/**
	 * Appends a random number to the username part of an email address.
	 *
	 * @param email The original email address.
	 * @return The modified email address with a random number appended to the username.
	 */
	public static String appendRandomNumberToEmail(String email) {
		// Split the email into username and domain parts
		String[] emailParts = email.split("@");
		if (emailParts.length != 2)
			throw new IllegalArgumentException("Invalid email address");
		String username = emailParts[0];
		String domain = emailParts[1];
		Random random = new Random();
		int randomNumber = 100000 + random.nextInt(900000); // Generates a 6-digit random number
		return username + randomNumber + "@" + domain;
	}


}
