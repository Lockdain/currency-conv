package com.thereimlearningjava.currency_conv.remote;

/**
 * An interface for UrlFactory
 * @author Alex Sergeenko
 * @version 0.0.1
 */
public interface UrlFactory {
	/**a setter for the fixer.io API key*/
	void setApiKey(String apiKey); 
	/**a getter for the fixer.io API key*/
	String getApiKey();
	/**a getter for the service URL*/
	String getBaseUrl();
	/**returns GET URL for buy rate*/
	String getBuyRateUrl();
	/**returns GET for sell rate*/
	String getSellRateUrl();
	
}
