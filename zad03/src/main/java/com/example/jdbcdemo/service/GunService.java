package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Gun;

public interface GunService {
	
	public int addGun(Gun gun);
	public List<Gun> getAllGuns();
	public List<Gun> getAllGunsASC();
	public List<Gun> getAllGunsDESC();
	public void deleteAllGuns();
	
	/* transactional */
	public void addAllGuns(List<Gun> guns);

	
}
