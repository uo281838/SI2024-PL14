package diego_Actividad;

import giis.demo.util.SwingUtil;
import java.util.List;
import javax.swing.table.TableModel;

public class ActividadController {
	private ActividadModel model;
	private ActividadView view;

	public ActividadController(ActividadModel m, ActividadView v) {
		this.model = m;
		this.view = v;
		this.initView();
	}

	public void initController() {
		view.getBtnMostrarActividades().addActionListener(e -> SwingUtil.exceptionWrapper(() -> mostrarActividades()));
		view.getBtnCrearActividad().addActionListener(e -> SwingUtil.exceptionWrapper(() -> crearActividad()));
	}

	public void initView() {
		mostrarActividades();
		view.getFrame().setVisible(true);
	}

	public void mostrarActividades() {
		List<ActividadDisplayDTO> actividades = model.getListaActividades();
		TableModel tmodel = SwingUtil.getTableModelFromPojos(actividades,
				new String[] { "nombre", "tipo", "aforo", "dias", "cuotaSocios", "cuotaNoSocios" });
		view.getTablaActividades().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaActividades());
	}

	public void crearActividad() {
		ActividadEntity actividad = view.obtenerDatosActividad();
		model.crearActividad(actividad);
		mostrarActividades();
	}
}
