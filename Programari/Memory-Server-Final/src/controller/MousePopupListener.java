package controller;

import java.awt.event.MouseAdapter;

import model.GestioPartida;
import model.Operacions;
import view.MainWindow;
import view.classMouseAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MousePopupListener extends MouseAdapter implements ActionListener {
	
	/**
	 * La classe MousePopupListener s'encarrega de gestionar les accions dudes a terme per l'usuari a la finestra
	 * principal del programa a través del ratolí.
	 */
	private MainWindow vista;
	private GestioPartida partida;
	private classMouseAdapter mouse;
	private ButtonController controler;
	
	public MousePopupListener(MainWindow vista,ButtonController controler,GestioPartida partida){
		this.vista = vista;
		this.partida = partida;
		this.controler = controler;
	}
	
	public void actionPerformed(ActionEvent e) {
		  String quinaOpcio = e.getActionCommand();
		  if(("Bloquear/Desbloquear").equals(quinaOpcio)){
			  if(vista.isBlocked()){
				  vista.desBloquearSelectedRow();
				  String nickname = vista.getNicknameTable();
				  partida.disblockUser(nickname);

			  }else{
				  vista.bloquearSelectedRow();
				  String nickname = vista.getNicknameTable();
				  partida.blockUser(nickname);
			  }
		  }
		  if(("Eliminar").equals(quinaOpcio)){
			  String nickname = vista.getTableNickname();
			  partida.eliminarUsuari(nickname);
		  }
		  
	  }

}
