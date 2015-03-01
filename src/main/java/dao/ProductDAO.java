package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Model.Product;

public interface ProductDAO {
	
	public List<Product> products();
	public Product findById(int id);
}
