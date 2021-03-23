package com.nissan.training.advancedjava.assignment.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nissan.training.advancedjava.assignment.model.Booking;

@Repository
public class BookingDAOImpl implements BookingDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addBooking(Booking booking) {
		System.out.println(booking.getEmail());
		sessionFactory.getCurrentSession().saveOrUpdate(booking);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> viewBooking(String email) {
		String hql = "from Booking where email='"+email+"'";
		return (List<Booking>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public void cancelBooking(String id) {
		Booking b = new Booking();
		b.setId(Integer.parseInt(id));
		sessionFactory.getCurrentSession().delete(b);
	}

}
