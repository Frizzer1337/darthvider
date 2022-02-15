package com.roadtoepam.darthvider.model.entity;

public class User {
	
	private long id;
	private String login;
	private String email;
	private float balance;
	private int role;
	private int status;
	
	public User() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public static User.Builder newBuilder(){
		
		return new User().new Builder();
		
	}
	
	public class Builder{
		
		protected Builder() {};

		public User.Builder setId(long id) {
			
			User.this.id = id;
			
			return this;		
		}

		public User.Builder setLogin(String login) {
			
			User.this.login = login;
			
			return this;	
		}

		public User.Builder setEmail(String email) {
			User.this.email = email;
			return this;	
		}

		public User.Builder setBalance(float balance) {
			
			User.this.balance = balance;
			
			return this;	
		}

		public User.Builder setStatus(int status) {
			User.this.status = status;
			
			return this;	
		}

		public User.Builder setRole(int role) {
			
			User.this.role = role;
			
			return this;	
		}		
		
		public User build() {
			
			return User.this;
			
		}
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + role;
		result = prime * result + (int) (status ^ (status >>> 32));
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
		User other = (User) obj;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (role != other.role)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", login=");
		builder.append(login);
		builder.append(", email=");
		builder.append(email);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", role=");
		builder.append(role);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
