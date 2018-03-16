package date;

import java.time.LocalDateTime;

public class DateStamp {

	private LocalDateTime fullDate = LocalDateTime.now();

	private String day = addZero(fullDate.getDayOfMonth());
	private String month = addZero(fullDate.getMonthValue());
	private String year = addZero(fullDate.getYear());

	private String hour = addZero(fullDate.getHour());
	private String min = addZero(fullDate.getMinute());
	private String sec = addZero(fullDate.getSecond());

	private String date = day + "." + month + "." + year;
	private String time = hour + ":" + min + ":" + sec;

	private String dateTimeStamp = date + " (" + time + " Uhr)";

	
	/**
	 * Fügt eine Null eine, wenn der Wert kleiner als 10 ist.
	 * 
	 * @param val
	 * @return
	 */
	private String addZero(int val) {
		if (val < 10) {
			return "0" + String.valueOf(val);
		}
		return String.valueOf(val);
	}
	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################
	/**
	 * Gibt ein Datum zurück.
	 * 
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Gibt eine Zeit zurück.
	 * 
	 * @return
	 */
	public String getTime() {
		return time;
	}
	/**
	 * Gibt ein Datum mit Zeit zurück.
	 * 
	 * @return
	 */
	public String getDateTimeStamp() {
		return dateTimeStamp;
	}
}
