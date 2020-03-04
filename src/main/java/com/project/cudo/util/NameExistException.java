package com.project.cudo.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.project.cudo.dao.FoodStorage;

public class NameExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Map<?,?> nameExist(FoodStorage fs){
		Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30101);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30101);
		jsonStorage.put("res_data", fs.getAnimal());
		
		return jsonStorage;
	}
}
