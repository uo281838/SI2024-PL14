package unai.lista_actividades;

public class ListaActividadesDisplayDTO {

	private String nombre;
	private String desc;
	private String inst;
	private String precio_s;
	private String precio_n;
	private String periodo;
	private String finicio;
	private String ffin;

	public ListaActividadesDisplayDTO() {
	}

	public ListaActividadesDisplayDTO(String rowNombre, String rowDescripcion, String rowInstalacion, String rowPrecioS, String rowPrecioN, String rowPeriodo,
			String rowFinicio, String rowFfin) {
		this.nombre=rowNombre;
		this.desc=rowDescripcion;
		this.inst=rowInstalacion;
		this.precio_s=rowPrecioS;
		this.precio_n=rowPrecioN;
		this.periodo=rowPeriodo;
		this.finicio=rowFinicio;
		this.ffin=rowFfin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getInst() {
		return inst;
	}

	public void setInst(String inst) {
		this.inst = inst;
	}

	public String getPrecio_s() {
		return precio_s;
	}

	public void setPrecio_s(String precio_s) {
		this.precio_s = precio_s;
	}

	public String getPrecio_n() {
		return precio_n;
	}

	public void setPrecio_n(String precio_n) {
		this.precio_n = precio_n;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getFinicio() {
		return finicio;
	}

	public void setFinicio(String finicio) {
		this.finicio = finicio;
	}

	public String getFfin() {
		return ffin;
	}

	public void setFfin(String ffin) {
		this.ffin = ffin;
	}


}
