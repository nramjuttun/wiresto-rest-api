package com.wiresto.domain;

import java.io.Serializable;


//assume there is a separate table to store currency data
public class Currency implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String code;//e.g USD, CAD
	
	private String symbol;//e.g. $
	
	public Currency(){
		
	}
	
	public Currency(int id, String code, String symbol){
		this.id = id;
		this.code = code;
		this.symbol = symbol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	

}
