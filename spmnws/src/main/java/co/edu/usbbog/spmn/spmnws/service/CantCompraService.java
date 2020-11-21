package co.edu.usbbog.spmn.spmnws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usbbog.spmn.spmnws.model.CantCompra;
import co.edu.usbbog.spmn.spmnws.model.CantCompraPK;
import co.edu.usbbog.spmn.spmnws.model.Producto;
import co.edu.usbbog.spmn.spmnws.model.Recibo;
import co.edu.usbbog.spmn.spmnws.repository.ICantCompraRepository;
import co.edu.usbbog.spmn.spmnws.repository.IProductoRepository;
import co.edu.usbbog.spmn.spmnws.repository.IReciboRepository;

@Service
public class CantCompraService implements ICantCompraService {
	
	@Autowired
	private ICantCompraRepository cantCompraRepo;
	@Autowired
	private IProductoRepository productoRepo;
	@Autowired
	private IReciboRepository reciboRepo;

	@Override
	public String crearCantCompra(int producto1, int recibo1, int cantidad) {
		Producto producto= productoRepo.getOne(producto1);
		Recibo recibo= reciboRepo.getOne(recibo1);
		CantCompraPK pk = new CantCompraPK(producto.getId(), recibo.getId());
		CantCompra cantCompra= new CantCompra(pk,cantidad);
		cantCompra.setProducto1(producto);
		cantCompra.setRecibo1(recibo);
		cantCompraRepo.save(cantCompra);
		return "Se guardo el cantCompra";
	}

	@Override
	public String eliminarCantCompra(CantCompra cantCompra) {
		try {
			if (cantCompraRepo.existsById(cantCompra.getProducto1().getId())) {
				cantCompraRepo.delete(cantCompra);
			return "Se elimino el cantCompra";
			}else {
				return "El cantCompra no existe";
			}
		} catch (IllegalArgumentException e) {
			return "No se elimino el cantCompra: " + e.getMessage();
		}
	}

	@Override
	public String modificarCantCompra(CantCompra cantCompra) {
		try {
			if(cantCompraRepo.existsById(cantCompra.getProducto1().getId())) {
				cantCompraRepo.delete(cantCompra);
				cantCompraRepo.save(cantCompra);
				return "Se modifico el cantCompra";
			}else {
				return "No se encontro el cantCompra";
			}
		} catch (IllegalArgumentException e) {
			return "No se encontro el cantCompra: " + e.getMessage();
		}
	}

	@Override
	public int contarCantCompra() {
		int contador = 0;
		try {
			contador = (int) cantCompraRepo.count();
			return contador;
		} catch (IllegalArgumentException e) {
			return contador;
		}
	}

	@Override
	public CantCompra mostrarCantCompra(int id) {
		CantCompra cantCompra = new CantCompra();
		try {
			if(cantCompraRepo.existsById(id)) {				
				cantCompra = cantCompraRepo.getOne(id);
				return cantCompra;
			}else {
				return null;
			}
		} catch (IllegalArgumentException e) {
			cantCompra = null;
		}
		return cantCompra;
	}

	@Override
	public List<CantCompra> findAll() {
		List<CantCompra> listaCantCompra = new ArrayList<CantCompra>();
		try {
			listaCantCompra = cantCompraRepo.findAll();
			return listaCantCompra;
		} catch (IllegalArgumentException e) {
			return listaCantCompra;
		}
	}

}
