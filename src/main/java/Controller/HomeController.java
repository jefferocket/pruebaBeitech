package Controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Conexion.Conectar;
import Model.Actualizador;
import Model.Customer;
import Model.Product;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import dao.ExchangeDAOImpl;
import dao.ProductDAO;
import dao.ProductDAOImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private ProductDAO daoProductos;
	private CustomerDAO daoClientes;
	private ExchangeDAOImpl daoTasa;

	public void iniciar() {
		// TODO Auto-generated constructor stub
		try {
			Conectar con = new Conectar();
			daoProductos = new ProductDAOImpl(con.getConnection());
			daoClientes = new CustomerDAOImpl(con.getConnection());
			daoTasa = new ExchangeDAOImpl(con.getConnection());
			Thread mecanismo = new Actualizador(daoTasa);
			mecanismo.start();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		iniciar();

		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}

	@RequestMapping(value = "/seleccionUsuario", method = RequestMethod.GET)
	public ModelAndView seleccionUsuario() {
		Customer selectedClient = new Customer(); 
		ModelAndView model = new ModelAndView("seleccionusuario");

		List<Customer> clientes = daoClientes.allCustomer();
		model.addObject("selectedClient", selectedClient);
		model.addObject("clientes", clientes);

		//model.addAttribute("mensaje", "Hola" );

		return model;
	}

	@RequestMapping(value = "/seleccion", method = RequestMethod.POST)
	public ModelAndView seleccion(@ModelAttribute Customer cliente) {

		ModelAndView model = new ModelAndView("seleccion");
		Customer clienteID = daoClientes.findById(cliente.getId());
		List<Product> productos = daoProductos.products();
		String indices[] = new String[productos.size()];
		for (int i = 0; i < indices.length; i++) {
			indices[i] = ""+productos.get(i).getId();
		}
		model.addObject("cliente",clienteID);
		model.addObject("productos", productos);
		model.addObject("indices", indices);

		model.addObject("mensaje", "Hola" );

		return model;
	}

	@RequestMapping(value = "/agregarproductos", method = RequestMethod.GET)
	public String agregarProductos(@ModelAttribute Customer cliente,Model model, HttpServletRequest requ) {

		String lista [] = requ.getParameterValues("lista");
		cliente.setAvaible_products(lista);
		daoClientes.insertarClienteProductos(cliente);

		return "home";
	}

	@RequestMapping(value = "/ordenesform", method = RequestMethod.GET)
	public ModelAndView ordenesForm(HttpServletRequest requ) {

		ModelAndView model = new ModelAndView("ordenesform");
		List<Customer> clientes = daoClientes.clientes();
		model.addObject("clientes",clientes);

		return model;
	}

	@RequestMapping(value="/loadcliente/{accountid}", method = RequestMethod.GET)
	public @ResponseBody String getAccountDetails(@PathVariable(value="accountid") String accountid){  
		String result = "<strong> Productos disponibles </strong><br>";
		Customer cliente = daoClientes.findById(Integer.parseInt(accountid));
		if(cliente.getAvaible_products() == null){
			result += "<h3>No hay productos disponibles para este cliente </h3>";
		}else{
			result += "<table>"; 
			String products[] = cliente.getAvaible_products();
			for (String id : products) {
				Product disponible = daoProductos.findById(Integer.parseInt(id));
				result +="<tr><form ><td><label id=\""+disponible.getId()
						+ "\">"+disponible.getName()
						+ "</label></td><td><input type=\"submit\" onclick= \"sumarTotal("+disponible.getPrice()
						+ ")\" value=\"Agregar\"></input></td></form></tr>"; 
			}
			result+="</table><br>";
		}
		return result;  

	}
}
