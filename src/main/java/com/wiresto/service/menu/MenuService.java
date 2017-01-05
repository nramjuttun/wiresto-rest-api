package com.wiresto.service.menu;

import java.math.BigDecimal;
import java.util.List;

import com.wiresto.domain.Menu;

public interface MenuService {
	
	/**
	 * Get a menu by its id
	 * 
	 * @param menuId
	 * @return Menu
	 * @throws IllegalArgumentException if menuId null
	 * @throws MenuNotFoundException 
	 */
	public Menu getMenu(Integer menuId) throws IllegalArgumentException, MenuNotFoundException;
	
	
	public List<Menu> listAllMenus();
	
	
	/**
	 * Get the number of active submenus within given menu
	 * 
	 * @param menuId
	 * @return int 
	 * @throws IllegalArgumentException if menuId null
	 * @throws MenuNotFoundException
	 */
	public int getNumberOfActiveSubMenus(Integer menuId) throws IllegalArgumentException, MenuNotFoundException;

	
	/**
	 * Get the sum of all prices of items (including items in submenus) for given menu
	 * @param menuId
	 * @return double
	 * @throws IllegalArgumentException if menuId null
	 * @throws MenuNotFoundException
	 */
	public BigDecimal getTotalItemsPrice(Integer menuId) throws IllegalArgumentException, MenuNotFoundException;
		
	

}
