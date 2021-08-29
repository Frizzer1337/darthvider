package com.roadtoepam.darthvider.entity;

import java.sql.Date;

public class UserContract {
	
	private long id;
	private long idContract;
	private Date startDate;
	private Date endDate;
	private boolean isActive;
	private int discount;
	
	public UserContract() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdContract() {
		return idContract;
	}
	public void setIdContract(long idContract) {
		this.idContract = idContract;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
public static UserContract.Builder newBuilder(){
		
		return new UserContract().new Builder();
		
	}
	
	public class Builder{
		
		protected Builder() {};

		public UserContract.Builder setId(long id) {
			
			UserContract.this.id = id;
			
			return this;		
		}

		public UserContract.Builder setIdContract(int idContract) {
			
			UserContract.this.idContract = idContract;
			
			return this;	
		}

		public UserContract.Builder setStartDate(Date startDate) {
			UserContract.this.startDate = startDate;
			return this;	
		}

		public UserContract.Builder setEndDate(Date endDate) {
			
			UserContract.this.endDate = endDate;
			
			return this;	
		}

		public UserContract.Builder setIsActive(boolean isActive) {
			UserContract.this.isActive = isActive;
			
			return this;	
		}

		public UserContract.Builder setDiscount(int discount) {
			
			UserContract.this.discount = discount;
			
			return this;	
		}		
		
		public UserContract build() {
			
			return UserContract.this;
			
		}
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + discount;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idContract ^ (idContract >>> 32));
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		UserContract other = (UserContract) obj;
		if (discount != other.discount)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (idContract != other.idContract)
			return false;
		if (isActive != other.isActive)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserContract [id=");
		builder.append(id);
		builder.append(", idContract=");
		builder.append(idContract);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", discount=");
		builder.append(discount);
		builder.append("]");
		return builder.toString();
	}
	

}
