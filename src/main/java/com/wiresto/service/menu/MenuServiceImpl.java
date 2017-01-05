package com.wiresto.service.menu;

import java.math.BigDecimal;
import java.util.List;

import com.wiresto.dao.menu.MenuDAO;
import com.wiresto.domain.Menu;
import com.wiresto.domain.MenuItem;

public class MenuServiceImpl implements MenuService{
	
	private MenuDAO menuDAO;

	@Override
	public Menu getMenu(Integer menuId) throws IllegalArgumentException,
			MenuNotFoundException {

		if(menuId==null)
			throw new IllegalArgumentException("Menu id is NULL");
		
		Menu menu = menuDAO.get(menuId);

		if(menu==null)
			throw new MenuNotFoundException(menuId);		
		
		System.out.println(this.getTotalItemsPrice(menu));
		System.out.println(this.getNumActiveMenus(menu.getSubmenus()));
		
		return menu;
	}

	@Override
	public List<Menu> listAllMenus() {
		return menuDAO.getAll();
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
