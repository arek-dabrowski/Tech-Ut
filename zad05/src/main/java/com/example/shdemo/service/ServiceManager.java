package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Distributor;
import com.example.shdemo.domain.Gun;
import com.example.shdemo.domain.Label;
import com.example.shdemo.domain.Producer;
import com.example.shdemo.domain.User;

public interface ServiceManager {

	/*Producer methods*/
	Long addProducer(Producer producer);
	List<Producer> getAllProducers();
	void deleteProducer(Producer producer);
	Producer findProducerById(Long id);
	
	/*Distributor methods*/
	Long addDistributor(Distributor distributor);
	List<Distributor> getAllDistributors();
	void deleteDistributor(Distributor distributor);
	Distributor findDistributorById(Long id);
	
	/*Gun methods*/
	Long addGun(Gun gun);
	Long addDistributorToGun(Gun gun, Distributor distributor);
	List<Gun> getAllGuns();
	void deleteGun(Gun gun);
	Gun findGunById(Long id);
	List<Gun> findGunsByProducer(Long id);
	
	/*Label Methods*/
	Long addLabel(Label label);
	Long addLabelToGun(Gun gun, Label label);
	List<Label> getAllLabels();
	void deleteLabel(Label label);
	Label findLabelById(Long id);
	
	/*User methods*/
	Long addUser(User user);
	List<User> getAllUsers();
	void deleteUser(User user);
	User findUserById(Long id);
	
	/*Business methods*/
	List<Gun> getOwnedGuns(User user);
	void sellGun(Long userId, Long gunId);
	void reserveGun(Long labelId, Long gunId);
}
