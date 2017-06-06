/**
 * All rights reserved.hCentive.
 */
package com.hcentive.hackathon.commons.utils;

import org.apache.commons.lang.StringUtils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * @author Nitin.Gupta
 * 
 */
public class PhoneNumberUtils {

	public static String addPrefix(String phoneNumber) {

		String prefixes[] = new String[] { "0091", "+91 ", "+91-", "+91",
				"091", "00", "0", "+" };

		StringBuffer sb = new StringBuffer();

		phoneNumber = phoneNumber.replaceAll("[-+.^:,]", "");
		phoneNumber = phoneNumber.trim();

		for (int i = 0; i < prefixes.length; i++) {
			if (StringUtils.startsWith(phoneNumber, prefixes[i])) {
				sb.append("91");
				sb.append((StringUtils.substringAfter(phoneNumber, prefixes[i]))
						.trim());

				return sb.toString();
			}
		}

		return phoneNumber;
	}

	public static String getValidPhoneNumber(String phoneNumber,
			String countryCode) {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try {
			PhoneNumber _phoneNumber = phoneUtil
					.parse(phoneNumber, countryCode);

			if (phoneUtil.isValidNumber(_phoneNumber)) {
				return StringUtils.replace(
						phoneUtil.format(_phoneNumber, PhoneNumberFormat.E164),
						"+", "");
			}

		} catch (NumberParseException e) {
		}

		return null;
	}

	public static void main(String[] args) {

		System.out.println(PhoneNumberUtils.getValidPhoneNumber("09716017701",
				"IN"));
		System.out.println(PhoneNumberUtils.getValidPhoneNumber(
				"+91 - 9716017701", "IN"));
		System.out.println(PhoneNumberUtils.getValidPhoneNumber(
				"+91 -9716017701", "IN"));
		System.out.println(PhoneNumberUtils.getValidPhoneNumber(
				"+91- 9716017701", "IN"));
		System.out.println(PhoneNumberUtils.getValidPhoneNumber(
				"+91- 9716017701", "IN"));
		System.out.println(PhoneNumberUtils.getValidPhoneNumber(
				"+91-9716017701", "IN"));
		System.out.println(PhoneNumberUtils.getValidPhoneNumber(
				"00919716017701", "IN"));
		System.out.println(PhoneNumberUtils.getValidPhoneNumber(
				"+919716017701", "IN"));
		System.out.println(PhoneNumberUtils.getValidPhoneNumber(
				"0919716017701", "IN"));
		System.out.println(PhoneNumberUtils.getValidPhoneNumber("919716017701",
				"IN"));

		/*
		 * System.out.println(PhoneNumberUtils.addPrefix("09716017701"));
		 * System.out.println(PhoneNumberUtils.addPrefix("+91 - 9716017701"));
		 * System.out.println(PhoneNumberUtils.addPrefix("+91 -9716017701"));
		 * System.out.println(PhoneNumberUtils.addPrefix("+91- 9716017701"));
		 * System.out.println(PhoneNumberUtils.addPrefix("+91-9716017701"));
		 * System.out.println(PhoneNumberUtils.addPrefix("00919716017701"));
		 * System.out.println(PhoneNumberUtils.addPrefix("+919716017701"));
		 * System.out.println(PhoneNumberUtils.addPrefix("0919716017701"));
		 * System.out.println(PhoneNumberUtils.addPrefix("+9716017701"));
		 * System.out.println(PhoneNumberUtils.addPrefix("919716017701"));
		 */

	}
}
