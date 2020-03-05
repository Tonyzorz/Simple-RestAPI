package com.project.cudo.util.exceptionClass;

import java.util.Map;

public abstract class MiniException extends RuntimeException{
	public abstract Map<?,?> toMap();
}
