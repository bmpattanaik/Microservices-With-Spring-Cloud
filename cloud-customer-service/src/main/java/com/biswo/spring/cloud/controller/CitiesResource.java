package com.biswo.spring.cloud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biswo.spring.cloud.entity.Cities;
import com.biswo.spring.cloud.repository.CitiesRepository;

@RestController
@RequestMapping(path = "/cities")
public class CitiesResource {
	
	Logger LOG=LoggerFactory.getLogger(CitiesResource.class);
	
	@Autowired
	CitiesRepository citiesRepository;
	
	
	@GetMapping("/getAllCities")
	public List<Cities> retrieveAllCities() {
		LOG.info("Triggering get request for All Cities");
		List<Cities> cities=citiesRepository.findAll();
		if(cities!=null && !cities.isEmpty()) {
			LOG.info("Size of the cites:{}",cities.size());
		}
		return cities;
	}

}
