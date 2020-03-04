package com.project.cudo.exceptions;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;

import com.project.cudo.dao.FoodStorage;
import com.project.cudo.util.NameExistException;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NameExistException.class)
	public final Map<?,?> idExist(FoodStorage fs, Map<String, Object> jsonStorage){
		System.out.println("It takes care of it!");
		return NameExistException.nameExist(fs);
	}
}
