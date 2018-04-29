package com.decibelinsight.DB;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Collection {
	
	private static Logger log = Logger.getLogger(Collection.class);
	
	private static Collection collection;
	private ArrayList<Database> dataBaseList = new ArrayList<Database>();
	
	private Collection() {
		log.log(Level.INFO, "Collection created");
	}
	
	/**
	 * Return the instance of the collection
	 * 
	 * @return
	 */
	public static Collection getInstance() {
		if(collection == null) {
			collection = new Collection();
		}
		
		return collection;
	}
	
	/**
	 * Add database object to list keep track of all connections
	 * 
	 */
	public void addToList(Database database) {
		dataBaseList.add(database);
	}
	
	/**
	 * Get the ArrayList<Database>
	 * 
	 * @return
	 */
	public ArrayList<Database> getList(){
		return dataBaseList;
	}

}
