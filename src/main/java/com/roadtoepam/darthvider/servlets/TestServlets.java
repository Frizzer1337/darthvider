package com.roadtoepam.darthvider.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.dao.impl.UserDaoImpl;
import com.roadtoepam.darthvider.dao.impl.UserRoleDaoImpl;
import com.roadtoepam.darthvider.entity.RoleInfo;
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
		
		var userDAO = new UserDaoImpl();
		var roleDao = new UserRoleDaoImpl();
		Optional<User> user = Optional.empty();
		List<User> userList = null;
		List<RoleInfo> roleList = null;
		try {
			userList = userDAO.findAll();
			boolean ok = userDAO.delete(8);
			response.getWriter().println(ok);
		    user = userDAO.findById(8);
		    response.getWriter().println(user);
		    roleList = roleDao.findAll();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.getWriter().println("Served at: " + request.getContextPath());
	
		for(RoleInfo roleInList: roleList) {
			
			response.getWriter().println(roleInList);
			
		}	
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
