package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExValidator {

	private Pattern pattern;
	private Matcher matcher;

	/*
	 * ^ = nicht 0 oder 1
	 */
	private static final String IPADDRESS_PATTERN = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}"
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";

	// ein bis 5 Zahlen
	private static final String ONLY_NUMBER_PATTERN = "\\d{1,5}";

	/**
	 * Validiert eine IP-Adresse mit RegEx.
	 * 
	 * @return true gültige IP, false ungültige IP address
	 */
	public boolean validateIP(String valueToCheck) {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		matcher = pattern.matcher(valueToCheck);
		return matcher.matches();
	}

	/**
	 * Validiert eine IP-Adresse mit RegEx.
	 * 
	 * @return true gültige IP, false ungültige IP address
	 */
	public boolean validateOnlyNumField(String valueToCheck) {
		pattern = Pattern.compile(ONLY_NUMBER_PATTERN);
		matcher = pattern.matcher(valueToCheck);
		return matcher.matches();
	}
}
