/*
 * Copyright 2017 (C) <Centennial College> COMP303-assign2
 * 
 * Created on : 05-02-2017
 * Author     : Hao Jiang (300858525)
 *
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 *-----------------------------------------------------------------------------
 */
package com.java.assignment.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.java.assignment.domain.Record;

public class DabaBaseManuplation {
	
	
	public static Record getRecord(String productID)
	{
		
		try {
			// create a mysql database connection
			// String myDriver = "org.gjt.mm.mysql.Driver";
			final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/test";
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection conn = DriverManager.getConnection(myUrl, "root", "admin");

			// the mysql insert statement
			System.out.println("connection built.");
			Record record = null;
			String selectSQL = "SELECT * WHERE USER_ID = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setString(1, productID);
			ResultSet rs = preparedStatement.executeQuery(selectSQL );
			while (rs.next()) {
				record = new Record();
				record.setProductId(Integer.valueOf(productID));
				record.setProductName( rs.getString("ProductName"));
				
			    record.setProductPrice(Double.valueOf(rs.getString("Price")));
			    record.setProductQty(Integer.valueOf(rs.getString("Quantity")));
			    
			    record.setProductCategory(rs.getString("ProductName"));
			    
//				String userid = rs.getString("USER_ID");
//				String username = rs.getString("USERNAME");
			}


			conn.close();
			
			return record;
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			return null;
		}
		
	}
	
	public static String addRecord(Record record)
	{
		try {
			// create a mysql database connection
			// String myDriver = "org.gjt.mm.mysql.Driver";
			final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/test";
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection conn = DriverManager.getConnection(myUrl, "root", "admin");

			// the mysql insert statement
			System.out.println("connection built.");
			String query = " insert into product (PRODUCTID, PRODUCTNAME,QUANTITY,PRICE,CATEGORY)"
					+ " values (?, ?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, record.getProductId().toString());
			preparedStmt.setString(2, record.getProductName());
			preparedStmt.setInt(3, record.getProductQty());
			preparedStmt.setDouble(4, record.getProductPrice());
			preparedStmt.setString(5, record.getProductCategory());

			// execute the preparedstatement
			preparedStmt.execute();

			conn.close();
			
			return record.getProductId().toString();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			return null;
		}
		
	}

}
