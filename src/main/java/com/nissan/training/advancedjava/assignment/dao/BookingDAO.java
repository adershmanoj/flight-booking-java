package com.nissan.training.advancedjava.assignment.dao;

import java.util.List;

import com.nissan.training.advancedjava.assignment.model.Booking;

public interface BookingDAO {
	public void addBooking(Booking booking);
	public List<Booking> viewBooking(String email);
	public void cancelBooking(String id);
}
