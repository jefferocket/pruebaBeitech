package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Customer;


public class CustomerDAOImpl implements CustomerDAO{

	private Connection conn;
	private Statement stmt;
	
	public CustomerDAOImpl(Connection conexion) throws SQLException {
		// TODO Auto-generated constructor stub
		conn = conexion; 
		stmt= conn.createStatement();
	}
	@Override
	public List<Customer> clientes() {
		// TODO Auto-generated method stub
		String query = "select * from customer";
		ResultSet rs;
		List<Customer> clientes = new ArrayList<Customer>();
		try {
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				Customer carga = new Customer();
				carga.setId(rs.getInt("customer_id"));
				carga.setName(rs.getString("name"));
				carga.setEmail(rs.getString("email"));
				clientes.add(carga);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientes;
	}
	@Override
	public Customer findById(int id) {
		String query = "SELECT DISTINCT c.customer_id , c.name, c.email, ap.product_id"
				+ " FROM customer c"
				+ " LEFT OUTER JOIN avaible_products ap"
				+ " ON c.customer_id = ap.customer_id"
				+ " WHERE c.customer_id = "+id;
		ResultSet rs;
		Customer buscar = null;

		try {
			rs = stmt.executeQuery(query);
			List<Integer> productsId = new ArrayList<Integer>();
			while(rs.next()){
				if(buscar == null){
					buscar = new Customer();
					buscar.setId(rs.getInt("customer_id"));
					buscar.setName(rs.getString("name"));
					buscar.setEmail(rs.getString("email"));
				}else{
					int product_id = rs.getInt("product_id"); 
					if( product_id > 0){
						productsId.add(product_id);
					}
				}
				if (buscar != null) {
					int arr[] = new int[productsId.size()];
					int cont=0;
					for (Integer integer : productsId) {
						arr[cont] = integer.intValue();
						cont++;
					}
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return buscar;
	}
	@Override
	public void insertarClienteProductos(Customer cliente) {
		String query = "insert into avaible_products values ( ";
		String completo;
		String []products = cliente.getAvaible_products();
		for (int i = 0; i < products.length; i++) {
			completo = query + products[i] + ", "+ cliente.getId()+ " )";
			try {
				stmt.executeUpdate(completo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
