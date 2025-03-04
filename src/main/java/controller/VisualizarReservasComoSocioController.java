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
	

	public void initController() {

		this.view.getBtnBuscar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
			actualizarTabla(view.getFTFFecha().getText(),
					view.getCBInstalaciones().getSelectedItem().toString());
			actualizarAforo(view.getCBInstalaciones().getSelectedItem().toString());
		}));

		// Listener para la seleccion de columnas en la tabla
		this.view.getTablaReservas().getSelectionModel().addListSelectionListener(e -> {
			// Verifica si una fila está seleccionada
			if (!e.getValueIsAdjusting()) {
				int selectedRow = this.view.getTablaReservas().getSelectedRow();
				System.out.println(selectedRow);

				if (selectedRow != -1) { // Si se ha seleccionado una fila

					// Obtener los valores de la fila seleccionada
					String reservadoPor = (String) this.view.getTablaReservas().getValueAt(selectedRow, 2);

					// Mensaje a mostrar en el JTextField
					String mensaje = "";

					if (reservadoPor.equalsIgnoreCase("N/A")) {
						this.view.getTFDescripcion().setText("Esta hora está disponible");
					} else if (reservadoPor.equals("Reservado por ti")) {
						// Obtener nombre y apellidos del usuario
						//String nombreCompleto = this.model.getNombreSocio(this.view.getTFDni().getText());
						//this.view.getTFDescripcion().setText("Reservado por " + nombreCompleto);
					} else if (reservadoPor.equals("Reservado")) {
						// En este caso, la hora está reservada por otro usuario
						this.view.getTFDescripcion().setText("Esta hora está reservada por otro usuario");
					} else {
						String nombreActividad = (String) this.view.getTablaReservas().getValueAt(selectedRow, 2);
																																																					
						mensaje = "Reservado para '" + nombreActividad + "'";
						this.view.getTFDescripcion().setText(mensaje);
					}
				}
			}
		});

	}

	//Funcion para mostrar el aforo de una determinada instalación
	private void actualizarAforo(String instalacionSeleccionada) {
		// Validar que la instalación seleccionada no esté vacía
		if (instalacionSeleccionada != null && !instalacionSeleccionada.trim().isEmpty()) {
			// Obtener el aforo máximo de la instalación seleccionada
			Integer aforo = this.model.getAforoInstalacion(instalacionSeleccionada);

			// Actualizar la etiqueta con el aforo máximo
			if (aforo != null) {
				this.view.getLblAforo().setText(aforo.toString());
			} else {
				this.view.getLblAforo().setText("");
			}
		}
	}

	
	

	//Funcion para obtener los nombres de las instalaciones y poder insertarlos en el combobox
	public void obtenerNombreInstalaciones() {

		// Obtener los resultados de la consulta (nombre y precio por hora)
		List<Object[]> l = this.model.getNombreInstalaciones();

		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();

		// Iterar sobre los resultados
		for (Object[] fila : l) {
			if (fila.length > 0 && fila[0] != null) {
				// Aquí estamos tomando solo el nombre para mostrar en el ComboBox
				String nombreInstalacion = fila[0].toString();
				modelo.addElement(nombreInstalacion);
			}
		}

		this.view.setCBReservasModel(modelo);

	}

	
	//Funcion para obtener el precio por hora de las instalaciones
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

			this.view.getTFPrecioHora().setText(String.format("%.2f", precioHora) + "€");
		} else {
			// Si no se encuentra el precio, mostrar un mensaje de error
			JOptionPane.showMessageDialog(null, "No se pudo obtener el precio de la instalación seleccionada.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	
	
	//Funcion principal de la clase , que se invoca al pulsar el boton de busqueda y que rellena la tabla con todos los parametros pasados
	//y muestra las reservas a partir de horas en los siguientes 30 dias
	
	public void actualizarTabla(String fecha, String instalacion) {
		// Limpiar la tabla antes de mostrar nuevos datos
		DefaultTableModel modelo = (DefaultTableModel) this.view.getTablaReservasModel();
		modelo.setRowCount(0);
		

		if (fecha == null || fecha.trim().isEmpty() || !fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
			// Verifica formato YYYY-MM-DD
			JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha válida en formato YYYY-MM-DD.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}


		//Muestra la fecha seleccionada en la cabecera de la tabla
		this.view.getLblFechaTabla().setText(fecha);

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

		//Almacenamos la informacion traida de la bd
		List<Object[]> reservas = this.model.getReservarInstalaciones(fecha, instalacion);
		List<Object[]> actividades = this.model.getReservarActividades(fecha, instalacion);

		String[] horarios = { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
				"18:00", "19:00", "20:00", "21:00", "22:00" };

		// Crear un Set para tener un acceso rápido a las horas reservadas
		// (instalaciones + actividades)
		Set<String> horariosReservados = new HashSet<>();
		Map<String, String> reservadosPor = new HashMap<>();
																

		// Añadir reservas de socios a la lista
		for (Object[] reserva : reservas) {
			if (reserva.length > 3 && reserva[0] != null && reserva[1] != null && reserva[4] != null) {
				String horaInicio = reserva[0].toString();
				String dniSocio = reserva[4].toString(); // Esto es el 'dni' del socio que hizo la reserva
				horariosReservados.add(horaInicio); // Guardar la hora reservada
				reservadosPor.put(horaInicio, "Socio"); // Asociar la hora con el usuario que la reservó
			}
		}

		// Añadir actividades a la lista
		for (Object[] actividad : actividades) {
			if (actividad.length > 1 && actividad[0] != null && actividad[1] != null) {
				String horaInicio = actividad[0].toString();
				String nombreActividad = actividad[1].toString(); // El nombre de la actividad
				horariosReservados.add(horaInicio); // Añadimos la actividad al conjunto de horarios reservados
				reservadosPor.put(horaInicio, nombreActividad); // Guardar el nombre de la actividad
			}
		}

		// Crear el modelo de la tabla
		DefaultTableModel modelo1 = new DefaultTableModel();

		//Se definen las columnas
		modelo1.addColumn("Horario");
		modelo1.addColumn("Estado");
		modelo1.addColumn("Reservado por");


		
		//Rellenamos las filas de la tabla
		for (String hora : horarios) {
			String estado = horariosReservados.contains(hora) ? "Reservado" : "Disponible";
			String reservadoPorUsuario = horariosReservados.contains(hora)
					? (reservadosPor.get(hora).equals("Actividad") ? "Actividad" : reservadosPor.get(hora))
					: "N/A";

			
			
			//Se añade la fila
			modelo1.addRow(new Object[] { hora, estado, reservadoPorUsuario });
		}

		this.view.setTablaReservasModel(modelo1);
	}

}
