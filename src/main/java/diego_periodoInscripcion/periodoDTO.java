package diego_periodoInscripcion;

public class periodoDTO {
	
	private String nombre;
	private String descripcion;
	private String fechaI;
	private String fechaF;
	private String fechaFNS;
	
	public periodoDTO(String nombre, String descripcion, String fechaI, String fechaF, String fechaFNS) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaI = fechaI;
		this.fechaF = fechaF;
		this.fechaFNS = fechaFNS;
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

	public String getFechaI() {
		return fechaI;
	}

	public void setFechaI(String fechaI) {
		this.fechaI = fechaI;
	}

	public String getFechaF() {
		return fechaF;
	}

	public void setFechaF(String fechaF) {
		this.fechaF = fechaF;
	}

	public String getFechaFNS() {
		return fechaFNS;
	}

	public void setFechaFNS(String fechaFNS) {
		this.fechaFNS = fechaFNS;
	}
	
	
	
	
	
	
}
