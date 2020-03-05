package com.project.cudo.util;

import java.util.Map;

import com.project.cudo.dao.FoodStorage;
import com.project.cudo.util.exceptionClass.UserCheckException;

public class JsonStorageUtil {

	static FoodStorage foodStorage = FoodStorage.getInstance();

	//유저 체크 30100 
	public static void userCheck(FoodStorage fs){
		if(!fs.getName().equals(foodStorage.getName())){
			throw new UserCheckException();
		}
	}

	//정상 처리 200 
	public static Map<?,?> success(Map<String, Object> jsonStorage){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_200);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_200);
		jsonStorage.put("res_data", foodStorage.getAnimal());
		return jsonStorage;
	}
	
}
