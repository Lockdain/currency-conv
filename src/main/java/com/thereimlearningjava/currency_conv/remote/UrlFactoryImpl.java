package com.thereimlearningjava.currency_conv.remote;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDate;

import org.apache.log4j.Logger;

/**
 * An implementation of UrlFactory
 * @author Alex Sergeenko
 * @version 0.0.1
 */
public class UrlFactoryImpl implements UrlFactory{
	/**
	 * String variables defining connection
	 */
	private String apiKey = "4f199d3f722b29e804a12924fd9156c9"; //API user key for http://fixer.io
	private static final String baseUrl = "http://data.fixer.io/api/"; //base URL for http://fixer.io
	
	private String buyRateUrl; // buy rate
	private String sellRateUrl; // sell rate
	private LocalDate buyDate; //buy date from JDatePicker
	
	/**Log4j logger*/
	final static Logger logger = Logger.getLogger(UrlFactoryImpl.class);

	/**
	 * Further methods are getters/setters
	 */
	public void setBuyDate(LocalDate buyDate) {
		this.buyDate = buyDate;
	}
	public LocalDate getBuyDate() {
		return buyDate;
	}
	
	public void setApiKey(String apiKey) {
		// TODO Auto-generated method stub
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		// TODO Auto-generated method stub
		return apiKey;
	}

	public String getBaseUrl() {
		// TODO Auto-generated method stub
		return baseUrl;
	}
	/**
	 * Returning URLs for http://fixer.io
	 */
	public String getBuyRateUrl() { // do not forget to use a relevant 'create' method before use it!
		// TODO Auto-generated method stub
		return buyRateUrl;
	}

	public String getSellRateUrl() { // do not forget to use a relevant 'create' method before use it!
		// TODO Auto-generated method stub
		return sellRateUrl;
	}
	/**
	 * Concatenate URL for fixer.io current (sell) request
	 */
	public void createSellUrl() { //do not forget - it is LATEST, without a date
		StringBuilder sb = new StringBuilder(this.getBaseUrl());
		sellRateUrl = sb.append("latest").append("?access_key=").append(this.getApiKey()).append("&base=").append("EUR").append("&symbols=").append("RUB").toString();
		System.out.println("The URL for current rates: " + sellRateUrl);
		logger.debug("The URL for current rates: " + sellRateUrl);
	}
	/**
	 * Concatenate URL for fixer.io past (buy) historical request
	 */
	public void createBuyRateUrl() {
		StringBuilder sb = new StringBuilder(this.getBaseUrl());
		if (buyDate!= null) {
			sb.append(buyDate.toString()).append("?").append("access_key=").append(apiKey).append("&").append("base=EUR&symbols=RUB");
			buyRateUrl = sb.toString();
			logger.debug("The URL for buy rate is: " + buyRateUrl);
		} else throw new NullPointerException("ERROR: buyDate is not defined in the class RemoteRequestImpl");
	}
	
	/**Check if fixer.io website is available*/
	public static boolean isServiceAvailable() {
		Socket sock = new Socket();
	    InetSocketAddress addr = new InetSocketAddress("www.fixer.io", 80);
	    try {
	        sock.connect(addr,3000);
	        return true;
	    } catch (IOException e) {
	        return false;
	    } finally {
	        try {sock.close();}
	        catch (IOException e) {}
	    }
	}	
}
