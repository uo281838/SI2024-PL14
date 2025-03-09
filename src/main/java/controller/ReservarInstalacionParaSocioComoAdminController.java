package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import giis.demo.util.SwingUtil;
import model.ReservarInstalacionParaSocioComoAdminModel;
import view.ReservarInstalacionParaSocioComoAdminView;

public class ReservarInstalacionParaSocioComoAdminController {
	private ReservarInstalacionParaSocioComoAdminModel model;
	private ReservarInstalacionParaSocioComoAdminView view;
	
	public ReservarInstalacionParaSocioComoAdminController (ReservarInstalacionParaSocioComoAdminModel m, 
			ReservarInstalacionParaSocioComoAdminView v) {
		this.model=m;
		this.view=v;
		this.initView();

	}
	
	public void initView() {
		view.getFrame().setVisible(true);
		this.meterInstalaciones();
		
	}
	
	public void initController() {
	    view.getBBuscar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
	        
	    	insertarCorrectamenteDatos(view.getTFDia().getText(), view.getTFHoraInicio().getText(),
	        		view.getTFHoraFin().getText());
	    	buscar();
	        
	    }));
	  
	    view.getBComprobar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
	    	meterReserva();
	    }));
	    view.getBConfirmar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
	        confirmarReserva();
	    }));
	    
	    
	    
	}


	
	
	public void buscar() {
		// Obtener la fecha, hora de inicio, hora de fin y la instalación seleccionada
	    String fecha = view.getTFDia().getText();
	    String horainicio = view.getTFHoraInicio().getText();
	    String horafin = view.getTFHoraFin().getText();
	    String instalacion = (String) view.getCBInstalacion().getSelectedItem();
		
		// Validar que los campos no estén vacíos
	    if (fecha.isEmpty() || horainicio.isEmpty() || horafin.isEmpty() || instalacion.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de buscar.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    // Obtener el ID de la instalación seleccionada
	    int instalacionId = model.getInstalacionId(instalacion);
	    
	    // Llamar al modelo para obtener las reservas
	    List<Object[]> reservas = model.getReservasInstalacion(fecha, horainicio, horafin, instalacionId);
	    
	    // Si no hay reservas
	    if (reservas.isEmpty()) {
	        view.getTFEstado().setText("Libre");
	        view.getTFOcupado().setText("N/A");
	    } else {
	        // Si hay reservas, establecer estado "Ocupado" y mostrar el nombre del usuario
	        Object[] reserva = reservas.get(0);  // Obtener la primera reserva
	        view.getTFEstado().setText("Ocupado");
	        view.getTFOcupado().setText((String) reserva[1]);  // Nombre del usuario
	    }
	}


	
	public void meterInstalaciones() {
		List<Object[]> list = this.model.getInstalaciones();
		
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();
		
		for(Object[] fila: list) {
			if(fila.length > 0 && fila[0] !=null) {
				String nombreInstalacion = fila[0].toString();
				modelo.addElement(nombreInstalacion);
			}
		}
		this.view.setCBInstalacionModel(modelo);
	}
	
	public boolean insertarCorrectamenteDatos(String dia, String horainicio, String horafin) {
	    if (dia == null || dia.trim().isEmpty() || !dia.matches("\\d{4}-\\d{2}-\\d{2}")) {
	        JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha válida en formato YYYY-MM-DD.", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate fechaIngresada = LocalDate.parse(dia, formatter);
	        LocalDate fechaHoy = LocalDate.now();
	        LocalDate fechaLimite = fechaHoy.plus(30, ChronoUnit.DAYS);

	        if (fechaIngresada.isBefore(fechaHoy)) {
	            JOptionPane.showMessageDialog(null, "La fecha no puede ser anterior al día de hoy.", "Error",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        if (fechaIngresada.isAfter(fechaLimite)) {
	            JOptionPane.showMessageDialog(null, "No se puede seleccionar una fecha más de 30 días en el futuro.",
	                    "Error", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error al analizar la fecha. Asegúrese de que esté en formato YYYY-MM-DD.", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Validar que la hora sea en formato HH:00 (horas enteras)
	    if (horainicio == null || horainicio.trim().isEmpty() || !horainicio.matches("^(0[9-9]|1[0-9]|2[0-2]):00$")) {
	        JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de inicio válida en formato HH:00 (entre 09:00 y 22:00).", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (horafin == null || horafin.trim().isEmpty() || !horafin.matches("^(0[9-9]|1[0-9]|2[0-2]):00$")) {
	        JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de fin válida en formato HH:00 (entre 09:00 y 22:00).", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    try {
	        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	        LocalTime inicio = LocalTime.parse(horainicio, timeFormatter);
	        LocalTime fin = LocalTime.parse(horafin, timeFormatter);

	        // Validar que la hora de fin no sea más de 2 horas después de la de inicio
	        if (fin.isAfter(inicio.plusHours(2))) {
	            JOptionPane.showMessageDialog(null, "La hora de fin no puede ser más de 2 horas después de la hora de inicio.", "Error",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        // Validar que la hora de inicio es menor que la hora de fin
	        if (inicio.isAfter(fin) || inicio.equals(fin)) {
	            JOptionPane.showMessageDialog(null, "La hora de inicio debe ser menor que la hora de fin.", "Error",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	    } catch (DateTimeParseException e) {
	        JOptionPane.showMessageDialog(null, "Error en el formato de hora. Use HH:00.", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    JOptionPane.showMessageDialog(null, "Datos insertados correctamente.", "Éxito",
	            JOptionPane.INFORMATION_MESSAGE);
	    return true;
	}


	public boolean meterReserva() {
	    // Obtener el DNI del campo de texto
	    String dni = view.getTFNumeroSocio().getText();

	    // Llamar al modelo para verificar el usuario y obtener su estado o nombre
	    String resultado = model.verificarUsuario(dni);

	    // Evaluar el resultado devuelto
	    if (resultado.equals("Usuario no existe")) {
	        view.getTFNombre().setText(""); // Vaciar el campo de nombre
	        JOptionPane.showMessageDialog(view.getFrame(), 
	                                      "El usuario no existe en la base de datos.", 
	                                      "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } else if (resultado.startsWith("Es moroso - ")) {
	        // Extraer el nombre del usuario moroso
	        String nombreMoroso = resultado.substring(11); // "Es moroso - " ocupa 11 caracteres
	        view.getTFNombre().setText(nombreMoroso); // Mostrar el nombre en el campo

	        JOptionPane.showMessageDialog(view.getFrame(), 
	                                      "No se podrá realizar la reserva porque el usuario es moroso.\nNombre: " + nombreMoroso, 
	                                      "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } else if (resultado.equals("No es socio")) {
	        JOptionPane.showMessageDialog(view.getFrame(), 
	                                      "No se podrá realizar la reserva porque el usuario no es socio.", 
	                                      "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } else {
	        // El usuario es válido, rellenar automáticamente el campo de nombre
	        view.getTFNombre().setText(resultado);

	        JOptionPane.showMessageDialog(view.getFrame(), 
	                                      "El usuario es válido para realizar la reserva.", 
	                                      "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        return true;
	    }
	}

	

	public void confirmarReserva() {
	    // Obtener los datos de la vista
	    String fecha = view.getTFDia().getText();
	    String horaInicio = view.getTFHoraInicio().getText();
	    String horaFin = view.getTFHoraFin().getText();
	    String instalacion = (String) view.getCBInstalacion().getSelectedItem();
	    boolean pagado = view.getCBPagado().isSelected();  // Checkbox de pagado
	    int usuarioId = model.obtenerUsuarioId(view.getTFNombre().getText());  // Obtener ID del usuario
	    
	    // Obtener el ID de la instalación seleccionada
	    int instalacionId = model.getInstalacionId(instalacion);
	    
	   if(!insertarCorrectamenteDatos(fecha, horaInicio, horaFin)) {
		   return;
	   }
	   if(!meterReserva()) {
		   return;
	   }
	    
	    // Intentar insertar la reserva
	    boolean exito = model.insertarReserva(usuarioId, instalacionId, fecha, horaInicio, horaFin, pagado);
	    
	    if (exito) {
	        JOptionPane.showMessageDialog(null, "Reserva realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "La instalación ya está ocupada en ese horario.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
}
