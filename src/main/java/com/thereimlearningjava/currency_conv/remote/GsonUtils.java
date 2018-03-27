package com.thereimlearningjava.currency_conv.remote;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.thereimlearningjava.currency_conv.datamodel.JsonResponsePojo;

public class GsonUtils {
	
	/**Log4j*/
	final static Logger logger = Logger.getLogger(GsonUtils.class);
	
	/**
	 * Returns a Json string from the given URL
	 * @return String Json
	 * @author Sergeenko
	 * @since 26.03.2018
	 */
	public static String getJsonStringFromURL(String url) {
		
		RestTemplate restTemplate = new RestTemplate(); //will use Spring RestTemplate to get the Json string
		
		/**Check if the URL is null*/
		if (url == null || (url.isEmpty())) {
			
			restTemplate = null; //says Hi! to the GC
			logger.error("The method getJsonStringFromURL has received an empty or null parameter");
			System.out.println("Error: getJsonStringFromUrl method returned null");
			throw new RuntimeException("Null or empty string was received");
			
		} else if(UrlFactoryImpl.isServiceAvailable()){ /**check if API website is available*/
			logger.debug("fixer.io is available and connection exists");
			String response = restTemplate.getForObject(url, String.class); //parse the response

			return response;
		} else {
			System.out.println("Fixer.io website is unavailable, please check your Internet connection");
			restTemplate = null; //say Hi to the GC
			logger.error("Fixer.io website is unavailable, please check your Internet connection");
			throw new RuntimeException("No internet connection available or API provider is down");
		}
	}
	/**
	 * Returns an instance of mapped POJO from the given Json string
	 * @param response
	 * @return JsonHistPojo
	 * @version 0.0.1
	 */
	public static JsonResponsePojo parseResponse(String response) {
		Gson gson = new Gson();
		JsonResponsePojo jhp = new JsonResponsePojo();
		jhp = gson.fromJson(response, JsonResponsePojo.class); //parse the response to the combined JsonHistPojo + Rates instance
		
		/**We have to check if request was successful*/
		if(!jhp.getSuccess()) { //if it wasn't successful
			jhp = null; //mark it for the GC
			System.out.println("The fixer.io request was not successfull. See the response: " + response);
			logger.error("fixer.io sent a reply marked as not successed: " + response);
			throw new RuntimeException("The API request was not successful, check your URL");
		}
		else {
		return jhp;		
		}
	}
	
}
