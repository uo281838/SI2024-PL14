package diego_periodoInscripcion;

public class PeriodoEntity {
	private int id;
	private String nombre;
	private String descripcion;
	private String fechaInicio;
	private String fechaFin;
	private String fechaFinNoSocios;

	// Constructor vacío necesario para frameworks como JDBC, Hibernate, etc.
	public PeriodoEntity() {
	}

	// Constructor con parámetros (opcional, por si lo necesitas)
	public PeriodoEntity(int id, String nombre, String descripcion, String fechaInicio, String fechaFin,
			String fechaFinNoSocios) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaFinNoSocios = fechaFinNoSocios;
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

	public String getFechaFinNoSocios() {
		return fechaFinNoSocios;
	}

	public void setFechaFinNoSocios(String fechaFinNoSocios) {
		this.fechaFinNoSocios = fechaFinNoSocios;
	}

	@Override
	public String toString() {
		return "PeriodoEntity [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", fechaFinNoSocios=" + fechaFinNoSocios + "]";
	}
}
