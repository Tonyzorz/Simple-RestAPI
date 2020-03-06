package com.project.cudo.dao;

public class Cat extends Animals{

	public Cat(String name) {
		super(name, "Cat", null);
	}
	
	@Override
	public int feed(int food) {
		if(food >= 150){
			food -= 150;
			System.out.println("you fed your cat 150g");
		} else {
			System.out.println("not enough minerals");
		}		
		return food;
	}
}
