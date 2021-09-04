package com.roadtoepam.darthvider.dao.impl;

import static com.roadtoepam.darthvider.dao.impl.UserColumnName.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.roadtoepam.darthvider.connectionpool.ConnectionPool;
import com.roadtoepam.darthvider.dao.AbstractDao;
import com.roadtoepam.darthvider.dao.UserRoleDao;
import com.roadtoepam.darthvider.entity.RoleInfo;
import com.roadtoepam.darthvider.exception.DaoException;

public class UserRoleDaoImpl extends AbstractDao implements UserRoleDao {
	
	private static final String SELECT_ALL_ROLES = "SELECT role,role_info FROM user_role_info";
	private static final String ADD_USER_ROLE="INSERT INTO user_role_info (role,role_info) VALUES ( ?,? )";
	private static final String UPDATE_USER_ROLE="UPDATE user_role_info SET role = ?,role_info=? WHERE id_user=?";
	private static final String DELETE_USER_ROLE="DELETE FROM user_role_info WHERE role=?";
	
	 ConnectionPool connectionPool = ConnectionPool.getInstance();

	 
	@Override
	public List<RoleInfo> findAll() throws DaoException {
		
		 ArrayList<RoleInfo> roleList = new ArrayList<>();
			
			try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(SELECT_ALL_ROLES);
				var resultSet = statement.executeQuery();){
				 
			      while (resultSet.next()) {
			    	  
			    	 int roleId = resultSet.getInt(ROLE);
			    	 String roleInfo = resultSet.getString(ROLE_INFO);
			    	  
			    	 HashMap<Integer,String> map = new HashMap<>();
			    	 
			    	 map.put(roleId, roleInfo);
			    	 
			    	 RoleInfo role = new RoleInfo();
			    	 
			    	 role.setRoleInfo(map);
			    	
			    	 roleList.add(role);
			    	  
			      }
			      
			      return roleList;
			      
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			      
		
	}

	@Override
	public boolean add(RoleInfo role, int id) throws DaoException {
			
			try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(ADD_USER_ROLE);){
			
		    	 String roleInfo = role.getRoleInfo().get(id);
		    	 
		    	 statement.setInt(1,id);
		    	 statement.setString(2, roleInfo);
		    	 
		    	 int status = statement.executeUpdate();
		    	  
			     return status > 0;
			      
				} catch (SQLException e) {
					throw new DaoException(e);
				}
		
		
	}

	@Override
	public boolean update(RoleInfo role, int id) throws DaoException {
		
		try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(UPDATE_USER_ROLE);){
			
		    	 String roleInfo = role.getRoleInfo().get(id);
		    	 
		    	 statement.setInt(1,id);
		    	 statement.setString(2, roleInfo);
		    	 
		    	 int status = statement.executeUpdate();
		    	  
			     return status > 0;
			      
				} catch (SQLException e) {
					throw new DaoException(e);
				}
		
	}

	@Override
	public boolean delete(int id) throws DaoException {
		
		try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(DELETE_USER_ROLE);){
		    	 
		    	 statement.setInt(1,id);
		    	 
		    	 int status = statement.executeUpdate();
		    	  
			     return status > 0;
			      
				} catch (SQLException e) {
					throw new DaoException(e);
				}
	}

}
