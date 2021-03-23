package com.nissan.training.advancedjava.assignment.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nissan.training.advancedjava.assignment.model.Flight;
import com.nissan.training.advancedjava.assignment.model.Search;

@Repository
public class FlightDAOImpl implements FlightDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> getFlights(Search search) {
		String hql = "from Flight where departure = '" + search.getDeparture() + "' and arrival = '"+search.getDestination()+"' and seats>="+search.getSeats();
		return (List<Flight>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public Flight getFlight(int id) {
		return (Flight) sessionFactory.getCurrentSession().get(
                Flight.class, id);
	}

	@Override
	public void reduceFlightCapacity(int flightId) {
		String hql = "Update Flight set seats = seats-1 where id="+flightId;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
		// TODO Auto-generated method stub
		
	}
}
