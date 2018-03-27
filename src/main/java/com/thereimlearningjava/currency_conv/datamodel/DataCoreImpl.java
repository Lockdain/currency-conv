package com.thereimlearningjava.currency_conv.datamodel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

/**
 * An implementation of main data object model which incapsulates all project data
 * @author Alex Sergeenko
 * @version 0.0.1
 * @since 03.26.18
 */
public class DataCoreImpl implements DataCore {
	
	private LocalDate prevDate; //the previous date
	private double spread; //spread value
	private double amount; //amount
	private double result; //calculated result
	private double buyRate; //buying rate (then)
	private double sellRate; //sell rate (now)
	
	/**Log4j logger*/
	final static Logger logger = Logger.getLogger(DataCoreImpl.class);
	
	/**
	 * Default constructor
	 */
	public DataCoreImpl(){	
	}
	
	/**
	 * An extended constructor
	 * @param spread
	 * @param amount
	 * @param result
	 */
	DataCoreImpl(LocalDate prevDate, double spread, double amount, double result){
		
		this.prevDate = prevDate;
		this.spread = spread;
		this.amount = amount;
		this.result = result;
		
	}
	
	/**
	 * There are implementations of setters/getters methods inherited from the interface
	 */
	public void setPrevDate(LocalDate localDate) {
		// TODO Auto-generated method stub
		this.prevDate = localDate;
		
	}

	public LocalDate getPrevDate() {
		// TODO Auto-generated method stub
		return prevDate;
	}
	/**
	 * Returns current date LocalDate object using by UrlFactory. Actually doesn't use because of 'latest' fixer.io request
	 * 
	 */
	public static LocalDate getCurrDate() { 
		// TODO Auto-generated method stub
		LocalDateTime currDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE; // this is for "YYYY-MM-DD" pattern
		String text = currDate.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);
		
		return parsedDate;
	}
	/**
	 * Returns date from string object in ISO Local Date format (suitable for fixer.io)
	 * @param String with a date in dd.MM.yyyy
	 * @return LocalDate
	 * @author Sergeenko 
	 */
	public static LocalDate createIsoDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // this is for "dd.MM.yyyy" pattern from the Swing JDatePicker
		
		if(!(dateStr == "") || !dateStr.isEmpty()) { //have to check if the input string is not a waste
			LocalDate date = LocalDate.parse(dateStr, formatter);
			return date;
		} else {
			logger.error("The input string for the createIsoDate() was empty or null");
			throw new NullPointerException("Error: Input string is null"); //or throw a new exception
		}
		
	}
	/**Amount setter*/
	public void setAmount(double amount) {
		// TODO Auto-generated method stub
		this.amount = amount;
	}
	/**Amount getter*/
	public double getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}
	/**Spread setter*/
	public void setSpread(double spread) {
		// TODO Auto-generated method stub
		this.spread = spread;
	}
	/**Spread getter*/
	public double getSpread() {
		// TODO Auto-generated method stub
		return spread;
	}
	/**Setter*/
	public void setBuyRate(double buyRate) {
		// TODO Auto-generated method stub
		this.buyRate = buyRate;
	}
	/**Getter*/
	public double getBuyRate() {
		// TODO Auto-generated method stub
		return buyRate;
	}
	/**Setter*/
	public void setSellRate(double sellRate) {
		// TODO Auto-generated method stub
		this.sellRate = sellRate;
	}
	/**Getter*/
	public double getSellRate() {
		// TODO Auto-generated method stub
		return sellRate;
	}
	/**Calculate the final profit*/
	public double getResult() { //calculating the final profit/loss
		// TODO Auto-generated method stub
		result = amount * ((sellRate * (1 - spread / 100))  - (buyRate * (1 + spread / 100))); //can be also < 0
		//logger.debug("Resulted profit was calculated successfully!");
		return result;
	}

}
