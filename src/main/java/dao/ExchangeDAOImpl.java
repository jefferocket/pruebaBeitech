package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExchangeDAOImpl implements ExchangeDAO{

	private Connection conn;
	private Statement stmt;
	
	public ExchangeDAOImpl(Connection conexion) throws SQLException {
		conn = conexion;
		stmt= conn.createStatement();
	}
	
	@Override
	public void insertar(float rate) {
		String query = "insert into exchange_rate (currency , rate )"
				+ " values ( 'USD' , "+rate+" );";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public float tasaReciente() {
		float tasa = -1;
		String query = "SELECT * FROM exchange_rate ORDER BY id DESC LIMIT 1";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.first()) {
				tasa = rs.getFloat("rate");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tasa;
	}

}
