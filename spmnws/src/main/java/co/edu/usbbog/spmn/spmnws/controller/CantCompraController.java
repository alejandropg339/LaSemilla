package co.edu.usbbog.spmn.spmnws.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import co.edu.usbbog.spmn.spmnws.model.CantCompra;
import co.edu.usbbog.spmn.spmnws.service.ICantCompraService;


@RestController
public class CantCompraController {

	@Autowired
	private ICantCompraService cantCompraService;
	
	@PostMapping("/crearCantCompra")
    public String crearCantCompra(@RequestBody String newCantCompra) {
        JSONObject respuesta= new JSONObject();
        
        JSONObject entrada= new JSONObject(newCantCompra );
        
        
        if(cantCompraService.crearCantCompra(entrada.getInt("producto1"),entrada.getInt("recibo1"), entrada.getInt("cantidad")).equals("Se guardo el cantCompra")) {
            respuesta.put("respuesta", true);
            return respuesta.toString();
        }else {
            respuesta.put("respuesta", false);
            return respuesta.toString();
        }
        
        
    }
	
	@PostMapping("/eliminarCantCompra")
    public String eliminarCantCompra(@RequestBody CantCompra deleteCantCompra) {
		JSONObject respuesta= new JSONObject();
		if(cantCompraService.eliminarCantCompra(deleteCantCompra).equals("Se elimino el cantCompra")) {
			respuesta.put("respuesta", true);
			return respuesta.toString();
		}else {
			respuesta.put("respuesta", false);
			return respuesta.toString();
		}
    }
	
	@PostMapping("/mostrarCantCompra")
    public String buscarCantVenta(@RequestBody CantCompra  cantCompra) {
        JSONArray array = new JSONArray();
        CantCompra cantCompras = cantCompraService.mostrarCantCompra(cantCompra.getProducto1().getId());
        if (cantCompras != null) {
            JSONObject cantCompraJson = new JSONObject();
            cantCompraJson.put("Producto ", cantCompras.getProducto1().getId());
            cantCompraJson.put("Recibo ", cantCompras.getRecibo1().getId());
            cantCompraJson.put("Cantidad ", cantCompras.getCantidad());
            array.put(cantCompraJson);
            return array.toString();
        } else {
            return "No se encontro";
        }

    }
	
	@PostMapping("/modificarCantCompra")
	public String modificarCantCompra(@RequestBody CantCompra editcantCompra) {
		JSONObject respuesta= new JSONObject();
		if(cantCompraService.modificarCantCompra(editcantCompra).equals("Se modifico el cantVenta")) {
			respuesta.put("respuesta", true);
			return respuesta.toString();
		}else {
			respuesta.put("respuesta", false);
			return respuesta.toString();
		}
		
	}
	
	@PostMapping("/contarCantCompra")
	public String contarCantCompra() {
		JSONObject respuesta= new JSONObject();
			int aux=cantCompraService.contarCantCompra();
			respuesta.put("Count", aux);
			return respuesta.toString();		
	}
	
	@PostMapping("/allCantCompra")
	public String getCa() {
		JSONArray array= new JSONArray();
		List<CantCompra> cantCompra=cantCompraService.findAll();
		for (int i = 0; i < cantCompra.size(); i++) {
			JSONObject cantCompraJson= new JSONObject();
			cantCompraJson.put("Producto", cantCompra.get(i).getProducto1().getId());
			cantCompraJson.put("Recibo", cantCompra.get(i).getRecibo1().getId());
			cantCompraJson.put("Cantidad", cantCompra.get(i).getCantidad());
			array.put(cantCompraJson);			
		}
		return array.toString();		
	}
}
