package com.nissan.training.advancedjava.assignment.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nissan.training.advancedjava.assignment.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean addCustomer(Customer customer) {
		String hql = "from Customer where email='" + customer.getEmail() + "'";
		if(sessionFactory.getCurrentSession().createQuery(hql).uniqueResult() == null) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
		return true;
		}
		else {
			return false;
		}
	}

	public Customer getCustomer(String email) {
		String hql = "from Customer where email='" + email + "'";
		try {
			return (Customer) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		sessionFactory.getCurrentSession().update(customer);
		return customer;
	}

}