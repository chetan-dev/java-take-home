package com.viooh.checkout;

import java.util.ArrayList;
import java.util.Comparator;

public class CheckOut {

    private Loader loader;

    /**
     * Does checkout
     */
    public void doCheckout() {
        //------------ LOADING DATA -------------------//
        loader.load();
        ArrayList<Item> list = loader.getItems();
        ArrayList<Rule> rules = loader.getRules();
        verifyLoadedData(list,rules);

        //----------- TRANSFORMING ITEMS --------------//
        // We want each item to have QTY 1. If item original had QTY > 1 then we will add the extra items as
        // their separate objects to our list, as our rules expect items with QTY = 1.
        transformList(list);

        //------------ CALCULATING THE DISCOUNTS ------------//
        // We will calculate the best discount using a greedy discount approach. Algorithm will iterate through the items
        // doing rounds of discount applications until no more discounts are available. Every round we evaluate the rules
        // and apply single discount from the rule that gives us the best discount in that round. When the best discount is
        // applied, rule will mark the items that were used as a part of the discount and they will not be considered in
        // following rounds. The process is repeated until there are no more discounts available.
        Double totalDiscount= calculateDiscounts(list,rules); //moved the code for totalDiscount in calculateDiscounts method

        //------ PRINTING THE RECEIPT -----------//
        printReceipt(list,totalDiscount); //moved the code for printing receipt in printReceipt method

        //------ END OF CHECKOUT -----------//
    }

    private void verifyLoadedData(ArrayList<Item> list ,ArrayList<Rule> rules ){

        if (list == null) {
            System.out.println("Error: could not load items from " + getLoader().getItemsFileName());
        }
        if (rules == null) {
            System.out.println("Error: could not load rules from " + getLoader().getRulesFileName());
        }
        if (list == null || rules == null) {
            System.exit(1);
        }
        //System.out.println(list.size());
        System.out.println("Loaded checkout items and rules");
    }

    private void transformList(ArrayList<Item> list){
        System.out.println("Transforming items");
        int s = list.size();
        for (int i = 0; i < s; i++) {
            Item item = list.get(i);
            for (int j = item.getItemQty(); j > 1; j--) {
                Item n = new Item();
                n.setItemId(item.getItemId());
                n.setItemPrice(item.getItemPrice());
                n.setItemGrp(item.getItemGrp());
                n.setItemQty(1);
                list.add(n);
            }
            item.setItemQty(1);
        }
    }

    private Double calculateDiscounts(ArrayList<Item> list,ArrayList<Rule> rules){
        Double roundDiscount = null;
        Double totalDiscount = 0.0;
        Double bestRuleDiscount = 0.0;
        Rule bestRule = null;
        Double total = 0.0;
        System.out.println();
        System.out.println("======= APPLYING RULES ============");
        do {
            for (Rule rule : rules) {
                roundDiscount = rule.getBestSingleDiscount(list);
                if (roundDiscount != null && roundDiscount > bestRuleDiscount) {
                    bestRuleDiscount = roundDiscount;
                    bestRule = rule;
                }
            }
            if (bestRule != null) {
                ArrayList<Item> discountedItems = bestRule.applyBestSingleDiscount(list);
                totalDiscount = totalDiscount + bestRuleDiscount;
                printDiscount(discountedItems, bestRule);
            } else {
                break;
            }
            roundDiscount = null;
            bestRuleDiscount = 0.0;
            bestRule = null;
        } while (true);

        return totalDiscount;
    }

    private void printReceipt(ArrayList<Item> list,Double totalDiscount){
        Double total =0.0;
        list.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getItemId().compareTo(o2.getItemId());
            }
        });

        System.out.println();
        System.out.println("========== RECEIPT ===============");
        for (Item item : list) {
            total += item.getItemPrice();
            System.out.print(item.getItemId());
            System.out.print(" - ");
            System.out.print(item.getItemGrp());;
            System.out.print(" PRICE: ");
            System.out.print(item.getItemPrice());
            if (item.getItemDiscount() != null) {
                System.out.print(" DISCOUNT: ");
                System.out.print(item.getItemDiscount());
            }
            System.out.println("");
        }
        System.out.println("==================================");
        System.out.println("Total: " + total);
        System.out.println("Discount: " + totalDiscount);
        System.out.println("Grand Total: " + (total - totalDiscount));
        System.out.println("==================================");

    }


    public void printDiscount(ArrayList<Item> items, Rule rule) {
        //------ PRINT RULE DISCOUNT -----------//
        System.out.println("Applied discount " + rule.getClass().getSimpleName());
        for (Item i : items) {
            System.out.println("   - " + i);
        }
    }

    public Loader getLoader() {
        return loader;
    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }
}
