package com.roadtoepam.darthvider.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.dao.impl.ConnectedTariffDaoImpl;
import com.roadtoepam.darthvider.dao.impl.TariffDaoImpl;
import com.roadtoepam.darthvider.dao.impl.UserDaoImpl;
import com.roadtoepam.darthvider.dao.impl.UserRoleDaoImpl;
import com.roadtoepam.darthvider.entity.ConnectedTariff;
import com.roadtoepam.darthvider.entity.RoleInfo;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.util.MailSender;
import com.roadtoepam.darthvider.util.security.PasswordEncrypter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class TestServlets extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TariffDaoImpl dao = new TariffDaoImpl();
		
		try {
			response.getWriter().print(dao.findAll().toString());
		} catch (IOException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
