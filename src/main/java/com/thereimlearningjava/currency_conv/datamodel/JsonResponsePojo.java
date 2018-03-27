package com.thereimlearningjava.currency_conv.datamodel;

import org.apache.log4j.Logger;

/**
 * POJO class to map attributes from historical- and latest- type requests from fixer.io
 * @author Alex Sergeenko
 * @version 0.0.1
 */
public class JsonResponsePojo {
	
	private boolean success; //the request success flag
	private double timestamp;
	Rates rates; //the current rate
	
	private String base; //the base currency (EUR)
	private String date; //the date
	
	/**Log4j logger*/
	final static Logger logger = Logger.getLogger(JsonResponsePojo.class);
	
	public JsonResponsePojo(){
	}
	
	/**Setter*/
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**Getter*/
	public boolean getSuccess() {
		return success;
	}
	/**Setter*/
	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}
	/**Getter*/
	public double getTimestamp() {
		return timestamp;
	}
	/**Setter*/
	public void setBase(String base) {
		this.base = base;
	}
	/**Getter*/
	public String getBase() {
		return base;
	}
	/**Setter*/
	public void setDate(String date) {
		this.date = date;
	}
	/**Getter*/
	public String getDate() {
		return date;
	}
	/**Getter*/
	public Rates getRates() {
		return rates;
	}
	/**Folded setter (folded Json structure)*/
	public void setRates(Rates rates) {
		this.rates = rates;
	}
}
