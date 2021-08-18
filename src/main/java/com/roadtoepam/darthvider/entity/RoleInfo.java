package com.roadtoepam.darthvider.entity;

import java.util.HashMap;
import java.util.Map;

public class RoleInfo {
	
	HashMap<Integer,String> roleInfo;

	public Map<Integer, String> getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(HashMap<Integer, String> roleInfo) {
		this.roleInfo = roleInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleInfo == null) ? 0 : roleInfo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleInfo other = (RoleInfo) obj;
		if (roleInfo == null) {
			if (other.roleInfo != null)
				return false;
		} else if (!roleInfo.equals(other.roleInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleInfo [roleInfo=");
		builder.append(roleInfo);
		builder.append("]");
		return builder.toString();
	}

	

}
