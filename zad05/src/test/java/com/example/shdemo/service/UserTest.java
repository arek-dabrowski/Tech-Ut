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
import com.example.shdemo.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class UserTest {
	
	@Autowired
	ServiceManager serviceManager;

	private final String FIRST_NAME_1 = "John";
	private final String LAST_NAME_1 = "Doe";
	@SuppressWarnings("deprecation")
	private final Date YOB_1 = new Date(1995, 2, 2);
	
	private final String FIRST_NAME_2 = "Anna";
	private final String LAST_NAME_2 = "Nowak";
	@SuppressWarnings("deprecation")
	private final Date YOB_2 = new Date(2006, 6, 18);
	
	private final String GUN_NAME_1 = "MP5";
	@SuppressWarnings("deprecation")
	private final Date GUN_DATE_1 = new Date(2015, 4, 16);
	private final Boolean SOLD_1 = false;
	private final Double WEIGHT_1 = 2.19;
	
	private final String GUN_NAME_2 = "AK-47";
	@SuppressWarnings("deprecation")
	private final Date GUN_DATE_2 = new Date(1999, 2, 15);
	private final Boolean SOLD_2 = false;
	private final Double WEIGHT_2 = 3.14;
	
	@Test
	public void addUserCheck() {
		List<User> retrievedUsers = serviceManager.getAllUsers();

		for (User user : retrievedUsers) {
			if (user.getFirstName().equals(FIRST_NAME_1) && user.getLastName().equals(LAST_NAME_1) && user.getBirthDate().equals(YOB_1)) {
				serviceManager.deleteUser(user);
			}
		}
		
		User user = new User(FIRST_NAME_1, LAST_NAME_1, YOB_1);
		Long userId = serviceManager.addUser(user);
		User retrievedUser = serviceManager.findUserById(userId);
		
		assertEquals(FIRST_NAME_1, retrievedUser.getFirstName());
		assertEquals(LAST_NAME_1, retrievedUser.getLastName());		
		assertEquals(YOB_1, retrievedUser.getBirthDate());
		assertEquals(true, retrievedUser.getOfAge());
		assertEquals(new Integer(0), retrievedUser.getNumberOfRegisteredGuns());
	}
	
	@Test
	public void updateUserCheck() {
		List<User> retrievedUsers = serviceManager.getAllUsers();

		for (User user : retrievedUsers) {
			if (user.getFirstName().equals(FIRST_NAME_1) && user.getLastName().equals(LAST_NAME_1) && user.getBirthDate().equals(YOB_1)) {
				serviceManager.deleteUser(user);
			}
		}
		
		User user = new User(FIRST_NAME_1, LAST_NAME_1, YOB_1);
		Long userId = serviceManager.addUser(user);
		User userToUpdate = serviceManager.findUserById(userId);
		userToUpdate.setFirstName(FIRST_NAME_2);
		userToUpdate.setBirthDate(YOB_2);
		
		serviceManager.updateUser(userToUpdate);

		User retrievedUser = serviceManager.findUserById(userId);
		
		assertEquals(FIRST_NAME_2, retrievedUser.getFirstName());
		assertEquals(LAST_NAME_1, retrievedUser.getLastName());		
		assertEquals(YOB_2, retrievedUser.getBirthDate());
		assertEquals(false, retrievedUser.getOfAge());
		assertEquals(new Integer(0), retrievedUser.getNumberOfRegisteredGuns());
	}
	
	@Test
	public void deletUserCheck() {
		List<User> retrievedUsers = serviceManager.getAllUsers();

		for (User user : retrievedUsers) {
			if (user.getFirstName().equals(FIRST_NAME_1) && user.getLastName().equals(LAST_NAME_1) && user.getBirthDate().equals(YOB_1)) {
				serviceManager.deleteUser(user);
			}
		}
		
		User user1 = new User(FIRST_NAME_1, LAST_NAME_1, YOB_1);
		User user2 = new User(FIRST_NAME_2, LAST_NAME_2, YOB_2);
		
		Long user1Id = serviceManager.addUser(user1);
		Long user2Id = serviceManager.addUser(user2);
		
		User userToDelete = serviceManager.findUserById(user2Id);
		
		int beforeDeleteSize = serviceManager.getAllUsers().size();
		serviceManager.deleteUser(userToDelete);
		int afterDeleteSize = serviceManager.getAllUsers().size();
		
		User lastUser = serviceManager.getAllUsers().get(afterDeleteSize-1);
		
		assertEquals(beforeDeleteSize, afterDeleteSize + 1);
		assertEquals(lastUser.getId(), user1Id);
		assertEquals(FIRST_NAME_1, lastUser.getFirstName());
		assertEquals(LAST_NAME_1, lastUser.getLastName());		
		assertEquals(YOB_1, lastUser.getBirthDate());
		assertEquals(true, lastUser.getOfAge());
		assertEquals(new Integer(0), lastUser.getNumberOfRegisteredGuns());	
	}
	
	
	@Test
	public void deleteUserAssignedToGunCheck() {
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
		
		
		Gun gun1 = new Gun(GUN_NAME_1, GUN_DATE_1, SOLD_1, WEIGHT_1);
		Long gun1Id = serviceManager.addGun(gun1);
		
		Gun gun2 = new Gun(GUN_NAME_2, GUN_DATE_2, SOLD_2, WEIGHT_2);
		Long gun2Id = serviceManager.addGun(gun2);
		
		User user1 = new User(FIRST_NAME_1, LAST_NAME_1, YOB_1);
		User user2 = new User(FIRST_NAME_2, LAST_NAME_2, YOB_2);
		
		Long user1Id = serviceManager.addUserToGun(gun1, user1);
		Long user2Id = serviceManager.addUserToGun(gun2, user2);
		
		User userToDelete = serviceManager.findUserById(user2Id);
		
		int beforeDeleteSize = serviceManager.getAllUsers().size();
		serviceManager.deleteUser(userToDelete);
		int afterDeleteSize = serviceManager.getAllUsers().size();
		
		User lastUser = serviceManager.getAllUsers().get(afterDeleteSize-1);
		Gun retrievedGun1 = serviceManager.findGunById(gun1Id);
		Gun retrievedGun2 = serviceManager.findGunById(gun2Id);
		
		assertEquals(beforeDeleteSize, afterDeleteSize + 1);
		assertEquals(gun1.getId(), retrievedGun1.getId());
		assertEquals(gun2.getId(), retrievedGun2.getId());
		assertEquals(user1Id, lastUser.getId());
		assertEquals(FIRST_NAME_1, lastUser.getFirstName());
		assertEquals(LAST_NAME_1, lastUser.getLastName());		
		assertEquals(YOB_1, lastUser.getBirthDate());
		assertEquals(true, lastUser.getOfAge());
		assertEquals(new Integer(0), lastUser.getNumberOfRegisteredGuns());			
	}
	
}
