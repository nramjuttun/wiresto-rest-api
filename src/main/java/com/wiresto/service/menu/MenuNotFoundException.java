package com.wiresto.service.menu;

public class MenuNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public MenuNotFoundException(){
		super();
	}
	
	public MenuNotFoundException(int menuId){
		super(String.format("Could not find menu with id: %d", menuId));		
	}

}
