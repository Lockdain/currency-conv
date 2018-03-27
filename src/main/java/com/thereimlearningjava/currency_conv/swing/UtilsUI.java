package com.thereimlearningjava.currency_conv.swing;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utils using for UI validation
 * @author Sergeenko
 * @version 0.0.1
 *
 */
public class UtilsUI {
	/**
	 * Check if input string is suitable for the parsing to double via regex pattern
	 * @param input string
	 * @return boolean
	 * @author Sergeenko
	 */
	public static boolean isDouble(String input){
		
		Pattern pattern = Pattern.compile("^[-+]?\\d+(\\.{0,1}(\\d+?))?$");
		Matcher matcher = pattern.matcher(input);
		boolean result = matcher.matches();
		
		return result;
		
	}
	/**
	 * Returns true if input LocalDate from Date picker is from future
	 * @author Sergeenko
	 * @param localDate
	 * @return boolean
	 */
	public static boolean isFutureDate(LocalDate localDate) {		
		Date dateFrom = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateNow = new Date();
		
		if(dateFrom.getTime() > dateNow.getTime()) {
			return true;
		} 
		else return false;	
		
	}
}
