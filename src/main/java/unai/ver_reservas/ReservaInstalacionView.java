package unai.ver_reservas;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.util.Date;

import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Font;

/**
 * Vista de la pantalla que muestra las carreras activas y permite interactuar con ellas.
 * <br/>Se ha generado con WindowBulder y modificado para ser conforme a MVC teniendo en cuenta:
 * - Se elimina main (es invocada desde CarrerasMain) y se incluye Title en el frame
 * - No se incluye ningun handler de eventos pues estos van en el controlador
 * - Las tablas se encierran en JOptionPane para que se puedan visualizar las cabeceras
 * - Se asinga nombre a las tablas si se van a automatizar la ejecucion de pruebas
 * - Incluye al final los metodos adicionales necesarios para acceder al UI desde el controlador
 */
public class ReservaInstalacionView {

	private JFrame frame;
	private JTable tabHorario;
	private JComboBox<Object> cbInstalacion;

	/**
	 * Create the application.
	 */
	public ReservaInstalacionView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Carreras");
		frame.setName("Carreras");
		frame.setBounds(0, 0, 1230, 510);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(10, 11, 1194, 733);
		frame.getContentPane().add(contentPane);
		
		cbInstalacion = new JComboBox<>();
		cbInstalacion.setBounds(138, 48, 194, 22);
		contentPane.add(cbInstalacion);
		
		JLabel lblInstalacion = new JLabel("Instalacion:");
		lblInstalacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstalacion.setBounds(20, 52, 108, 14);
		contentPane.add(lblInstalacion);
		
		JScrollPane tablePanel = new JScrollPane(tabHorario);
		tablePanel.setBounds(20, 164, 1142, 261); // Ajusta el tamaño como desees
		contentPane.add(tablePanel);

		
		// Establecer la política de desplazamiento
		tablePanel.setPreferredSize(new Dimension(1142, 499)); // Tamaño preferido
		tablePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tablePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		tabHorario = new JTable();
		tabHorario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabHorario.setFillsViewportHeight(true);  // Hace que la tabla llene el área visible
		tablePanel.setViewportView(tabHorario);
		
		JLabel lblSeleccionaFechasPara = new JLabel("Selecciona una instalación para mostrar sus reservas");
		lblSeleccionaFechasPara.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSeleccionaFechasPara.setBounds(20, 11, 602, 26);
		contentPane.add(lblSeleccionaFechasPara);
	}

	//Getters y Setters anyadidos para acceso desde el controlador (repersentacion compacta)
	public JFrame getFrame() { return this.frame; }
	public JTable getTabHorario() { return this.tabHorario; }


	public JComboBox<Object> getCbInstalacion() { return this.cbInstalacion; }
}
