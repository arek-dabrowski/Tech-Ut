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
public class ProducerTest {
	
	@Autowired
	ServiceManager serviceManager;

	private final String COMPANY_NAME_1 = "H&K";
	private final Boolean ACTIVE_1 = true;
	
	private final String COMPANY_NAME_2 = "S&W";
	private final Boolean ACTIVE_2 = true;

	@Test
	public void addProducerCheck() {
		List<Producer> retrievedProducers = serviceManager.getAllProducers();

		for (Producer producer : retrievedProducers) {
			if (producer.getCompanyName().equals(COMPANY_NAME_1)) {
				serviceManager.deleteProducer(producer);
			}
		}
		
		Producer producer = new Producer(COMPANY_NAME_1, ACTIVE_1);
		Long producerId = serviceManager.addProducer(producer);
		Producer retrievedProducer = serviceManager.findProducerById(producerId);
		
		assertEquals(COMPANY_NAME_1, retrievedProducer.getCompanyName());
		assertEquals(ACTIVE_1, retrievedProducer.getActive());		
	}
	
	
	@Test
	public void deleteProducerCheck() {
		List<Producer> retrievedProducers = serviceManager.getAllProducers();

		for (Producer producer : retrievedProducers) {
			if (producer.getCompanyName().equals(COMPANY_NAME_1) || producer.getCompanyName().equals(COMPANY_NAME_2)) {
				serviceManager.deleteProducer(producer);
			}
		}
		
		Producer producer1 = new Producer(COMPANY_NAME_1, ACTIVE_1);
		Producer producer2 = new Producer(COMPANY_NAME_2, ACTIVE_2);
		
		Long producer1Id = serviceManager.addProducer(producer1);
		serviceManager.addProducer(producer2);
		
		Producer producerToDelete = serviceManager.findProducerById(producer1Id);
		
		int beforeDeleteSize = serviceManager.getAllProducers().size();
		serviceManager.deleteProducer(producerToDelete);
		int afterDeleteSize = serviceManager.getAllProducers().size();
		
		Producer lastProducer = serviceManager.getAllProducers().get(afterDeleteSize-1);
		
		assertEquals(beforeDeleteSize, afterDeleteSize + 1);
		assertEquals(COMPANY_NAME_2, lastProducer.getCompanyName());
		assertEquals(ACTIVE_2, lastProducer.getActive());		
	}
	
}
