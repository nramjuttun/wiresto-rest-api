package com.wiresto.data.mock;


import java.net.URI;
import java.net.URISyntaxException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.wiresto.domain.Currency;
import com.wiresto.domain.Menu;
import com.wiresto.domain.MenuItem;
import com.wiresto.domain.MenuItemPrice;
import com.wiresto.domain.StarRank;

public class MockMenuFactory {
	
	private static final Currency CURRENCY = new Currency(1, "CAD", "$");
	
	private static final Random GENERATOR = new Random();
	
	private static final String[] MENUS = {"Daily Menu", "Summer Menu", "Winter Menu", "Kids Special"};
	private static final String[] SUBMENUS = {"For Adults", "Spicy Mexican", "Happy Hour", "Take-out"};	
	private static final String[] MENU_ITEMS = {"Pizza","Fries","Sausage","Lobster","Rice","Coke","Pasta"};
	
	private int maxSubMenus;
	private int maxItemsPerMenu;
	private int subMenuDepthLevel;	
	
	/**
	 * 
	 * @param maxSubMenus Randomly generate between 0 to [maxSubMenus] submenus per menu
	 * @param maxItemsPerMenu Randomly generate between 1 to [maxItemsPerMenu] items per menu
	 * @param subMenuDepthLevel Set the max nested level of submenus per menu
	 */
	private MockMenuFactory(int maxSubMenus, int maxItemsPerMenu, int subMenuDepthLevel){	
		this.maxSubMenus = maxSubMenus;
		this.maxItemsPerMenu = maxItemsPerMenu;
		this.subMenuDepthLevel = subMenuDepthLevel;
	}
	
	public Menu create(){		
		Menu menu = getMenu(true, subMenuDepthLevel, "");
		return menu;
	}		
	
	public Menu getMenu(boolean isRootMenu, int depthLevel, String parentMenuName){		
		
		Menu menu = new Menu();
		int id = GENERATOR.nextInt(1000000)+1;
		menu.setId(id);
		
		String menuName = null;
		String menuDesc = null;
		if(isRootMenu){
			menuName = "menu"+id;
			menuDesc = getMenuDesc() + " of " + menuName;
		}else{
			menuName = "submenu"+id +"-"+parentMenuName;
			menuDesc = getSubMenuDesc() + " of " + menuName;
		}
		menu.setName(menuName);
		menu.setDescription(menuDesc);
		
		menu.setActive(GENERATOR.nextDouble()>=0.5?true:false);
		
		menu.setItems(getMenuItems(menuName));
		
		//reached leaf node
		if(!isRootMenu && depthLevel<=0)
			return menu;
		
		List<Menu> subMenus = new ArrayList<Menu>();
		int numSubMenus = GENERATOR.nextInt(maxSubMenus+1);		
		for(int i=0; i<numSubMenus; i++){
			Menu subMenu = getMenu(false, depthLevel-1, menuName);
			if(subMenu!=null){
				subMenus.add(subMenu);
			}			
		}		
		menu.setSubmenus(subMenus);
		
		return menu;
	}	
	
	private List<MenuItem> getMenuItems(String menuName){
		List<MenuItem> items = new ArrayList<MenuItem>();
		int itemNum = GENERATOR.nextInt(maxItemsPerMenu)+1;		
		for(int i=0; i<itemNum; i++){
			items.add(getMenuItem(menuName));
		}
		return items;
	}
	
	private MenuItem getMenuItem(String menuName){
		MenuItem item = new MenuItem();
		int id = GENERATOR.nextInt(1000000)+1;
		item.setId(id);
		item.setName("menuItem"+id);		
		
		item.setDescription(getMenuItemDesc() + " item of " + menuName);
		item.setPrice(getPrice(id));
		item.setRank(getRank());
		item.setPhotoLink(getLink(id));
		
		Date startDate = new Date();
		item.setStartDate(startDate);
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(startDate);
		c1.add(Calendar.MONTH, GENERATOR.nextInt(11) + 1);
		c1.add(Calendar.DAY_OF_MONTH, GENERATOR.nextInt(30) + 1);
		c1.add(Calendar.HOUR, GENERATOR.nextInt(11) + 1);
		item.setEndDate(c1.getTime());		
	
		item.setAvailableDays(getDays());
		
		return item;
	}
	
	private MenuItemPrice getPrice(int menuItemId){
		MenuItemPrice p = new MenuItemPrice();
		p.setMenuItemId(menuItemId);
		p.setCurrency(CURRENCY);
		p.setValue(new Random().nextDouble()*10);
		
		return p;
	}
	
	private Set<DayOfWeek> getDays(){
		Set<DayOfWeek> days = new HashSet<DayOfWeek>();
		
		int num = GENERATOR.nextInt(7)+1;
		
		for(int i=0;i<num ;i++){
			days.add(getDay());
		}
		
		return days;
	}
	
	private DayOfWeek getDay(){
		int num = GENERATOR.nextInt(7) + 1;		
		return DayOfWeek.of(num);
	}
	
	private StarRank getRank(){
		int num = GENERATOR.nextInt(5) + 1;		
		return StarRank.get(num);
	}
	
	private String getMenuDesc(){
		if(MENUS.length==0)
			return null;
		
		int id = GENERATOR.nextInt(MENUS.length);
		return MENUS[id];
	}
	
	private String getMenuItemDesc(){
		if(MENU_ITEMS.length==0)
			return null;
		
		int id = GENERATOR.nextInt(MENU_ITEMS.length);
		return MENU_ITEMS[id];
	}
	
	private String getSubMenuDesc(){
		if(SUBMENUS.length==0)
			return null;
		
		int id = GENERATOR.nextInt(SUBMENUS.length);
		return SUBMENUS[id];
	}

	private URI getLink(int id){
		try {
			return new URI("http://s3.amazon.com/photo"+id);
		} catch (URISyntaxException e) {
			return null;
		}
	}
}
