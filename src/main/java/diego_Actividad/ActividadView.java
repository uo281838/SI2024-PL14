package diego_Actividad;

import javax.swing.*;

public class ActividadView {
    private JFrame frame;
    private JTable tablaActividades;
    private JButton btnMostrarActividades;
    private JButton btnCrearActividad;

    public ActividadView() {
        frame = new JFrame("Gestión de Actividades");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tablaActividades = new JTable();
        btnMostrarActividades = new JButton("Mostrar Actividades");
        btnCrearActividad = new JButton("Crear Actividad");

        JPanel panel = new JPanel();
        panel.add(btnMostrarActividades);
        panel.add(btnCrearActividad);
        frame.add(panel, "North");
        frame.add(new JScrollPane(tablaActividades), "Center");
    }

    public JFrame getFrame() { return frame; }
    public JTable getTablaActividades() { return tablaActividades; }
    public JButton getBtnMostrarActividades() { return btnMostrarActividades; }
    public JButton getBtnCrearActividad() { return btnCrearActividad; }

    public ActividadEntity obtenerDatosActividad() {
        return new ActividadEntity(0, "Gimnasio", "Yoga", "Deporte", 30, "Lunes, Miércoles", "3 meses", 20.0, 30.0, 1);
    }
}
