package com.coach.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coach.rest.bo.Customer;
import com.coach.rest.bo.Employee;
import com.coach.rest.bo.Message;
import com.coach.rest.dao.CustomerDao;

@RestController()
public class SampleRestController {

	@Autowired
	CustomerDao customerDao;

	@RequestMapping("/")
	public String welcome() {
		return "Welcome to Rest Example.";
	}

	/*
	 * Simple Get Request
	 */
	// @PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = { "/samplegetrequest" }, method = RequestMethod.GET)
	public String simpleGetRequest() {
		return "Hello, Simple Get Request can be accessed..";
	}

	/*
	 * Default : Get Method
	 */
	@RequestMapping("/hello/{player}")
	public Message message(@PathVariable String player) {
		Message msg = new Message(player, "Hello " + player);
		return msg;
	}

	/**
	 * Spring 4, new method
	 * 
	 * @return
	 */
	@GetMapping("/getexample")
	public String getEx() {
		return "Welcome to getEx example.";
	}

	/*
	 * Simple @Get example
	 */
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerDao.getCustomers();
	}

	/*
	 * Get Customer based on ID
	 */
	@GetMapping("/customers/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") int id) {
		System.out.println("Path Variable : " + id);
		Customer customer = customerDao.get(id);
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(customer, HttpStatus.OK);
	}

	/*
	 * Multi URL Example
	 */
	@RequestMapping(value = { "/mobile", "/internal" }, method = RequestMethod.GET)
	public String getHomePage(ModelMap model) {
		return "welcome";
	}

	/*
	 * Simple @ResponseBody Example
	 */
	@RequestMapping(value = "/customers/all", method = RequestMethod.GET)
	public @ResponseBody List<Customer> listAllCustomers() {
		return customerDao.getCustomers();
	}

	/**
	 * @RequestBody and @ModelAttribute
	 * 
	 * @param greeting
	 * @return
	 */
	@PostMapping("/greeting")
	public String greetingSubmit(@RequestBody Message greeting) {
		System.out.println("Name :" + greeting.getName());
		return "result";
	}

	/*
	 * For XML resposne
	 */
	@RequestMapping(value = "/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
		if (id <= 3) {
			Employee employee = new Employee(1, "Mukesh", "Ambani", "mambani@reliance.com");
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/employeesdyn")
	public ResponseEntity<Employee> getEmployeeDyn() {
		Employee employee = new Employee(1, "Mukesh", "Ambani", "mambani@reliance.com");
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/testconsumes", method = RequestMethod.POST)
	public ResponseEntity<String> processXml(@RequestBody String requestBody) {
		return new ResponseEntity<String>("Handled application/xml request. Request body was: " + requestBody,
				new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/testconsumesjson/{id}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> processJson(@RequestBody String name) {
		return new ResponseEntity<String>("Handled application/json request. Request body was: " + name,
				new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/put/{id}", method = RequestMethod.PUT)
	public ResponseEntity putExample(@PathVariable("id") int id) {
		return new ResponseEntity("Do Some Put Operation " + id, HttpStatus.OK);
	}

	@RequestMapping(value = "/producesjsonvalues", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<Customer> processPc() {
		return customerDao.getCustomers();
	}

	@RequestMapping(value = "/employeesxml/{id}", method = RequestMethod.GET, consumes = "application/json", produces = "application/xml")
	public ResponseEntity<Employee> getEmployeeByIdXml(@PathVariable("id") int id) {
		if (id <= 3) {
			Employee employee = new Employee(1, "Mukesh", "Ambani", "mambani@reliance.com");
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
}
