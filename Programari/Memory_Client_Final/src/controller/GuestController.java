package controller;

import view.VentanaError;

public class GuestController {

	/**
	 * La classe GuestController s'encarrega de gestionar les accions realitzades per l'usuari quan entra 
	 * com a usuari convidat, ja que en aquest mètode inici de sessió, no es realitza cap connexió amb el servidor
	 */
	private ButtonController buttonController;
	
	public GuestController(ButtonController buttonController){
		this.buttonController = buttonController;
	}
	
	/**
	 * SetOptionOn permet accedir al joc com a usuari convidat. Primerament mostra un missatge informant a 
	 * l'usuari de que ha aconseguit iniciar sessió sense problemes i activa els botons que permeten jugar a 
	 * les diferents modalitats del memory. Un cop s'han activat els botons retorna un 1, el qual quedarà emmagatzemat
	 * a la variable isGuest de la classe ButtonController i definirà si s'ha d'establir una connexió amb el servidor
	 * o no.
	 * @return: Retorna sempre un 1 ja que implica que l'usuari s'ha registrat com a usuari convidat
	 */
	public int SetOptionOn(){
		VentanaError ve = new VentanaError("Te has registrado como usuario invitado", "Session Information");
		ve.setVisible(true);
		buttonController.setButtonsActive();
		buttonController.OccultButtons();
		return 1;
	}
	
}
