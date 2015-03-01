package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;

import Model.Product;
@Repository
public class ProductDAOImpl implements ProductDAO{

	private Connection conn;
	private Statement stmt;
	
	public ProductDAOImpl(Connection conexion) throws SQLException {
		
		conn = conexion; 
		stmt= conn.createStatement();
	}

	@Override
	public List<Product> products() {
		// TODO Auto-generated method stub
		String query = "select * from product";
		ResultSet rs;
		List<Product> products = new ArrayList<Product>();
		try {
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				Product carga = new Product();
				carga.setId(rs.getInt("product_id"));
				carga.setName(rs.getString("name"));
				carga.setPrice(rs.getInt("price"));
				products.add(carga);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product findById(int id) {
		String query = "select * from product where product_id = "+id;
		ResultSet rs;
		Product buscar = null;

		try {
			rs = stmt.executeQuery(query);
			if(rs.first()){
				buscar = new Product();
				buscar.setId(rs.getInt("product_id"));
				buscar.setName(rs.getString("name"));
				buscar.setPrice(rs.getInt("price"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return buscar;
	}
}
