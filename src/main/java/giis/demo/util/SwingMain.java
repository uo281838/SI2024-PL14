package giis.demo.util;

import java.awt.EventQueue;
import javax.swing.JFrame;

import controller.VisualizarReservasComoSocioController;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import giis.demo.tkrun.*;
import diego_Actividad.*;
import diego_periodoInscripcion.*;
import unai.lista_actividades.*;
import model.VisualizarReservasComoSocioModel;
import view.VisualizarReservasComoSocioView;

/**
 * Punto de entrada principal que incluye botones para la ejecucion de las pantallas 
 * de las aplicaciones de ejemplo
 * y acciones de inicializacion de la base de datos.
 * No sigue MVC pues es solamente temporal para que durante el desarrollo se tenga posibilidad
 * de realizar acciones de inicializacion
 */
public class SwingMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //NOSONAR codigo autogenerado
			public void run() {
				try {
					SwingMain window = new SwingMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); //NOSONAR codigo autogenerado
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Main");
		frame.setBounds(0, 0, 327, 324);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		JButton btnEjecutarTkrun = new JButton("Ejecutar giis.demo.tkrun");
		btnEjecutarTkrun.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				CarrerasController controller=new CarrerasController(new CarrerasModel(), new CarrerasView());
				controller.initController();
			}
		});
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.getContentPane().add(btnEjecutarTkrun);
		
			
		JButton btnInicializarBaseDeDatos = new JButton("Inicializar Base de Datos en Blanco");
		btnInicializarBaseDeDatos.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
			}
		});
		frame.getContentPane().add(btnInicializarBaseDeDatos);
			
		JButton btnCargarDatosIniciales = new JButton("Cargar Datos Iniciales para Pruebas");
		btnCargarDatosIniciales.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
				db.loadDatabase();
			}
		});
		frame.getContentPane().add(btnCargarDatosIniciales);
		
		JButton btnActividades = new JButton("Añadir actividades");
		btnActividades.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				ActividadController controller=new ActividadController(new ActividadModel(), new ActividadView());
				controller.initController();
			}
		});
		frame.getContentPane().add(btnActividades);
		
		JButton btnPeriodoInscripcion = new JButton("Añadir periodo de inscripcion");
		btnPeriodoInscripcion.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				PeriodoController controller=new PeriodoController(new PeriodoModel(), new PeriodoView());
				controller.initController();
			}
		});
		frame.getContentPane().add(btnPeriodoInscripcion);
		
		JButton btnVerListaActividades = new JButton("Ver lista de actividades");
		btnVerListaActividades.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				ListaActividadesController controller=new ListaActividadesController(new ListaActividadesModel(), new ListaActividadesView());
				controller.initController();
			}
		});
		frame.getContentPane().add(btnVerListaActividades);

		JButton btnVisualizarReservasComoSocio = new JButton("Ver reservas como socio");
		btnVisualizarReservasComoSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VisualizarReservasComoSocioController controller = new VisualizarReservasComoSocioController(new VisualizarReservasComoSocioModel(),new VisualizarReservasComoSocioView());
				controller.initController();
			}
		});
		frame.getContentPane().add(btnVisualizarReservasComoSocio);
		
		
		
		
		
		
		
		
		
		
		
		
>>>>>>> branch 'main' of https://github.com/uo281838/SI2024-PL14
	}

	public JFrame getFrame() { return this.frame; }
	
}
