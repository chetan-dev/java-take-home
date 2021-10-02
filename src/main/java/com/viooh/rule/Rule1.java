package com.viooh.rule;

import com.viooh.checkout.Item;
import com.viooh.checkout.Rule;

import java.util.ArrayList;
import java.util.HashMap;

//Rule1: buy any 3 equal priced items and pay for 2
public class Rule1 implements Rule {



	@Override
	public Double getBestSingleDiscount(ArrayList<Item> items) {
		Double result = null;

		// Create a map from Price -> Number Of Items
		HashMap<Double, Integer> m = new HashMap<>();

		for (Item i : items) {
			Double itemPrice = i.getItemPrice();
			// Ignore items that have already been discounted
			if (i.getItemDiscounted() != null && i.getItemDiscounted() == Boolean.TRUE) {
				continue;
			}
			if (m.get(itemPrice) == null) {
				m.put(itemPrice, 1);
			} else {
				Integer samePriceCount = m.get(itemPrice);
				samePriceCount = samePriceCount + 1;
				m.put(itemPrice, samePriceCount);
				if (samePriceCount >= 3) {
					// We found 3 or more items that have this price, so we can get 1 item free. Discount
					// will be equal to this price. If the discount is larger then best discount found so far
					// this will be our new best result.
					if (result == null || itemPrice > result) {
						result = itemPrice;
					}
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
			if ((i.getItemDiscounted() == null || i.getItemDiscounted() == Boolean.FALSE) && i.getItemPrice().equals(bestDiscount)) {
				count++;
				i.setItemDiscounted(Boolean.TRUE);
				result.add(i);
				if (count == 3) {
					i.setItemDiscount(bestDiscount);
					break;
				}
			}
		}
		return result;
	}

	//removed non required method
}
