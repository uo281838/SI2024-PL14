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
	
	
	
	public void reservarHora(int idSocio, String fecha, String instalacion, String horaInicio, String horaFin) {
	    String sql = "INSERT INTO RESERVA_INSTALACION (usuario_id, instalacion_id, fecha, hora_inicio, hora_fin, pagado) " +
	                 "VALUES (?, (SELECT id FROM INSTALACION WHERE nombre = ?), ?, ?, ?, FALSE)";
	    db.executeUpdate(sql, idSocio, instalacion, fecha, horaInicio, horaFin);
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

	
	/*
	public String getNombreSocio(String dni) {
		String nombre = null;
		String sql = "SELECT nombre FROM USUARIO WHERE dni = ?";

		List<Object[]> resultado = db.executeQueryArray(sql, dni);
		return ((String) resultado.get(0)[0]);

	}
	
	*/
	
	

	public List<Object[]> getReservarActividades(String fecha, String instalacion) {
		List<Object[]> resultado = new ArrayList<>();

		String sql = "SELECT a.hora_inicio, a.nombre " + "FROM ACTIVIDAD a "
				+ "JOIN INSTALACION i ON a.instalacion_id = i.id " + "WHERE i.nombre = ?\r\n"
				+ "AND ? BETWEEN a.fecha_inicio AND a.fecha_fin";

		resultado = db.executeQueryArray(sql, instalacion, fecha);

		

		return resultado;
	}
	
	
	
	public boolean esUsuarioMoroso(int idUsuario) {
	    // Consulta SQL para verificar si el usuario está marcado como moroso
	    String sql = "SELECT estado FROM USUARIO WHERE id = ?";
	    
	    List<Object[]> resultado = db.executeQueryArray(sql, idUsuario);
	    
	    if (resultado != null && !resultado.isEmpty()) {
	        // Suponemos que 'estado' es un campo de texto que puede tener valores como 'moroso' o 'activo'.
	        String estado = (String) resultado.get(0)[0];
	        
	        // Si el estado del usuario es 'moroso', retornamos true
	        return estado.equalsIgnoreCase("moroso");
	    }
	    
	    // Si no hay resultado o el usuario no tiene estado de moroso, retornamos false
	    return false;
	}


	
	
	
	public int obtenerHorasReservadas(int idSocio, String fecha) {

	    String sql = "SELECT hora_inicio, hora_fin FROM RESERVA_INSTALACION WHERE usuario_id = ? AND fecha = ?";
	    List<Object[]> resultado = db.executeQueryArray(sql, idSocio, fecha);

	    int horasReservadas = 0;

	 // Iteramos sobre las reservas para contar las horas
	    for (Object[] reserva : resultado) {
	        String horaInicio = (String) reserva[0];
	        String horaFin = (String) reserva[1];

	        // Convertimos las horas a formato decimal
	        double horaInicioDecimal = convertirHoraAEntero(horaInicio);
	        double horaFinDecimal = convertirHoraAEntero(horaFin);

	        // Calculamos las horas reservadas sumando la diferencia entre hora de fin y hora de inicio
	        horasReservadas += (horaFinDecimal - horaInicioDecimal);
	    }


	    return horasReservadas;
	}

	// Función para convertir la hora a formato entero (hora de 24 horas como número entero)
	private double convertirHoraAEntero(String hora) {
	    String[] partes = hora.split(":");
	    int horaInt = Integer.parseInt(partes[0]);
	    int minutos = Integer.parseInt(partes[1]);

	    // Convertimos los minutos a una fracción de hora (por ejemplo: 30 minutos = 0.5 horas)
	    return horaInt + (minutos / 60.0);
	}

	
	public void registrarPagoAhora(int idSocio, Double monto) {
	    String sql = "INSERT INTO PAGOS (usuario_id, monto, fecha_pago, tipo_pago) VALUES (?, ?, NOW(), 'PAGO_INMEDIATO')";
	    db.executeUpdate(sql, idSocio, monto);
	}

	public void registrarPagoFinalMes(int idSocio, Double monto) {
	    String sql = "INSERT INTO PAGOS (usuario_id, monto, fecha_pago, tipo_pago) VALUES (?, ?, NOW(), 'PAGO_AL_FINAL_DEL_MES')";
	    db.executeUpdate(sql, idSocio, monto);
	}

	
	
	

}
