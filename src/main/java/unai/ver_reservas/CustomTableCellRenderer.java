package unai.ver_reservas;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Llamar al método de la clase base para obtener la celda estándar
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Verificar si el valor de la celda es "Reservado" (cualquier cosa que no sea "Libre")
        if (value != null && !value.toString().equals("Libre")) {
            // Cambiar el fondo a color naranja si no está libre
            cell.setBackground(Color.ORANGE);
        } else {
            // De lo contrario, establecer un fondo por defecto (blanco)
            cell.setBackground(Color.WHITE);
        }

        return cell;
    }
}