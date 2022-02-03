package com.roadtoepam.darthvider.entity;

public class Tariff {
	
	private long id;
	private String name;
	private String info;
	private String shortInfo;
	private float price;
	private int discount;
	private int status;
	private int dueType;
	
	
	public Tariff() {
		
	}
	
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getStatus() {
		return status;
	}
	public void setActive(int isActive) {
		this.status = isActive;
	}
	public int getDueType() {
		return dueType;
	}
	public void setDueType(int dueType) {
		this.dueType = dueType;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getShortInfo() {
		return shortInfo;
	}

	public void setShortInfo(String shortInfo) {
		this.shortInfo = shortInfo;
	}
	

	public static Tariff.Builder newBuilder(){
		
		return new Tariff().new Builder();
		
	}
	
	public class Builder{
		
		protected Builder() {};

		public Tariff.Builder setId(long id) {
			
			Tariff.this.id = id;
			
			return this;		
		}

		public Tariff.Builder setName(String name) {
			
			Tariff.this.name = name;
			
			return this;	
		}

		public Tariff.Builder setPrice(float price) {
			
			Tariff.this.price = price;
			
			return this;	
		}

		public Tariff.Builder setInfo(String info) {
			
			Tariff.this.info = info;
			
			return this;	
		}

		public Tariff.Builder setShortInfo(String shortInfo) {
			Tariff.this.shortInfo = shortInfo;
			
			return this;	
		}

		public Tariff.Builder setDiscount(int discount) {
			
			Tariff.this.discount = discount;
			
			return this;	
		}
		
		public Tariff.Builder setDueType(int dueType) {
			
			Tariff.this.dueType = dueType;
			
			return this;	
		}	
		
		public Tariff.Builder setStatus(int status) {
			
			Tariff.this.status = status;
			
			return this;	
		}	
		
		public Tariff build() {
			
			return Tariff.this;
			
		}
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + discount;
		result = prime * result + dueType;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + (int) (status ^ (status >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((shortInfo == null) ? 0 : shortInfo.hashCode());
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
		Tariff other = (Tariff) obj;
		if (discount != other.discount)
			return false;
		if (dueType != other.dueType)
			return false;
		if (id != other.id)
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (status != other.status)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (shortInfo == null) {
			if (other.shortInfo != null)
				return false;
		} else if (!shortInfo.equals(other.shortInfo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tariff [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", info=");
		builder.append(info);
		builder.append(", shortInfo=");
		builder.append(shortInfo);
		builder.append(", price=");
		builder.append(price);
		builder.append(", discount=");
		builder.append(discount);
		builder.append(", isActive=");
		builder.append(status);
		builder.append(", dueType=");
		builder.append(dueType);
		builder.append("]");
		return builder.toString();
	}

	

}
