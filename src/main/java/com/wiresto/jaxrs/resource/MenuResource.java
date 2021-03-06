package com.wiresto.jaxrs.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wiresto.domain.Menu;
import com.wiresto.service.menu.MenuNotFoundException;
import com.wiresto.service.menu.MenuService;

@Path("/menus")
public class MenuResource {
	
	private MenuService menuService;
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})	
	public List<Menu> listMenus(){
		return menuService.listAllMenus();
	}

	@GET
	@Path("/{menuId}")
	@Produces({MediaType.APPLICATION_JSON})	
	public Menu getMenu(@PathParam("menuId") Integer menuId) throws IllegalArgumentException, MenuNotFoundException{
		return menuService.getMenu(menuId);
	}
	
	@GET
	@Path("/{menuId}/items")
	@Produces({MediaType.APPLICATION_JSON})	
	public Object getMenuItems(@PathParam("menuId") Integer menuId, @QueryParam("groupBy") String groupBy) throws IllegalArgumentException, MenuNotFoundException{
		
		if("price".equals(groupBy)){
			return menuService.getItemsGroupedByPrice(menuId);
		}else{
			return menuService.getItems(menuId);
		}		
	}
	
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	

}
