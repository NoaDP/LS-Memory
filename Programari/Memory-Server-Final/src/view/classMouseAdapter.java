package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import controller.ButtonController;
import controller.MousePopupListener;

public class classMouseAdapter extends MouseAdapter{
	
	private JTable table;
	private JMenuItem itemBloquear;
	private JMenuItem itemEliminar;
	private MainWindow vista;
	private MousePopupListener mousePopup;
	
	public classMouseAdapter(JTable table,MainWindow vista){
		this.table = table;
		this.vista = vista;
	}
    
	public void mouseReleased(MouseEvent e) {
		
        int r = table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < table.getRowCount()) {
            table.setRowSelectionInterval(r, r);
        } else {
            table.clearSelection();
        }

        int rowindex = table.getSelectedRow();
        if (rowindex < 0)
            return;
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
            JPopupMenu popup = createYourPopUp();
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
	
	public JPopupMenu createYourPopUp() {
		JPopupMenu menu = new JPopupMenu();
		itemBloquear = new JMenuItem("Bloquear/Desbloquear");
		itemEliminar = new JMenuItem("Eliminar");
		menu.add(itemBloquear);
		itemBloquear.setHorizontalTextPosition(JMenuItem.LEFT);
		itemBloquear.addActionListener(mousePopup);
		menu.add(itemEliminar);
		itemEliminar.setHorizontalTextPosition(JMenuItem.LEFT);
		itemEliminar.addActionListener(mousePopup);
		return menu;
	}
	
	public void setMousePopup(MousePopupListener mousePopup){
		this.mousePopup = mousePopup;
	}

}
