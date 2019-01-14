package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

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
public class ServiceManagerTest {
	
	@Autowired
	ServiceManager serviceManager;

	private final String COMPANY_NAME_1 = "H&K";
	private final Boolean ACTIVE_1 = true;
	
	private final String COMPANY_NAME_2 = "H&K";
	private final Boolean ACTIVE_2 = true;
	
	private final String GUN_NAME_1 = "MP5";
	private final String GUN_DATE_1 = "16-04-2015";
	private final Boolean SOLD_1 = false;
	private final Double WEIGHT_1 = 2.19;

	@Test
	public void addProducerCheck() {
		Producer producer = new Producer(COMPANY_NAME_1, ACTIVE_1);
		Long producerId = serviceManager.addProducer(producer);
		Producer retrievedProducer = serviceManager.findProducerById(producerId);
		
		assertEquals(COMPANY_NAME_1, retrievedProducer.getCompanyName());
		assertEquals(ACTIVE_1, retrievedProducer.getActive());		
	}
	
	@Test
	public void addGunCheck() {
		Producer producer = new Producer(COMPANY_NAME_2, ACTIVE_2);
		Long producerId = serviceManager.addProducer(producer);
		Producer retrievedProducer = serviceManager.findProducerById(producerId);
		
		Gun gun = new Gun(GUN_NAME_1, GUN_DATE_1, SOLD_1, WEIGHT_1, retrievedProducer);
		Long gunId = serviceManager.addGun(gun);
		Gun retrievedGun = serviceManager.findGunById(gunId);
		
		assertEquals(GUN_NAME_1, retrievedGun.getName());
		assertEquals(GUN_DATE_1, retrievedGun.getProductionDate());
		assertEquals(SOLD_1, retrievedGun.getSold());
		assertEquals(WEIGHT_1, retrievedGun.getWeight());	
	}
	
	@Test
	public void deleteProducerCheck() {
		Producer producer = new Producer(COMPANY_NAME_1, ACTIVE_1);
		Long producerId = serviceManager.addProducer(producer);
		Producer retrievedProducer = serviceManager.findProducerById(producerId);
		
		assertEquals(COMPANY_NAME_1, retrievedProducer.getCompanyName());
		assertEquals(ACTIVE_1, retrievedProducer.getActive());		
	}
	
}
