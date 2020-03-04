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

	public int feed(int food){
		if(this.type.equals("Cat")){
			if(food >= 150){
				food -= 150;
				System.out.println("you fed your cat 150g");
			} else {
				System.out.println("not enough minerals");
			}
		} else if(this.type.equals("Bird")){
			if(food >= 50){
				food -= 50;
				System.out.println("you fed your bird 50g");
			} else {
				System.out.println("not enough minerals");
			}
		}else {
			if(food >= 250){
				food -= 250;
				System.out.println("you fed your turtle 250g");
			} else {
				System.out.println("not enough minerals");
			}
		}
		return food;
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
	
	
}
