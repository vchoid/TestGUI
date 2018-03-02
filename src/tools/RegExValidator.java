package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	private static final String ONE_IP_FIELD_PATTERN = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";

	/**
	 * Validiert eine  IP-Adresse mit RegEx.
	 * @return true gültige IP, false ungültige IP address
	 */
	public boolean validateIP(String valueToCheck) {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		matcher = pattern.matcher(valueToCheck);
		return matcher.matches();
	}
	
	/**
	 * Validiert eine  IP-Adresse mit RegEx.
	 * @return true gültige IP, false ungültige IP address
	 */
	public boolean validateOneIPField(String valueToCheck) {
		pattern = Pattern.compile(ONE_IP_FIELD_PATTERN);
		matcher = pattern.matcher(valueToCheck);
		return matcher.matches();
	}

}
