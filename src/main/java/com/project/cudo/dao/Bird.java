package com.project.cudo.dao;

public class Bird extends Animals{

	
	public Bird(String name){
		super(name, "Bird", null);
	}

	@Override
	public int feed(int food) {
		if(food >= 50){
			food -= 50;
			System.out.println("you fed your bird 50g");
		} else {
			System.out.println("not enough minerals");
		}		
		return food;
	}
}
