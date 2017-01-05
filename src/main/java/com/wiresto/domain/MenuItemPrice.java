package com.wiresto.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

//assume there is a JOIN table to associate menu item id with price and currrency id
public class MenuItemPrice implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int menuItemId;
	
	private BigDecimal value;
		
	private Currency currency;	

	public int getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(double doubleVal){
		value = new BigDecimal(doubleVal).setScale(2, RoundingMode.HALF_EVEN);
	}	

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	

}
