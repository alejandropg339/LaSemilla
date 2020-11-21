package co.edu.usbbog.spmn.spmnws.service;

import java.util.List;

import co.edu.usbbog.spmn.spmnws.model.CantCompra;


public interface ICantCompraService {

	public String crearCantCompra(int producto1, int recibo1, int cantidad);
	public String eliminarCantCompra(CantCompra cantCompra);
	public String modificarCantCompra(CantCompra cantCompra);
	public int contarCantCompra();
    public CantCompra mostrarCantCompra(int id);
    public List<CantCompra> findAll();
}
