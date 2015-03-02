package Model;

public class Customer {
	private int id;
	private String name;
	private String email;
	private String []avaible_products;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getAvaible_products() {
		return avaible_products;
	}
	public void setAvaible_products(String[] avaible_products) {
		this.avaible_products = avaible_products;
	}
	
	
	

}
