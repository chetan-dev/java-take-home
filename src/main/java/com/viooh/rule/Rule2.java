package com.viooh.rule;

import com.viooh.checkout.Item;
import com.viooh.checkout.Rule;

import java.util.ArrayList;
import java.util.HashMap;

// Rule2: buy 2 equal priced items for a special price
public class Rule2 implements Rule {

//	String param1;
//	String param2;
	String itemId;
	Double specialPrice;

	@Override
	public Double getBestSingleDiscount(ArrayList<Item> items) {
		Double result = null;

		// Create a map from Price -> Number Of Items
		HashMap<Double, Integer> m = new HashMap<>();
		int itemCount = 0;

		for (Item i : items) {
			Double itemPrice = i.getItemPrice();
			// Ignore items that have already been discounted
			if (i.getItemDiscounted() != null && i.getItemDiscounted() == Boolean.TRUE) {
				continue;
			}

			// We are only interested in items that match rule's item id.
			if (i.getItemId().equals(itemId)) {
				itemCount++;
				if (itemCount >= 2) {
					// We found 2 or more items that have this price, so we can get them for special. Discount
					// will be equal to 2 * itemPrice - specialPrice.
					result = i.getItemPrice() * 2 - specialPrice;
					break;
				}
			}

		}
		return result;
	}

	@Override
	public ArrayList<Item> applyBestSingleDiscount(ArrayList<Item> items) {
		Double bestDiscount = getBestSingleDiscount(items);
		ArrayList<Item> result = new ArrayList<>();

		int count = 0;
		for (Item i : items) {
			if ((i.getItemDiscounted() == null || i.getItemDiscounted() == Boolean.FALSE) && i.getItemId().equals(itemId)) {
				count++;
				i.setItemDiscounted(Boolean.TRUE);
				i.setItemDiscount(specialPrice / 2);
				result.add(i);
				if (count == 2) {
					return result;
				}
			}
		}
		return result;
	}

	@Override
	public void initialize(String name, String param1, String param2, String param3, String param4) throws Exception {
		if (name.equals("Rule2")) {
			//load parameters

			itemId = param1;
			specialPrice = Double.valueOf(param2);
		} else {
			throw new Exception("This is Rule2 and not" + name);
		}
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(Double specialPrice) {
		this.specialPrice = specialPrice;
	}
//
//	public String getParam1() {
//		return param1;
//	}
//
//	public void setParam1(String param1) {
//		this.param1 = param1;
//	}
//
//	public String getParam2() {
//		return param2;
//	}
//
//	public void setParam2(String param2) {
//		this.param2 = param2;
//	}
}
