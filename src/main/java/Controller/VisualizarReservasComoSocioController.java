package Controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.VisualizarReservasComoSocioModel;
import View.VisualizarReservasComoSocioView;
import giis.demo.util.SwingUtil;

public class VisualizarReservasComoSocioController {

	
	private VisualizarReservasComoSocioModel model;
	private VisualizarReservasComoSocioView view;
	
	
	public VisualizarReservasComoSocioController(VisualizarReservasComoSocioModel m , VisualizarReservasComoSocioView v) {
		
		this.model = m;
		this.view = v;
		this.initview();
	}
	


	public void initController() {
		
		this.view.getBtnBuscar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> actualizarTabla(view.getTFDni().getText(),view.getFTFFecha().getText(),view.getCBInstalaciones().getSelectedItem().toString())));
		
	}
	
	
	private void initview() {
		
		
		// Crear un modelo de tabla vacío con las columnas "Horario" y "Estado"
	    DefaultTableModel modelo = new DefaultTableModel();
	    modelo.addColumn("Horario");
	    modelo.addColumn("Estado");

	    // Asignar el modelo vacío a la tabla en la vista
	    this.view.setTablaReservasModel(modelo);
	    
	    
	    this.obtenerNombreInstalaciones();
		
		
		this.view.getFrame().setVisible(true);
		
		
		
	}
	
	public void obtenerNombreInstalaciones() {
		
		List<Object[]> l = this.model.getNombreInstalaciones();
		
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();
		
		for (Object[] fila : l) {
		    if (fila.length > 0 && fila[0] != null) {	    	
		        modelo.addElement(fila[0].toString());
		        System.out.println(fila[0].toString());
		        
		    }
		}
		
		this.view.setCBReservasModel(modelo);
		
		
	}
	
	
	public void actualizarTabla(String dni ,String fecha, String instalacion) {
		
		
		boolean existeDNI = this.model.existeDNI(dni);
		
		if(!existeDNI) {
		    JOptionPane.showMessageDialog(null, "El usuario con este DNI no existe.", "Error", JOptionPane.ERROR_MESSAGE);
		    return;
		}
		
		
		
		// Obtener reservas de la BD para esa fecha e instalación
	    List<Object[]> reservas = this.model.getReservarInstalaciones(fecha, instalacion);
	    
	    String[] horarios = {
	            "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", 
	            "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", 
	            "20:00", "21:00", "22:00"
	        };
	    
	    
	 // Convertir la lista de reservas en un Set para búsqueda rápida
	    Set<String> horariosReservados = new HashSet<>();
	    for (Object[] reserva : reservas) {
	        if (reserva.length > 0 && reserva[0] != null) {
	            horariosReservados.add(reserva[0].toString());  // Guardar solo las horas reservadas
	        }
	    }
	    
	 // Crear el modelo de la tabla
	    DefaultTableModel modelo = new DefaultTableModel();
	    
	 // Definir columnas
	    modelo.addColumn("Horario");
	    modelo.addColumn("Estado");
	    
	    for (String hora : horarios) {
	        String estado = horariosReservados.contains(hora) ? "Reservado" : "Disponible";
	        modelo.addRow(new Object[]{hora, estado});
	    }
	    
	    //Asignar modelo a la tabla en la vista
	    this.view.setTablaReservasModel(modelo);
	    
		
		
	}
	
}
