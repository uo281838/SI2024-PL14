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

public class VisualizarReservasComoSocioView {

	private JFrame frame;
	private JTextField TFDni;
	private JTable TablaReservas;
	private JTextField TFDescripcion;
	private JLabel lblNewLabel_3;
	private JTextField TFAforo;
	private JSeparator separator_1;
	private JComboBox CBInstalaciones;
	private JButton btnBuscar;
	private JFormattedTextField FTFFecha;
	private JLabel TFPrecioHora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarReservasComoSocioView window = new VisualizarReservasComoSocioView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
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
	
	
	
	
	
	
	
	
	


	public JTextField getTFDni() {
		return TFDni;
	}


	public void setTFDni(JTextField tFDni) {
		TFDni = tFDni;
	}


	/**
	 * Create the application.
	 */
	public VisualizarReservasComoSocioView() {
		initialize();
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
		frame = new JFrame();
		frame.setBounds(100, 100, 623, 517);
		frame.getContentPane().setLayout(null);
		
		JLabel EtiquetaIntroduceId = new JLabel("Introduce tu DNI:");
		EtiquetaIntroduceId.setBounds(10, 10, 141, 13);
		frame.getContentPane().add(EtiquetaIntroduceId);
		
		TFDni = new JTextField();
		TFDni.setBounds(10, 33, 141, 19);
		frame.getContentPane().add(TFDni);
		TFDni.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Instalación");
		lblNewLabel.setBounds(184, 10, 85, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 162, 279, 280);
		frame.getContentPane().add(scrollPane);
		
		TablaReservas = new JTable();
		scrollPane.setViewportView(TablaReservas);
		
		JLabel lblSeleccioneUnDia = new JLabel("Seleccione un dia:");
		lblSeleccioneUnDia.setBounds(348, 10, 105, 13);
		frame.getContentPane().add(lblSeleccioneUnDia);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción");
		lblNewLabel_1.setBounds(356, 152, 85, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 113, 609, 2);
		frame.getContentPane().add(separator);
		
		TFDescripcion = new JTextField();
		TFDescripcion.setBounds(356, 175, 179, 37);
		frame.getContentPane().add(TFDescripcion);
		TFDescripcion.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Aforo:");
		lblNewLabel_3.setBounds(356, 227, 45, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		TFAforo = new JTextField();
		TFAforo.setBounds(356, 250, 74, 19);
		frame.getContentPane().add(TFAforo);
		TFAforo.setColumns(10);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(306, 115, 19, 327);
		frame.getContentPane().add(separator_1);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 72, 85, 21);
		frame.getContentPane().add(btnBuscar);
		
		CBInstalaciones = new JComboBox();
		CBInstalaciones.setBounds(184, 33, 126, 19);
		frame.getContentPane().add(CBInstalaciones);
		
		FTFFecha = new JFormattedTextField();

		
		FTFFecha.setToolTipText("");
		FTFFecha.setBounds(348, 33, 96, 19);
		frame.getContentPane().add(FTFFecha);
		
		JLabel lblNewLabel_4 = new JLabel("€/hora de la instalación:");
		lblNewLabel_4.setBounds(17, 139, 156, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		TFPrecioHora = new JLabel("");
		TFPrecioHora.setBounds(184, 139, 85, 13);
		frame.getContentPane().add(TFPrecioHora);
		
		JLabel lblNewLabel_5 = new JLabel("YYYY-MM-DD");
		lblNewLabel_5.setBounds(462, 36, 119, 13);
		frame.getContentPane().add(lblNewLabel_5);
	}


	
}
