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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class LabelTest {
	
	@Autowired
	ServiceManager serviceManager;

	private final Boolean RESERVED_1 = true;
	private final Double PRICE_1 = 199.99;
	
	private final Boolean RESERVED_2 = true;
	private final Double PRICE_2 = 2999.99;

	private final String GUN_NAME_1 = "MP5";
	@SuppressWarnings("deprecation")
	private final Date GUN_DATE_1 = new Date(2015, 4, 16);
	private final Boolean SOLD_1 = false;
	private final Double WEIGHT_1 = 2.19;
	
	private final String GUN_NAME_2 = "AK-47";
	@SuppressWarnings("deprecation")
	private final Date GUN_DATE_2 = new Date(1999, 1, 1);
	private final Boolean SOLD_2 = false;
	private final Double WEIGHT_2 = 3.14;
	
	@Test
	public void addLabelCheck() {
		List<Label> retrievedLabels = serviceManager.getAllLabels();

		for (Label label : retrievedLabels) {
			if (label.getReserved().equals(RESERVED_1) && label.getPrice().equals(PRICE_1)) {
				serviceManager.deleteLabel(label);
			}
		}
		
		Label label = new Label(RESERVED_1, PRICE_1);
		Long labelId = serviceManager.addLabel(label);
		Label retrievedLabel = serviceManager.findLabelById(labelId);
		
		assertEquals(RESERVED_1, retrievedLabel.getReserved());
		assertEquals(PRICE_1, retrievedLabel.getPrice());		
	}
	
	@Test
	public void updateLabelCheck() {
		List<Label> retrievedLabels = serviceManager.getAllLabels();

		for (Label label : retrievedLabels) {
			if (label.getReserved().equals(RESERVED_1) && label.getPrice().equals(PRICE_1)) {
				serviceManager.deleteLabel(label);
			}
		}
		
		Label label = new Label(RESERVED_1, PRICE_1);
		Long labelId = serviceManager.addLabel(label);
		
		Label labelToUpdate = serviceManager.findLabelById(labelId);
		labelToUpdate.setPrice(PRICE_2);
		serviceManager.updateLabel(labelToUpdate);
		
		Label retrievedLabel = serviceManager.findLabelById(labelId);
		
		assertEquals(RESERVED_1, retrievedLabel.getReserved());
		assertEquals(PRICE_2, retrievedLabel.getPrice());		
	}
	
	@Test
	public void deleteLabelCheck() {
		List<Label> retrievedLabels = serviceManager.getAllLabels();

		for (Label label : retrievedLabels) {
			if (label.getReserved().equals(RESERVED_1) && label.getPrice().equals(PRICE_1)) {
				serviceManager.deleteLabel(label);
			}
		}
		
		Label label1 = new Label(RESERVED_1, PRICE_1);
		Label label2 = new Label(RESERVED_2, PRICE_2);
		
		Long label1Id = serviceManager.addLabel(label1);
		Long label2Id = serviceManager.addLabel(label2);
		
		Label labelToDelete = serviceManager.findLabelById(label2Id);
		
		int beforeDeleteSize = serviceManager.getAllLabels().size();
		serviceManager.deleteLabel(labelToDelete);
		int afterDeleteSize = serviceManager.getAllLabels().size();
		
		Label lastLabel = serviceManager.getAllLabels().get(afterDeleteSize-1);
		
		assertEquals(beforeDeleteSize, afterDeleteSize + 1);
		assertEquals(lastLabel.getId(), label1Id);
		assertEquals(RESERVED_1, lastLabel.getReserved());
		assertEquals(PRICE_1, lastLabel.getPrice());		
	}
	
	
	@Test
	public void deleteLabelAssignedToGunCheck() {
		List<Label> retrievedLabels = serviceManager.getAllLabels();
		List<Gun> retrievedGuns = serviceManager.getAllGuns();

		for (Label label : retrievedLabels) {
			if (label.getReserved().equals(RESERVED_1) && label.getPrice().equals(PRICE_1)) {
				serviceManager.deleteLabel(label);
			}
		}
		
		for (Gun gun : retrievedGuns) {
			if (gun.getName().equals(GUN_NAME_1) || gun.getName().equals(GUN_NAME_2)) {
				serviceManager.deleteGun(gun);
			}
		}
		
		Gun gun1 = new Gun(GUN_NAME_1, GUN_DATE_1, SOLD_1, WEIGHT_1);
		Long gun1Id = serviceManager.addGun(gun1);
		
		Gun gun2 = new Gun(GUN_NAME_2, GUN_DATE_2, SOLD_2, WEIGHT_2);
		Long gun2Id = serviceManager.addGun(gun2);
		
		Label label1 = new Label(RESERVED_1, PRICE_1);
		Label label2 = new Label(RESERVED_2, PRICE_2);
		
		serviceManager.addLabelToGun(gun1, label1);
		Long label2Id = serviceManager.addLabelToGun(gun2, label2);
		
		Label labelToDelete = serviceManager.findLabelById(label2Id);
		
		int beforeDeleteSize = serviceManager.getAllLabels().size();
		serviceManager.deleteLabel(labelToDelete);
		int afterDeleteSize = serviceManager.getAllLabels().size();
		
		Label lastLabel = serviceManager.getAllLabels().get(afterDeleteSize-1);
		Gun retrievedGun1 = serviceManager.findGunById(gun1Id);
		Gun retrievedGun2 = serviceManager.findGunById(gun2Id);
		
		assertEquals(beforeDeleteSize, afterDeleteSize + 1);
		assertEquals(lastLabel.getId(), retrievedGun1.getLabel().getId());
		assertEquals(null, retrievedGun2.getLabel());
		assertEquals(gun1.getId(), retrievedGun1.getId());
		assertEquals(gun2.getId(), retrievedGun2.getId());
		assertEquals(RESERVED_1, lastLabel.getReserved());
		assertEquals(PRICE_1, lastLabel.getPrice());		
	}
	
}
