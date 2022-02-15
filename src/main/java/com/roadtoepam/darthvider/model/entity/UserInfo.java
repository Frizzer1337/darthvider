package com.roadtoepam.darthvider.model.entity;

public class UserInfo {
	
	private long id;
	private String name;
	private String surname;
	private String city;
	private String phone;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public static UserInfo.Builder newBuilder(){
		
		return new UserInfo().new Builder();
		
	}
	
	public class Builder{
		
		protected Builder() {};

		public UserInfo.Builder setId(long id) {
			
			UserInfo.this.id = id;
			
			return this;		
		}

		public UserInfo.Builder setSurname(String surname) {
			
			UserInfo.this.surname = surname;
			
			return this;	
		}

		public UserInfo.Builder setName(String name) {
			UserInfo.this.name = name;
			return this;	
		}

		public UserInfo.Builder setPhone(String phone) {
			
			UserInfo.this.phone = phone;
			
			return this;	
		}

		public UserInfo.Builder setCity(String city) {
			
			UserInfo.this.city = city;
			
			return this;	
		}
		
		public UserInfo build() {
			
			return UserInfo.this;
			
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("UserInfo [id=");
		builder2.append(id);
		builder2.append(", name=");
		builder2.append(name);
		builder2.append(", surname=");
		builder2.append(surname);
		builder2.append(", city=");
		builder2.append(city);
		builder2.append(", phone=");
		builder2.append(phone);
		builder2.append("]");
		return builder2.toString();
	}

}
