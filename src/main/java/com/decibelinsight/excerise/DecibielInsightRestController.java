package com.decibelinsight.excerise;

import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.decibelinsight.DB.Collection;
import com.decibelinsight.DB.Database;

@RestController
public class DecibielInsightRestController {

	private static Logger log = Logger.getLogger(DecibielInsightRestController.class);

	
	@RequestMapping("/database")
	public Database database(String serverName, String password, String user, String schema) {
		
		//Validate valid parameters from GETed JSON object
		if (serverName != null && password != null && user != null && schema != null) {
			log.log(Level.INFO, "servers: " + serverName + "\n" + " schemas: " + schema + "\n" + " users: " + user
					+ "\n" + " passwords: " + password);

			this.processString(password, user, schema, serverName);

			Collection.getInstance().getList().stream().forEach(e -> {
				try {
					e.connectToDB();
				} catch (ClassNotFoundException | SQLException ex) {
					log.log(Level.ERROR, ex.getMessage());
				}
			});

			return new Database(serverName, schema, user, password);
		} else {
			log.log(Level.DEBUG, "Default values used.");
			return new Database("server", "schema", "user", "password");
		}
	}

	/**
	 * Separate the JSON into String arrays
	 * 
	 * @param password
	 * @param user
	 * @param schema
	 * @param server
	 */
	private void processString(String password, String user, String schema, String server) {
		String[] passwordArr = password.split(",");
		String[] userArr = user.split(",");
		String[] schemaArr = schema.split(",");
		String[] serverArr = server.split(",");

		this.createDatabase(passwordArr, userArr, schemaArr, serverArr);

	}

	/**
	 * Create a Database object for each JSON object POST'ed to endpoint
	 * 
	 * @param passwordArr
	 * @param userArr
	 * @param schemaArr
	 * @param serverArr
	 */
	private void createDatabase(String[] passwordArr, String[] userArr, String[] schemaArr, String[] serverArr) {
		for (int counter = 0; counter <= passwordArr.length - 1; counter++) {
			Database currentDatabaseConn = new Database(serverArr[counter], schemaArr[counter], userArr[counter], passwordArr[counter]);
			Collection.getInstance().addToList(currentDatabaseConn);
			log.log(Level.INFO, currentDatabaseConn.toString());
		}

	}
}
