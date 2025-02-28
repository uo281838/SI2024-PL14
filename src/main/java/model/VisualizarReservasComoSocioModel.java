package model;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class VisualizarReservasComoSocioModel {

	private Database db = new Database();

	public List<Object[]> getReservarInstalaciones(String fechaInscripcion, String Instalacion) {

		String sql = "SELECT RESERVA_INSTALACION.hora_inicio, " + "RESERVA_INSTALACION.hora_fin, "
				+ "INSTALACION.nombre, "
				+ "CASE WHEN RESERVA_INSTALACION.id IS NOT NULL THEN 'Reservada' ELSE 'Disponible' END AS estado_reserva, "
				+ "USUARIO.dni AS dni_socio " + "FROM INSTALACION "
				+ "LEFT JOIN RESERVA_INSTALACION ON INSTALACION.id = RESERVA_INSTALACION.instalacion_id "
				+ "AND RESERVA_INSTALACION.fecha = ? "
				+ "LEFT JOIN USUARIO ON RESERVA_INSTALACION.usuario_id = USUARIO.id " + "WHERE INSTALACION.nombre = ? "
				+ "ORDER BY RESERVA_INSTALACION.hora_inicio";

		return db.executeQueryArray(sql, fechaInscripcion, Instalacion);

	}

	public List<Object[]> getNombreInstalaciones() {
		String sql = "SELECT nombre FROM INSTALACION";
		return db.executeQueryArray(sql, null);

	}

	public boolean existeDNI(String dni) {
		String sql = "SELECT COUNT(*) FROM USUARIO WHERE dni = ?";
		List<Object[]> lista = db.executeQueryArray(sql, dni);
		int count = Integer.parseInt(lista.get(0)[0].toString());
		return count > 0;

	}

	public Integer getAforoInstalacion(String nombreInstalacion) {
		String sql = "SELECT aforo_maximo FROM INSTALACION WHERE nombre = ?";
		List<Object[]> result = db.executeQueryArray(sql, new Object[] { nombreInstalacion });

		if (result != null && !result.isEmpty()) {
			return (Integer) result.get(0)[0];
		}

		return null;
	}

	public Double getPrecioHoraInstalacion(String nombreInstalacion) {

		String sql = "SELECT precio_hora " + "FROM INSTALACION " + "WHERE nombre = ? ";

		List<Object[]> resultado = db.executeQueryArray(sql, nombreInstalacion);

		return ((Number) resultado.get(0)[0]).doubleValue();

	}

	public String getNombreSocio(String dni) {
		String nombre = null;
		String sql = "SELECT nombre FROM USUARIO WHERE dni = ?";

		List<Object[]> resultado = db.executeQueryArray(sql, dni);
		return ((String) resultado.get(0)[0]);

	}

	public List<Object[]> getReservarActividades(String fecha, String instalacion) {
		List<Object[]> resultado = new ArrayList<>();

		String sql = "SELECT a.hora_inicio, a.nombre " + "FROM ACTIVIDAD a "
				+ "JOIN INSTALACION i ON a.instalacion_id = i.id " + "WHERE i.nombre = ?\r\n"
				+ "AND ? BETWEEN a.fecha_inicio AND a.fecha_fin";

		resultado = db.executeQueryArray(sql, instalacion, fecha);

		

		return resultado;
	}

}
