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
package com.java.assignment.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.assignment.db.DabaBaseManuplation;
import com.java.assignment.domain.Record;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Product.html");
		PrintWriter out = response.getWriter();
		out.println("Invalid user name or password");
		rd.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Record record = new Record();
		try {
			record.setProductId(Long.valueOf(request.getParameter("productID")));

			record.setProductName(request.getParameter("ProductName"));

			record.setProductPrice(Double.valueOf(request.getParameter("Price")));
			
			record.setProductQty(Integer.valueOf(request.getParameter("Quantity")));
			
			record.setProductCategory(request.getParameter("Category"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DabaBaseManuplation.addRecord(record);

	}

}
