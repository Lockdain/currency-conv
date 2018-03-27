package com.thereimlearningjava.currency_conv.datamodel;

import java.time.LocalDate;

/**
 * A basic interface for data object model
 * @author Alex Sergeenko
 * @version 0.0.1
 *
 */
interface DataCore { //package default access
	/**The setter for buy date*/
	void setPrevDate(LocalDate prevDate); //previous date setter
	/**The buy date getter*/
	LocalDate getPrevDate(); //previous date getter
	/**amount setter*/
	void setAmount(double amount); //amount setter
	/**amount getter*/
	double getAmount(); //amount getter
	/**spread setter*/
	void setSpread(double spread); //spread setter
	/**spread getter*/
	double getSpread(); //spread getter
	/**Purchase rate setter*/
	void setBuyRate(double buyRate); //buy rate setter
	/**Purchase rate getter*/
	double getBuyRate(); //buy rate getter
	/**Latest rate setter*/
	void setSellRate(double sellRate); //sell rate setter
	/**Latest rate getter*/
	double getSellRate(); //sell rate getter
	/**Result getter*/
	double getResult(); //calculate/recalculate method signature
	

}
