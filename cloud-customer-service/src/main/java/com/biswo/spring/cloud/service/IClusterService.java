package com.biswo.spring.cloud.service;

import com.biswo.spring.cloud.model.Cluster;

public interface IClusterService {

	public Cluster saveUser(Cluster inUser);

	public Object[] getAllUsers();
}
