package com.decibelinsight.excerise;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.decibelinsight.DB.Collection;
import com.decibelinsight.excerise.DecibielInsightRestController;

public class DecibielInsightRestControllerTest {
	
	private final String SERVER_TEST = "jdbc:mysql://localhost/";
	private final String USER_TEST = "user";
	private final String PASSWORD_TEST = "password";
	private final String SCHEMA_TEST = "decibelInsight";

	/**
	 * Test of the database in restcontroller class
	 * 
	 */
	@Test
	public void databaseTest() {
		
		DecibielInsightRestController controller = new DecibielInsightRestController();
		
		controller.database(SERVER_TEST, PASSWORD_TEST, USER_TEST, SCHEMA_TEST);
		
		assertFalse(Collection.getInstance().getList().isEmpty());
		
	}

}
