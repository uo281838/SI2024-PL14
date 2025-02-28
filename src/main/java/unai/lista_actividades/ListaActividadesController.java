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
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true); 
	}

    public void initController() {
        // Agrega los eventos a los componentes de la vista
        view.getListaPeriodo().addActionListener(e -> cargarFechasDesdePeriodo());
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
        
        String fechaInicioStr = formatDate(fechaInicio);
        String fechaFinStr = formatDate(fechaFin);

        // Validar que ambas fechas estén seleccionadas antes de actualizar la tabla
        if (fechaInicio == null || fechaFin == null) {
            return;
        }

        // Obtener datos del modelo
        List<ListaActividadesDisplayDTO> actividades = model.getListaActividades(fechaInicioStr, fechaFinStr);

        // Actualizar la tabla con los datos obtenidos
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Nombre", "Descripción", "Instalación", "Precio Socio", "Precio No Socio", "Periodo", "Inicio", "Fin"});

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

        // Asignar el modelo a la tabla en la vista
        view.getTablaActividades().setModel(tableModel);
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

    /**
     * Carga automáticamente las fechas cuando se elige un período en el ComboBox
     */
    private void cargarFechasDesdePeriodo() {
        String periodoSeleccionado = (String) view.getListaPeriodo().getSelectedItem();
        if (periodoSeleccionado == null) return;

        Date fechaInicio = null;
        Date fechaFin = null;

        switch (periodoSeleccionado) {
            case "Enero":
                fechaInicio = Util.isoStringToDate("2024-01-01");
                fechaFin = Util.isoStringToDate("2024-01-31");
                break;
            case "Junio":
                fechaInicio = Util.isoStringToDate("2024-06-01");
                fechaFin = Util.isoStringToDate("2024-06-30");
                break;
            case "Septiembre":
                fechaInicio = Util.isoStringToDate("2024-09-01");
                fechaFin = Util.isoStringToDate("2024-09-30");
                break;
            default:
                JOptionPane.showMessageDialog(view.getFrame(), "Periodo no válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        view.setFechaInicio(fechaInicio);
        view.setFechaFin(fechaFin);
        obtenerActividades();
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return (date != null) ? sdf.format(date) : null;
    }
    
}
