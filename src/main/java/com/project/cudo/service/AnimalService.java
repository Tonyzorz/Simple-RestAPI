package com.project.cudo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.cudo.dao.Animals;
import com.project.cudo.dao.FoodStorage;
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
		System.out.println(jsonStorage.toString());
		return foodStorage.getAnimal();
	}
	
	//동물 등록 
	public Map<?,?> registerAnimals(FoodStorage fs){
		
		JsonStorageUtil.userCheck(fs);
		
		//false시 등록된 이름이 없다
		//true시 등록된 이름이 존재한다.
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
			for(int i = 0; i < fs.getAnimal().size(); i++){
				fs.getAnimal().get(i).setId(foodStorage.idGenerator());
				foodStorage.getAnimal().add(fs.getAnimal().get(i));
			}
			JsonStorageUtil.success(jsonStorage);
		}
		
		return jsonStorage;
	}
	
	//동물 먹이주기 
	public Map<?,?> feedAnimals(FoodStorage fs){
		JsonStorageUtil.userCheck(fs);
		
		int animalsFed = 0;
		int jsonNumberOfAnimals = 0;
		
		for(int i = 0; i < foodStorage.getAnimal().size(); i++){
			for(jsonNumberOfAnimals = 0; jsonNumberOfAnimals < fs.getAnimal().size(); jsonNumberOfAnimals++){
				if(foodStorage.getAnimal().get(i).getId().equals(fs.getAnimal().get(jsonNumberOfAnimals).getId())){
					foodStorage.setFood(fs.getAnimal().get(jsonNumberOfAnimals).feed(foodStorage.getFood()));
					System.err.println(foodStorage.getFood());
					animalsFed += 1;
				}
			}
		}
		
		//jsonNumberOfAnimals 와 먹이를 받은 동물 수가 똑같으면 정상적으로 처리 
		if(animalsFed == jsonNumberOfAnimals){
			JsonStorageUtil.success(jsonStorage);
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
			return JsonStorageUtil.success(jsonStorage);
		} else {
			throw new NoIdExistException();
		}
		
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
			return JsonStorageUtil.success(jsonStorage);
		} else {
			throw new NoIdExistException();
		}
	}
	
}
