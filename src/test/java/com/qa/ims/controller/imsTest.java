package com.qa.ims.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.Ims;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class imsTest {

	@Spy // for the methods in customerController
	@InjectMocks // for any classes our customerController calls (in this case cust
	private Ims ismSystem;

	private Ims ims = new Ims();
	private String fileLocation = ("src/main/resources/sql-schema.sql");

	@Test
	public void inittest() {

		ims.init("jdbc:mysql://" + Utils.MYSQL_URL + "/practice", "root", "root", "src/main/resources/sql-schema.sql");
		ims.init("jdbc:mysql://" + Utils.MYSQL_URL + "/practice", "root", "wrongPassword",
				"src/main/resources/sql-schema.sql");
	}

}
