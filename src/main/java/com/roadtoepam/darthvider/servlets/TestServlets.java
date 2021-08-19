package com.roadtoepam.darthvider.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.dao.impl.UserDaoImpl;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.utils.security.PasswordEncrypter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class TestServlets extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().print("Hello");
		
		String pass = "password";
		String hashpass = PasswordEncrypter.encrypt(pass);
		
		response.getWriter().append(pass).append("  " + hashpass);
		
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
