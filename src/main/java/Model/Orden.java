package Model;

public class Orden {

	private String idCliente;
	private String euros;
	private String tasa;
	private String dolares;
	private String delivery;
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getEuros() {
		return euros;
	}
	public void setEuros(String euros) {
		this.euros = euros;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	public String getDolares() {
		return dolares;
	}
	public void setDolares(String dolares) {
		this.dolares = dolares;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
}
