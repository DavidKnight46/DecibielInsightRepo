package com.decibelinsight.excerise;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.decibelinsight.DB.Database;

public class DatabaseTest {
	
	private final String SERVER_NAME_TEST = "jdbc:mysql://localhost/";
	private final String SCHEMA_TEST = "decibelInsight";
	private final String USER_TEST = "user";
	private final String PASSWORD_TEST = "password";
	
	Database DB;
	Database DB_FALSE_DRIVER;
	
	@Before
	public void before() {
		DB = new Database(SERVER_NAME_TEST, SCHEMA_TEST, USER_TEST, PASSWORD_TEST);
		DB_FALSE_DRIVER = new Database("test", SCHEMA_TEST, USER_TEST, PASSWORD_TEST);
	}

	/**
	 * Ensure correct serverName is set in Database object
	 * 
	 */
	@Test
	public void serverNameTest() {
		assertTrue(DB.getServerName().equals(SERVER_NAME_TEST));
	}
	
	/**
	 * Ensure correct schema is set in Database object
	 * 
	 */
	@Test
	public void schemaTest() {
		assertTrue(DB.getSchema().equals(SCHEMA_TEST));
	}
	
	/**
	 * Ensure correct user is set in Database object
	 * 
	 */
	@Test
	public void userTest() {
		assertTrue(DB.getUser().equals(USER_TEST));
	}
	
	/**
	 * Ensure correct password is set in Database object
	 * 
	 */
	@Test
	public void passwordTest() {
		assertTrue(DB.getPassword().equals(PASSWORD_TEST));
	}

}
