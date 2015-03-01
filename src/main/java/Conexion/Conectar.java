package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conectar {
	public String db = "beitech";
	public String url = "jdbc:mysql://localhost/"+db;
	public String user = "jefferson";
	public String pass = "jefferson";


	public Connection getConnection(){

		Connection link = null;

		try{

			Class.forName("com.mysql.jdbc.Driver");

			link = DriverManager.getConnection(this.url, this.user, this.pass);

		}catch(Exception ex){

			ex.printStackTrace();

		}


		return link;

	}

}
