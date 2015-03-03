package Controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





import Conexion.Conectar;
import dao.OrdenDAO;
import dao.OrdenDAOImpl;


@Controller
public class ServiceRESTController {

	private OrdenDAO daoOrden;
	private void iniciar(){
		try {
			daoOrden = new OrdenDAOImpl(new Conectar().getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/formFechas", method = RequestMethod.GET)
	public String home() {
		iniciar();
		return "formFechas";
	}
	
	
	
}
