package diego_Actividad;

import giis.demo.util.Database;
import giis.demo.util.ApplicationException;
import giis.demo.util.Util;
import java.util.List;

public class ActividadModel {
	private Database db = new Database();

	public List<ActividadDisplayDTO> getListaActividades() {
		String sql = "SELECT nombre, tipo, aforo, dias, cuota_socio, cuota_no_socio FROM ACTIVIDAD";
		return db.executeQueryPojo(ActividadDisplayDTO.class, sql);
	}

	public void crearActividad(ActividadEntity actividad) {
		if (actividad == null) {
			throw new ApplicationException("La actividad no puede ser nula.");
		}

		String sql = "INSERT INTO ACTIVIDAD (instalacion, nombre, tipo, aforo, dias, periodo_duracion, cuota_socio, cuota_no_socio, periodo_inscripcion) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		db.executeUpdate(sql, actividad.getInstalacion(), actividad.getNombre(), actividad.getTipo(),
				actividad.getAforo(), actividad.getDias(), actividad.getPeriodoDuracion(), actividad.getCuotaSocios(),
				actividad.getCuotaNoSocios(), actividad.getPeriodoInscripcion());
	}
}
