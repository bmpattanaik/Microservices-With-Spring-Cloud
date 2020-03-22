package com.biswo.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biswo.spring.cloud.entity.Cluster;
import com.biswo.spring.cloud.service.IClusterService;

@RestController
public class ClusterController {
	
	@Autowired
	IClusterService clusterService;
	
	@PostMapping("/clusters")
	public Cluster save(@RequestBody Cluster u) {
		return clusterService.saveUser(u);
	}
 
	@GetMapping("/clusters")
	public Object[] getAll() {
		return clusterService.getAllUsers();
 
	}

}
