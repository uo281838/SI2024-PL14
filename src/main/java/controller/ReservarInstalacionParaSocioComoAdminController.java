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
	    
	    /**
	    view.getBConfirmar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
	    	
	    	String fecha = view.getTFDia().getText();
	    	String horainicio = view.getTFHoraInicio().getText();
	    	String horafin = view.getTFHoraFin().getText();
	    	// int instalacionid = (int) view.getCBInstalacion().getSelectedItem();
	    	int instalacionId = (Integer) view.getCBInstalacion().getSelectedItem();
	    	
	    	boolean pagado = view.getCBPagado().isSelected();
	    	
	    	// Obtener el ID del usuario desde el nombre (suponiendo que se haya seleccionado un usuario previamente)
	    	String nombre = view.getTFNombre().getText();
	    	int usuarioid =  model.obtenerUsuarioId(nombre);
	    	model.insertarReserva(usuarioid, instalacionId, fecha, horainicio, horafin, pagado);
	    	
	    	// Mostrar mensaje de éxito al usuario
	        JOptionPane.showMessageDialog(view.getFrame(), 
	                                      "Se realizó la reserva correctamente.", 
	                                      "Éxito", 
	                                      JOptionPane.INFORMATION_MESSAGE);
	        // Cerrar la ventana después de hacer clic en OK
	        // view.getFrame().dispose(); // Cierra la ventana
	        
	    }));
	    */
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
		if(dia == null || dia.trim().isEmpty() || !dia.matches("\\d{4}-\\d{2}-\\d{2}")) {
			// Verificamos el formato YYYY-MM-DD
			JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha válida en forato YYYY-MM-DD.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
			
		}
		
		// Verificar si la fecha ingresada está más de 30 días en el futuro
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaIngresada = LocalDate.parse(dia, formatter);
			LocalDate fechaHoy = LocalDate.now();
			LocalDate fechaLimite = fechaHoy.plus(30, ChronoUnit.DAYS);

			// Verificar si la fecha es anterior a hoy
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
			JOptionPane.showMessageDialog(null,
					"Error al analizar la fecha. Asegúrese de que esté en formato YYYY-MM-DD.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(horainicio == null || horainicio.trim().isEmpty() || !horainicio.matches("^\\d{2}:\\d{2}$")) {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora válida en forato HH:MM.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (horafin == null || horafin.trim().isEmpty() || !horafin.matches("^\\d{2}:\\d{2}$")) {
	        JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de fin válida en formato HH:MM.", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
		
				 // Validar que la hora de fin no sea más de 2 horas después de la de inicio
			    try {
			        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			        LocalTime inicio = LocalTime.parse(horainicio, timeFormatter);
			        LocalTime fin = LocalTime.parse(horafin, timeFormatter);

			        if (fin.isAfter(inicio.plusHours(2))) {
			            JOptionPane.showMessageDialog(null, "La hora de fin no puede ser más de 2 horas después de la hora de inicio.", "Error",
			                    JOptionPane.ERROR_MESSAGE);
			            return false;
			        }

			    } catch (DateTimeParseException e) {
			        JOptionPane.showMessageDialog(null, "Error en el formato de hora. Use HH:MM.", "Error",
			                JOptionPane.ERROR_MESSAGE);
			        return false;
			    }

			    // Si todas las validaciones son correctas
			    JOptionPane.showMessageDialog(null, "Datos insertados correctamente.", "Éxito",
			            JOptionPane.INFORMATION_MESSAGE);	
			    return true;
			
	}

	public boolean meterReserva() {
		// Obtener los valores de los campos
        String dni = view.getTFNumeroSocio().getText();
        String nombre = view.getTFNombre().getText();
        
        // Llamar al método del modelo para verificar al usuario
        String resultado = model.verificarUsuario(dni, nombre);
        
        // Mostrar el mensaje correspondiente en función del resultado
        if (resultado.equals("Usuario no existe")) {
            JOptionPane.showMessageDialog(view.getFrame(), 
                                          "El usuario no existe en la base de datos.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (resultado.equals("Es moroso")) {
            JOptionPane.showMessageDialog(view.getFrame(), 
                                          "No se podrá realizar la reserva porque el usuario es moroso.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (resultado.equals("No es socio")) {
            JOptionPane.showMessageDialog(view.getFrame(), 
                                          "No se podrá realizar la reserva porque el usuario no es socio.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Usuario válido
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
