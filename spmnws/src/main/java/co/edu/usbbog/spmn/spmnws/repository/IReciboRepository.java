package co.edu.usbbog.spmn.spmnws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbbog.spmn.spmnws.model.Recibo;
import java.util.List;

public interface IReciboRepository extends JpaRepository<Recibo, Integer>{
	public List<Recibo> findByEstado(String estado);
}
