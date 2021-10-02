package com.viooh.checkout;

import java.util.ArrayList;

public interface Rule {
    public abstract Double getBestSingleDiscount(ArrayList<Item> items);

    public abstract ArrayList<Item> applyBestSingleDiscount(ArrayList<Item> items);

    public default void initialize(String name, String param1, String param2, String param3, String param4) throws Exception
    {
        //override this method if required for a rule
    }
}
