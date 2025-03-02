package diego_Actividad;

public class ActividadDisplayDTO {

	// Declaramos variables
	private String nombre;
	private String tipo;
	private int aforo;
	private String dias;
	private double cuotaSocios;
	private double cuotaNoSocios;

	// Constructor
	public ActividadDisplayDTO() {};
	
	public ActividadDisplayDTO(String nombre, String tipo, int aforo, String dias, double cuotaSocios,
			double cuotaNoSocios) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.aforo = aforo;
		this.dias = dias;
		this.cuotaSocios = cuotaSocios;
		this.cuotaNoSocios = cuotaNoSocios;
	}

	// Getters y Setters
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
}
