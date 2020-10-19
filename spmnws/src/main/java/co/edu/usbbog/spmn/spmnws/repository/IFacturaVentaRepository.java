package co.edu.usbbog.spmn.spmnws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.usbbog.spmn.spmnws.model.FacturaVenta;

public interface IFacturaVentaRepository extends JpaRepository<FacturaVenta, Integer>{
	
	public List<FacturaVenta> findById(String id);
		
	
}
