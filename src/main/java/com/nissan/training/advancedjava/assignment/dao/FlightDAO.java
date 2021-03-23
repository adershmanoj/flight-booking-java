package com.nissan.training.advancedjava.assignment.dao;

import java.util.List;

import com.nissan.training.advancedjava.assignment.model.Flight;
import com.nissan.training.advancedjava.assignment.model.Search;

public interface FlightDAO {
	public List<Flight> getFlights(Search search);
	public Flight getFlight(int id);
	public void reduceFlightCapacity(int flightId);
}
