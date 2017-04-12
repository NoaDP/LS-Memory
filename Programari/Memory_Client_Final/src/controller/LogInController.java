package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import network.Client;
import network.ServerConnection;
import view.LogIn;
import view.VentanaError;

public class LogInController implements ActionListener {


	/**
	 * La classe LogInController s'encarrega de gestionar l'inici de sessió com a usuari registrat a la base de 
	 * dades del servidor. Primerament comprova si tots els camps de text han estat omplerts, s'emmagatzema la informació
	 * en una variable tipus client i tot seguit s'envia aquesta informació al servidor on es comprova si les dades inserides
	 * són correctes. En cas de que que alguns camps estiguin buits, es mostrarà un error i no permetrà continuar el procés, fins
	 * que s'haguin omplert tots el camps. 
	 */
	private ButtonController buttonController;
	private LogIn Login;
	private ServerConnection sConnect;
	private Client client;

	public LogInController (LogIn Login, ButtonController buttonController,ServerConnection sConnect,Client client){
		this.buttonController = buttonController;
		this.Login = Login;
		this.sConnect  = sConnect;
		this.client = client;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getActionCommand().equals("LogIn") && !Login.Blancs()){
			Login.setVisible(false);
			String s = new String();
			Client cli = new Client(s,Login.typedNickname().toString(),s,Login.typedPassword().toString(),0,this.client.getBlocked());
			sConnect.sendMessage(2,cli);
		}else{
			if (Login.typedNickname().isEmpty() && Login.typedPassword().isEmpty()){
				VentanaError ventanaError = new VentanaError("Error. Todos los campos están en blanco", "LogIn Error");
				ventanaError.setVisible(true);
			}else{
				if (Login.typedNickname().isEmpty()){
					VentanaError ventanaError = new VentanaError("Error. Falta el campo: Nickname", "LogIn Error");
					ventanaError.setVisible(true);
				}
				if(Login.typedPassword().isEmpty()){
					VentanaError ventanaError = new VentanaError("Error. Falta el campo: Password", "LogIn Error");
					ventanaError.setVisible(true);
				}	
			}
		}
	}
}
