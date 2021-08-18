package com.roadtoepam.darthvider.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.dao.impl.UserDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class TestServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().print("Hello");
		
		var userDAO = new UserDaoImpl();
		Optional<User> user = Optional.empty();
		List<User> userList = null;
		try {
			userList = userDAO.findAll();
		    user = userDAO.findById(8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.getWriter().println("Served at: " + request.getContextPath());
	
		for(User userInList: userList) {
			
			response.getWriter().println(userInList);
			
		}	
		
		 
		
		response.getWriter().println(user);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
