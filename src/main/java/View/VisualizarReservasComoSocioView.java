package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VisualizarReservasComoSocioView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable TablaReservas;
	private JTextField TFDescripcion;
	private JTextField textField_3;
	private JLabel lblNewLabel_2;
	private JTextField TFPropietarioReserva;
	private JLabel lblNewLabel_3;
	private JTextField TFAforo;
	private JSeparator separator_1;

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
	

	/**
	 * Create the application.
	 */
	public VisualizarReservasComoSocioView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 623, 517);
		frame.getContentPane().setLayout(null);
		
		JLabel EtiquetaIntroduceId = new JLabel("Introduce tu id de socio:");
		EtiquetaIntroduceId.setBounds(10, 10, 164, 13);
		frame.getContentPane().add(EtiquetaIntroduceId);
		
		textField = new JTextField();
		textField.setBounds(10, 33, 141, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Instalación");
		lblNewLabel.setBounds(184, 10, 85, 13);
		frame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(184, 33, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 162, 252, 280);
		frame.getContentPane().add(scrollPane);
		
		TablaReservas = new JTable();
		scrollPane.setViewportView(TablaReservas);
		
		JLabel lblSeleccioneUnDia = new JLabel("Seleccione un dia:");
		lblSeleccioneUnDia.setBounds(323, 10, 105, 13);
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
		
		textField_3 = new JTextField();
		textField_3.setBounds(323, 33, 96, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Reserva de:");
		lblNewLabel_2.setBounds(356, 222, 74, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		TFPropietarioReserva = new JTextField();
		TFPropietarioReserva.setBounds(356, 246, 74, 19);
		frame.getContentPane().add(TFPropietarioReserva);
		TFPropietarioReserva.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Aforo:");
		lblNewLabel_3.setBounds(462, 222, 45, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		TFAforo = new JTextField();
		TFAforo.setBounds(461, 246, 74, 19);
		frame.getContentPane().add(TFAforo);
		TFAforo.setColumns(10);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(306, 115, 19, 327);
		frame.getContentPane().add(separator_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 72, 85, 21);
		frame.getContentPane().add(btnBuscar);
	}
}
