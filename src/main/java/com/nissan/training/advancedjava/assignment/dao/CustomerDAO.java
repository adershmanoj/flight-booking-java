package com.nissan.training.advancedjava.assignment.dao;


import com.nissan.training.advancedjava.assignment.model.Customer;

public interface CustomerDAO {

	public boolean addCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public Customer getCustomer(String username);
}
