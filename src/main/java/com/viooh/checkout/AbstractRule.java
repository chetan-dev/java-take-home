//package com.viooh.checkout;
//
//import java.util.ArrayList;
//
//public abstract class AbstractRule {
//
//	public abstract Double getBestSingleDiscount(ArrayList<Item> items);
//
//	public abstract ArrayList<Item> applyBestSingleDiscount(ArrayList<Item> items);
//
//	public abstract void initialize(String name, String param1, String param2, String param3, String param4) throws Exception;
//
//}


/* commented this abstract class and created an interface instead with default method and
instead of extending the rules to this abstract , made all the rules to implement new interface Rule */