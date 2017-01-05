package com.wiresto.dao.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wiresto.data.mock.MockMenuFactory;
import com.wiresto.domain.Menu;

/**
 * Provides mock menu. This DAO delegates to a mock menu data factory to create the menus.
 */
public class MockMenuDAOImpl implements MenuDAO {
	
	private Map<Integer,Menu> data = new HashMap<Integer,Menu>();
	
	MockMenuFactory factory;
	
	public MockMenuDAOImpl(MockMenuFactory factory, int numberMenus){
		this.factory = factory;		
		loadData(numberMenus);
	}

	@Override
	public Menu get(int menuId) {		
		return data.get(menuId);
	}
	
	@Override
	public List<Menu> getAll() {
		List<Menu> list = new ArrayList<Menu>();
		for(Integer key : data.keySet()){
			list.add(data.get(key));
		}
		return list;
	}
	
	private void loadData(int numMenus){
		for(int i=0; i<numMenus;i++){
			Menu menu = factory.create();
			data.put(menu.getId(), menu);
		}		
	}

	

}
