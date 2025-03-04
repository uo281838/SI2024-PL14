package model;

import java.util.List;

import giis.demo.util.Database;

public class ReservarInstalacionParaSocioComoAdminModel {
	
	private Database db = new Database();
	
	public ReservarInstalacionParaSocioComoAdminModel () {
		
	}

	public List<Object[]> getInstalaciones() {
		String sql = "SELECT nombre FROM INSTALACION";
		List<Object[]> ins = db.executeQueryArray(sql, null);
		System.out.println(ins);
		return ins;
	}

	public List<Object[]> getReservarInstalaciones(String fecha, String horainicio, String horafin, String instalacion) {
	    String sql = "SELECT RI.id AS reserva_id, U.nombre AS usuario, RI.fecha, RI.hora_inicio, RI.hora_fin "
	               + "FROM RESERVA_INSTALACION RI "
	               + "JOIN USUARIO U ON RI.usuario_id = U.id "
	               + "WHERE RI.instalacion_id = ? "
	               + "AND RI.fecha = ? "
	               + "AND ((RI.hora_inicio <= ? AND RI.hora_fin > ?) "
	               + "OR (RI.hora_inicio < ? AND RI.hora_fin >= ?))";

	    return db.executeQueryArray(sql, instalacion, fecha, horainicio, horainicio, horafin, horafin);
	}


	// Verifica que exista el usuario en la base de datos
	public String verificarUsuario (String dni, String nombre) {
		// SQL para verificar la existencia del usuario con su DNI y nombre
	    String sql = "SELECT estado, rol FROM USUARIO WHERE dni = ? AND nombre = ?";
	    
	    // Ejecutar la consulta y obtener el resultado
	    List<Object[]> resultados = db.executeQueryArray(sql, dni, nombre);
	    
	    if (resultados.isEmpty()) {
	        // Si no se encuentra ningún usuario con ese DNI y nombre
	        return "Usuario no existe";
	    } else {
	        // Si el usuario existe, verificamos su estado
	        String estado = (String) resultados.get(0)[0];
	        String rol = (String) resultados.get(0)[1];
	        
	        // Verificar si es moroso o no es socio
	        if (estado.equals("MOROSO")) {
	            return "Es moroso";
	        } else if (rol.equals("NO_SOCIO")) {
	            return "No es socio";
	        } else {
	            // El usuario es socio y no es moroso
	            return "Usuario válido";
	        }
	    }
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

	
	public int obtenerUsuarioId(String nombre) {
		// Método para obtener el usuario_id a partir del nombre, se puede hacer con una consulta SQL
	    String sql = "SELECT id FROM USUARIO WHERE nombre = ?";
	    List <Object[]> resultados = db.executeQueryArray(sql, nombre);
	    
	    if(resultados.isEmpty()) {
	    	return -1;
	    }else {
	    	return (int) resultados.get(0)[0];
	    }
	
	}
	
	public List<Object[]> getReservasInstalacion(String fecha, String horainicio, String horafin, int instalacionId) {
	    // Consulta SQL para obtener la reserva, si existe
	    String sql = "SELECT RI.id AS reserva_id, "
	               + "U.nombre AS usuario, "
	               + "RI.fecha, "
	               + "RI.hora_inicio, "
	               + "RI.hora_fin "
	               + "FROM RESERVA_INSTALACION RI "
	               + "JOIN USUARIO U ON RI.usuario_id = U.id "
	               + "WHERE RI.instalacion_id = ? "
	               + "AND RI.fecha = ? "
	               + "AND ? BETWEEN RI.hora_inicio AND RI.hora_fin;";
	    
	    // Ejecutar la consulta con los parámetros adecuados (instalacion_id, fecha, hora_inicio)
	    return db.executeQueryArray(sql, instalacionId, fecha, horainicio);
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

	
	
}
