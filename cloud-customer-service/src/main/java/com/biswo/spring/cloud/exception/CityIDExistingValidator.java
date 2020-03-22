package com.biswo.spring.cloud.exception;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biswo.spring.cloud.repository.CitiesRepository;

@Component
public class CityIDExistingValidator implements ConstraintValidator<CityIDExisting, Long> {

	
	Logger LOG = LoggerFactory.getLogger(CityIDExistingValidator.class);

	@Autowired
	CitiesRepository citiesRepository;
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return Objects.isNull(value) || citiesRepository.findById(value).isPresent();
	}

}
