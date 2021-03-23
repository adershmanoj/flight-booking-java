package com.nissan.training.advancedjava.assignment.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nissan.training.advancedjava.assignment.model.Booking;
import com.nissan.training.advancedjava.assignment.model.Customer;
import com.nissan.training.advancedjava.assignment.model.Flight;
import com.nissan.training.advancedjava.assignment.model.Login;
import com.nissan.training.advancedjava.assignment.model.Search;
import com.nissan.training.advancedjava.assignment.service.CustomerService;

@Controller
public class CustomerController {

	private static final Logger logger = Logger.getLogger(CustomerController.class);

	public CustomerController() {
		System.out.println("Controller()");
	}

	@Autowired
	private CustomerService customerService;
	private Customer customer;
	private int flightId;
	private int seats;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping(value = "/") // home page
	public ModelAndView displayHome(ModelAndView model) throws IOException {
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView newContact(@ModelAttribute Customer customer, ModelAndView modelview) {
		if (customerService.addCustomer(customer)) {
			ModelAndView model = new ModelAndView("welcome");
			model.addObject("customer", customer);
			return model;
		} else {
			modelview.setViewName("home");
			modelview.addObject("error", "This username already exists. Please enter a different one");
			return modelview;
		}
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ModelAndView saveCustomer(@ModelAttribute Login login, ModelAndView modelview) {
		modelview.setViewName("home");
		customer = customerService.getCustomer(login.getEmail()); // getting customer record
		if (customer != null) {
			if (customer.getPassword().equals(login.getPassword())) { // correct password
				ModelAndView model = new ModelAndView("welcome");
				model.addObject("customer", customer);
				return model;

			} else {
				modelview.addObject("error", "Incorrect password");
				return modelview;
			}
		} else {
			modelview.addObject("error", "This email does not exist");
			return modelview;
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchFlights(@ModelAttribute Search search) {
		seats = search.getSeats();
		List<Flight> flightList = customerService.searchFlights(search);
		ModelAndView model = new ModelAndView("list-flights");
		model.addObject("flights", flightList);
		return model;
	}

	@RequestMapping(value = "/booking", method = RequestMethod.GET)
	public ModelAndView bookingDetails(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("booking");
		flightId = Integer.parseInt(request.getParameter("id"));
		return model;
		
	}

	@RequestMapping(value = "/payment")
	public ModelAndView saveBooking(@ModelAttribute Booking booking) {
		ModelAndView model = new ModelAndView();
		booking.setEmail(customer.getEmail());
		booking.setFlightId(flightId);
		while (--seats > 0) {
			customerService.addBooking(booking);
			customerService.reduceFlightCapacity(flightId);
			model.setViewName("booking");
			return model;
		}
		customerService.addBooking(booking);
		customerService.reduceFlightCapacity(flightId);
		model.setViewName("payment");
		return model;
	}

	@RequestMapping(value = "/view")
	public ModelAndView viewBooking() {
		ModelAndView model = new ModelAndView();
		List<Booking> bookingList = customerService.viewBooking(customer.getEmail());
		if (bookingList.size() != 0) { // booking found
			List<Flight> flightList = customerService.getFlightsFromBookings(bookingList);
			model.setViewName("view-booking");
			model.addObject("bookings", bookingList);
			model.addObject("flights", flightList);
		} else

		{
			model.setViewName("welcome");
			model.addObject("message", "No booking found");
		}
		return model;
	}

	@RequestMapping(value = "/cancel")
	public ModelAndView cancelBooking() {
		ModelAndView model = new ModelAndView();
		List<Booking> bookingList = customerService.viewBooking(customer.getEmail());
		if (bookingList.size() != 0) {
			List<Flight> flightList = customerService.getFlightsFromBookings(bookingList);
			model.setViewName("cancel-booking");
			model.addObject("bookings", bookingList);
			model.addObject("flights", flightList);
		} else {
			model.setViewName("welcome");
			model.addObject("message", "No booking found");
		}
		return model;
	}

	@RequestMapping(value = "/cancelBooking", method = RequestMethod.GET)
	public ModelAndView cancelBooking(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("welcome");
		String id = request.getParameter("id");
		customerService.cancelBooking(id);
		model.addObject("message", "Successfully cancelled booking");
		return model;
	}

	@RequestMapping(value = "/update")
	public ModelAndView updateCustomer() {
		ModelAndView model = new ModelAndView("update");
		model.addObject("customer", customer);
		return model;
	}

	@RequestMapping(value = "/updateProfile")
	public ModelAndView updateCustomerProfile(@ModelAttribute("customer") Customer newCustomer) {
		ModelAndView model = new ModelAndView("welcome");
		newCustomer.setId(customer.getId());
		customer = customerService.updateCustomer(newCustomer);
		model.addObject("message", "Profile updated successfully!");
		return model;
	}

}