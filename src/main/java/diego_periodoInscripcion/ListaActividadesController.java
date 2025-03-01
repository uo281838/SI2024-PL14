package diego_periodoInscripcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        initController();
    }

    private void initController() {
        // Agrega los eventos a los componentes de la vista
        view.getListaPeriodo().addActionListener(e -> cargarFechasDesdePeriodo());
        view.getTablaActividades().setModel(new DefaultTableModel(new Object[]{"Nombre", "Descripción", "Instalación", "Precio Socio", "Precio No Socio", "Periodo", "Inicio", "Fin"}, 0));
    }

    /**
     * Método para obtener la lista de actividades en el periodo seleccionado
     */
    public void obtenerActividades() {
        Date fechaInicio = view.fechaInicio().getDate();
        Date fechaFin = view.fechaFin().getDate();

        if (fechaInicio == null || fechaFin == null) {
            JOptionPane.showMessageDialog(view.getFrame(), "Debe seleccionar ambas fechas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<periodoDTO> actividades = model.getListaCarreras(fechaInicio, fechaFin);
        cargarTablaActividades(actividades);
    }

    /**
     * Carga las actividades en la tabla de la vista
     */
    private void cargarTablaActividades(List<periodoDTO> actividades) {
        DefaultTableModel model = (DefaultTableModel) view.getTablaActividades().getModel();
        model.setRowCount(0); // Limpiar tabla

        for (periodoDTO actividad : actividades) {
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
}
