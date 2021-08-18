package com.roadtoepam.darthvider.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConnectedTariff {
	
	HashMap<Integer,ArrayList<Integer>> contractInfo;

	public Map<Integer,ArrayList<Integer>> getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(HashMap<Integer,ArrayList<Integer>> contractInfo) {
		this.contractInfo = contractInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractInfo == null) ? 0 : contractInfo.hashCode());
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
		ConnectedTariff other = (ConnectedTariff) obj;
		if (contractInfo == null) {
			if (other.contractInfo != null)
				return false;
		} else if (!contractInfo.equals(other.contractInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConnectedTariff [contractInfo=");
		builder.append(contractInfo);
		builder.append("]");
		return builder.toString();
	}
	
}