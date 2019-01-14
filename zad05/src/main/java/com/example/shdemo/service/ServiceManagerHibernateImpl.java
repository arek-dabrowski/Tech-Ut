package com.example.shdemo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Producer;

@Component
@Transactional
public class ServiceManagerHibernateImpl implements ServiceManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Long addProducer(Producer producer) {
		producer.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(producer);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Producer> getAllProducers() {
		return sessionFactory.getCurrentSession().getNamedQuery("producer.all")
				.list();
	}

	@Override
	public void deleteProducer(Producer producer) {
		producer = (Producer) sessionFactory.getCurrentSession().get(Producer.class,
				producer.getId());
		
		sessionFactory.getCurrentSession().delete(producer);
		
	}

	@Override
	public Producer findProducerById(Long id) {
		return (Producer) sessionFactory.getCurrentSession().get(Producer.class, id);
	}

}
