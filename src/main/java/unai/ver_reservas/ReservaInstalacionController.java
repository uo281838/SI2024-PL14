package unai.ver_reservas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ReservaInstalacionController {
    private ReservaInstalacionModel model;
    private ReservaInstalacionView view;

    public ReservaInstalacionController(ReservaInstalacionModel model, ReservaInstalacionView view) {
        this.model = model;
        this.view = view;
        initView();       // Inicializa la vista
        initController(); // Inicializa los eventos del controlador
    }

    // Inicializa la vista: carga el ComboBox y muestra la ventana
    public void initView() {
        cargarInstalacionesEnComboBox();
        view.getFrame().setVisible(true);
    }

    // Inicializa los eventos: por ejemplo, el ActionListener del ComboBox
    public void initController() {
        view.getCbInstalacion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedInstalacion = (String) view.getCbInstalacion().getSelectedItem();
                if (selectedInstalacion != null && !selectedInstalacion.isEmpty()) {
                    InstalacionDTO instalacion = obtenerInstalacionPorNombre(selectedInstalacion);
                    if (instalacion != null) {
                        // Obtiene las reservas para los próximos 30 días
                        List<ReservaInstalacionDTO> reservas = model.getReservasForNext30Days(instalacion.getId());
                        actualizarTablaHorario(reservas);
                    }
                }
            }
        });
    }


    // Carga las instalaciones en el ComboBox
    private void cargarInstalacionesEnComboBox() {
        List<InstalacionDTO> instalaciones = model.getInstalaciones();
        view.getCbInstalacion().removeAllItems();
        view.getCbInstalacion().addItem(""); // Opción vacía
        for (InstalacionDTO instalacion : instalaciones) {
            view.getCbInstalacion().addItem(instalacion.getNombre());
        }
    }

    // Obtiene la instalación según su nombre (se asume que los nombres son únicos)
    private InstalacionDTO obtenerInstalacionPorNombre(String nombreInstalacion) {
        List<InstalacionDTO> instalaciones = model.getInstalaciones();
        for (InstalacionDTO instalacion : instalaciones) {
            if (instalacion.getNombre().equals(nombreInstalacion)) {
                return instalacion;
            }
        }
        return null;
    }

    private void actualizarTablaHorario(List<ReservaInstalacionDTO> reservas) {
        // Crear el modelo de la tabla para mostrar las reservas
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Hora/Fecha");

        // Agregar las fechas de los próximos 30 días
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 30; i++) {
            LocalDate fecha = today.plusDays(i);
            model.addColumn(fecha);  // Agregar como LocalDate
        }

        // Crear las filas con los horarios (por ejemplo de 9:00 a 22:00)
        for (int hora = 9; hora < 21; hora++) {
            String horaStr = String.format("%02d:00-%02d:00", hora, hora + 1);
            Object[] fila = new Object[31];  // Una fila para cada hora, más la columna "Hora/Fecha"
            fila[0] = horaStr;  // Primera celda de la fila con la hora

            // Llenar las reservas para cada hora y fecha
            for (int i = 0; i < 30; i++) {
                LocalDate fecha = today.plusDays(i);
                boolean reservado = false;

                for (ReservaInstalacionDTO reserva : reservas) {
                    // Convertimos la fecha de la reserva a LocalDate
                    LocalDate reservaFecha = LocalDate.parse(reserva.getFecha());

                    // Verificamos si la fecha y la hora coinciden con la reserva
                    if (reservaFecha.equals(fecha) && reserva.getHoraInicio().toString().equals(String.format("%02d:00", hora))) {
                        // Si la reserva coincide, ponemos el nombre del usuario en la celda
                        fila[i + 1] = "Reservado por " + reserva.getNombreUsuario();
                        reservado = true;
                        break;
                    }
                }

                // Si no está reservado, mostramos "Libre"
                if (!reservado) {
                    fila[i + 1] = "Libre";
                }
            }

            model.addRow(fila);
        }

        // Asignar el modelo a la tabla
        view.getTabHorario().setModel(model);
        
     // Asignar el modelo a la tabla
        view.getTabHorario().setModel(model);

        // Ajustar el ancho de las columnas para que se ajusten al contenido (incluyendo la cabecera)
        for (int column = 0; column < view.getTabHorario().getColumnCount(); column++) {
            int width = 0;

            // Calcular el ancho máximo de la cabecera y de los valores de las celdas
            for (int row = 0; row < view.getTabHorario().getRowCount(); row++) {
                TableCellRenderer renderer = view.getTabHorario().getCellRenderer(row, column);
                Component comp = view.getTabHorario().prepareRenderer(renderer, row, column);
                width = Math.max(width, comp.getPreferredSize().width);
            }

            // Ajustar el ancho de la columna (también incluye el ancho de la cabecera)
            TableColumn tableColumn = view.getTabHorario().getColumnModel().getColumn(column);
            int headerWidth = view.getTabHorario().getTableHeader().getDefaultRenderer().getTableCellRendererComponent(view.getTabHorario(), 
                tableColumn.getHeaderValue(), false, false, -1, column).getPreferredSize().width;
            tableColumn.setPreferredWidth(Math.max(width, headerWidth) + 10);  // Un poco de margen adicional
        }

        // Configura el modo de redimensionamiento de la tabla
        view.getTabHorario().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  // Evita que las columnas se ajusten automáticamente

     // Aplicar el renderizador personalizado para cambiar el fondo
        for (int i = 1; i < model.getColumnCount(); i++) {
            view.getTabHorario().getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
        }
        


        
    }


}
