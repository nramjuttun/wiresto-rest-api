package com.wiresto.service.menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.wiresto.domain.Menu;
import com.wiresto.domain.MenuItem;

public interface MenuService {
	
	public List<Menu> listAllMenus();
	
	/**
	 * Get a menu by its id
	 * 
	 * @param menuId
	 * @return Menu
	 * @throws IllegalArgumentException if menuId null
	 * @throws MenuNotFoundException 
	 */
	public Menu getMenu(Integer menuId) throws IllegalArgumentException, MenuNotFoundException;
	
	
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

	/**
	 * List menu items (grouped by price) for given menu 
	 * @param menuId
	 * @return Map<BigDecimal, List<MenuItem>>
	 * @throws IllegalArgumentException if menuId null
	 * @throws MenuNotFoundException
	 */
	public Map<BigDecimal, List<MenuItem>> getItemsGroupedByPrice(Integer menuId) throws IllegalArgumentException, MenuNotFoundException;

	/**
	 * 
	 * @param menuId
	 * @return List<MenuItem>
	 * @throws IllegalArgumentException if menuId null
	 * @throws MenuNotFoundException
	 */
	public List<MenuItem> getItems(Integer menuId) throws IllegalArgumentException, MenuNotFoundException;
		
	

}
