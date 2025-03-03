package diego_Actividad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class ActividadController {
    private ActividadModel model;
    private ActividadView view;
    private String lastSelectedKey = ""; // Guarda la última clave seleccionada

    public ActividadController(ActividadModel model, ActividadView view) {
        this.model = model;
        this.view = view;
        this.initView();
    }

    public void initController() {
        view.getBtnGuardar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> guardarActividad()));
        view.getBtnMostrar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> getListaActividades()));

        view.getTablaActividades().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                SwingUtil.exceptionWrapper(() -> updateDetail());
            }
        });
    }

    public void initView() {
        getListaActividades();
        view.getFrame().setVisible(true);
    }

    /**
     * Guarda una nueva actividad en la base de datos.
     */
    public void guardarActividad() {
        try {
            String nombre = view.getNombreField().getText();
            String descripcion = view.getDescripcionField().getText();
            int instalacionId = Integer.parseInt(view.getListaInstalaciones().getText()); // Suponiendo que se recibe como texto
            int aforoMaximo = Integer.parseInt(view.getAforoMaximoField().getText());
            double costeSocio = Double.parseDouble(view.getCosteSocioField().getText());
            double costeNoSocio = Double.parseDouble(view.getCosteNoSocioField().getText());
            String fechaInicio = Util.dateToIsoString(view.getFechaInicioChooser().getDate());
            String fechaFin = Util.dateToIsoString(view.getFechaFinChooser().getDate());
            String dias = view.getDiasField().getText();
            String horaInicio = view.getHoraInicioField().getText();
            String horaFin = view.getHoraFinField().getText();
            int periodoInscripcionId = Integer.parseInt(view.getPeriodoInscripcionField().getText());

            if (nombre.isEmpty() || descripcion.isEmpty() || fechaInicio == null || fechaFin == null || dias.isEmpty() || horaInicio.isEmpty() || horaFin.isEmpty()) {
                throw new ApplicationException("Todos los campos deben estar completos.");
            }

            model.guardarActividad(nombre, descripcion, instalacionId, aforoMaximo, costeSocio, costeNoSocio, fechaInicio, fechaFin, dias, horaInicio, horaFin, periodoInscripcionId);
            view.mostrarMensaje("Actividad guardada correctamente.");
            getListaActividades();
        } catch (ApplicationException ex) {
            view.mostrarError(ex.getMessage());
        }
    }

    /**
     * Obtiene la lista de actividades desde el modelo y la muestra en la vista.
     */
    public void getListaActividades() {
        List<ActividadDisplayDTO> actividades = model.getListaActividades();
        TableModel tmodel = SwingUtil.getTableModelFromPojos(actividades, new String[]{"id", "nombre", "descripcion", "instalacion_id", "aforo_maximo", "coste_socio", "coste_no_socio", "fecha_inicio", "fecha_fin", "dias", "hora_inicio", "hora_fin", "periodo_inscripcion_id"});
        view.getTablaActividades().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getTablaActividades());

        restoreDetail();

        List<Object[]> actividadesList = model.getListaActividadesArray();
        ComboBoxModel<Object> lmodel = SwingUtil.getComboModelFromList(actividadesList);
        view.getListaActividades().setModel(lmodel);
    }

    /**
     * Restaura la selección de detalles en la tabla.
     */
    public void restoreDetail() {
        this.lastSelectedKey = SwingUtil.selectAndGetSelectedKey(view.getTablaActividades(), this.lastSelectedKey);
        if ("".equals(this.lastSelectedKey)) {
            view.getDetalleActividad().setModel(new DefaultTableModel());
        } else {
            this.updateDetail();
        }
    }

    /**
     * Actualiza la vista con los detalles de la actividad seleccionada.
     */
    public void updateDetail() {
        this.lastSelectedKey = SwingUtil.getSelectedKey(view.getTablaActividades());
        int idActividad = Integer.parseInt(this.lastSelectedKey);

        ActividadEntity actividad = model.getActividad(idActividad);
        TableModel tmodel = SwingUtil.getRecordModelFromPojo(actividad, new String[]{"id", "nombre", "descripcion", "instalacion_id", "aforo_maximo", "coste_socio", "coste_no_socio", "fecha_inicio", "fecha_fin", "dias", "hora_inicio", "hora_fin", "periodo_inscripcion_id"});
        view.getDetalleActividad().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getDetalleActividad());
    }
}

