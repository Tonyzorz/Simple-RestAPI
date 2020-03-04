package com.project.cudo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.cudo.dao.Animals;
import com.project.cudo.dao.FoodStorage;
import com.project.cudo.util.ErrorCodes;
import com.project.cudo.util.NameExistException;
import com.project.cudo.util.JsonStorageUtil;

@Service("AnimalService")
public class AnimalService {
	
	//임시 메모리 
	FoodStorage foodStorage = FoodStorage.getInstance();
	
	//등록된 동물 전체 가져오기 
	public List<Animals> getAnimals(){
		return foodStorage.getAnimal();
	}
	
	//동물 등록 
	public Map<?,?> registerAnimals(FoodStorage fs){
		Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();
		if(JsonStorageUtil.userCheck(fs, jsonStorage)){
			return jsonStorage;
		}
		
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
			//JsonStorageUtil.exist(fs, jsonStorage);
			System.out.println("Animal service throwing exception");
			throw new NameExistException();
		} else {
			for(int i = 0; i < fs.getAnimal().size(); i++){
				fs.getAnimal().get(i).setId(foodStorage.idGenerator());
				foodStorage.getAnimal().add(fs.getAnimal().get(i));
			}
			JsonStorageUtil.success(fs, jsonStorage);
		}
		
		return jsonStorage;
	}
	
	//동물 먹이주기 
	public Map<?,?> feedAnimals(FoodStorage fs){
		System.out.println(foodStorage.toString());
		Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();
		
		if(JsonStorageUtil.userCheck(fs, jsonStorage)){
			return jsonStorage;
		}		
		//받은 제이슨 값 동물하나 당 존재여부 확인 
		boolean idValidation_TrueMeansIDExist = false;
		int animalsFed = -1;
		int j = 0;
		for(int i = 0; i < foodStorage.getAnimal().size(); i++){
			for(j = 0; j < fs.getAnimal().size(); j++){
				if(foodStorage.getAnimal().get(i).getId().equals(fs.getAnimal().get(j).getId())){
					foodStorage.setFood(fs.getAnimal().get(j).feed(foodStorage.getFood()));
					System.err.println(foodStorage.getFood());
					idValidation_TrueMeansIDExist = true;
					animalsFed += 1;
					break;
				}else {
					idValidation_TrueMeansIDExist = false;
				}
			}
		}
//		//idValidation이 참일시, 해당 아이디는 존재한다는 뜻으로 flag에 true값을 대입
//		//testing은 animal리스트 아이디 존재 여부를 카운팅 해주는 변수, 즉 하나의 오차가 있을시 제이슨이랑 안맞다는 뜻. 
		if(idValidation_TrueMeansIDExist && animalsFed == j){
			JsonStorageUtil.success(fs, jsonStorage);
			jsonStorage.put("food_left", foodStorage.getFood());			
		} else {
			JsonStorageUtil.noId(fs, jsonStorage);
		}
		return jsonStorage;
	}
	
	//동물들다 해방 
	public void deleteAnimals(){
		foodStorage.getAnimal().clear();
	}
	
	//해당 아이디 동물 해방 
	public Map<?,?> deleteAnimal(String id){
		Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();
		
		boolean flag = false;
		for(int i = 0; i < foodStorage.getAnimal().size(); i++){
			if(foodStorage.getAnimal().get(i).getId().equals(id)){
				foodStorage.getAnimal().remove(i);
				flag = true;
				break;
			}
		}
		if(flag){
			jsonStorage.put("res_code", ErrorCodes.RES_CODE_200);
			jsonStorage.put("res_msg", ErrorCodes.RES_MSG_200);
		} else {
			jsonStorage.put("res_code", ErrorCodes.RES_CODE_30102);
			jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30102);
			jsonStorage.put("res_data", foodStorage.getAnimal());
		}
		
		return jsonStorage;
	}
	
	//해당 아이디 동물 가져오기 
	public Map<?,?> getAnimal(String id){
		Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();
		
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

		} else {
			jsonStorage.put("res_code", ErrorCodes.RES_CODE_30102);
			jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30102);
			jsonStorage.put("res_data", foodStorage.getAnimal());
		}
		
		return jsonStorage;
	}
	
	//해당 아이디 동물 정보 업데이트 
	public Map<?,?> updateAnimal(String id, FoodStorage fs){
		System.out.println(fs.toString());
		Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();
		int place = 0;
		boolean flag = false;
		for(int i = 0; i < foodStorage.getAnimal().size(); i++){
			if(foodStorage.getAnimal().get(i).getId().equals(id)){
				place = i;
				flag = true;
				foodStorage.getAnimal().get(i).setName(fs.getAnimal().get(0).getName());
				break;
			}
		}
		if(flag){
			//JsonStorageUtil.exist(fs, jsonStorage);
			//throw new NameExistException();
		} else {
			JsonStorageUtil.noId(fs, jsonStorage);
		}
		return jsonStorage;
	}
	
}
