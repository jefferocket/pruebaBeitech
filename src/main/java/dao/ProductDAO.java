package dao;

import java.util.List;

import Model.Product;

public interface ProductDAO {
	
	public List<Product> products();
	public Product findById(int id);
}
