package com.wiresto.dao.menu;

import java.util.List;

import com.wiresto.domain.Menu;

public interface MenuDAO {
	
	/**
	 * Retrieve a menu by its id
	 * 
	 * @param menuId
	 * @return Menu or NULL if corresponding menu with id not found
	 */
	public Menu get(int menuId); 
	
	public List<Menu> getAll();

}
