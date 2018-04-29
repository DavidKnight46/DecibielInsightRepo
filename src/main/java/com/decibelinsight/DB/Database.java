package com.decibelinsight.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Database {

	private static Logger log = Logger.getLogger(Database.class);

	private final String SERVER_NAME;
	private final String SCHEMA;
	private final String USER;
	private final String PASSWORD;

	private final String ORACLE = "oracle";
	private final String MYSQL = "mysql";

	private String DRIVER;

	public Database(String server, String schema, String user, String password) {
		this.SERVER_NAME = server;
		this.SCHEMA = schema;
		this.USER = user;
		this.PASSWORD = password;

	}

	public String getServerName() {
		return this.SERVER_NAME;
	}

	public String getSchema() {
		return this.SCHEMA;
	}

	public String getUser() {
		return this.USER;
	}

	public String getPassword() {
		return this.PASSWORD;
	}

	/**
	 * Attempt to connect to DB supplied by the JSON object
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	public void connectToDB() throws SQLException, ClassNotFoundException {

		this.determineDriver(SERVER_NAME);

		if (DRIVER != null) {
			Class.forName(DRIVER);

			Connection conn = DriverManager.getConnection(SERVER_NAME + SCHEMA, USER, PASSWORD);

			if (conn.isClosed()) {
				log.log(Level.ERROR, "Unable to connect to " + SERVER_NAME + SCHEMA);
			} else {
				log.log(Level.INFO, "Able to connect to " + SERVER_NAME + SCHEMA);
			}
		} else {
			log.log(Level.DEBUG, "Database driver is null.");
		}

	}

	/**
	 * Determine the correct type of DB driver to be used
	 * 
	 * @param str
	 */
	private void determineDriver(String str) {

		if (str.contains(ORACLE)) {
			DRIVER = "com.oracle.jdbc.Driver";
			log.log(Level.INFO, "Oracle DB driver used");
		} else if (str.contains(MYSQL)) {
			DRIVER = "com.mysql.jdbc.Driver";
			log.log(Level.INFO, "mysql DB driver used");
		} else {
			log.log(Level.INFO, "Unknown DB driver detected");
		}
	}

	/**
	 * Display textual information on Database object
	 * 
	 */
	@Override
	public String toString() {
		return SERVER_NAME + SCHEMA + " User: " + USER + " Password: " + PASSWORD;

	}
}
