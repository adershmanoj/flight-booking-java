package com.nissan.training.advancedjava.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nissan.training.advancedjava.assignment.dao.BookingDAO;
import com.nissan.training.advancedjava.assignment.dao.CustomerDAO;
import com.nissan.training.advancedjava.assignment.dao.FlightDAO;
import com.nissan.training.advancedjava.assignment.model.Booking;
import com.nissan.training.advancedjava.assignment.model.Customer;
import com.nissan.training.advancedjava.assignment.model.Flight;
import com.nissan.training.advancedjava.assignment.model.Search;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private FlightDAO flightDAO;
	
	@Autowired
	private BookingDAO bookingDAO;

	@Override
	@Transactional
	public boolean addCustomer(Customer customer) {
		return customerDAO.addCustomer(customer);
	}

	public Customer getCustomer(String email) {
		return customerDAO.getCustomer(email);
	}

	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDAO.updateCustomer(customer);
	}

	@Override
	public List<Flight> searchFlights(Search search) {
		// TODO Auto-generated method stub
		return flightDAO.getFlights(search);
	}

	@Override
	public void addBooking(Booking booking) {
		bookingDAO.addBooking(booking);
		
	}

	@Override
	public List<Booking> viewBooking(String email) {
		return bookingDAO.viewBooking(email);
	}

	@Override
	public void cancelBooking(String id) {
		bookingDAO.cancelBooking(id);
		
	}

	@Override
	public Flight getFlightById(int id) {
		return flightDAO.getFlight(id);
	}

	@Override
	public List<Flight> getFlightsFromBookings(List<Booking> bookingList) {
		List<Flight> flightList = new ArrayList<Flight>();
		for(Booking b: bookingList) { // get flight details for each booking
			flightList.add(getFlightById(b.getFlightId()));
		}
		return flightList;
	}

	@Override
	public void reduceFlightCapacity(int flightId) {
		flightDAO.reduceFlightCapacity(flightId);
		
	}
	

}
