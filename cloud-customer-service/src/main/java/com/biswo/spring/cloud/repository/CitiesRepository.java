package com.biswo.spring.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biswo.spring.cloud.entity.Cities;

public interface CitiesRepository extends JpaRepository<Cities, Long> {

}
