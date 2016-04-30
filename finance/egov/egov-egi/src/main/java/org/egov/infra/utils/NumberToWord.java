/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.infra.utils;

import org.apache.commons.lang.StringUtils;
import org.egov.infra.exception.ApplicationRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.FieldPosition;

interface DefinePlace {
	long ZEROS = 0;
	long UNITS = 1;
	long TENS = 10 * UNITS;
	long HUNDREDS = 10 * TENS;
	long THOUSANDS = 10 * HUNDREDS;
	long TENTHOUSANDS = 10 * THOUSANDS;
	long LAKHS = 10 * TENTHOUSANDS;
	long TENLAKHS = 10 * LAKHS;
	long CRORES = 10 * TENLAKHS;
	long TENCRORES = 10 * CRORES;
	long HUNDREDCRORES = 10 * TENCRORES;
	long THOUSANDCRORES = 10 * HUNDREDCRORES;
	long TENTHOUSANDCRORES = 10 * THOUSANDCRORES;
}

/**
 * The Class NumberToWord.
 */
public class NumberToWord {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NumberToWord.class);

	/** The Constant CARDINAL. */
	private static final String[] CARDINAL = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Thirty",
			"Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety", "Hundred" };

	/**
	 * Format decimal values.
	 * @param value the value
	 * @return the string
	 */
	public static String formatDecimal(final Double value) {
		final DecimalFormat formater = new DecimalFormat("###0.00");
		final FieldPosition _fldPos = new FieldPosition(0);
		final StringBuffer _adaptor = new StringBuffer();
		formater.format(value, _adaptor, _fldPos);
		return _adaptor.toString();
	}

	/**
	 * Amount in words.
	 * @param number the number
	 * @return the string
	 */
	public static String amountInWords(final Double number) {
		return NumberToWord.convertToWord(formatDecimal(Double.valueOf(number)));
	}

	/**
	 * Number to string.
	 * @param strNumberToConvert the str number to convert
	 * @return the string
	 */
	public static String numberToString(final String strNumberToConvert) {
		if (StringUtils.contains(strNumberToConvert, ".")) {
			throw new ApplicationRuntimeException("Can not pass decimal values");
		} else {
			return NumberToWord.convertToWord(strNumberToConvert).replace("Rupees", "").replace("Only", "");
		}
	}

	/**
	 * Translate the given currency number to word .
	 * @param number the number
	 * @return the string
	 */
	public static String convertToWord(String number) {

		String paise = "";

		if (number.contains(".")) {
			final String[] splitPaise = number.split("[.]");
			if (splitPaise.length == 2) {
				if (!splitPaise[1].equals("00")) {
					paise = "and " + paiseInWords(splitPaise[1]) + " " + "Paise Only";
				}
			}
			number = splitPaise[0];
		}

		final String returnValue = translateToWord(number);

		return (paise.isEmpty() ? ("Rupees " + returnValue + " Only ") : ("Rupees " + returnValue + " " + paise));

	}

	/**
	 * Translate the given number to word. Decimal places not allowed, for decimal use {@see NumberToWord#convertToWord(String)}
	 * @param String number
	 * @return String word
	 **/
	public static String translateToWord(String number) {
		long num = 0L;
		try {
			num = Long.parseLong(number);
		} catch (final NumberFormatException e) {
			LOGGER.error("Invalid Number, Please enter a valid Number.");
			throw new ApplicationRuntimeException("Exception occurred in convertToWord", e);
		}

		Long subNum = 0L;
		String returnValue = "";

		if (Long.parseLong(number) == DefinePlace.ZEROS || number.length() > 12) {
			returnValue += getWord(Long.parseLong(number));
		}

		while (num > 0 && number.length() <= 12) {
			number = "" + num;
			final long place = getPlace(number);

			if (place == DefinePlace.HUNDREDCRORES || place == DefinePlace.THOUSANDCRORES || place == DefinePlace.TENTHOUSANDCRORES) {
				subNum = Long.parseLong("" + number.charAt(0));
				returnValue += getWord(subNum);
				if (place == DefinePlace.HUNDREDCRORES) {
					num -= subNum * DefinePlace.HUNDREDCRORES;
					if (num == 0) {
						returnValue += " Hundred Crores ";
					} else {
						returnValue += " Hundred ";
					}
				} else if (place == DefinePlace.THOUSANDCRORES) {
					num -= subNum * DefinePlace.THOUSANDCRORES;
					if (num == 0) {
						returnValue += " Thousand Crores ";
					} else {
						returnValue += " Thousand  ";
					}
				} else {
					returnValue = "";
					subNum = Long.parseLong(number.charAt(0) + "" + number.charAt(1));
					num -= subNum * DefinePlace.THOUSANDCRORES;
					if (subNum >= 21 && (subNum % 10) != 0 && num == 0) {
						returnValue += getWord(Long.parseLong(String.valueOf(number.charAt(0))) * 10) + " " + getWord(subNum % 10) + " Thousand Crores ";
					} else if (num == 0) {
						returnValue += getWord(Long.parseLong(String.valueOf(number.charAt(0))) * 10) + " " + " Thousand Crores ";
					} else {
						returnValue += getWord(Long.parseLong(String.valueOf(number.charAt(0))) * 10) + " " + getWord(subNum % 10) + " Thousand  ";
					}
				}

			} else if (place == DefinePlace.TENS || place == DefinePlace.TENTHOUSANDS || place == DefinePlace.TENLAKHS || place == DefinePlace.TENCRORES) {

				subNum = Long.parseLong(String.valueOf(number.charAt(0)) + String.valueOf(number.charAt(1)));

				if (subNum >= 21 && (subNum % 10) != 0) {
					returnValue += getWord(Long.parseLong(String.valueOf(number.charAt(0))) * 10) + " " + getWord(subNum % 10);
				} else {
					returnValue += getWord(subNum);
				}

				if (place == DefinePlace.TENS) {
					num = 0;
				} else if (place == DefinePlace.TENTHOUSANDS) {
					num -= subNum * DefinePlace.THOUSANDS;
					returnValue += " Thousands ";
				} else if (place == DefinePlace.TENLAKHS) {
					num -= subNum * DefinePlace.LAKHS;
					returnValue += " Lakhs ";
				} else if (place == DefinePlace.TENCRORES) {
					num -= subNum * DefinePlace.CRORES;
					returnValue += " Crores ";
				}

			} else {
				subNum = Long.parseLong(String.valueOf(number.charAt(0)));
				returnValue += getWord(subNum);
				if (place == DefinePlace.UNITS) {
					num = 0;
				} else if (place == DefinePlace.HUNDREDS) {
					num -= subNum * DefinePlace.HUNDREDS;
					returnValue += " Hundred ";
				} else if (place == DefinePlace.THOUSANDS) {
					num -= subNum * DefinePlace.THOUSANDS;
					returnValue += " Thousand ";
				} else if (place == DefinePlace.LAKHS) {
					num -= subNum * DefinePlace.LAKHS;
					returnValue += " Lakh ";
				} else if (place == DefinePlace.CRORES) {
					num -= subNum * DefinePlace.CRORES;
					returnValue += " Crore ";
				}
			}
		}
		return returnValue;
	}

	/**
	 * Normalize the unformatted number by removing comma and space.
	 * @param number the number
	 * @return the string
	 */
	private static String normalize(final String number) {
		final String cleanedNumber = number.replace(',', ' ').replaceAll(" ", "");
		if (number.length() > 1 && cleanedNumber.startsWith("0")) {
			return cleanedNumber.replaceFirst("0", "");
		}
		return cleanedNumber;
	}

	/**
	 * this method returns the place where the number exists. it does by checking number length. Gets the place.
	 * @param number the number
	 * @return the place
	 */
	private static long getPlace(final String number) {
		switch (number.length()) {

		case 1:
			return DefinePlace.UNITS;
		case 2:
			return DefinePlace.TENS;
		case 3:
			return DefinePlace.HUNDREDS;
		case 4:
			return DefinePlace.THOUSANDS;
		case 5:
			return DefinePlace.TENTHOUSANDS;
		case 6:
			return DefinePlace.LAKHS;
		case 7:
			return DefinePlace.TENLAKHS;
		case 8:
			return DefinePlace.CRORES;
		case 9:
			return DefinePlace.TENCRORES;
		case 10:
			return DefinePlace.HUNDREDCRORES;
		case 11:
			return DefinePlace.THOUSANDCRORES;
		case 12:
			return DefinePlace.TENTHOUSANDCRORES;
		}
		return 0;
	}

	/**
	 * Gets the word.
	 * @param number the number
	 * @return the word
	 */
	public static String getWord(final Long number) {
		final int value = number.intValue();
		switch (value) {
		case 30:
			return CARDINAL[21];
		case 40:
			return CARDINAL[22];
		case 50:
			return CARDINAL[23];
		case 60:
			return CARDINAL[24];
		case 70:
			return CARDINAL[25];
		case 80:
			return CARDINAL[26];
		case 90:
			return CARDINAL[27];
		case 100:
			return CARDINAL[28];
		default:
			if ((value < 21)) {
				return CARDINAL[value];
			} else {
				return "";
			}
		}

	}

	/**
	 * Paise in words.
	 * @param paise the paise
	 * @return the string
	 */
	private static String paiseInWords(final String paise) {

		Long subNum = 0L;
		String returnValue = "";

		if (paise.length() >= 2) {
			subNum = Long.parseLong(paise.charAt(0) + "" + paise.charAt(1));
		} else {
			subNum = Long.parseLong(paise.charAt(0) + "");
		}

		if (subNum >= 21 && (subNum % 10) != 0) {
			returnValue += getWord(Long.parseLong("" + paise.charAt(0)) * 10) + " " + getWord(subNum % 10);

		} else {
			returnValue += getWord(subNum);

		}

		return returnValue;
	}
}
