package com.project.cudo.dao;

import java.util.Map;

public class ErrorCodes {

	FoodStorage foodStorage = FoodStorage.getInstance();
	
	public static final String RES_CODE_200 = "200";
	public static final String RES_CODE_30100 = "30100";
	public static final String RES_CODE_30101 = "30101";
	public static final String RES_CODE_30102 = "30102";
	
	public static final String RES_MSG_200 = "정상적으로 처리하였습니다.";
	public static final String RES_MSG_30100 = "만 요청할 수 있습니다.";
	public static final String RES_MSG_30101 = "이미 등록된 이름은 사용할 수 없습니다.";
	public static final String RES_MSG_30102 = "등록되지 않은 id입니다.";

	//유저 체크 30100 
	public void userCheck(FoodStorage fs, Map<String,Object> jsonStorage){
		if(!fs.name.equals(foodStorage.getName())){
			jsonStorage.put("res_code", ErrorCodes.RES_CODE_30100);
			jsonStorage.put("res_msg", foodStorage.getName()+ErrorCodes.RES_MSG_30100);
		}
	}

	//정상 처리 200 
	public void success(FoodStorage fs, Map<String, Object> jsonStorage){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_200);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_200);
		jsonStorage.put("res_data", foodStorage.getAnimal());
	}
	
	//이미 중복 30101
	public void exist(FoodStorage fs, Map<String, Object> jsonStorage){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30101);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30101);
		jsonStorage.put("res_data", fs.getAnimal());
	}
	//아이디 미 등록 30102
	public void noId(FoodStorage fs, Map<String, Object> jsonStorage){
		jsonStorage.put("res_code", ErrorCodes.RES_CODE_30102);
		jsonStorage.put("res_msg", ErrorCodes.RES_MSG_30102);
		jsonStorage.put("res_data", fs.getAnimal());
	}
}
