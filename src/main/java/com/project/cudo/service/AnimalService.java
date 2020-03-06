package com.project.cudo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.cudo.dao.Animals;
import com.project.cudo.dao.Bird;
import com.project.cudo.dao.Cat;
import com.project.cudo.dao.FoodStorage;
import com.project.cudo.dao.Turtle;
import com.project.cudo.util.ErrorCodes;
import com.project.cudo.util.JsonStorageUtil;
import com.project.cudo.util.exceptionClass.NameExistException;
import com.project.cudo.util.exceptionClass.NoIdExistException;

@Service("AnimalService")
public class AnimalService {
	
	//임시 메모리 
	FoodStorage foodStorage = FoodStorage.getInstance();
	Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();

	//등록된 동물 전체 가져오기 
	public List<Animals> getAnimals(){
		System.out.println(foodStorage.toString());
		return foodStorage.getAnimal();
	}
	
	//동물 등록 
	public Map<?,?> registerAnimals(FoodStorage fs){
		
		//User "Tom" check utility method
		JsonStorageUtil.userCheck(fs);
		
		//동물 이름 중복 확인 
		boolean checkName_TrueMeansNameExists = false;
		
		for(int i = 0; i < foodStorage.getAnimal().size(); i++){
			for(int j = 0; j < fs.getAnimal().size(); j++){
				if(foodStorage.getAnimal().get(i).getName().equals(fs.getAnimal().get(j).getName())){
					checkName_TrueMeansNameExists = true;
					break;
				}
			}
		}
		
		if(checkName_TrueMeansNameExists){
			throw new NameExistException();
		} else {
			//등록된 동물마다 FoodStorage안에 있는 idGenerator통해 랜덤 아이디 등록 
			for(int i = 0; i < fs.getAnimal().size(); i++){
				Animals tmp = fs.getAnimal().get(i);
				String randomId = foodStorage.idGenerator();
				
				if(tmp.getType().equals("Cat")) {
					
					Cat cat = new Cat(tmp.getName());
					cat.setId(randomId);
					foodStorage.getAnimal().add(cat);
					
				}else if(tmp.getType().equals("Turtle")){
					
					
					Turtle turtle = new Turtle(tmp.getName());
					turtle.setId(randomId);
					foodStorage.getAnimal().add(turtle);
					
				}else if(tmp.getType().equals("Bird")){
					
					Bird bird = new Bird(tmp.getName());
					bird.setId(randomId);
					foodStorage.getAnimal().add(bird);
					
				}
			}
			//성공시 호출하는 메소드. 
			JsonStorageUtil.jsonDataSuccess(jsonStorage);
		}
		
		return jsonStorage;
	}
	
	//동물 먹이주기 
	public Map<?,?> feedAnimals(FoodStorage fs){
		//아이디 톰 체크 
		JsonStorageUtil.userCheck(fs);
		
		//먹이를 받은 동물 수 
		int animalsFed = 0;
		//제이슨 바디로 받은 동물 수 
		int jsonNumberOfAnimals = 0;
		
		//동물이 먹이를 받을 시 animalsFed는 +1 증가 
		for(int i = 0; i < foodStorage.getAnimal().size(); i++){
			String foodStorageId = foodStorage.getAnimal().get(i).getId();
			for(jsonNumberOfAnimals = 0; jsonNumberOfAnimals < fs.getAnimal().size(); jsonNumberOfAnimals++){
				//아이디가 똑같을 시 실행 
				String jsonAnimalId = fs.getAnimal().get(jsonNumberOfAnimals).getId();
				if(foodStorageId.equals(jsonAnimalId)){
					foodStorage.setFood(foodStorage.getAnimal().get(jsonNumberOfAnimals).feed(foodStorage.getFood()));
					System.err.println(foodStorage.getFood());
					animalsFed += 1;
				}
			}
		}
		
		//jsonNumberOfAnimals 와 먹이를 받은 동물 수가 똑같으면 정상적으로 처리 
		if(animalsFed == jsonNumberOfAnimals){
			JsonStorageUtil.jsonDataSuccess(jsonStorage);
			jsonStorage.put("food_left", foodStorage.getFood());
			return jsonStorage;
		} else {
			throw new NoIdExistException();
		}
	}
	
	//동물들다 해방 
	public void deleteAnimals(){
		foodStorage.getAnimal().clear();
	}
	
	//해당 아이디 동물 해방 
	public Map<?,?> deleteAnimal(String id){
		
		boolean animalIdExist = false;
		
		for(int i = 0; i < foodStorage.getAnimal().size(); i++){
			if(foodStorage.getAnimal().get(i).getId().equals(id)){
				foodStorage.getAnimal().remove(i);
				animalIdExist = true;
				break;
			}
		}
		if(animalIdExist){
			JsonStorageUtil.jsonDataSuccess(jsonStorage);
		} else {
			throw new NoIdExistException();
		}
		return jsonStorage;

	}
	
	//해당 아이디 동물 가져오기 
	public Map<?,?> getAnimal(String id){
		
		int place = 0;
		boolean flag = false;
		
		for(int i = 0; i < foodStorage.getAnimal().size(); i++){
			if(foodStorage.getAnimal().get(i).getId().equals(id)){
				place = i;
				flag = true;
				break;
			}
		}
		
		if(flag){
			jsonStorage.put("res_code", ErrorCodes.RES_CODE_200);
			jsonStorage.put("res_msg", ErrorCodes.RES_MSG_200);
			jsonStorage.put("res_data", foodStorage.getAnimal().get(place));
			return jsonStorage;
		} else throw new NoIdExistException();
		
	}
	
	//해당 아이디 동물 정보 업데이트 
	public Map<?,?> updateAnimal(String id, FoodStorage fs){
		
		boolean urlIdNFoodStorageIdMatch = false;
		
		for(int i = 0; i < foodStorage.getAnimal().size(); i++){
			if(foodStorage.getAnimal().get(i).getId().equals(id)){
				urlIdNFoodStorageIdMatch = true;
				foodStorage.getAnimal().get(i).setName(fs.getAnimal().get(0).getName());
				break;
			}
		}
		
		if(urlIdNFoodStorageIdMatch){
			JsonStorageUtil.jsonDataSuccess(jsonStorage);
		} else {
			throw new NoIdExistException();
		}
		return jsonStorage;
	}
	
}
