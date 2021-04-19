package org.simplilearn.workshop.model;

import java.math.BigDecimal;

public class CartItem {

	private long productId;
	private long categoryId;
	private String name;
	private BigDecimal rate;
	private BigDecimal price;
	private int qty;

	public long getProductId() {
		return this.productId;
	}
	
	public long getCategoryId() {
		return categoryId;
	}
	
	public String getName() {
		return this.name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public int getQty() {
		return this.qty;
	}

	public void setProductId(long id) {
		this.productId = id;
	}

	public void setName(String value) {
		this.name = value;
	}

	public void setPrice(BigDecimal value) {
		this.price = value;
	}
	
	
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	

	public void setRate(BigDecimal value) {
		this.rate = value;
	}

	public void setQty(int value) {
		this.qty = value;
	}
}
