package com.wiresto.domain;

public enum StarRank {
	
	ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5);
	
	private int value;
	
	private StarRank(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static StarRank get(int value){
		for (StarRank r : StarRank.values()){
			if(r.getValue()==value){
				return r;
			}
		}
		return null;
	}
}
