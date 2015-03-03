package dao;

import Model.Orden;

public interface OrdenDAO {
	public void insertar(Orden orden);
	public String getOrdenesIntervalo(String desde, String hasta);

}
