package com.qa.ims.persistance.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

public class CustomerDaoMysqTest {

	CustomerDaoMysql customer = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME,
			"root", "root");

	@Test
	public void test1() {
		CustomerDaoMysql customer = new CustomerDaoMysql("root", "root");
		CustomerDaoMysql customer1 = new CustomerDaoMysql("jdbc:mysql://localhost:3306/ims", "root", "root");

	}

	@Test
	public void readAllTestPositive() {
//		CustomerDaoMysql customer = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME,
//				"root", "root");
		ArrayList<Customer> customers = new ArrayList<>();
		customers = (ArrayList<Customer>) customer.readAll();
		assertNotNull(customers);
	}

	@Test
	public void readAllTestNegative() {
		CustomerDaoMysql customer = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME,
				"root", "wrong password");
		ArrayList<Customer> customers = new ArrayList<>();
		customers = (ArrayList<Customer>) customer.readAll();
		assertEquals(new ArrayList<>(), customers);
	}

	@Test
	public void createTest() {
		Customer savedperson = new Customer("Bobby", "Shmurder");
		Customer testedperson = (customer.create(savedperson));
		assertEquals(testedperson.getFirstName(), "Bobby");
		assertEquals(testedperson.getSurname(), "Shmurder");
		customer.delete(testedperson.getId());
	}

	@Test
	public void createTestNegative() {
		CustomerDaoMysql customer = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME,
				"root", "wrong password");
		Customer savedperson = new Customer("Bobby", "Shmurder");
		assertNull(customer.create(savedperson));
	}

	@Test
	public void readLatestTestNegative() {
		CustomerDaoMysql customer = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME,
				"root", "wrong password");
		Customer customers = customer.readLatest();
		assertNull(customers);
	}

	@Test
	public void updateTest() {
		Customer originalperson = new Customer("Bobby", "Shmurder");
		Customer addedperson = (customer.create(originalperson));
		addedperson.setFirstName("Sam");
		addedperson.setSurname("Smith");
		Customer updatedperson = (customer.update(addedperson));
		assertEquals(updatedperson.getFirstName(), "Sam");
		assertEquals(updatedperson.getSurname(), "Smith");
		customer.delete(updatedperson.getId());

	}

	@Test
	public void deleteTestNegative() {
		CustomerDaoMysql customer = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME,
				"root", "wrong password");
		customer.delete(Long.parseLong("1"));
	}

	@Test
	public void updateTestNegative() {
		CustomerDaoMysql customer = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME,
				"root", "wrong password");

		Customer person = new Customer("Jhon", "Smith");
		Customer customers = customer.update(person);
		assertNull(customers);
	}
}
