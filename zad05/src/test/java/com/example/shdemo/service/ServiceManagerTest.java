package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Gun;
import com.example.shdemo.domain.Label;
import com.example.shdemo.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ServiceManagerTest {
	
	@Autowired
	ServiceManager serviceManager;
	
	private final Boolean RESERVED_1 = false;
	private final Double PRICE_1 = 199.99;
	
	private final String GUN_NAME_1 = "MP5";
	@SuppressWarnings("deprecation")
	private final Date GUN_DATE_1 = new Date(2015, 4, 16);
	private final Boolean SOLD_1 = false;
	private final Double WEIGHT_1 = 2.19;
	
	private final String GUN_NAME_2 = "AK-47";
	@SuppressWarnings("deprecation")
	private final Date GUN_DATE_2 = new Date(1999, 1, 3);
	private final Boolean SOLD_2 = true;
	private final Double WEIGHT_2 = 3.19;
	
	private final String FIRST_NAME_1 = "John";
	private final String LAST_NAME_1 = "Doe";
	@SuppressWarnings("deprecation")
	private final Date YOB_1 = new Date(1995, 2, 2);
	
	private final String FIRST_NAME_2 = "Anna";
	private final String LAST_NAME_2 = "Kowalska";
	@SuppressWarnings("deprecation")
	private final Date YOB_2 = new Date(1956, 1, 2);
	
	@Test
	public void sellGunCheck() {
		List<User> retrievedUsers = serviceManager.getAllUsers();
		List<Gun> retrievedGuns = serviceManager.getAllGuns();
		List<Label> retrievedLabels = serviceManager.getAllLabels();
		
		for (User user : retrievedUsers) {
			if (user.getFirstName().equals(FIRST_NAME_1) && user.getLastName().equals(LAST_NAME_1) && user.getBirthDate().equals(YOB_1)) {
				serviceManager.deleteUser(user);
			}
		}
		
		for (Gun gun : retrievedGuns) {
			if (gun.getName().equals(GUN_NAME_1)) {
				serviceManager.deleteGun(gun);
			}
		}
		
		for (Label label : retrievedLabels) {
			if (label.getReserved().equals(RESERVED_1) && label.getPrice().equals(PRICE_1)) {
				serviceManager.deleteLabel(label);
			}
		}
		
		Gun gun = new Gun(GUN_NAME_1, GUN_DATE_1, SOLD_1, WEIGHT_1);
		Long gunId = serviceManager.addGun(gun);
		
		Label label = new Label(RESERVED_1, PRICE_1);
		serviceManager.addLabelToGun(gun, label);
		
		User user = new User(FIRST_NAME_1, LAST_NAME_1, YOB_1);
		Long userId = serviceManager.addUser(user);
		
		User beforeSellUser = serviceManager.findUserById(userId);
		int beforeSell = beforeSellUser.getNumberOfRegisteredGuns();
		
		Boolean successed = serviceManager.sellGun(user, gun);
		
		User afterSellUser = serviceManager.findUserById(userId);
		
		Gun soldGun = serviceManager.findGunById(gunId);
		
		assertEquals(true, successed);	
		assertEquals(new Integer(beforeSell + 1), afterSellUser.getNumberOfRegisteredGuns());	
		assertEquals(GUN_NAME_1, soldGun.getName());	
		assertEquals(GUN_DATE_1, soldGun.getProductionDate());	
		assertEquals(!SOLD_1, soldGun.getSold());	
		assertEquals(WEIGHT_1, soldGun.getWeight());	
		assertEquals(null, soldGun.getLabel());
	}
	
	@Test
	public void reserveGunCheck() {
		List<Gun> retrievedGuns = serviceManager.getAllGuns();
		List<Label> retrievedLabels = serviceManager.getAllLabels();
		
		for (Gun gun : retrievedGuns) {
			if (gun.getName().equals(GUN_NAME_1)) {
				serviceManager.deleteGun(gun);
			}
		}
		
		for (Label label : retrievedLabels) {
			if (label.getReserved().equals(RESERVED_1) && label.getPrice().equals(PRICE_1)) {
				serviceManager.deleteLabel(label);
			}
		}
		
		Gun gun = new Gun(GUN_NAME_1, GUN_DATE_1, SOLD_1, WEIGHT_1);
		Long gunId = serviceManager.addGun(gun);
		
		Gun savedGun = serviceManager.findGunById(gunId);
		
		Label label = new Label(RESERVED_1, PRICE_1);
		Long labelId = serviceManager.addLabelToGun(savedGun, label);
		
		serviceManager.reserveGun(savedGun);
		
		Label retrievedLabel = serviceManager.findLabelById(labelId);
		Gun retrievedGun = serviceManager.findGunById(gunId);
		
		assertEquals(GUN_NAME_1, retrievedGun.getName());	
		assertEquals(GUN_DATE_1, retrievedGun.getProductionDate());	
		assertEquals(SOLD_1, retrievedGun.getSold());	
		assertEquals(WEIGHT_1, retrievedGun.getWeight());	
		assertEquals(retrievedLabel.getId(), retrievedGun.getLabel().getId());
		assertEquals(!RESERVED_1, retrievedGun.getLabel().getReserved());
	}
		
	@Test
	public void registerGunCheck() {
		List<User> retrievedUsers = serviceManager.getAllUsers();
		List<Gun> retrievedGuns = serviceManager.getAllGuns();
		
		for (User user : retrievedUsers) {
			if (user.getFirstName().equals(FIRST_NAME_1) && user.getLastName().equals(LAST_NAME_1) && user.getBirthDate().equals(YOB_1)) {
				serviceManager.deleteUser(user);
			}
		}
		
		for (Gun gun : retrievedGuns) {
			if (gun.getName().equals(GUN_NAME_1)) {
				serviceManager.deleteGun(gun);
			}
		}
		
		Gun gun = new Gun(GUN_NAME_2, GUN_DATE_2, SOLD_2, WEIGHT_2);
		Long gunId = serviceManager.addGun(gun);
				
		User user1 = new User(FIRST_NAME_1, LAST_NAME_1, YOB_1);
		Long user1Id = serviceManager.addUser(user1);
		
		User user2 = new User(FIRST_NAME_2, LAST_NAME_2, YOB_2);
		Long user2Id = serviceManager.addUser(user2);
		
		User beforeRegisterUser1 = serviceManager.findUserById(user1Id);
		int beforeRegister1 = beforeRegisterUser1.getNumberOfRegisteredGuns();
		User beforeRegisterUser2 = serviceManager.findUserById(user2Id);
		int beforeRegister2 = beforeRegisterUser2.getNumberOfRegisteredGuns();

		Boolean successed1 = serviceManager.registerGun(user1, gun);
		Boolean successed2 = serviceManager.registerGun(user2, gun);
		
		User afterRegisterUser1 = serviceManager.findUserById(user1Id);
		User afterRegisterUser2 = serviceManager.findUserById(user2Id);
		
		Gun registeredGun = serviceManager.findGunById(gunId);
		
		assertEquals(true, successed1);	
		assertEquals(true, successed2);	
		assertEquals(new Integer(beforeRegister1 + 1), afterRegisterUser1.getNumberOfRegisteredGuns());	
		assertEquals(new Integer(beforeRegister2 + 1), afterRegisterUser2.getNumberOfRegisteredGuns());	
		assertEquals(GUN_NAME_2, registeredGun.getName());	
		assertEquals(GUN_DATE_2, registeredGun.getProductionDate());	
		assertEquals(SOLD_2, registeredGun.getSold());	
		assertEquals(WEIGHT_2, registeredGun.getWeight());	
		assertEquals(null, registeredGun.getLabel());
	}
	
}
