package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ReservarInstalacionParaActividadComoAdminView {

	private JFrame frame;
	private JTextField TFFecha;
	private JTextField TFHoraInicio;
	private JTextField TFHoraFin;
	private JButton BSeleccionar;
	private JButton BRemover;
	private JTextField TFInstalacion;
	private JButton BCancelar;
	private JButton BConfirmar;
	private JButton bComprobar;
	private JTable tInstalaciones;
	private JTable tActividades;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;
	private JTable tReservas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservarInstalacionParaActividadComoAdminView window = new ReservarInstalacionParaActividadComoAdminView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReservarInstalacionParaActividadComoAdminView() {
		initialize();
		
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 742, 416);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccionar fecha:");
		lblNewLabel.setBounds(25, 29, 135, 14);
		frame.getContentPane().add(lblNewLabel);
		
		TFFecha = new JTextField();
		TFFecha.setBounds(25, 54, 106, 20);
		frame.getContentPane().add(TFFecha);
		TFFecha.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Hora incio:");
		lblNewLabel_1.setBounds(224, 29, 72, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		TFHoraInicio = new JTextField();
		TFHoraInicio.setBounds(224, 54, 106, 20);
		frame.getContentPane().add(TFHoraInicio);
		TFHoraInicio.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Hora fin:");
		lblNewLabel_2.setBounds(416, 29, 72, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		TFHoraFin = new JTextField();
		TFHoraFin.setBounds(416, 54, 106, 20);
		frame.getContentPane().add(TFHoraFin);
		TFHoraFin.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Seleccionar instalacion: ");
		lblNewLabel_3.setBounds(25, 110, 165, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		BSeleccionar = new JButton(">");
		BSeleccionar.setBounds(173, 171, 54, 23);
		frame.getContentPane().add(BSeleccionar);
		
		BRemover = new JButton("<");
		BRemover.setBounds(173, 205, 54, 23);
		frame.getContentPane().add(BRemover);
		
		TFInstalacion = new JTextField();
		TFInstalacion.setBounds(261, 188, 203, 20);
		frame.getContentPane().add(TFInstalacion);
		TFInstalacion.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Instalacion \r\nseleccionada:");
		lblNewLabel_4.setBounds(261, 154, 203, 23);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Seleccionar actividad:");
		lblNewLabel_5.setBounds(515, 110, 155, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		BCancelar = new JButton("Cancelar");
		BCancelar.setBounds(629, 347, 91, 23);
		frame.getContentPane().add(BCancelar);
		
		BConfirmar = new JButton("Confirmar\r\n");
		BConfirmar.setBounds(528, 347, 91, 23);
		frame.getContentPane().add(BConfirmar);
		
		bComprobar = new JButton("Comprobar\r\n");
		bComprobar.setBounds(579, 53, 124, 23);
		frame.getContentPane().add(bComprobar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(515, 154, 165, 136);
		frame.getContentPane().add(scrollPane_1);
		
		tActividades = new JTable();
		scrollPane_1.setViewportView(tActividades);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 154, 119, 93);
		frame.getContentPane().add(scrollPane);
		
		tInstalaciones = new JTable();
		scrollPane.setViewportView(tInstalaciones);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(25, 309, 452, 61);
		frame.getContentPane().add(scrollPane_2);
		
		tReservas = new JTable();
		scrollPane_2.setViewportView(tReservas);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTFFecha() {
		return TFFecha;
	}

	public void setTFFecha(JTextField tFFecha) {
		TFFecha = tFFecha;
	}

	public JTextField getTFHoraInicio() {
		return TFHoraInicio;
	}

	public void setTFHoraInicio(JTextField tFHoraInicio) {
		TFHoraInicio = tFHoraInicio;
	}

	public JTextField getTFHoraFin() {
		return TFHoraFin;
	}

	public void setTFHoraFin(JTextField tFHoraFin) {
		TFHoraFin = tFHoraFin;
	}

	public JButton getBSeleccionar() {
		return BSeleccionar;
	}

	public void setBSeleccionar(JButton bSeleccionar) {
		BSeleccionar = bSeleccionar;
	}

	public JButton getBRemover() {
		return BRemover;
	}

	public void setBRemover(JButton bRemover) {
		BRemover = bRemover;
	}

	public JTextField getTFInstalacion() {
		return TFInstalacion;
	}

	public void setTFInstalacion(JTextField tFInstalacion) {
		TFInstalacion = tFInstalacion;
	}

	public void setTFInstalacion(String texto) {
		this.TFInstalacion.setText(texto);
	}

	public JButton getBCancelar() {
		return BCancelar;
	}

	public void setBCancelar(JButton bCancelar) {
		BCancelar = bCancelar;
	}

	public JButton getBConfirmar() {
		return BConfirmar;
	}

	public void setBConfirmar(JButton bConfirmar) {
		BConfirmar = bConfirmar;
	}

	public JButton getbComprobar() {
		return bComprobar;
	}

	public void setbComprobar(JButton bComprobar) {
		this.bComprobar = bComprobar;
	}

	public JTable gettInstalaciones() {
		return tInstalaciones;
	}

	public void settInstalaciones(JTable tInstalaciones) {
		this.tInstalaciones = tInstalaciones;
	}
	
	public void setTablaReservasModel(DefaultTableModel modelo) {
	    tInstalaciones.setModel(modelo); // Reemplaza el modelo de la tabla existente
	}
	
	
	public DefaultTableModel getTablaReservasModel() {
	    return (DefaultTableModel) this.tInstalaciones.getModel(); // Obtener y devolver el modelo de la tabla
	}

	public JTable gettActividades() {
		return tActividades;
	}

	public void settActividades(JTable tActividades) {
		this.tActividades = tActividades;
	}
	
	public void setTablaActividadesModel(DefaultTableModel modelo) {
	    tActividades.setModel(modelo); // Reemplaza el modelo de la tabla existente
	}
	
	
	public DefaultTableModel getTablaActividadesModel() {
	    return (DefaultTableModel) this.tActividades.getModel(); // Obtener y devolver el modelo de la tabla
	}

	public JTable gettReservas() {
		return tReservas;
	}

	public void settReservas(JTable tReservas) {
		this.tReservas = tReservas;
	}

	public void setTablaReservaModel(DefaultTableModel modelo) {
	    tReservas.setModel(modelo); // Reemplaza el modelo de la tabla existente
	}
	
	
	public DefaultTableModel getTablaReservaModel() {
	    return (DefaultTableModel) this.tReservas.getModel(); // Obtener y devolver el modelo de la tabla
	}
}
