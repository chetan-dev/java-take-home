package com.viooh.checkout;

import com.viooh.rule.Rule1;
import com.viooh.rule.Rule2;
import com.viooh.rule.Rule3;
import com.viooh.rule.Rule4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Loader {

	String itemsFileName;
	String rulesFileName;
	ArrayList<Rule> rules = new ArrayList<>();
	ArrayList<Item> items = new ArrayList<>();

	public Loader() {
		//------ RULES SETUP -----------//
		// Adding new rules is easy. Just extend AbstractRule and the new rule to the list below.
		rules.add(new Rule1());
		rules.add(new Rule2());
		rules.add(new Rule3());
		rules.add(new Rule4());
		//------ END RULES SETUP -------//
	}

	/**
	 * Loads files
	 */
	public void load() {
		//-------- LOAD RULES ----------//
		File file = new File(rulesFileName);
		System.out.println("Loading rules from " + file.getAbsolutePath());
		ArrayList<Rule> results = null;
		BufferedReader b = null;
		String line = null;
		if (file.exists()) {
			try {
				b = new BufferedReader(new FileReader(file));
				line = b.readLine();
				while (line != null) {
					String[] p = line.split(",");
					System.out.println("Loading rule " + p[0]);
					for (int i = 0; i < rules.size(); i++) {
						try {
							rules.get(i).initialize(
									p[0],
									(p.length > 1)? p[1] : null,
									(p.length > 2)? p[2] : null,
									(p.length > 3)? p[3] : null,
									(p.length > 4)? p[4] : null
							);
						} catch (Exception e) {

						}
					}
					line = b.readLine();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}

		} else {
			System.out.println("File " + file + " does not exist");
		}

		//--------------- LOAD ITEMS ----------//
		file = new File(itemsFileName);
		System.out.println("Loading items from " + file.getAbsolutePath());
		Item item = null;
		if (file.exists()) {
			try {
				items = new ArrayList<>();
				b = new BufferedReader(new FileReader(file));
				line = b.readLine();
				while (line != null) {
					if (line.startsWith("item-id")) {
						System.out.println("Skipping the header");
						line = b.readLine();
						continue;
					}
					System.out.println("Loading item");
					String[] p = line.split(",");
					item = new Item();
					item.setItemId(p[0]);
					item.setItemGrp(p[1]);
					item.setItemQty(Integer.valueOf(p[2]));
					item.setItemPrice(Double.valueOf(p[3]));
					items.add(item);
					line = b.readLine();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}

		} else {
			System.out.println("File " + file + " does not exist");
		}
	}

	public String getItemsFileName() {
		return itemsFileName;
	}

	public void setItemsFileName(String itemsFileName) {
		this.itemsFileName = itemsFileName;
	}

	public String getRulesFileName() {
		return rulesFileName;
	}

	public void setRulesFileName(String rulesFileName) {
		this.rulesFileName = rulesFileName;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
