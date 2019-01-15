package com.example.shdemo.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Distributor;
import com.example.shdemo.domain.Gun;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class DistributorTest {
	
	@Autowired
	ServiceManager serviceManager;

	private final String COMPANY_NAME_1 = "DHL";
	private final String COUNTRY_1 = "Poland";
	
	private final String COMPANY_NAME_2 = "Deliv";
	private final String COUNTRY_2 = "Hawaii";
	
	private final String GUN_NAME_1 = "MP5";
	@SuppressWarnings("deprecation")
	private final Date GUN_DATE_1 = new Date(2015, 4, 16);
	private final Boolean SOLD_1 = false;
	private final Double WEIGHT_1 = 2.19;

	
	@Test
	public void addDistributorCheck() {
		List<Distributor> retrievedDistributors = serviceManager.getAllDistributors();

		for (Distributor distributor : retrievedDistributors) {
			if (distributor.getCompanyName().equals(COMPANY_NAME_1)) {
				serviceManager.deleteDistributor(distributor);
			}
		}
		
		Distributor distributor = new Distributor(COMPANY_NAME_1, COUNTRY_1);
		Long distributorId = serviceManager.addDistributor(distributor);
		Distributor retrievedDistributor = serviceManager.findDistributorById(distributorId);
		
		assertEquals(COMPANY_NAME_1, retrievedDistributor.getCompanyName());
		assertEquals(COUNTRY_1, retrievedDistributor.getCountry());		
	}
	
	@Test
	public void deleteDistributorCheck() {
		List<Distributor> retrievedDistributors = serviceManager.getAllDistributors();

		for (Distributor distributor : retrievedDistributors) {
			if (distributor.getCompanyName().equals(COMPANY_NAME_1) || distributor.getCompanyName().equals(COMPANY_NAME_2)) {
				serviceManager.deleteDistributor(distributor);
			}
		}
		
		Distributor distributor1 = new Distributor(COMPANY_NAME_1, COUNTRY_1);
		Distributor distributor2 = new Distributor(COMPANY_NAME_2, COUNTRY_2);
		Long distributor1Id = serviceManager.addDistributor(distributor1);
		Long distributor2Id = serviceManager.addDistributor(distributor2);
		Distributor distributorToDelete = serviceManager.findDistributorById(distributor2Id);
		
		int beforeDeleteSize = serviceManager.getAllDistributors().size();
		serviceManager.deleteDistributor(distributorToDelete);
		int afterDeleteSize = serviceManager.getAllDistributors().size();

		Distributor lastDistributor = serviceManager.getAllDistributors().get(afterDeleteSize-1);
		
		assertEquals(beforeDeleteSize, afterDeleteSize + 1);
		assertEquals(distributor1Id, lastDistributor.getId());
		assertEquals(COMPANY_NAME_1, lastDistributor.getCompanyName());
		assertEquals(COUNTRY_1, lastDistributor.getCountry());
	}
	
	@Test
	public void deleteDistributorAssignedToGunCheck() {
		List<Distributor> retrievedDistributors = serviceManager.getAllDistributors();

		for (Distributor distributor : retrievedDistributors) {
			if (distributor.getCompanyName().equals(COMPANY_NAME_1) || distributor.getCompanyName().equals(COMPANY_NAME_2)) {
				serviceManager.deleteDistributor(distributor);
			}
		}
		
		Gun gun = new Gun(GUN_NAME_1, GUN_DATE_1, SOLD_1, WEIGHT_1);
		serviceManager.addGun(gun);
		
		Distributor distributor1 = new Distributor(COMPANY_NAME_1, COUNTRY_1);
		Distributor distributor2 = new Distributor(COMPANY_NAME_2, COUNTRY_2);
		Long distributor1Id = serviceManager.addDistributorToGun(gun, distributor1);
		Long distributor2Id = serviceManager.addDistributorToGun(gun, distributor2);
		
		Distributor distributorToDelete = serviceManager.findDistributorById(distributor2Id);
		
		int beforeDeleteSize = serviceManager.getAllDistributors().size();
		serviceManager.deleteDistributor(distributorToDelete);
		int afterDeleteSize = serviceManager.getAllDistributors().size();
		
		Distributor lastDistributor = serviceManager.getAllDistributors().get(afterDeleteSize-1);
		
		assertEquals(beforeDeleteSize, afterDeleteSize + 1);
		assertEquals(distributor1Id, lastDistributor.getId());
		assertEquals(COMPANY_NAME_1, lastDistributor.getCompanyName());
		assertEquals(COUNTRY_1, lastDistributor.getCountry());		
	}
	
}
