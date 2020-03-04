package com.project.cudo.util;

import java.util.Map;

import com.project.cudo.dao.FoodStorage;

public class JsonStorageUtil {

	static FoodStorage foodStorage = FoodStorage.getInstance();

	//유저 체크 30100 
	public static boolean userCheck(FoodStorage fs, Map<String,Object> jsonStorage){
		if(!fs.name.equals(foodStorage.getName())){
			jsonStorage.put("res_code", ErrorCodes.RES_CODE_30100);
			jsonStorage.put("res_msg", foodStorage.getName()+ErrorCodes.RES_MSG_30100);
			return true;
		}
		return false;
	}

	//정상 처리 200 
	public static void success(FoodStorage fs, Map<String, Object> jsonStorage){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_200);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_200);
		jsonStorage.put("res_data", foodStorage.getAnimal());
	}
	
	//이미 중복 30101
	public static void exist(FoodStorage fs, Map<String, Object> jsonStorage){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30101);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30101);
		jsonStorage.put("res_data", fs.getAnimal());
	}
	//아이디 미 등록 30102
	public static void noId(FoodStorage fs, Map<String, Object> jsonStorage){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30102);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30102);
		jsonStorage.put("res_data", fs.getAnimal());
	}
}
