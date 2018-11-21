package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Gun;

public interface GunService {
	
	public int addGun(Gun gun);
	public void addAllGuns(List<Gun> guns);
	
	public Gun getGun(long id);
	
	public List<Gun> getAllGuns();
	public List<Gun> getAllGunsASC();
	public List<Gun> getAllGunsDESC();
	public List<Gun> getAllUndamagedGuns();
	
	public int deleteGun(Gun gun);
	public void deleteAllGuns();
	
	public int updateGun(Gun gun);
	
}
