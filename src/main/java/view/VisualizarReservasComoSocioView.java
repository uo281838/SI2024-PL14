package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JList;

public class VisualizarReservasComoSocioView {

	private JFrame frmVisualizarReservas;
	private JTable TablaReservas;
	private JTextField TFDescripcion;
	private JLabel lblNewLabel_3;
	private JSeparator separator_1;
	private JComboBox CBInstalaciones;
	private JButton btnBuscar;
	private JFormattedTextField FTFFecha;
	private JLabel TFPrecioHora;
	private JLabel LblAforo;
	private JLabel lblFechaTabla;
	private JButton btnReservar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarReservasComoSocioView window = new VisualizarReservasComoSocioView();
					window.frmVisualizarReservas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrame() {
		return frmVisualizarReservas;
	}

	public void setFrame(JFrame frame) {
		this.frmVisualizarReservas = frame;
	}

	public void setTablaReservasModel(DefaultTableModel modelo) {
		TablaReservas.setModel(modelo); // Reemplaza el modelo de la tabla existente
	}

	public DefaultTableModel getTablaReservasModel() {
		return (DefaultTableModel) this.TablaReservas.getModel(); // Obtener y devolver el modelo de la tabla
	}

	public void setCBReservasModel(DefaultComboBoxModel modelo) {
		CBInstalaciones.setModel(modelo);

	}

	public JLabel getLblAforo() {
		return LblAforo;
	}

	public void setLblAforo(JLabel lblAforo) {
		LblAforo = lblAforo;
	}

	public JLabel getTFPrecioHora() {
		return TFPrecioHora;
	}

	public void setTFPrecioHora(JLabel tFPrecioHora) {
		TFPrecioHora = tFPrecioHora;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnReservar() {
		return btnReservar;
	}

	public void setBtnReservar(JButton btnReservar) {
		this.btnReservar = btnReservar;
	}

	/**
	 * Create the application.
	 */
	public VisualizarReservasComoSocioView() {
		initialize();
	}

	public JLabel getLblFechaTabla() {
		return lblFechaTabla;
	}

	public void setLblFechaTabla(JLabel lblFechaTabla) {
		this.lblFechaTabla = lblFechaTabla;
	}

	public JFormattedTextField getFTFFecha() {
		return FTFFecha;
	}

	public void setFTFFecha(JFormattedTextField fTFFecha) {
		FTFFecha = fTFFecha;
	}

	public JComboBox getCBInstalaciones() {
		return CBInstalaciones;
	}

	public void setCBInstalaciones(JComboBox cBInstalaciones) {
		CBInstalaciones = cBInstalaciones;
	}

	public JTable getTablaReservas() {
		return TablaReservas;
	}

	public JTextField getTFDescripcion() {
		return TFDescripcion;
	}

	public void setTFDescripcion(JTextField tFDescripcion) {
		TFDescripcion = tFDescripcion;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVisualizarReservas = new JFrame();
		frmVisualizarReservas.setTitle("Visualizar reservas");
		frmVisualizarReservas.setBounds(100, 100, 623, 581);
		frmVisualizarReservas.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Instalación");
		lblNewLabel.setBounds(10, 10, 85, 13);
		frmVisualizarReservas.getContentPane().add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 225, 307, 309);
		frmVisualizarReservas.getContentPane().add(scrollPane);

		TablaReservas = new JTable();
		scrollPane.setViewportView(TablaReservas);

		JLabel lblSeleccioneUnDia = new JLabel("Seleccione un dia: (Solo se permite ver 1 mes de antelación)");
		lblSeleccioneUnDia.setBounds(184, 10, 351, 13);
		frmVisualizarReservas.getContentPane().add(lblSeleccioneUnDia);

		JLabel lblNewLabel_1 = new JLabel("Descripción");
		lblNewLabel_1.setBounds(356, 152, 85, 13);
		frmVisualizarReservas.getContentPane().add(lblNewLabel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 113, 609, 2);
		frmVisualizarReservas.getContentPane().add(separator);

		TFDescripcion = new JTextField();
		TFDescripcion.setBounds(356, 175, 226, 37);
		frmVisualizarReservas.getContentPane().add(TFDescripcion);
		TFDescripcion.setColumns(10);

		lblNewLabel_3 = new JLabel("Aforo:");
		lblNewLabel_3.setBounds(10, 162, 45, 13);
		frmVisualizarReservas.getContentPane().add(lblNewLabel_3);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(327, 113, 19, 429);
		frmVisualizarReservas.getContentPane().add(separator_1);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 72, 85, 21);
		frmVisualizarReservas.getContentPane().add(btnBuscar);

		CBInstalaciones = new JComboBox();
		CBInstalaciones.setBounds(10, 33, 126, 19);
		frmVisualizarReservas.getContentPane().add(CBInstalaciones);

		FTFFecha = new JFormattedTextField();

		FTFFecha.setToolTipText("");
		FTFFecha.setBounds(184, 33, 96, 19);
		frmVisualizarReservas.getContentPane().add(FTFFecha);

		JLabel lblNewLabel_4 = new JLabel("€/hora de la instalación:");
		lblNewLabel_4.setBounds(10, 139, 156, 13);
		frmVisualizarReservas.getContentPane().add(lblNewLabel_4);

		TFPrecioHora = new JLabel("");
		TFPrecioHora.setBounds(184, 139, 85, 13);
		frmVisualizarReservas.getContentPane().add(TFPrecioHora);

		JLabel lblNewLabel_5 = new JLabel("YYYY-MM-DD");
		lblNewLabel_5.setBounds(306, 36, 119, 13);
		frmVisualizarReservas.getContentPane().add(lblNewLabel_5);

		LblAforo = new JLabel("");
		LblAforo.setBounds(70, 162, 45, 13);
		frmVisualizarReservas.getContentPane().add(LblAforo);

		lblFechaTabla = new JLabel("");
		lblFechaTabla.setBounds(10, 202, 171, 13);
		frmVisualizarReservas.getContentPane().add(lblFechaTabla);

		btnReservar = new JButton("Reservar");
		btnReservar.setBounds(356, 249, 126, 21);
		frmVisualizarReservas.getContentPane().add(btnReservar);
		
		JLabel lblNewLabel_2 = new JLabel("15 dias máximo tiempo de reserva");
		lblNewLabel_2.setBounds(356, 457, 192, 13);
		frmVisualizarReservas.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("30 dias máximo tiempo de visualización");
		lblNewLabel_2_1.setBounds(356, 478, 192, 13);
		frmVisualizarReservas.getContentPane().add(lblNewLabel_2_1);
	}
}
