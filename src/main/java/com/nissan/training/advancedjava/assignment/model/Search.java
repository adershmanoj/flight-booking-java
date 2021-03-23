package com.nissan.training.advancedjava.assignment.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Search {
	private String departure;
	private String destination;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;
	private int seats;
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
}
