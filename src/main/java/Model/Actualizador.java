package Model;

import dao.ExchangeDAO;

public class Actualizador extends Thread{
	private LectorXML lector;
	private ExchangeDAO exdao;
	public Actualizador(ExchangeDAO exdao){
		lector = new LectorXML();
		this.exdao = exdao;
	}
	public void run(){
		while(true){
			try {
				lector.leer();
				exdao.insertar(lector.getRate());
				Thread.sleep(1800000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Hilo ejecutandose");
		}
	}
}
