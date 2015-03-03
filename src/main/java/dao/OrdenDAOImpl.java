package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Orden;

public class OrdenDAOImpl implements OrdenDAO{

	private Connection conn;
	private Statement stmt;
	public OrdenDAOImpl(Connection conn) throws SQLException {
		this.conn = conn;
		stmt = conn.createStatement();
	}
	@Override
	public void insertar(Orden orden) {
		String query = "insert into orden (delivery_address, customer_id)"
				+ " values ( '"+orden.getDelivery()+"' , "+orden.getIdCliente()+");";
		try {
			stmt.executeUpdate(query);

			String segundo = "SELECT * FROM orden ORDER BY order_id DESC LIMIT 1";
			ResultSet rs = stmt.executeQuery(segundo);
			rs.first();
			int idOrden = rs.getInt("order_id");
			String tercer = "insert into order_detail (total_price, order_id, rate ) values ( "+orden.getDolares()
					+ ", "+idOrden+", "+orden.getTasa()+" );";
			stmt.executeUpdate(tercer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public String getOrdenesIntervalo(String desde, String hasta) {
		String query = "select o.customer_id , c.name, o.order_id, o.date from orden o"
				+ " left join customer c on o.customer_id = c.customer_id"
				+ " where o.date BETWEEN '"+desde+"' AND '"+hasta+"' ORDER BY o.customer_id;";
		String retorno = "";
		try {
			retorno += "<table border=\"1\">  <tr> <th>ID Cliente</th> <th>Nombre</th> <th>ID orden</th> <th>Fecha de orden</th> </tr>";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				retorno+="<tr><td>"+rs.getInt("customer_id");
				retorno+="</td><td>"+rs.getString("name");
				retorno+="</td><td>"+rs.getInt("order_id");
				retorno+="</td><td>"+rs.getObject("date");
				retorno+="</td></tr>";
			}
			retorno+="</table>";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


		return retorno;
	}

}
