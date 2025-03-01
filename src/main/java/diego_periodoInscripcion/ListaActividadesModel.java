package diego_periodoInscripcion;

import java.util.Date;
import java.util.List;

import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class ListaActividadesModel {
	private static final String MSG_PERIODO_NO_NULO = "Se deben introducir dos fechas";
	
	private Database db=new Database();


	public List<periodoDTO> getListaCarreras(Date fechaInicio, Date fechaFin) {
		validateNotNull(fechaInicio,MSG_PERIODO_NO_NULO);
		validateNotNull(fechaFin,MSG_PERIODO_NO_NULO);
		String sql = 
		        "SELECT a.nombre AS nombre_actividad, " 
		        + "a.descripcion, "
		        + "i.nombre AS nombre_instalacion, "
		        + "a.coste_socio, "
		        + "a.coste_no_socio, "
		        + "p.nombre AS nombre_periodo, "
		        + "a.fecha_inicio, "
		        + "a.fecha_fin "
		        + "FROM ACTIVIDAD a "
		        + "JOIN INSTALACION i ON a.instalacion_id = i.id "
		        + "LEFT JOIN PERIODO_INSCRIPCION p ON a.periodo_inscripcion_id = p.id "
		        + "WHERE a.fecha_inicio <= ? " 
		        + "AND a.fecha_fin >= ?";

		    String f1 = Util.dateToIsoString(fechaInicio);
		    String f2 = Util.dateToIsoString(fechaFin);
		    
		return db.executeQueryPojo(periodoDTO.class, sql, f1, f2);
	}
	
	private void validateNotNull(Object obj, String message) {
		if (obj==null)
			throw new ApplicationException(message);
	}
}
