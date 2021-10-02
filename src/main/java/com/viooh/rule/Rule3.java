package com.viooh.rule;

import com.viooh.checkout.Item;
import com.viooh.checkout.Rule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

// Rule3: buy 3 (in a group of items) and the cheapest is free
public class Rule3 implements Rule {


	@Override
	public Double getBestSingleDiscount(ArrayList<Item> items) {
		Double result = null;
		ArrayList<Item> bestGroup = getGroupWithBestDiscount(items);
		if (bestGroup != null) {
			result = bestGroup.get(2).getItemPrice();
		}
		return result;
	}

	ArrayList<Item> getGroupWithBestDiscount(ArrayList<Item> items) {
		Double result = null;
		ArrayList<Item> bestGroup = null;

		// Create a map from Group -> Items of that group that haven't been discounted yet
		HashMap<String, ArrayList<Item>> m = new HashMap<>();

		for (Item i : items) {
			String group = i.getItemGrp();
			// Ignore items that have already been discounted
			if (i.getItemDiscounted() != null && i.getItemDiscounted() == Boolean.TRUE) {
				continue;
			}
			if (m.get(group) == null) {
				ArrayList<Item> l = new ArrayList<>();
				l.add(i);
				m.put(group, l);
			} else {
				m.get(group).add(i);
			}
		}
		// Go through all groups with with 3 or more searching for a group with best discount

		for (ArrayList<Item> groupedItems : m.values()) {
			if (groupedItems.size() < 3) {
				continue;
			}
			groupedItems.sort(new Comparator<Item>() {
				@Override
				public int compare(Item o1, Item o2) {
					if (o1.getItemPrice() > o2.getItemPrice()) {
						return -1;
					} else if (o1.getItemPrice() < o2.getItemPrice()) {
						return 1;
					} else {
						return 0;
					}
				}
			});
			// Get cheapest of 3 items in a group - this will be our discount for this group
			Double p = groupedItems.get(2).getItemPrice();
			if (result == null || p > result) {
				result = p;
				bestGroup = groupedItems;
			}
		}

		return bestGroup;
	}

	@Override
	public ArrayList<Item> applyBestSingleDiscount(ArrayList<Item> items) {
		ArrayList<Item> result = new ArrayList<>();
		ArrayList<Item> bestGroup = getGroupWithBestDiscount(items);
		result.add(bestGroup.get(0));
		result.add(bestGroup.get(1));
		result.add(bestGroup.get(2));
		result.get(0).setItemDiscounted(true);
		result.get(1).setItemDiscounted(true);
		result.get(2).setItemDiscounted(true);
		result.get(2).setItemDiscount(bestGroup.get(2).getItemPrice());
		return result;
	}

	//removed non required method
}
