package Model;

import java.util.List;

import giis.demo.util.Database;

public class VisualizarReservasComoSocioModel {
	
	private Database db = new Database();
	
	public List<Object []> getReservarInstalaciones(String fechaInscripcion , String Instalacion ){
		
		String slq = "SELECT RESERVA_INSTALACION.fecha, RESERVA_INSTALACION.hora_inicio,"
				+ " RESERVA_INSTALACION.hora_fin, INSTALACION.nombre, CASE WHEN RESERVA_INSTALACION.id "
				+ "IS NOT NULL THEN 'Reservada' ELSE 'Disponible' END AS estado_reserva FROM INSTALACION "
				+ "LEFT JOIN RESERVA_INSTALACION ON INSTALACION.id = RESERVA_INSTALACION.instalacion_id AND "
				+ "RESERVA_INSTALACION.fecha = ? WHERE INSTALACION.nombre = ? ORDER BY "
				+ "RESERVA_INSTALACION.hora_inicio";
		
		return db.executeQueryArray(slq, fechaInscripcion,Instalacion);

	}

}
