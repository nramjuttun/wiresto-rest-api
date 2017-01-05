package com.wiresto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//assume there is a JOIN table to associate a menu id with menu item id
//assume there is a JOIN table to associate a menu id with sub-menu id
public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name; //assume a unique key in DB table
	
	private String description;
	
	private boolean active;
	
	List<MenuItem> items = new ArrayList<MenuItem>();
	
	List<Menu> submenus = new ArrayList<Menu>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		if(items!=null)
			this.items.addAll(items);
	}

	public List<Menu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<Menu> submenus) {
		if(submenus!=null)
			this.submenus.addAll(submenus);
	}
	
	
	
	

}
