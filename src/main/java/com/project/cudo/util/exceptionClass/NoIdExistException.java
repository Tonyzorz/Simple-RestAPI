package com.project.cudo.util.exceptionClass;

import java.util.LinkedHashMap;
import java.util.Map;
import com.project.cudo.dao.FoodStorage;
import com.project.cudo.util.ErrorCodes;

public class NoIdExistException extends MiniException{

	private static final long serialVersionUID = 1L;
	static Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();

	public NoIdExistException(){
	}

	//아이디 미 등록 30102
	public static Map<?,?> noId(){
		FoodStorage storage = FoodStorage.getInstance();
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30102);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30102);
		jsonStorage.put("res_data", storage.getAnimal());
		return jsonStorage;
	}

	@Override
	public Map<?, ?> toMap() {
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30102);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30102);
		jsonStorage.put("res_data", FoodStorage.getInstance().getAnimal());
		return jsonStorage;
	}
	
}
