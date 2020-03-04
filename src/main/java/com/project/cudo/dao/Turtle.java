package com.project.cudo.dao;

public class Turtle extends Animals {

	public Turtle(String name) {
		super(name, "Turtle", null);
	}

	@Override
	public int feed(int food) {
		if (food >= 50) {
			food -= 50;
			System.out.println("you fed your bird 50g");
		} else {
			System.out.println("not enough minerals");
		}
		return food;
	}
}
