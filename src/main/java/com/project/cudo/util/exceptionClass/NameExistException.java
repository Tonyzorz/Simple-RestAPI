package com.project.cudo.util.exceptionClass;

import java.util.LinkedHashMap;
import java.util.Map;
import com.project.cudo.dao.FoodStorage;
import com.project.cudo.util.ErrorCodes;

public class NameExistException extends MiniException{

	private static final long serialVersionUID = 1L;
	static Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();

	public NameExistException(){
	}
	
	public Map<?,?> toMap(){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30101);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30101);
		jsonStorage.put("res_data", FoodStorage.getInstance().getAnimal());
		return jsonStorage;
	}
	
}
