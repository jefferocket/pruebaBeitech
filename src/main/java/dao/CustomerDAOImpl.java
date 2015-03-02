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
		String query = "SELECT DISTINCT c.customer_id , c.name, c.email, ap.product_id"
				+ "  FROM customer c"
				+ "  LEFT OUTER JOIN avaible_products ap"
				+ " ON c.customer_id = ap.customer_id ";
		ResultSet rs;
		List<Customer> clientes = new ArrayList<Customer>();
		try {
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				Customer carga = new Customer();
				carga.setId(rs.getInt("customer_id"));
				carga.setName(rs.getString("name"));
				carga.setEmail(rs.getString("email"));
				String id_pro = rs.getString("product_id");
				int pos = clienteExiste(clientes, carga.getId()); 
				if (pos < 0) {
					if(id_pro != null){
						String arr[] = {id_pro};
						carga.setAvaible_products(arr);
						clientes.add(carga);
					}
				} else {
					String arr[] = clientes.get(pos).getAvaible_products();
					String nuevo[] = new String [arr.length +1];
					for (int i = 0; i < nuevo.length; i++) {
						if(i <= arr.length-1)
							nuevo[i] = arr[i];
						else
							nuevo[i] = id_pro;
					}
					clientes.get(pos).setAvaible_products(nuevo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clientes;
	}
	private int clienteExiste(List<Customer> lista, int id){
		int i = 0;
		for (Customer customer : lista) {
			if(customer.getId() == id)
				return i;
			i++;
		}
		return -1;
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
