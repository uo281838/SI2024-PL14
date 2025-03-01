package diego_periodoInscripcion;

import java.util.Date;

public class PeriodoDisplayDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaFinNoSocios;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public Date getFechaFinNoSocios() { return fechaFinNoSocios; }
    public void setFechaFinNoSocios(Date fechaFinNoSocios) { this.fechaFinNoSocios = fechaFinNoSocios; }
}
