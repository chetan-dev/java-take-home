package com.viooh.rule;

import com.viooh.checkout.Item;
import com.viooh.checkout.Rule;

import java.util.ArrayList;

// Rule4: for each N items X, you get K items Y for free
public class Rule4 implements Rule {
	int n;
	String x;
	int k;
	String y;

	@Override
	public Double getBestSingleDiscount(ArrayList<Item> items) {
		Double result = null;
		Double price = null;

		// count of item x
		int a = 0;
		// count of item y
		int b = 0;

		for (Item i : items) {
			// Ignore items that have already been discounted
			if (i.getItemDiscounted() != null && i.getItemDiscounted() == Boolean.TRUE) {
				continue;
			}
			if (i.getItemId().equals(x)) {
				a++;
			}
			if (i.getItemId().equals(y)) {
				b++;
				price = i.getItemPrice();
			}
		}

		// Check if we have more than N items X and more than K items Y.
		if (a>=n&&b>=k)  {
			result = price * k;
		}
		return result;
	}

	@Override
	public ArrayList<Item> applyBestSingleDiscount(ArrayList<Item> items) {
		ArrayList<Item> result = new ArrayList<>();
		// count of item x
		int a = 0;
		// count of item y
		int b = 0;

		for (Item i : items) {
			// Ignore items that have already been discounted
			if (i.getItemDiscounted() != null && i.getItemDiscounted() == Boolean.TRUE) {
				continue;
			}
			if (i.getItemId().equals(x) && a < n) {
				a++;
				i.setItemDiscounted(true);
				result.add(i);
			}
			if (i.getItemId().equals(y) && b < k) {
				b++;
				i.setItemDiscounted(true);
				i.setItemDiscount(i.getItemPrice());
				result.add(i);
			}
		}
		return result;
	}

	@Override
	public void initialize(String name, String param1, String param2, String param3, String param4) throws Exception {
		if (name.equals("Rule4")) {
			//load parameters
			setN(Integer.valueOf(param1));
			setX(param2);
			setK(Integer.valueOf(param3));
			setY(param4);

		} else {
			throw new Exception("This is Rule4 and not" + name);
		}
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
}
