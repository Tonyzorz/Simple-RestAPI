package com.project.cudo.dao;

public class Cat extends Animals{

	public String name;
	public String type;
	public String id;
	
	public Cat(String name, String type, String id) {
		this.name = name;
		this.type = type;
		this.id = id;
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
