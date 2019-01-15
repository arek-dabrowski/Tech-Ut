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
import com.example.shdemo.domain.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class GunTest {
	
	@Autowired
	ServiceManager serviceManager;

	private final String COMPANY_NAME_1 = "H&K";
	private final Boolean ACTIVE_1 = true;
	
	private final String GUN_NAME_1 = "MP5";
	@SuppressWarnings("deprecation")
	private final Date GUN_DATE_1 = new Date(2015, 4, 16);
	private final Boolean SOLD_1 = false;
	private final Double WEIGHT_1 = 2.19;
	
	@Test
	public void addGunCheck() {
		List<Gun> retrievedGuns = serviceManager.getAllGuns();
		for (Gun gun : retrievedGuns) {
			if (gun.getName().equals(GUN_NAME_1)) {
				serviceManager.deleteGun(gun);
			}
		}
		
		List<Producer> retrievedProducers = serviceManager.getAllProducers();
		for (Producer producer : retrievedProducers) {
			if (producer.getCompanyName().equals(COMPANY_NAME_1)) {
				serviceManager.deleteProducer(producer);
			}
		}
		
		Producer producer = new Producer(COMPANY_NAME_1, ACTIVE_1);
		Long producerId = serviceManager.addProducer(producer);
		Producer retrievedProducer = serviceManager.findProducerById(producerId);
		
		Gun gun = new Gun(GUN_NAME_1, GUN_DATE_1, SOLD_1, WEIGHT_1, retrievedProducer);
		Long gunId = serviceManager.addGun(gun);
		Gun retrievedGun = serviceManager.findGunById(gunId);
		
		assertEquals(GUN_NAME_1, retrievedGun.getName());
		assertEquals(GUN_DATE_1, retrievedGun.getProductionDate());
		assertEquals(SOLD_1, retrievedGun.getSold());
		assertEquals(WEIGHT_1, retrievedGun.getWeight());
		assertEquals(COMPANY_NAME_1, retrievedGun.getProducer().getCompanyName());	
		assertEquals(ACTIVE_1, retrievedGun.getProducer().getActive());	
	}
		
}
