package com.biswo.spring.cloud.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biswo.spring.cloud.entity.Cities;
import com.biswo.spring.cloud.exception.CityIDExisting;
import com.biswo.spring.cloud.exception.RecordNotFoundException;
import com.biswo.spring.cloud.repository.CitiesRepository;

@RestController
@RequestMapping(path = "/customer/cities")
@Validated
public class CitiesResource {

	Logger LOG = LoggerFactory.getLogger(CitiesResource.class);

	@Autowired
	CitiesRepository citiesRepository;

	@GetMapping(value = "/getAllCities")
	public List<Cities> retrieveAllCities() {
		LOG.info("Triggering get request for All Cities");
		List<Cities> cities = citiesRepository.findAll();
		if (cities != null && !cities.isEmpty()) {
			LOG.info("Size of the cites:{}", cities.size());
		}
		return cities;
	}

	@GetMapping(value = "/getCityDetails/{id}")
	public ResponseEntity<Cities> retrieveCities(@PathVariable("id") @CityIDExisting long id) {
		LOG.info("Triggering get city from all cities where id:{}", id);
		Optional<Cities> city = citiesRepository.findById(id);
		LOG.info("Triggering get city from all cities where id:{}", id);
		if (!city.isPresent()) {
			throw new RecordNotFoundException("Invalid employee id : " + id);
		}
		return new ResponseEntity<Cities>(city.get(), HttpStatus.OK);
	}

	@PostMapping(value = "/createNewCity")
	public ResponseEntity<Object> createNewCity(@RequestBody @Valid Cities city) {
		LOG.info("Triggering post request for city :{}", city);
		Cities savedCity = citiesRepository.save(city);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCity.getId())
				.toUri();
		LOG.info("URL:{}", location.getRawPath());
		return ResponseEntity.created(location).build();

	}

	@PutMapping(value = "/updateCity")
	public ResponseEntity<Object> updateCity(@RequestBody Cities city) {

		LOG.info("Triggering update city  where id:{}", city.getId());
		Optional<Cities> cityOptional = citiesRepository.findById(city.getId());
		
		if (!cityOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		LOG.info("City Details:{}",cityOptional.get());
		city.setId(city.getId());

		citiesRepository.save(city);

		return ResponseEntity.noContent().build();
	}

}
