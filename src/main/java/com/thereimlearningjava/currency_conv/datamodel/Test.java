package com.thereimlearningjava.currency_conv.datamodel;

import java.text.ParseException;
import java.time.LocalDate;

import com.thereimlearningjava.currency_conv.remote.UrlFactoryImpl;
import com.thereimlearningjava.currency_conv.swing.UtilsUI;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		UrlFactoryImpl rri = new UrlFactoryImpl();
		rri.createSellUrl();
		System.out.println(rri.getSellRateUrl());
		
		LocalDate dt = DataCoreImpl.createIsoDate("28.03.2011");
		System.out.println(dt);
		
		System.out.println(UtilsUI.isDouble("55555.5555"));
	}

}
