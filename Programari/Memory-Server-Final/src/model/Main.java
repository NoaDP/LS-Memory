package model;

import javax.swing.SwingUtilities;

import view.MainWindow;
import controller.ButtonController;
import controller.ComboListener;
import controller.MousePopupListener;

public class Main {

	/**
	 * La classe Main és la classe principal del programa servidor. Aquest s'encarrega de cridar a la finestra gràfica principal
	 * i al ButtonController, encarregat de gestionar les accions que es poden donar a través de la finestra gràfica. També s'encarrega
	 * d'activar el MousePopupListener el qual permet enregistrar les accions que realitza l'usuari mitjançant el ratolí.
	 */
	public Main() {}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Configuracio configuracio = new Configuracio();
				String[] info = configuracio.llegir();
				ConectorDB conn = new ConectorDB(info[3], info[4], info[2], Integer.parseInt(info[0]));
				// crea la vista
				MainWindow vista = new MainWindow();
				// creem el model
				GestioPartida gp = new GestioPartida(conn);
				// crea el controlador i estableix la relacio C->V i C->M
				ButtonController controlador = new ButtonController(vista,gp);
				gp.setController(controlador);
				controlador.setNetwork(info[1],Integer.parseInt(info[5]));
				// establim la relacio V->C
				vista.registreControladorBotons(controlador);
				// fem la vista visible
				vista.setVisible(true);
				MousePopupListener actionListener = new MousePopupListener(vista,controlador,gp);
				ComboListener comboListener = new ComboListener(vista,controlador);
				vista.setExternalListeners(actionListener,comboListener);
			}
		});
	}

}
