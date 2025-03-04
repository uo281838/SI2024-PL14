package model;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		
		public boolean insertarReservaInstalacion(int usuario_id, int instalacion_id, String fecha, String hora_inicio, String hora_fin) {
		    String sql = "INSERT INTO RESERVA_INSTALACION (usuario_id, instalacion_id, fecha, hora_inicio, hora_fin, pagado) " +
		                 "VALUES (?, ?, ?, ?, ?, ?)";

		    // Conexión a la base de datos
		    try (Connection conn = db.getConnection()) { // Asegúrate de que `db.getConnection()` obtenga una conexión válida
		        // Crear el PreparedStatement
		        try (PreparedStatement ps = conn.prepareStatement(sql)) {
		            // Establecer los valores en el PreparedStatement
		            ps.setInt(1, usuario_id);
		            ps.setInt(2, instalacion_id);
		            ps.setString(3, fecha);
		            ps.setString(4, hora_inicio);
		            ps.setString(5, hora_fin);
		            ps.setBoolean(6, true); // 'true' para pagado

		            // Ejecutar la consulta de inserción
		            int result = ps.executeUpdate();  // Ejecuta la actualización

		            // Verificar si la inserción fue exitosa
		            if (result > 0) {
		                System.out.println("Reserva insertada correctamente.");
		                return true;  // Inserción exitosa
		            } else {
		                System.out.println("Error al insertar la reserva.");
		                return false;  // Error al insertar
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		            return false;  // Si ocurre un error en el PreparedStatement
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;  // Si ocurre un error en la conexión
		    }
		}

		// Método para obtener todas las reservas de la base de datos
		public List<Object[]> getReservas() {
		    String sql = "SELECT * FROM RESERVA_INSTALACION";  // Consulta para obtener todas las reservas
		    List<Object[]> reservas = db.executeQueryArray(sql, null);
		    
		    if (reservas == null || reservas.isEmpty()) {
		        System.out.println("No se encontraron reservas.");
		    } else {
		        for (Object[] row : reservas) {
		            // Puedes imprimir los resultados aquí si quieres, solo para depuración
		            System.out.println("Reserva: Usuario " + row[0] + ", Instalación " + row[1] + ", Fecha " + row[2]);
		        }
		    }
		    return reservas;
		}

	
}
