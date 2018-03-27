package com.thereimlearningjava.currency_conv.UIValidation;
/**
 * Validate input fields for the correct values
 * @author Sergeenko
 * @version 0.0.1
 */
public  class FieldValidator {

	private static boolean amountIsDefined;
	private static boolean datePickerIsDefined;
	private static boolean amountIsDouble;
	private static boolean datePickerIsNotFuture;
	private static boolean spreadIsDefined;
	/**Setter*/
	public static void spreadIsDefined(boolean b) {
		spreadIsDefined = b;
	}
	/**Variables setter*/
	public static void amountIsDefined(boolean b) {
		amountIsDefined = b;
	}
	/**VariableSetter*/
	public static void datePickerIsNotFuture(boolean b) {
		datePickerIsNotFuture = b;
	}

	/**Variables setter*/
	public static void datePickerIsDefined(boolean b) {
		datePickerIsDefined = b;
	}
	
	/**Variables setter*/
	public static void amountIsDouble(boolean b) {
		amountIsDouble = b;
	}
	
	/**If all flags are true - validate true*/
	public static boolean validated() {
		if(amountIsDefined & datePickerIsDefined & amountIsDouble & datePickerIsNotFuture & spreadIsDefined) return true;
		else return false;
	}
	
	/**Set all validation variables to false*/
	public static void purgeValidators() {
		amountIsDefined = false;
		datePickerIsDefined = false;
		amountIsDouble = false;
		datePickerIsNotFuture = false;
		spreadIsDefined = false;
	}
}
