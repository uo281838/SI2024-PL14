package model;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.util.Database;

public class ReservarInstalacionParaActividadComoAdminModel {

	private Database db = new Database();
	
	// Metodo que coge el las instalaciones
	public List<Object[]> getInstalaciones() {
	    String sql = "SELECT nombre FROM INSTALACION";
	    List<Object[]> ins = db.executeQueryArray(sql, null);
	    
	    if (ins == null || ins.isEmpty()) {
	        System.out.println("No se encontraron instalaciones.");
	    } else {
	        for (Object[] row : ins) {
	            System.out.println(row[0]);  // Accede al primer elemento del array (el nombre de la instalación)
	        }
	    }

	    return ins;
	}
	
	// Metodo que coge el las actividades
		public List<Object[]> getActividades() {
		    String sql = "SELECT nombre FROM ACTIVIDAD";
		    List<Object[]> act = db.executeQueryArray(sql, null);
		    
		    if (act == null || act.isEmpty()) {
		        System.out.println("No se encontraron actividades.");
		    } else {
		        for (Object[] row : act) {
		            System.out.println(row[0]);  // Accede al primer elemento del array (el nombre de la instalación)
		        }
		    }

		    return act;
		}
		
		// Metodo que coge el las ids de las instalaciones
		public List<Object[]> getidInstalacion() {
			String sql = "SELECT id FROM INSTALACION";
			List<Object[]> act = db.executeQueryArray(sql, null);
				    
				if (act == null || act.isEmpty()) {
					System.out.println("No se encontraron instalaciones.");
				} else {
					for (Object[] row : act) {
						System.out.println(row[0]);  // Accede al primer elemento del array (el nombre de la instalación)
				    }
				}

				return act;
		}
		
		public boolean insertarReserva(int usuarioId, int instalacionId, String fecha, String horaInicio, String horaFin, boolean pagado) {
		    // Primero, verificar si hay reservas en el horario solicitado
		    List<Object[]> reservas = getReservasInstalacion(fecha, horaInicio, horaFin, instalacionId);
		    
		    if (!reservas.isEmpty()) {
		        
		    	
		    	// Si hay reservas, no podemos insertar la nueva reserva (ya está ocupada)
		        return false;
		    }

		    // Si la instalación está libre, proceder a insertar la nueva reserva
		    String sql = "INSERT INTO RESERVA_INSTALACION (usuario_id, instalacion_id, fecha, hora_inicio, hora_fin, pagado) "
		               + "VALUES (?, ?, ?, ?, ?, ?)";
		    db.executeUpdate(sql, usuarioId, instalacionId, fecha, horaInicio, horaFin, pagado);
		    
		    return true;  // Reserva insertada correctamente
		}

		// Método para obtener todas las reservas de la base de datos
		public List<Object[]> getReservas() {
		    String sql = "SELECT RI.id AS reserva_id, "
		               + "U.nombre AS usuario, "
		               + "I.nombre AS instalacion, "
		               + "RI.fecha, "
		               + "RI.hora_inicio, "
		               + "RI.hora_fin, "
		               + "RI.pagado "
		               + "FROM RESERVA_INSTALACION RI "
		               + "JOIN USUARIO U ON RI.usuario_id = U.id "
		               + "JOIN INSTALACION I ON RI.instalacion_id = I.id";
		    
		    return db.executeQueryArray(sql);
		}



		public int getInstalacionId(String instalacionNombre) {
		    // Consultar la base de datos para obtener el id de la instalación basado en el nombre
		    String sql = "SELECT id FROM INSTALACION WHERE nombre = ?";
		    List<Object[]> resultados = db.executeQueryArray(sql, instalacionNombre);
		    
		    if (!resultados.isEmpty()) {
		        return (int) resultados.get(0)[0];  // Retorna el id de la instalación
		    }
		    
		    return -1;  // Si no se encuentra la instalación
		}

		public List<Object[]> getReservasInstalacion(String fecha, String horaInicio, String horaFin, int instalacionId) {
		    String sql = "SELECT RI.id AS reserva_id, "
		               + "U.nombre AS usuario, "
		               + "I.nombre AS instalacion, "
		               + "RI.fecha, "
		               + "RI.hora_inicio, "
		               + "RI.hora_fin "
		               + "FROM RESERVA_INSTALACION RI "
		               + "JOIN USUARIO U ON RI.usuario_id = U.id "
		               + "JOIN INSTALACION I ON RI.instalacion_id = I.id "
		               + "WHERE RI.instalacion_id = ? "
		               + "AND RI.fecha = ? "
		               + "AND ((? < RI.hora_fin AND ? > RI.hora_inicio))"; // Verifica solapamientos reales
		    
		    return db.executeQueryArray(sql, instalacionId, fecha, horaInicio, horaFin);
		}



	
}
