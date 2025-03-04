package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.ReservarInstalacionParaSocioComoAdminController;

import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReservarInstalacionParaSocioComoAdminView {

	private JFrame frame;
	private JTextField TFDia;
	private JButton BConfirmar;
	private JComboBox<Integer> CBInstalacion;
	private JTextField TFEstado;
	private JTextField TFOcupado;
	private JTextField TFNumeroSocio;
	private JTextField TFHoraInicio;
	private JTextField TFHoraFin;
	private JTextField TFNombre;
	private JButton BDescartar;
	private JButton BGenerar;
	private JButton BBuscar;
	private JButton BComprobar;
	private JTextArea TAPago;
	private JCheckBox CBPagado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservarInstalacionParaSocioComoAdminView window = new ReservarInstalacionParaSocioComoAdminView();
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
	public ReservarInstalacionParaSocioComoAdminView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 723, 591);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccionar dia:");
		lblNewLabel.setBounds(27, 21, 106, 14);
		frame.getContentPane().add(lblNewLabel);
		
		TFDia = new JTextField();
		TFDia.setBounds(27, 46, 106, 20);
		frame.getContentPane().add(TFDia);
		TFDia.setColumns(10);
		
		BConfirmar = new JButton("Confirmar");
		BConfirmar.setBounds(595, 413, 91, 23);
		frame.getContentPane().add(BConfirmar);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccionar instalacion:");
		lblNewLabel_1.setBounds(27, 85, 138, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		CBInstalacion = new JComboBox<Integer>();
		CBInstalacion.setToolTipText("");
		CBInstalacion.setBounds(27, 110, 106, 22);
		frame.getContentPane().add(CBInstalacion);
		
		JLabel lblNewLabel_2 = new JLabel("Estado de la instalacion:");
		lblNewLabel_2.setBounds(298, 85, 190, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(280, 24, 8, 172);
		frame.getContentPane().add(separator);
		
		TFEstado = new JTextField();
		TFEstado.setBounds(298, 111, 190, 20);
		frame.getContentPane().add(TFEstado);
		TFEstado.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ocupada por:");
		lblNewLabel_3.setBounds(498, 85, 123, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		TFOcupado = new JTextField();
		TFOcupado.setBounds(499, 111, 106, 20);
		frame.getContentPane().add(TFOcupado);
		TFOcupado.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 196, 608, 13);
		frame.getContentPane().add(separator_1);
		
		JLabel lblNewLabel_4 = new JLabel("DNI del socio que realiza la reserva:");
		lblNewLabel_4.setBounds(27, 213, 243, 20);
		frame.getContentPane().add(lblNewLabel_4);
		
		TFNumeroSocio = new JTextField();
		TFNumeroSocio.setBounds(27, 244, 243, 20);
		frame.getContentPane().add(TFNumeroSocio);
		TFNumeroSocio.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Hora inicio:\r\n");
		lblNewLabel_5.setBounds(147, 21, 106, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Hora fin:");
		lblNewLabel_6.setBounds(175, 85, 74, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		TFHoraInicio = new JTextField();
		TFHoraInicio.setBounds(143, 46, 106, 20);
		frame.getContentPane().add(TFHoraInicio);
		TFHoraInicio.setColumns(10);
		
		TFHoraFin = new JTextField();
		TFHoraFin.setBounds(179, 111, 74, 20);
		frame.getContentPane().add(TFHoraFin);
		TFHoraFin.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Nombre y Apellidos: ");
		lblNewLabel_7.setBounds(298, 220, 195, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		TFNombre = new JTextField();
		TFNombre.setBounds(298, 244, 190, 20);
		frame.getContentPane().add(TFNombre);
		TFNombre.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(27, 292, 515, 13);
		frame.getContentPane().add(separator_2);
		
		JLabel lblNewLabel_8 = new JLabel("Método de pago:");
		lblNewLabel_8.setBounds(27, 316, 138, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		BDescartar = new JButton("Descartar\r\n");
		BDescartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		BDescartar.setBounds(483, 413, 102, 23);
		frame.getContentPane().add(BDescartar);
		
		BGenerar = new JButton("Generar resguardo\r\n");
		BGenerar.setBounds(232, 413, 241, 23);
		frame.getContentPane().add(BGenerar);
		
		BBuscar = new JButton("Buscar\r\n");
		BBuscar.setBounds(84, 159, 91, 23);
		frame.getContentPane().add(BBuscar);
		
		BComprobar = new JButton("Comprobar");
		BComprobar.setBounds(530, 243, 130, 23);
		frame.getContentPane().add(BComprobar);
		
		JLabel lblNewLabel_9 = new JLabel("Introducir datos de pago:");
		lblNewLabel_9.setBounds(27, 341, 181, 14);
		frame.getContentPane().add(lblNewLabel_9);
		
		TAPago = new JTextArea();
		TAPago.setBounds(189, 316, 432, 39);
		frame.getContentPane().add(TAPago);
		
		CBPagado = new JCheckBox("¿Pagado?");
		CBPagado.setBounds(27, 367, 101, 23);
		frame.getContentPane().add(CBPagado);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTFDia() {
		return TFDia;
	}

	public void setTFDia(JTextField tFDia) {
		TFDia = tFDia;
	}

	public JButton getBConfirmar() {
		return BConfirmar;
	}

	public void setBConfirmar(JButton bConfirmar) {
		BConfirmar = bConfirmar;
	}

	public JComboBox<Integer> getCBInstalacion() {
		return CBInstalacion;
	}

	public void setCBInstalacion(JComboBox<Integer> cBInstalacion) {
		CBInstalacion = cBInstalacion;
	}
	
	public void setCBInstalacionModel(DefaultComboBoxModel cBInstalacion) {
		CBInstalacion.setModel(cBInstalacion);
	}

	public JTextField getTFEstado() {
		return TFEstado;
	}

	public void setTFEstado(JTextField tFEstado) {
		TFEstado = tFEstado;
		TFEstado.setEditable(false);
	}

	public JTextField getTFOcupado() {
		return TFOcupado;
	}

	public void setTFOcupado(JTextField tFOcupado) {
		TFOcupado = tFOcupado;
		TFOcupado.setEditable(false);
	}

	public JTextField getTFNumeroSocio() {
		return TFNumeroSocio;
	}

	public void setTFNumeroSocio(JTextField tFNumeroSocio) {
		TFNumeroSocio = tFNumeroSocio;
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

	public JTextField getTFNombre() {
		return TFNombre;
	}

	public void setTFNombre(JTextField tFNombre) {
		TFNombre = tFNombre;
	}

	public JButton getBDescartar() {
		return BDescartar;
	}

	public void setBDescartar(JButton bDescartar) {
		BDescartar = bDescartar;
	}

	public JButton getBGenerar() {
		return BGenerar;
	}

	public void setBGenerar(JButton bGenerar) {
		BGenerar = bGenerar;
	}

	public JButton getBBuscar() {
		return BBuscar;
	}

	public void setBBuscar(JButton bBuscar) {
		BBuscar = bBuscar;
	}

	public JButton getBComprobar() {
		return BComprobar;
	}

	public void setBComprobar(JButton bComprobar) {
		BComprobar = bComprobar;
	}

	public JTextArea getTAPago() {
		return TAPago;
	}

	public void setTAPago(JTextArea tAPago) {
		TAPago = tAPago;
	}

	public JCheckBox getCBPagado() {
		return CBPagado;
	}

	public void setCBPagado(JCheckBox cBPagado) {
		CBPagado = cBPagado;
	}


	
	
}

