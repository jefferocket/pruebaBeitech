package Controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Conexion.Conectar;
import dao.OrdenDAO;
import dao.OrdenDAOImpl;



@RequestMapping("/service")
@RestController
public class ServiceRESTController {
	
	@RequestMapping(value ="/servicefechas", method = RequestMethod.GET)
	public String servicio(@RequestParam(value="from") String from,@RequestParam(value="to") String to) {
		String result = "DESDE :" + from +" HASTA " + to;
		try {
			OrdenDAO order = new OrdenDAOImpl(new Conectar().getConnection());
			result = order.getOrdenesIntervalo(from, to);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
