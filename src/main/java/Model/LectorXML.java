package Model;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LectorXML {
	private String url;
	private String currency;
	private float rate;
	public LectorXML(){
		url = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
	}
	/**
	 * Lee e interpreta el archivo url para asignar la rasa de cambio  
	 */
	public void leer(){
		try
        {
            DocumentBuilderFactory f =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(url);
 
            doc.getDocumentElement().normalize();
       
            NodeList items = doc.getElementsByTagName("Cube");
            
            for (int i = 0; i < items.getLength(); i++)
            {
                Node n = items.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE){
                	Element e = (Element) n;
                	if(e.getAttribute("currency").equals("USD")){
                		currency = e.getAttribute("currency");
                		rate = Float.parseFloat(e.getAttribute("rate").trim());
                		//System.out.println("Currency : "+ e.getAttribute("currency"));
                    	//System.out.println("Rate : "+ e.getAttribute("rate"));
                    	break;
                	}
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	

}
