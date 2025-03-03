package diego_Actividad;

public class ActividadEntity {
    private int id;
    private String nombre;
    private String descripcion;
    private int instalacionId;
    private int aforoMaximo;
    private double costeSocio;
    private double costeNoSocio;
    private String fechaInicio;
    private String fechaFin;
    private String dias;
    private String horaInicio;
    private String horaFin;
    private int periodoInscripcionId;

    // Constructor vacío necesario para frameworks como JDBC, Hibernate, etc.
    public ActividadEntity() {
    }

    // Constructor con parámetros (opcional, por si lo necesitas)
    public ActividadEntity(int id, String nombre, String descripcion, int instalacionId, int aforoMaximo,
            double costeSocio, double costeNoSocio, String fechaInicio, String fechaFin, String dias,
            String horaInicio, String horaFin, int periodoInscripcionId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.instalacionId = instalacionId;
        this.aforoMaximo = aforoMaximo;
        this.costeSocio = costeSocio;
        this.costeNoSocio = costeNoSocio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.periodoInscripcionId = periodoInscripcionId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getInstalacionId() {
        return instalacionId;
    }

    public void setInstalacionId(int instalacionId) {
        this.instalacionId = instalacionId;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public double getCosteSocio() {
        return costeSocio;
    }

    public void setCosteSocio(double costeSocio) {
        this.costeSocio = costeSocio;
    }

    public double getCosteNoSocio() {
        return costeNoSocio;
    }

    public void setCosteNoSocio(double costeNoSocio) {
        this.costeNoSocio = costeNoSocio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getPeriodoInscripcionId() {
        return periodoInscripcionId;
    }

    public void setPeriodoInscripcionId(int periodoInscripcionId) {
        this.periodoInscripcionId = periodoInscripcionId;
    }

    @Override
    public String toString() {
        return "ActividadEntity [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", instalacionId="
                + instalacionId + ", aforoMaximo=" + aforoMaximo + ", costeSocio=" + costeSocio + ", costeNoSocio="
                + costeNoSocio + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", dias=" + dias
                + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", periodoInscripcionId=" + periodoInscripcionId
                + "]";
    }
}

