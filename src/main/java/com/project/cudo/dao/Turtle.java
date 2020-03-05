package com.project.cudo.dao;

public class Turtle extends Animals {

	public Turtle(String name) {
		super(name, "Turtle", null);
	}

	@Override
	public int feed(int food) {
		if (food >= 250) {
			food -= 250;
			System.out.println("you fed your Turtle 250g");
		} else {
			System.out.println("not enough minerals");
		}
		return food;
	}
}
