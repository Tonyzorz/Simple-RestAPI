package com.project.cudo.exceptions;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.cudo.util.exceptionClass.NameExistException;
import com.project.cudo.util.exceptionClass.NoIdExistException;
import com.project.cudo.util.exceptionClass.UserCheckException;

@ControllerAdvice
public class ExceptionHandler{
	
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(NameExistException.class)
	public Map<?,?> nameExist(Exception ex){
		return ((NameExistException) ex).toMap();
	}
	
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(UserCheckException.class)
	public Map<?,?> userCheck(Exception ex){
		return ((UserCheckException) ex).toMap();
	}
	
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(NoIdExistException.class)
	public Map<?,?> noIdExistk(Exception ex){
		return ((NoIdExistException) ex).toMap();
	}
	
}
