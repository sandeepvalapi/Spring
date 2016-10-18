package com.whitebay.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.whitebay.rest.bo.Customer;

@Component
public class CustomerDao {

	List<Customer> customerList = new ArrayList<>();

	public CustomerDao() {
		customerList.add(new Customer(111, "Mallik", "reddy", "malreddy@deloitte.com", "0099887766"));
		customerList.add(new Customer(112, "Ravi", "Pokuru", "rpokuru@deloitte.com", "9988776655"));
		customerList.add(new Customer(113, "Loganathan", "Meenakshi", "lmeena@deloitte.com", "6677889955"));
		customerList.add(new Customer(114, "Sandeep", "Valapi", "svalapi@deloitte.com", "6677889955"));
		customerList.add(new Customer(115, "Akriti", "Agarwal", "akagarwal@deloitte.com", "6677889955"));
		customerList.add(new Customer(116, "Charan", "Kuninty", "ckuninty@deloitte.com", "6677889955"));
		customerList.add(new Customer(117, "Aditya", "Majeti", "amajeti@deloitte.com", "6677889955"));
	}

	public List<Customer> getCustomers() {
		return customerList;
	}

	public Customer get(int id) {
		for (Customer c : customerList) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
}
