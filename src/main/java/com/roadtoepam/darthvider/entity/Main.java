package com.roadtoepam.darthvider.entity;

import com.roadtoepam.darthvider.connectionpool.ConnectionPool;
import com.roadtoepam.darthvider.dao.impl.UserDaoImpl;

public class Main {

	public static void main(String[] args) throws Exception {
		
		var dao = new UserDaoImpl();
		System.out.print(dao.findAll());
		

	}

}
	