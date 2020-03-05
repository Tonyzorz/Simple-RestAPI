package com.project.cudo.util.exceptionClass;

import java.util.LinkedHashMap;
import java.util.Map;
import com.project.cudo.dao.FoodStorage;
import com.project.cudo.util.ErrorCodes;

public class UserCheckException extends MiniException{

	private static final long serialVersionUID = 1L;
	static Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();

	public UserCheckException(){
	}

	//유저 체크 30100 
	public Map<?,?> toMap(){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30100);
		jsonStorage.put("res_msg", FoodStorage.getInstance().getName()+ErrorCodes.RES_MSG_30100);
		return jsonStorage;
	}
	
}
