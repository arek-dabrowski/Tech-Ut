package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Gun;
import com.example.shdemo.domain.Label;
import com.example.shdemo.domain.Producer;
import com.example.shdemo.domain.User;

public interface ServiceManager {

	Long addProducer(Producer producer);
	List<Producer> getAllProducers();
	void deleteProducer(Producer producer);
	Producer findProducerById(Long id);
	
	Long addGun(Gun gun);
	List<Gun> getAllGuns();
	void deleteGun(Gun gun);
	Gun findGunById(Long id);
	
	Long addLabel(Label label);
	List<Label> getAllLabels();
	void deleteLabel(Label label);
	Label findLabelById(Long id);
	
	Long addUser(User user);
	List<User> getAllUsers();
	void deleteUser(User user);
	User findUserById(Long id);
	
	List<Gun> getOwnedGuns(User user);
	void sellGun(Long userId, Long gunId);
	void reserveGun(Long labelId, Long gunId);
}
