package unai.lista_actividades;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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

/**
 * Vista de la pantalla que muestra las carreras activas y permite interactuar con ellas.
 * <br/>Se ha generado con WindowBulder y modificado para ser conforme a MVC teniendo en cuenta:
 * - Se elimina main (es invocada desde CarrerasMain) y se incluye Title en el frame
 * - No se incluye ningun handler de eventos pues estos van en el controlador
 * - Las tablas se encierran en JOptionPane para que se puedan visualizar las cabeceras
 * - Se asinga nombre a las tablas si se van a automatizar la ejecucion de pruebas
 * - Incluye al final los metodos adicionales necesarios para acceder al UI desde el controlador
 */
public class ListaActividadesView {

	private JFrame frame;
	private JTable tabActividades;
	private JDateChooser fecha1;
	private JDateChooser fecha2;
	private JComboBox<Object> cbPeriodo;

	/**
	 * Create the application.
	 */
	public ListaActividadesView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Carreras");
		frame.setName("Carreras");
		frame.setBounds(0, 0, 732, 540);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(20, 11, 622, 487);
		frame.getContentPane().add(contentPane);
		
		JComboBox<Object> cbPeriodo = new JComboBox();
		cbPeriodo.setBounds(87, 11, 103, 22);
		contentPane.add(cbPeriodo);
		
		JLabel lblPeriodo = new JLabel("Periodo:");
		lblPeriodo.setBounds(20, 19, 67, 14);
		contentPane.add(lblPeriodo);
		
		JDateChooser dcInicio = new JDateChooser();
		dcInicio.setBounds(87, 44, 103, 20);
		contentPane.add(dcInicio);
		
		JDateChooser dcFin = new JDateChooser();
		dcFin.setBounds(87, 75, 103, 20);
		contentPane.add(dcFin);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio:");
		lblFechaInicio.setBounds(20, 50, 67, 14);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha fin:");
		lblFechaFin.setBounds(20, 81, 67, 14);
		contentPane.add(lblFechaFin);
		
		JScrollPane tablePanel = new JScrollPane((Component) null);
		tablePanel.setBounds(30, 106, 521, 312);
		contentPane.add(tablePanel);
		
		tabActividades = new JTable();
		tablePanel.setViewportView(tabActividades);
	}

	//Getters y Setters anyadidos para acceso desde el controlador (repersentacion compacta)
	public JFrame getFrame() { return this.frame; }
	public JTable getTablaActividades() { return this.tabActividades; }
	public JDateChooser fechaInicio() { return this.fecha1;}
	public JDateChooser fechaFin() {return this.fecha2;}
	public void setFechaInicio(Date fecha) { this.fecha1.setDate(fecha);}
	public void setFechaFin(Date fecha) { this.fecha2.setDate(fecha);}

	public JComboBox<Object> getListaPeriodo() { return this.cbPeriodo; }
}
