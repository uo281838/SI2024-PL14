package diego_periodoInscripcion;

import java.util.Date;
import java.util.List;

import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class PeriodoModel {

	private static final String MSG_Nombre = "Introduzca nombre período";
	private static final String MSG_Fechas_No_Validas = "Introduzca fecha valida";

	private Database db = new Database();

	public void crearPeriodo(String nombre, String descripcion, Date fechaInicio, Date fechaFinal,
			Date fechaFinNoSocios) {
		validateNotNull(nombre, MSG_Nombre);
		validateNotNull(fechaInicio, MSG_Fechas_No_Validas);
		validateNotNull(fechaFinal, MSG_Fechas_No_Validas);
		validateNotNull(fechaFinNoSocios, MSG_Fechas_No_Validas);

		String sql = "INSERT INTO PERIODO (nombre, descripcion, fecha_inicio, fecha_fin, fecha_fin_no_socios) VALUES (?, ?, ?, ?, ?)";

		String f1 = Util.dateToIsoString(fechaInicio);
		String f2 = Util.dateToIsoString(fechaFinal);
		String f3 = Util.dateToIsoString(fechaFinNoSocios);

		db.executeUpdate(sql, nombre, descripcion, f1, f2, f3);

	}

	public List<PeriodoDisplayDTO> obtenerPeriodos() {
		String sql = "SELECT id, nombre, descripcion, fecha_inicio, fecha_fin, fecha_fin_no_socios FROM PERIODO";
		return db.executeQueryPojo(PeriodoDisplayDTO.class, sql);
	}

	private void validateNotNull(Object obj, String message) {
		if (obj == null) {
			throw new ApplicationException(message);
		}
	}

}
