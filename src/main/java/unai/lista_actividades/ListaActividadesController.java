package unai.lista_actividades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.demo.util.Util;

public class ListaActividadesController {
    private ListaActividadesModel model;
    private ListaActividadesView view;

    public ListaActividadesController(ListaActividadesModel model, ListaActividadesView view) {
        this.model = model;
        this.view = view;
        this.initView();
    }

	public void initView() {
		//Inicializa la fecha de hoy a un valor que permitira mostrar carreras en diferentes fases 
		//y actualiza los datos de la vista
	    cargarPeriodosEnComboBox();
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true); 
	}

    public void initController() {
        // Agrega los eventos a los componentes de la vista
        view.getListaPeriodo().addActionListener(e -> actualizarFechasDesdePeriodo());
        view.getTablaActividades().setModel(new DefaultTableModel(new Object[]{"Nombre", "Descripción", "Instalación", "Precio Socio", "Precio No Socio", "Periodo", "Inicio", "Fin"}, 0));
   
        PropertyChangeListener fechaListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (view.fechaInicio().getDate() != null && view.fechaFin().getDate() != null) {
                    actualizarTabla();  // Solo se actualiza cuando ambas fechas están definidas
                }
            }
        };

        view.fechaInicio().getDateEditor().addPropertyChangeListener("date", fechaListener);
        view.fechaFin().getDateEditor().addPropertyChangeListener("date", fechaListener);
    }

    private void cargarPeriodosEnComboBox() {
    	List<PeriodoDTO> periodos = model.getPeriodos();
    	view.getListaPeriodo().removeAllItems();
    	for (PeriodoDTO periodo : periodos) {
    		view.getListaPeriodo().addItem(periodo.getNombre());
    }
}
    private void actualizarFechasDesdePeriodo() {
        String nombrePeriodo = (String) view.getListaPeriodo().getSelectedItem();
        if (nombrePeriodo == null) return;

        List<PeriodoDTO> periodos = model.getPeriodos();
        for (PeriodoDTO periodo : periodos) {
            if (periodo.getNombre().equals(nombrePeriodo)) {
                view.setFechaInicio(Util.isoStringToDate(periodo.getFecha_inicio()));
                view.setFechaFin(Util.isoStringToDate(periodo.getFecha_fin()));
                break;
            }
        }
    }
   
    

    /**
     * Método para obtener la lista de actividades en el periodo seleccionado
     */
    public void obtenerActividades() {
        Date fechaInicio = view.fechaInicio().getDate();
        Date fechaFin = view.fechaFin().getDate();
        
        String fechaInicioStr = formatDate(fechaInicio);
        String fechaFinStr = formatDate(fechaFin);

        if (fechaInicio == null || fechaFin == null) {
            JOptionPane.showMessageDialog(view.getFrame(), "Debe seleccionar ambas fechas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<ListaActividadesDisplayDTO> actividades = model.getListaActividades(fechaInicioStr, fechaFinStr);
        cargarTablaActividades(actividades);
    }
    
    private void actualizarTabla() {
        Date fechaInicio = view.fechaInicio().getDate();
        Date fechaFin = view.fechaFin().getDate();

        if (fechaInicio == null || fechaFin == null) {
            return;
        }

        // Convertimos las fechas a string en formato ISO
        String fechaInicioStr = formatDate(fechaInicio);
        String fechaFinStr = formatDate(fechaFin);

        // Obtener los datos del modelo
        List<ListaActividadesDisplayDTO> actividades = model.getListaActividades(fechaInicioStr, fechaFinStr);

        // Verificar si la lista tiene datos
        if (actividades == null || actividades.isEmpty()) {
            System.out.println("No se encontraron actividades para el rango de fechas seleccionado.");
            return;
        }

        // Debug: Imprimir los datos obtenidos
        for (ListaActividadesDisplayDTO actividad : actividades) {
            System.out.println("Actividad obtenida: " + actividad.getNombre() + " - " + actividad.getPeriodo());
        }

        // Crear un nuevo modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{
            "Nombre", "Descripción", "Instalación", "Precio Socio", "Precio No Socio", "Periodo", "Inicio", "Fin"
        });

        // Llenar la tabla con los datos
        for (ListaActividadesDisplayDTO actividad : actividades) {
            tableModel.addRow(new Object[]{
                actividad.getNombre(),
                actividad.getDesc(),
                actividad.getInst(),
                actividad.getPrecio_s(),
                actividad.getPrecio_n(),
                actividad.getPeriodo(),
                actividad.getFinicio(),
                actividad.getFfin()
            });
        }

        // Asignar el modelo actualizado a la tabla en la vista
        view.getTablaActividades().setModel(tableModel);
        
        // Refrescar la vista
        view.getTablaActividades().revalidate();
        view.getTablaActividades().repaint();
    }

    /**
     * Carga las actividades en la tabla de la vista
     */
    private void cargarTablaActividades(List<ListaActividadesDisplayDTO> actividades) {
        DefaultTableModel model = (DefaultTableModel) view.getTablaActividades().getModel();
        model.setRowCount(0); // Limpiar tabla

        for (ListaActividadesDisplayDTO actividad : actividades) {
            model.addRow(new Object[]{
                actividad.getNombre(),
                actividad.getDesc(),
                actividad.getInst(),
                actividad.getPrecio_s(),
                actividad.getPrecio_n(),
                actividad.getPeriodo(),
                actividad.getFinicio(),
                actividad.getFfin()
            });
        }
    }

    

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return (date != null) ? sdf.format(date) : null;
    }
    
}
