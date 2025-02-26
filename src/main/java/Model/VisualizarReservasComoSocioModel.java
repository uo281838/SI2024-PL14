package Model;

import java.util.List;

import giis.demo.util.Database;

public class VisualizarReservasComoSocioModel {
	
	private Database db = new Database();
	
	public List<Object []> getReservarInstalaciones(String fechaInscripcion , String Instalacion ){
		
		String sql = "SELECT RESERVA_INSTALACION.hora_inicio,"
				+ " RESERVA_INSTALACION.hora_fin, INSTALACION.nombre, CASE WHEN RESERVA_INSTALACION.id "
				+ "IS NOT NULL THEN 'Reservada' ELSE 'Disponible' END AS estado_reserva FROM INSTALACION "
				+ "LEFT JOIN RESERVA_INSTALACION ON INSTALACION.id = RESERVA_INSTALACION.instalacion_id AND "
				+ "RESERVA_INSTALACION.fecha = ? WHERE INSTALACION.nombre = ? ORDER BY "
				+ "RESERVA_INSTALACION.hora_inicio";
		
		return db.executeQueryArray(sql, fechaInscripcion,Instalacion);

	}
	
	public List<Object[]> getNombreInstalaciones(){
		String sql = "SELECT nombre FROM INSTALACION";
		return db.executeQueryArray(sql, null);
		
	}
	
	public boolean existeDNI(String dni) {
		String sql = "SELECT COUNT(*) FROM USUARIO WHERE dni = ?";
		List<Object []> lista = db.executeQueryArray(sql, dni);
		int count = Integer.parseInt(lista.get(0)[0].toString()); // Convertir a entero
        return count > 0; // Retorna true si el usuario existe			
		
				
	}

}
