package com.project.cudo.dao;

public interface Subject {
	
	public void registerObserver(Animals animal);
	public void removeObserver(Animals animal);
	public void feed();
	public String idGenerator();
}
