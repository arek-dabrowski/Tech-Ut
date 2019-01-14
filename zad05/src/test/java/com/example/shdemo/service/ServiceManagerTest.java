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

import com.example.shdemo.domain.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ServiceManagerTest {
	
	@Autowired
	ServiceManager serviceManager;

	private final String COMPANY_NAME_1 = "Bolek";
	private final Boolean ACTIVE_1 = true;

//	private final String NAME_2 = "Lolek";
//	private final String PIN_2 = "4321";
//
//	private final String MODEL_1 = "126p";
//	private final String MAKE_1 = "Fiat";
//
//	private final String MODEL_2 = "Mondeo";
//	private final String MAKE_2 = "Ford";

	@Test
	public void addProducerCheck() {

		Producer producer = new Producer();
		producer.setCompanyName(COMPANY_NAME_1);
		producer.setActive(ACTIVE_1);
		// ... other properties here

		Long producerId = serviceManager.addProducer(producer);

		Producer retrievedPerson = serviceManager.findProducerById(producerId);
		assertEquals(COMPANY_NAME_1, retrievedPerson.getCompanyName());
		assertEquals(ACTIVE_1, retrievedPerson.getActive());
		// ... check other properties here	
		
		}
	
}
