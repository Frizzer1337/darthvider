package com.roadtoepam.darthvider.model.entity;

import java.util.HashMap;
import java.util.Map;

public class DueInfo {
	
	HashMap<Integer,String> dueInfo;

	public Map<Integer, String> getDueInfo() {
		return dueInfo;
	}

	public void setDueInfo(HashMap<Integer, String> dueInfo) {
		this.dueInfo = dueInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dueInfo == null) ? 0 : dueInfo.hashCode());
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
		DueInfo other = (DueInfo) obj;
		if (dueInfo == null) {
			if (other.dueInfo != null)
				return false;
		} else if (!dueInfo.equals(other.dueInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DueInfo [dueInfo=");
		builder.append(dueInfo);
		builder.append("]");
		return builder.toString();
	}

}
