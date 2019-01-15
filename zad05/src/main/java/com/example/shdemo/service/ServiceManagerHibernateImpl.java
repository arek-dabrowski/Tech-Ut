package com.example.shdemo.service;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Distributor;
import com.example.shdemo.domain.Gun;
import com.example.shdemo.domain.Label;
import com.example.shdemo.domain.Producer;
import com.example.shdemo.domain.User;

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
		return sessionFactory.getCurrentSession().getNamedQuery("producer.all").list();
	}

	@Override
	public void deleteProducer(Producer producer) {
		producer = (Producer) sessionFactory.getCurrentSession().get(Producer.class, producer.getId());
		for (Gun gun : findGunsByProducer(producer.getId())) {
			gun.setProducer(null);
			sessionFactory.getCurrentSession().update(gun);
		}
		sessionFactory.getCurrentSession().delete(producer);
	}

	@Override
	public Producer findProducerById(Long id) {
		return (Producer) sessionFactory.getCurrentSession().get(Producer.class, id);
	}
	
	@Override
	public Long addDistributor(Distributor distributor) {
		distributor.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(distributor);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Distributor> getAllDistributors() {
		return sessionFactory.getCurrentSession().getNamedQuery("distributor.all").list();
	}

	@Override
	public void deleteDistributor(Distributor distributor) {
		distributor = (Distributor) sessionFactory.getCurrentSession().get(Distributor.class, distributor.getId());

		for (Gun gun : getAllGuns()) {
			Distributor toRemove = null;
			for(Distributor dist : gun.getDistributors()) {
				if(dist.getId().compareTo(distributor.getId()) == 0) {
					toRemove = dist;
					break;
				}
			}
			if (toRemove != null)
				gun.getDistributors().remove(toRemove);	
		}
		
		sessionFactory.getCurrentSession().delete(distributor);
	}

	@Override
	public Distributor findDistributorById(Long id) {
		return (Distributor) sessionFactory.getCurrentSession().get(Distributor.class, id);
	}


	@Override
	public Long addGun(Gun gun) {
		gun.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(gun);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Gun> getAllGuns() {
		return sessionFactory.getCurrentSession().getNamedQuery("gun.all").list();
	}

	@Override
	public void deleteGun(Gun gun) {
		gun = (Gun) sessionFactory.getCurrentSession().get(Gun.class, gun.getId());
		sessionFactory.getCurrentSession().delete(gun);
	}

	@Override
	public Gun findGunById(Long id) {
		return (Gun) sessionFactory.getCurrentSession().get(Gun.class, id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Gun> findGunsByProducer(Long id) {
		return sessionFactory.getCurrentSession().getNamedQuery("gun.findByProd").setLong("id", id).list();
	}

	@Override
	public Long addLabel(Label label) {
		label.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(label);
	}
	
	@Override
	public Long addLabelToGun(Gun gun, Label label) {
		gun = (Gun) sessionFactory.getCurrentSession().get(Gun.class, gun.getId());
		label.setId(null);
		if (gun != null) {
			gun.setLabel(label);
			sessionFactory.getCurrentSession().update(gun);
		}
		return (Long) sessionFactory.getCurrentSession().save(label);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Label> getAllLabels() {
		return sessionFactory.getCurrentSession().getNamedQuery("label.all").list();
	}

	@Override
	public void deleteLabel(Label label) {
		label = (Label) sessionFactory.getCurrentSession().get(Label.class, label.getId());

		for (Gun gun : getAllGuns()) {
			if(gun.getLabel().getId().compareTo(label.getId()) == 0) {
				gun.setLabel(null);
				sessionFactory.getCurrentSession().update(gun);
				break;
			}
		}
		
		sessionFactory.getCurrentSession().delete(label);
	}

	@Override
	public Label findLabelById(Long id) {
		return (Label) sessionFactory.getCurrentSession().get(Label.class, id);
	}


	@Override
	@SuppressWarnings("deprecation")
	public Long addUser(User user) {
		user.setId(null);
		Date currentDate = new Date();
		currentDate.setYear(currentDate.getYear() + 1900 - 18);
		if(currentDate.compareTo(user.getBirthDate()) == -1) user.setOfAge(false);
		return (Long) sessionFactory.getCurrentSession().save(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return sessionFactory.getCurrentSession().getNamedQuery("user.all").list();
	}

	@Override
	public void deleteUser(User user) {
		user = (User) sessionFactory.getCurrentSession().get(User.class, user.getId());
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public User findUserById(Long id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public List<Gun> getOwnedGuns(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sellGun(Long userId, Long gunId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reserveGun(Long labelId, Long gunId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long addDistributorToGun(Gun gun, Distributor distributor) {
		gun = (Gun) sessionFactory.getCurrentSession().get(Gun.class, gun.getId());
		gun.getDistributors().add(distributor);
		return (Long) sessionFactory.getCurrentSession().save(distributor);
	}

	@Override
	public void registerGun(Long userId, Long gunId) {
		// TODO Auto-generated method stub
		
	}
	
}
