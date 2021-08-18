package com.roadtoepam.darthvider.entity;

public class Tariff {
	
	private long id;
	private String name;
	private float price;
	private int discount;
	private boolean isActive;
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getDueType() {
		return dueType;
	}
	public void setDueType(int dueType) {
		this.dueType = dueType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + discount;
		result = prime * result + dueType;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(price);
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
		if (isActive != other.isActive)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
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
		builder.append(", price=");
		builder.append(price);
		builder.append(", discount=");
		builder.append(discount);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", dueType=");
		builder.append(dueType);
		builder.append("]");
		return builder.toString();
	}
	

}
