package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Producer;

public interface ServiceManager {

	Long addProducer(Producer producer);
	List<Producer> getAllProducers();
	void deleteProducer(Producer producer);
	Producer findProducerById(Long id);
}
