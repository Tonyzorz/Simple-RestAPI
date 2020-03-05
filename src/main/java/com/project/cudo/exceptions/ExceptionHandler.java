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
	public Map<?,?> nameExist(){
		return NameExistException.nameExist();
	}
	
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(UserCheckException.class)
	public Map<?,?> userCheck(){
		return UserCheckException.userCheck();
	}
	
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(NoIdExistException.class)
	public Map<?,?> noIdExistk(){
		return NoIdExistException.noId();
	}
	
	
}
