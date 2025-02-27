package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.demo.util.SwingUtil;
import model.VisualizarReservasComoSocioModel;
import view.VisualizarReservasComoSocioView;

public class VisualizarReservasComoSocioController {

	private VisualizarReservasComoSocioModel model;
	private VisualizarReservasComoSocioView view;

	public VisualizarReservasComoSocioController(VisualizarReservasComoSocioModel m,
			VisualizarReservasComoSocioView v) {

		this.model = m;
		this.view = v;
		this.initview();
	}

	public void initController() {

		this.view.getBtnBuscar()
				.addActionListener(e -> SwingUtil.exceptionWrapper(() -> actualizarTabla(view.getTFDni().getText(),
						view.getFTFFecha().getText(), view.getCBInstalaciones().getSelectedItem().toString())));

		this.view.getTablaReservas().getSelectionModel().addListSelectionListener(e -> {
			// Verifica si una fila está seleccionada
			if (!e.getValueIsAdjusting()) {
				int selectedRow = this.view.getTablaReservas().getSelectedRow();

				if (selectedRow != -1) { // Si se ha seleccionado una fila

					// Obtener los datos de la fila seleccionada
					String horaSeleccionada = (String) this.view.getTablaReservas().getValueAt(selectedRow, 0); // Columna
																												// "Horario"

					// Obtener la fecha desde el JTextField o el JFormattedTextField
					String fechaSeleccionada = this.view.getFTFFecha().getText(); // Asumiendo que usas un
																					// JFormattedTextField para la fecha

				}
			}
		});

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
	
	
	
	public void obtenerPrecioHora(String nombreInstalacion) {
	    // Validar que el nombre de la instalación no esté vacío
	    if (nombreInstalacion == null || nombreInstalacion.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Por favor, seleccione una instalación.", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Consultar el precio por hora de la instalación seleccionada
	    Double precioHora = this.model.getPrecioHoraInstalacion(nombreInstalacion);

	    // Verificar si se obtuvo el precio
	    if (precioHora != null) {
	    	
	        this.view.getTFPrecioHora().setText(String.format("%.2f", precioHora)+"€");   
	    } else {
	        // Si no se encuentra el precio, mostrar un mensaje de error
	        JOptionPane.showMessageDialog(null, "No se pudo obtener el precio de la instalación seleccionada.", "Error",
	                JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	

	public void actualizarTabla(String dni, String fecha, String instalacion) {
		// Limpiar la tabla antes de mostrar nuevos datos
		DefaultTableModel modelo = (DefaultTableModel) this.view.getTablaReservasModel();
		modelo.setRowCount(0); // Esto elimina todas las filas de la tabla

		if (fecha == null || fecha.trim().isEmpty() || !fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
			// Verifica formato YYYY-MM-DD
			JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha válida en formato YYYY-MM-DD.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Validar que los campos no estén vacíos
		if (dni == null || dni.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese un DNI válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean existeDNI = this.model.existeDNI(dni);
		if (!existeDNI) {
			JOptionPane.showMessageDialog(null, "El usuario con este DNI no existe.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Verificar si la fecha ingresada está más de 30 días en el futuro
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaIngresada = LocalDate.parse(fecha, formatter);
			LocalDate fechaHoy = LocalDate.now();
			LocalDate fechaLimite = fechaHoy.plus(30, ChronoUnit.DAYS);

			// Verificar si la fecha es anterior a hoy
			if (fechaIngresada.isBefore(fechaHoy)) {
				JOptionPane.showMessageDialog(null, "La fecha no puede ser anterior al día de hoy.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (fechaIngresada.isAfter(fechaLimite)) {
				JOptionPane.showMessageDialog(null, "No se puede seleccionar una fecha más de 30 días en el futuro.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error al analizar la fecha. Asegúrese de que esté en formato YYYY-MM-DD.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		this.obtenerPrecioHora(instalacion);

		// Obtener reservas de la BD para esa fecha e instalación
		List<Object[]> reservas = this.model.getReservarInstalaciones(fecha, instalacion);

		String[] horarios = { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
				"18:00", "19:00", "20:00", "21:00", "22:00" };

		// Crear un Set para tener un acceso rápido a las horas y un mapa para asociar
		// las horas con los usuarios que hicieron la reserva
		Set<String> horariosReservados = new HashSet<>();
		Map<String, String> reservadosPor = new HashMap<>(); // Usamos un Map para asociar cada hora con el 'dni_socio'

		for (Object[] reserva : reservas) {
			if (reserva.length > 3 && reserva[0] != null && reserva[1] != null && reserva[4] != null) {
				String horaInicio = reserva[0].toString();
				String dniSocio = reserva[4].toString(); // Esto es el 'dni' del socio que hizo la reserva
				horariosReservados.add(horaInicio); // Guardar la hora reservada
				reservadosPor.put(horaInicio, dniSocio); // Asociar la hora con el usuario que la reservó
			}
		}

		// Crear el modelo de la tabla
		DefaultTableModel modelo1 = new DefaultTableModel();

		// Definir columnas, incluyendo "Reservado por"
		modelo1.addColumn("Horario");
		modelo1.addColumn("Estado");
		modelo1.addColumn("Reservado por");

		for (String hora : horarios) {
			String estado = horariosReservados.contains(hora) ? "Reservado" : "Disponible";
			String reservadoPorUsuario = horariosReservados.contains(hora) ? reservadosPor.get(hora) : "N/A"; // Recuperar
																												// el
																												// usuario
																												// que
																												// reservó,
																												// si lo
																												// hay

			// Si el DNI ingresado coincide con el del usuario que reservó, mostrar
			// "Reservado por ti"
			if (reservadoPorUsuario.equals(dni)) {
				reservadoPorUsuario = "Reservado por ti";
			}

			modelo1.addRow(new Object[] { hora, estado, reservadoPorUsuario });
		}

		// Asignar modelo a la tabla en la vista
		this.view.setTablaReservasModel(modelo1);
	}

}
