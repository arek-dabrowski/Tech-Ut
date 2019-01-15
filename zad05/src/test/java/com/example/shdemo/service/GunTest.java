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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class GunTest {
	
	@Autowired
	ServiceManager serviceManager;

//	private final String COMPANY_NAME_1 = "H&K";
//	private final Boolean ACTIVE_1 = true;
	
	private final String GUN_NAME_1 = "MP5";
	@SuppressWarnings("deprecation")
	private final Date GUN_DATE_1 = new Date(2015, 4, 16);
	private final Boolean SOLD_1 = false;
	private final Double WEIGHT_1 = 2.19;
	
	private final String GUN_NAME_2 = "AK-47";
	@SuppressWarnings("deprecation")
	private final Date GUN_DATE_2 = new Date(1999, 2, 15);
	private final Boolean SOLD_2 = true;
	private final Double WEIGHT_2 = 3.14;
	
	@Test
	public void addGunCheck() {
		List<Gun> retrievedGuns = serviceManager.getAllGuns();
		for (Gun gun : retrievedGuns) {
			if (gun.getName().equals(GUN_NAME_1)) {
				serviceManager.deleteGun(gun);
			}
		}
		
		Gun gun = new Gun(GUN_NAME_1, GUN_DATE_1, SOLD_1, WEIGHT_1);
		Long gunId = serviceManager.addGun(gun);
		Gun retrievedGun = serviceManager.findGunById(gunId);
		
		assertEquals(GUN_NAME_1, retrievedGun.getName());
		assertEquals(GUN_DATE_1, retrievedGun.getProductionDate());
		assertEquals(SOLD_1, retrievedGun.getSold());
		assertEquals(WEIGHT_1, retrievedGun.getWeight());
	}
	
	@Test
	public void updateGunCheck() {
		List<Gun> retrievedGuns = serviceManager.getAllGuns();
		for (Gun gun : retrievedGuns) {
			if (gun.getName().equals(GUN_NAME_1)) {
				serviceManager.deleteGun(gun);
			}
		}
		
		Gun gun = new Gun(GUN_NAME_1, GUN_DATE_1, SOLD_1, WEIGHT_1);
		Long gunId = serviceManager.addGun(gun);
		
		Gun gunToUpdate = serviceManager.findGunById(gunId);
		gunToUpdate.setProductionDate(GUN_DATE_2);
		gunToUpdate.setSold(SOLD_2);
		gunToUpdate.setWeight(WEIGHT_2);
		serviceManager.updateGun(gunToUpdate);
		
		Gun retrievedGun = serviceManager.findGunById(gunId);
		
		assertEquals(GUN_NAME_1, retrievedGun.getName());
		assertEquals(GUN_DATE_2, retrievedGun.getProductionDate());
		assertEquals(SOLD_2, retrievedGun.getSold());
		assertEquals(WEIGHT_2, retrievedGun.getWeight());
	}		
}
