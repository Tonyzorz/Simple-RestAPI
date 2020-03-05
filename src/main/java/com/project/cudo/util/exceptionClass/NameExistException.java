package com.project.cudo.util.exceptionClass;

import java.util.LinkedHashMap;
import java.util.Map;
import com.project.cudo.dao.FoodStorage;
import com.project.cudo.util.ErrorCodes;

public class NameExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public static FoodStorage foodStorage = FoodStorage.getInstance();
	static Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();

	public NameExistException(){
	}
	
	public static Map<?,?> nameExist(){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30101);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30101);
		jsonStorage.put("res_data", foodStorage.getAnimal());
		return jsonStorage;
	}


	
}
