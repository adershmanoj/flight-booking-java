package com.nissan.training.advancedjava.assignment.service;

import java.util.List;

import com.nissan.training.advancedjava.assignment.model.Booking;
import com.nissan.training.advancedjava.assignment.model.Customer;
import com.nissan.training.advancedjava.assignment.model.Flight;
import com.nissan.training.advancedjava.assignment.model.Search;

public interface CustomerService {
	
	public boolean addCustomer(Customer customer);

	public Customer getCustomer(String username);

	public Customer updateCustomer(Customer customer);
	
	public List<Flight> searchFlights(Search search);
	
	public void addBooking(Booking booking);
	
	public List<Booking> viewBooking(String email);
	
	public void cancelBooking(String id);
	
	public Flight getFlightById(int id);

	public List<Flight> getFlightsFromBookings(List<Booking> bookingList);
	
	public void reduceFlightCapacity(int flightId);
}
