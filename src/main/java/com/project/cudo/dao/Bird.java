package com.project.cudo.dao;

public class Bird extends Animals{

	public String name;
	public String type;
	public String id;
	
	public Bird(String name){
		this.name = name;
		this.type = this.getClass().getSimpleName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
