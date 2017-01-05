package com.wiresto.service.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wiresto.dao.menu.MenuDAO;
import com.wiresto.domain.Menu;
import com.wiresto.domain.MenuItem;

public class MenuServiceImpl implements MenuService{
	
	private MenuDAO menuDAO;
	
	@Override
	public List<Menu> listAllMenus() {
		return menuDAO.getAll();
	}

	@Override
	public Menu getMenu(Integer menuId) throws IllegalArgumentException,
			MenuNotFoundException {

		if(menuId==null)
			throw new IllegalArgumentException("Menu id is NULL");
		
		Menu menu = menuDAO.get(menuId);

		if(menu==null)
			throw new MenuNotFoundException(menuId);				
		
		return menu;
	}
	
	@Override
	public List<MenuItem> getItems(Integer menuId) throws IllegalArgumentException, MenuNotFoundException{
		Menu menu = this.getMenu(menuId);
		return menu.getItems();
	}

	@Override
	public Map<BigDecimal, List<MenuItem>> getItemsGroupedByPrice(Integer menuId) throws IllegalArgumentException, MenuNotFoundException{
		
		Map<BigDecimal, List<MenuItem>> map = new HashMap<BigDecimal, List<MenuItem>>();
		
		Menu menu = this.getMenu(menuId);
		
		for(MenuItem item : menu.getItems()){
			BigDecimal price = item.getPrice().getValue();
			List<MenuItem> items = map.get(price);
			if(items==null){
				items = new ArrayList<MenuItem>();
				map.put(price, items);
			}
			items.add(item);			
		}
		
		return map;
	}	
	
	@Override
	public BigDecimal getTotalItemsPrice(Integer menuId) throws IllegalArgumentException, MenuNotFoundException{
		
		Menu menu = this.getMenu(menuId);
		
		return this.getTotalItemsPrice(menu);
	}
	
	private BigDecimal getTotalItemsPrice(Menu menu){
		
		BigDecimal sum = new BigDecimal(0);
		
		for(Menu submenu : menu.getSubmenus()){
			sum = sum.add(getTotalItemsPrice(submenu));
		}
		
		for(MenuItem item : menu.getItems()){
			sum = sum.add(item.getPrice().getValue());
		}		
		
		return sum;
	}	
	
	
	@Override
	public int getNumberOfActiveSubMenus(Integer menuId) throws IllegalArgumentException, MenuNotFoundException {

		Menu menu = this.getMenu(menuId);
		
		return getNumActiveMenus(menu.getSubmenus());
	}
	
	private int getNumActiveMenus(List<Menu> menus){
		
		int count =0;	
		
		for(Menu menu : menus){
			count = count + getNumActiveMenus(menu.getSubmenus());
			
			if(menu.isActive()){
				count++;
			}
		}	
		
		return count;
	}


	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	

	
	
}
