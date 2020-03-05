package com.project.cudo.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.cudo.dao.Animals;
import com.project.cudo.dao.FoodStorage;
import com.project.cudo.service.AnimalService;

@RestController
public class AnimalController {
	
	@Resource(name = "AnimalService")
	AnimalService animalService;
	
	//Get animal list 
	@ResponseBody
	@RequestMapping(value = "/getAnimals", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Animals> getAnimals(){
		return animalService.getAnimals();
	}
	
	//동물 등록 
	@RequestMapping(value = "/registerAnimals", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<?,?> registerAnimals(@RequestBody FoodStorage fs) {
		return animalService.registerAnimals(fs);
	}
	
	//동물 먹이주기 
	@RequestMapping(value = "/feedAnimals", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE )
	public Map<?,?> feedAnimals(@RequestBody FoodStorage fs){
		return animalService.feedAnimals(fs);
	}
	
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
	
	//update list one animal
	@RequestMapping(value = "/updateAnimal/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<?,?> updateAnimal(@PathVariable String id, @RequestBody FoodStorage fs){
		return animalService.updateAnimal(id, fs);
	}
	
	//delete list
	@ResponseBody
	@RequestMapping(value = "/deleteAnimals", method = RequestMethod.DELETE)
	public String deleteAnimals(){
		animalService.deleteAnimals();
		return "Freed all the pets";
	}

	//delete by id
	@RequestMapping(value = "/deleteAnimal/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<?,?> deleteAnimal(@PathVariable String id){
		return animalService.deleteAnimal(id);
	}
	
	//find by id
	@RequestMapping(value = "/getAnimal/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<?,?> getAnimal(@PathVariable("id") String id){
		return animalService.getAnimal(id);
	}
	
}
