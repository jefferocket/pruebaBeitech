package Controller;


import javax.ws.rs.Path;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




import org.json.JSONObject;

import Model.Product;



@RequestMapping("/service")
@RestController
public class ServiceRESTController {
	
	@RequestMapping(value ="/servicefechas", method = RequestMethod.GET)
	public String servicio(@RequestParam(value="from") String from,@RequestParam(value="to") String to) {
		String result = "DESDE :" + from +" HASTA " + to;
		
		return result;
	}
	
}
