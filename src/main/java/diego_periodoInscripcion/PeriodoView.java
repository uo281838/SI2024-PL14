package diego_periodoInscripcion;

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class PeriodoView {
	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JDateChooser dateInicio;
	private JDateChooser dateFin;
	private JDateChooser dateFinNoSocios;
	private JButton btnGuardar;
	private JButton btnMostrar;
	private JTable tablaPeriodos;
	private JTable detallePeriodo;
	private JComboBox<Object> listaPeriodos;

	public PeriodoView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Gestión de Períodos");
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout(6, 2, 5, 5));

		panelFormulario.add(new JLabel("Nombre:"));
		txtNombre = new JTextField();
		panelFormulario.add(txtNombre);

		panelFormulario.add(new JLabel("Descripción:"));
		txtDescripcion = new JTextField();
		panelFormulario.add(txtDescripcion);

		panelFormulario.add(new JLabel("Fecha Inicio:"));
		dateInicio = new JDateChooser();
		panelFormulario.add(dateInicio);

		panelFormulario.add(new JLabel("Fecha Fin:"));
		dateFin = new JDateChooser();
		panelFormulario.add(dateFin);

		panelFormulario.add(new JLabel("Fecha Fin No Socios:"));
		dateFinNoSocios = new JDateChooser();
		panelFormulario.add(dateFinNoSocios);

		btnGuardar = new JButton("Guardar Período");
		panelFormulario.add(btnGuardar);
		btnMostrar = new JButton("Mostrar Períodos");
		panelFormulario.add(btnMostrar);

		frame.add(panelFormulario, BorderLayout.NORTH);

		tablaPeriodos = new JTable();
		JScrollPane scrollTabla = new JScrollPane(tablaPeriodos);
		frame.add(scrollTabla, BorderLayout.CENTER);

		JPanel panelDetalles = new JPanel(new BorderLayout());
		detallePeriodo = new JTable();
		panelDetalles.add(new JLabel("Detalles del Período"), BorderLayout.NORTH);
		panelDetalles.add(new JScrollPane(detallePeriodo), BorderLayout.CENTER);

		listaPeriodos = new JComboBox<>();
		panelDetalles.add(listaPeriodos, BorderLayout.SOUTH);

		frame.add(panelDetalles, BorderLayout.SOUTH);
	}

	// Métodos para acceder a los componentes desde el controlador
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getNombreField() {
		return txtNombre;
	}

	public JTextField getDescripcionField() {
		return txtDescripcion;
	}

	public JDateChooser getFechaInicioChooser() {
		return dateInicio;
	}

	public JDateChooser getFechaFinChooser() {
		return dateFin;
	}

	public JDateChooser getFechaFinNoSociosChooser() {
		return dateFinNoSocios;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnMostrar() {
		return btnMostrar;
	}

	public JTable getTablaPeriodos() {
		return tablaPeriodos;
	}

	public JTable getDetallePeriodo() {
		return detallePeriodo;
	}

	public JComboBox<Object> getListaPeriodos() {
		return listaPeriodos;
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(frame, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
	}

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
