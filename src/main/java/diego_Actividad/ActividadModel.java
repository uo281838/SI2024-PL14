package diego_Actividad;

import java.util.List;

import diego_periodoInscripcion.PeriodoEntity;
import giis.demo.util.Database;
import giis.demo.util.ApplicationException;

public class ActividadModel {
    private Database db = new Database();

    /**
     * Guarda una nueva actividad en la base de datos.
     */
    public void guardarActividad(String nombre, String descripcion, int instalacionId, int aforoMaximo, 
            double costeSocio, double costeNoSocio, String fechaInicio, String fechaFin, String dias, 
            String horaInicio, String horaFin, int periodoInscripcionId) {

        if (nombre == null || nombre.isEmpty() || fechaInicio == null || fechaFin == null || dias == null || 
            horaInicio == null || horaFin == null) {
            throw new ApplicationException("Todos los campos deben estar completos.");
        }

        String sql = "INSERT INTO ACTIVIDAD (nombre, descripcion, instalacion_id, aforo_maximo, coste_socio, " +
                     "coste_no_socio, fecha_inicio, fecha_fin, dias, hora_inicio, hora_fin, periodo_inscripcion_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        db.executeUpdate(sql, nombre, descripcion, instalacionId, aforoMaximo, costeSocio, costeNoSocio, 
                         fechaInicio, fechaFin, dias, horaInicio, horaFin, periodoInscripcionId);
    }

    /**
     * Obtiene la lista de actividades desde la base de datos.
     */
    public List<ActividadDisplayDTO> getListaActividades() {
        String sql = "SELECT id, nombre, descripcion, instalacion_id, aforo_maximo, coste_socio, coste_no_socio, " +
                     "fecha_inicio, fecha_fin, dias, hora_inicio, hora_fin, periodo_inscripcion_id FROM ACTIVIDAD";
        return db.executeQueryPojo(ActividadDisplayDTO.class, sql);
    }

    /**
     * Obtiene la lista de actividades en formato de array para usar en ComboBox.
     */
    public List<Object[]> getListaActividadesArray() {
        String sql = "SELECT id, nombre FROM ACTIVIDAD";
        return db.executeQueryArray(sql);
    }

    /**
     * Obtiene una actividad específica según su ID.
     */
    public ActividadEntity getActividad(int idActividad) {
        String sql = "SELECT id, nombre, descripcion, instalacion_id, aforo_maximo, coste_socio, coste_no_socio, " +
                     "fecha_inicio, fecha_fin, dias, hora_inicio, hora_fin, periodo_inscripcion_id " +
                     "FROM ACTIVIDAD WHERE id = ?";
        List<ActividadEntity> resultados = db.executeQueryPojo(ActividadEntity.class, sql, idActividad);
        if (resultados.isEmpty()) {
            throw new ApplicationException("No se encontró la actividad con ID: " + idActividad);
        }
        return resultados.get(0);
    }
    
    // Obtener Id Periodo
    public PeriodoEntity getPeriodoInscripcion(int idPeriodoInscripcion) {
        // SQL para obtener el periodo de inscripción por ID
        String sql = "SELECT id, nombre, descripcion, fecha_inicio, fecha_fin, cuota FROM PERIODO_INSCRIPCION WHERE id = ?";
        
        // Ejecutar la consulta y mapear el resultado a una lista de PeriodoInscripcionEntity
        List<PeriodoEntity> resultados = db.executeQueryPojo(PeriodoEntity.class, sql, idPeriodoInscripcion);
        
        // Si no hay resultados, lanzamos una excepción
        if (resultados.isEmpty()) {
            throw new ApplicationException("No se encontró el periodo de inscripción con ID: " + idPeriodoInscripcion);
        }
        
        // Si hay resultados, devolver el primer resultado (en este caso debería haber solo uno)
        return resultados.get(0);
    }
    
    public List<Object[]> getListaPeriodosInscripcionArray() {
        String sql = "SELECT id, nombre FROM PERIODO_INSCRIPCION";
        return db.executeQueryArray(sql);
    }

}

