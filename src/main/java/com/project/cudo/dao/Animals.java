package com.project.cudo.dao;

public class Animals extends BaseAnimals {

	private String name;
	private String type;
	private String id;
	
	public Animals( String name, String type, String id) {
		this.name = name;
		this.type = type;
		this.id = id;
	}
	
	public Animals() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	@Override
	public int feed(int food) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
