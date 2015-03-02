package dao;

import java.util.List;

import Model.Customer;

public interface CustomerDAO {

	public List<Customer> clientes();
	public Customer findById(int id);
	public void insertarClienteProductos(Customer cliente);
	public List<Customer> allCustomer();
}
