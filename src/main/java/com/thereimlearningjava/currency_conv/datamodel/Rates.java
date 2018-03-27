package com.thereimlearningjava.currency_conv.datamodel;
/**
 * A subclass needed to provide a consistent data while parsing historical Json nested structure into a POJO
 * @author Alex Sergeenko
 * @version 0.0.1
 * @since 03.2018
 */
public class Rates {
	
	private double RUB;
	
	/**Getter*/
	public double getRUB() {
		return RUB;
	}
	/**Setter*/
	public void setRUB(double RUB) {
		this.RUB = RUB;
	}
}
