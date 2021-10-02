package com.viooh.main;

import com.viooh.checkout.CheckOut;
import com.viooh.checkout.Loader;

/**
 * Main class
 */
public class Main {

	Loader loader;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//------------ MAIN FUNCTION -------------------//
		CheckOut checkOut = new CheckOut();
		Loader loader = new Loader();
		loader.setItemsFileName((args.length > 0)? args[0] : "items.csv");
		loader.setRulesFileName((args.length > 1)? args[1] : "rules.csv");
		checkOut.setLoader(loader);
		checkOut.doCheckout();
	}
}
