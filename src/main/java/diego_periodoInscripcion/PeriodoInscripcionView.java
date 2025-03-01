package diego_periodoInscripcion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class PeriodoInscripcionView extends JFrame {
	
	// 
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// Variables
	private JTextField textoNombre;
	private JTextField textoDescripcion;
	private JDateChooser textoFechaInicial, textoFechaFinalNoSocios, textoFechaFinal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PeriodoInscripcionView frame = new PeriodoInscripcionView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PeriodoInscripcionView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("A continuación podrá crear períodos de inscripción para asignar a actividades:\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 41, 561, 14);
		contentPane.add(lblNewLabel);
		
		JLabel etiquetaTitulo = new JLabel("Período de Inscripción");
		etiquetaTitulo.setBounds(195, 11, 168, 19);
		etiquetaTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		etiquetaTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(etiquetaTitulo);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(62, 78, 54, 14);
		contentPane.add(lblNewLabel_1);
		
		textoNombre = new JTextField();
		textoNombre.setBounds(164, 72, 141, 20);
		contentPane.add(textoNombre);
		textoNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Descripción:");
		lblNewLabel_2.setBounds(62, 116, 98, 14);
		contentPane.add(lblNewLabel_2);
		
		textoDescripcion = new JTextField();
		textoDescripcion.setBounds(164, 110, 372, 20);
		contentPane.add(textoDescripcion);
		textoDescripcion.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("F. Inicio:");
		lblNewLabel_3.setBounds(62, 154, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("F. Final:");
		lblNewLabel_4.setBounds(62, 197, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("F. Final No Socios:");
		lblNewLabel_5.setBounds(62, 247, 88, 14);
		contentPane.add(lblNewLabel_5);
		
		// Boton Creacion Periodo
		JButton botonCrear = new JButton("Crear Período");
		botonCrear.addActionListener(e -> crearPeriodo());
		
		botonCrear.setBounds(226, 281, 122, 23);
		contentPane.add(botonCrear);
		
		textoFechaInicial = new JDateChooser();
		textoFechaInicial.setBounds(164, 148, 141, 20);
		contentPane.add(textoFechaInicial);
		
		textoFechaFinalNoSocios = new JDateChooser();
		textoFechaFinalNoSocios.setBounds(164, 241, 141, 20);
		contentPane.add(textoFechaFinalNoSocios);
		
		textoFechaFinal = new JDateChooser();
		textoFechaFinal.setBounds(164, 191, 141, 20);
		contentPane.add(textoFechaFinal);
	}
	
	// Método Creación Período
	private void crearPeriodo() {
		String nombre = textoNombre.getText();
        String descripcion = textoDescripcion.getText();
        Date fechaInicio = textoFechaInicial.getDate();
        Date fechaFinalSocios = textoFechaFinal.getDate();
        Date fechaFinalNoSocios = textoFechaFinalNoSocios.getDate();

        if (fechaInicio == null || fechaFinalSocios == null || fechaFinalNoSocios == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar todas las fechas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Período creado:\n" +
                "Nombre: " + nombre + "\n" +
                "Descripción: " + descripcion + "\n" +
                "Inicio: " + fechaInicio + "\n" +
                "Fin Socios: " + fechaFinalSocios + "\n" +
                "Fin No Socios: " + fechaFinalNoSocios, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}

	
	// Getters y Setters para acceder desde el controlador
	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getTextoNombre() {
		return textoNombre;
	}

	public void setTextoNombre(JTextField textoNombre) {
		this.textoNombre = textoNombre;
	}

	public JTextField getTextoDescripcion() {
		return textoDescripcion;
	}

	public void setTextoDescripcion(JTextField textoDescripcion) {
		this.textoDescripcion = textoDescripcion;
	}

	public JDateChooser getTextoFechaInicial() {
		return textoFechaInicial;
	}

	public void setTextoFechaInicial(JDateChooser textoFechaInicial) {
		this.textoFechaInicial = textoFechaInicial;
	}

	public JDateChooser getTextoFechaFinalNoSocios() {
		return textoFechaFinalNoSocios;
	}

	public void setTextoFechaFinalNoSocios(JDateChooser textoFechaFinalNoSocios) {
		this.textoFechaFinalNoSocios = textoFechaFinalNoSocios;
	}

	public JDateChooser getTextoFechaFinal() {
		return textoFechaFinal;
	}

	public void setTextoFechaFinal(JDateChooser textoFechaFinal) {
		this.textoFechaFinal = textoFechaFinal;
	}
}
