package com.viooh.checkout;

public class Item {

	String itemId;
	String itemGrp;
	Integer itemQty;
	Double itemPrice;
	Boolean itemDiscounted;
	Double itemDiscount;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemGrp() {
		return itemGrp;
	}

	public void setItemGrp(String itemGrp) {
		this.itemGrp = itemGrp;
	}

	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Boolean getItemDiscounted() {
		return itemDiscounted;
	}

	public void setItemDiscounted(Boolean itemDiscounted) {
		this.itemDiscounted = itemDiscounted;
	}

	public Double getItemDiscount() {
		return itemDiscount;
	}

	public void setItemDiscount(Double itemDiscount) {
		this.itemDiscount = itemDiscount;
	}

	@Override
	public String toString() {
		return "Item{" +
				"itemId='" + itemId + '\'' +
				", itemGrp='" + itemGrp + '\'' +
				", itemQty=" + itemQty +
				", itemPrice=" + itemPrice +
				", itemDiscounted=" + itemDiscounted +
				", itemDiscount=" + itemDiscount +
				'}';
	}
}
