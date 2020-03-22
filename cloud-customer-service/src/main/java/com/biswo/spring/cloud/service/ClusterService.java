package com.biswo.spring.cloud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biswo.spring.cloud.entity.Cluster;

@Service
public class ClusterService implements IClusterService {

	public static List<Cluster> userStoragLe = new ArrayList<Cluster>();

	@Override
	public Cluster saveUser(Cluster inUser) {
		int index = userStoragLe.size();

		if (!userStoragLe.contains(inUser)) {
			inUser.setUserId(index + 1);
			userStoragLe.add(inUser);
			return inUser;
		}

		return inUser;
	}

	@Override
	public Object[] getAllUsers() {

		return userStoragLe.toArray();
	}
}
