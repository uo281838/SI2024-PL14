package Controller;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import Model.VisualizarReservasComoSocioModel;
import View.VisualizarReservasComoSocioView;

public class VisualizarReservasComoSocioController {

	
	private VisualizarReservasComoSocioModel model;
	private VisualizarReservasComoSocioView view;
	
	
	public VisualizarReservasComoSocioController(VisualizarReservasComoSocioModel m , VisualizarReservasComoSocioView v) {
		
		this.model = m;
		this.view = v;
		this.initview();
	}
	


	public void initController() {
		
	}
	
	
	private void initview() {
		
		this.view.getFrame().setVisible(true);
		
		// Crear un modelo de tabla vacío con las columnas "Horario" y "Estado"
	    DefaultTableModel modelo = new DefaultTableModel();
	    modelo.addColumn("Horario");
	    modelo.addColumn("Estado");

	    // Asignar el modelo vacío a la tabla en la vista
	    this.view.setTablaReservasModel(modelo);
		
	}
	
	
	public void actualizarTabla(String fecha, String instalacion) {
		
		List<Object[]> l = this.model.getReservarInstalaciones(fecha, instalacion);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		// Definir las columnas
	    modelo.addColumn("Horario");
	    modelo.addColumn("Estado");
	    
	    // Agregar los datos de la lista a la tabla
	    for (Object[] reserva : l) {
	        modelo.addRow(reserva);
	    }
	    
	    this.view.setTablaReservasModel(modelo);
		
	}
	
}
