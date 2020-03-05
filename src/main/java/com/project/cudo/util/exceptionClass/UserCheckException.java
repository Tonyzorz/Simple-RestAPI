package com.project.cudo.util.exceptionClass;

import java.util.LinkedHashMap;
import java.util.Map;
import com.project.cudo.dao.FoodStorage;
import com.project.cudo.util.ErrorCodes;

public class UserCheckException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public static FoodStorage foodStorage = FoodStorage.getInstance();
	static Map<String, Object> jsonStorage = new LinkedHashMap<String, Object>();

	public UserCheckException(){
	}

	//유저 체크 30100 
	public static Map<?,?> userCheck(){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30100);
		jsonStorage.put("res_msg", foodStorage.getName()+ErrorCodes.RES_MSG_30100);
		return jsonStorage;
	}
	
}
