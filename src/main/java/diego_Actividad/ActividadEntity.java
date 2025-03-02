package diego_Actividad;

public class ActividadEntity {

	private int id;
	private String instalacion;
	private String nombre;
	private String tipo;
	private int aforo;
	private String dias;
	private String periodoDuracion;
	private double cuotaSocios;
	private double cuotaNoSocios;
	private int periodoInscripcion;

	// Constructor vacío necesario para frameworks como JDBC, Hibernate, etc.
	public ActividadEntity() {
	}

	// Constructor con parámetros (opcional, por si lo necesitas)
	public ActividadEntity(int id, String instalacion, String nombre, String tipo, int aforo, String dias,
			String periodoDuracion, double cuotaSocios, double cuotaNoSocios, int periodoInscripcion) {
		this.id = id;
		this.instalacion = instalacion;
		this.nombre = nombre;
		this.tipo = tipo;
		this.aforo = aforo;
		this.dias = dias;
		this.periodoDuracion = periodoDuracion;
		this.cuotaSocios = cuotaSocios;
		this.cuotaNoSocios = cuotaNoSocios;
		this.periodoInscripcion = periodoInscripcion;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(String instalacion) {
		this.instalacion = instalacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public String getPeriodoDuracion() {
		return periodoDuracion;
	}

	public void setPeriodoDuracion(String periodoDuracion) {
		this.periodoDuracion = periodoDuracion;
	}

	public double getCuotaSocios() {
		return cuotaSocios;
	}

	public void setCuotaSocios(double cuotaSocios) {
		this.cuotaSocios = cuotaSocios;
	}

	public double getCuotaNoSocios() {
		return cuotaNoSocios;
	}

	public void setCuotaNoSocios(double cuotaNoSocios) {
		this.cuotaNoSocios = cuotaNoSocios;
	}

	public int getPeriodoInscripcion() {
		return periodoInscripcion;
	}

	public void setPeriodoInscripcion(int periodoInscripcion) {
		this.periodoInscripcion = periodoInscripcion;
	}

	@Override
	public String toString() {
		return "ActividadEntity [id=" + id + ", instalacion=" + instalacion + ", nombre=" + nombre + ", tipo=" + tipo
				+ ", aforo=" + aforo + ", dias=" + dias + ", periodoDuracion=" + periodoDuracion + ", cuotaSocios="
				+ cuotaSocios + ", cuotaNoSocios=" + cuotaNoSocios + ", periodoInscripcion=" + periodoInscripcion + "]";
	}

}
