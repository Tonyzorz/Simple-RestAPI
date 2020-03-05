package com.project.cudo.dao;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class FoodStorage {

	private ArrayList<Animals> animal;
	private int food;
	private String name;
	
	private static FoodStorage instance;
	
	private FoodStorage() {
		this.animal = new ArrayList<Animals>();
		this.animal.add(new Turtle("turquoise"));
		this.animal.get(0).setId(idGenerator());
		this.food = 2000;
		this.name = "Tom";
	}
	
	public static FoodStorage getInstance(){
		if(instance == null) {
			instance = new FoodStorage();
		}
		return instance;
	}

	public void feed() {
		for(int i = 0; i < animal.size(); i++){
			Animals animals = animal.get(i);
			food = animals.feed(food);
		}
		System.out.println("total remaining food is : " + food);
	}

	public String idGenerator() {
		int length = 6;
		byte[] array = new byte[100];
		new Random().nextBytes(array);
		
		String randomString = new String(array, Charset.forName("UTF-8"));

		StringBuffer r = new StringBuffer();
		
		for(int k = 0; k < randomString.length(); k++){
			char ch = randomString.charAt(k);
			if(((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) && (length > 0)){
				r.append(ch);
				length--;
			}
		}
		return r.toString();
	}

	public ArrayList<Animals> getAnimal() {
		return animal;
	}

	public void setAnimal(ArrayList<Animals> animal) {
		this.animal = animal;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FoodStorage [animal=" + animal + ", food=" + food + ", name=" + name + "]";
	}

}
