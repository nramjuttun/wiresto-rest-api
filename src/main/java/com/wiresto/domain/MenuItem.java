package com.wiresto.domain;

import java.io.Serializable;
import java.net.URI;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


//assume there is a JOIN table that associates a menu item to a menu
//assume a menu item can be associated with several menus
public class MenuItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name; //asume unique key in DB table
	
	private String description;
	
	private URI photoLink; //assume photo is stored in an external file system or cloud such AWS S3 for efficiency. We do not need to store in BLOB in DB.
	
	private StarRank rank;
	
	private MenuItemPrice price;
	
	private Set<DayOfWeek> availableDays = new HashSet<DayOfWeek>(); //for simplicity assume days are stored in a single field/column in DB. e.g. ["Monday","Wednesday"]
	
	private Date startDate;
	
	private Date endDate;

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

	public URI getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(URI photoLink) {
		this.photoLink = photoLink;
	}

	public StarRank getRank() {
		return rank;
	}

	public void setRank(StarRank rank) {
		this.rank = rank;
	}

	public MenuItemPrice getPrice() {
		return price;
	}

	public void setPrice(MenuItemPrice price) {
		this.price = price;
	}

	public Set<DayOfWeek> getAvailableDays() {
		return availableDays;
	}

	public void setAvailableDays(Set<DayOfWeek> availableDays) {
		if(availableDays!=null){
			this.availableDays.addAll(availableDays);
		}		
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
