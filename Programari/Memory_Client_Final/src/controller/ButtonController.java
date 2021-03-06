package controller;


import Memo1_1.Board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabableView;

import network.Client;
import network.ServerConnection;
import view.GameInformation;
import view.Level;
import view.LogIn;
import view.TimeTrial;
import view.MainWindow;
import view.RankingView;
import view.Registration;
import view.VentanaError;

public class ButtonController implements ActionListener{
	
	/**
	 * La classe ButtonController es l'encarregada de escoltar i gestionar les accions realitzades a traves del
	 * botons de la ventana principal del menu client. 
	 */
	private MainWindow view;
	private VentanaError ve;
	private TimeTrial vt;
	private int isGuest;
	private int earnedPoints;
	private Level level;
	private ServerConnection sConnect;
	private Client client;
	private RankingView rankingView;
	
	public ButtonController (MainWindow view,Client client,int port, String IP){
		this.view = view;
		this.sConnect = new ServerConnection(this,client,port,IP);
		this.client = client;
		this.isGuest=0;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		earnedPoints = 0;
		if (e.getActionCommand().equals("Register")){
			Registration r = new Registration();
			r.setVisible(true);
			RegisterController registerController = new RegisterController(r, this,sConnect,client);
			r.RegisterControl(registerController);	
		}
		
		if(e.getActionCommand().equals("Log in")){
			LogIn li = new LogIn();
			li.setVisible(true);
			LogInController loginController = new LogInController (li, this,sConnect,client);
			li.LoginControl(loginController);
		}
		
		if(e.getActionCommand().equals("Log out")){
			ve = new VentanaError("Has salido sin problemas", "Log Out");
			ve.setVisible(true);
			this.isGuest = 0;
			this.disableButtons();
		}
		
		if(e.getActionCommand().equals("Time trial")){
			TimeTrial Tt = new TimeTrial();
			Tt.setVisible(true);
			TrialController tc = new TrialController(Tt,isGuest,sConnect,client,this);
			Tt.FocusController(tc);
			Tt.ClassicController(tc);
			//Tt.closeWindow();

		}
		if (e.getActionCommand().equals("Guest")){
			client.setBlocked(0);
			GuestController guestController = new GuestController(this);
			this.isGuest = guestController.SetOptionOn();
		}
		
		if(e.getActionCommand().equals("Information")){
			GameInformation gameInformation = new GameInformation();
			gameInformation.setVisible(true);
		}
		
		if(e.getActionCommand().equals("Vs Machine")){
		    level = new Level();
			level.setVisible(true);
			level.setController(this);
			LevelController levelController = new LevelController(level, 3, isGuest,sConnect,client,this);
			level.EasyController(levelController);
			level.NormalController(levelController);
			level.HardController(levelController);
			setAllButtonsInactive();
		}
		
		if(e.getActionCommand().equals("Ranking")){
			rankingView = new RankingView();
			sConnect.sendMessage(1, client);
		}
	}
	
	

	/**
	 * showMessage s'encarrega de cridar i activar una nova finestra gràfica i se li passen 2 strings: una amb el 
	 * titol de la finsestra i l'altre el contingut del missatge a mostrar. És per això que reb com a paràmetres
	 * una String (s), ja que aquesta serà el missatge a mostrar a través de la finestra.
	 * @param s: Variable tipus String que conté el missatge que es vol mostrar a la finestra gràfica
	 */
	public void showMessage(String s){
		ve = new VentanaError(s, "ERROR 404");
		ve.setVisible(true);
	}
	
	/**
	 * setButtonsActive activa els botons que permeten accedir a la informació del joc, jugar als diferents
	 * jocs o bé tancar la sessió.
	 */
	public void setButtonsActive (){
		view.ActivateButtons();
	}
	 
	/**
	 * OccultButtons desactiva els botons de registre, inici de sessió o accés com a usuari convidat
	 */
	public void OccultButtons(){
		view.Occult();
	}
	
	/**
	 * disableButtons permet desactivar els botons que permetrian jugar al torneig, és ha dir, fa un 
	 * logOut del joc.
	 */
	public void disableButtons(){
		view.Disable();
	}
	
	/**
	 * EarnedPoints s'encarrega de assignar la puntuació rebuda tras una partida de qualsevol de les tres
	 * modalitats disponibles: Classic, Focus o vs. Machine; i assignar-la al valor earnedPoints propi de 
	 * la classe.
	 * @param earnedPoints: Variable tipus int que conté la puntuació obtinguda per l'usuari
	 */
	public void EarnedPoints (int earnedPoints){
		this.earnedPoints = earnedPoints;
	}
	
	/**
	 * IsGuest s'encarrega de retornar l'estat de la variable isGuest la qual permet definir si l'usuari
	 * ha entrat en el joc com a guest(usuari convidat en mode entrenament) o bé si s'ha loguellat com
	 * usuari o s'ha registrat.
	 * @return: Variable tipus int que indica si l'usuari s'ha conectat com usuari o no.
	 */
	public int isGuestActivated(){
		return isGuest;
	}
	
	/**
	 * setAllButtonsIncative com el seu nom indica s'encarrega de desactivar tots els botons de la ventana 
	 * principal del menú client. 
	 */
	public void setAllButtonsInactive(){
		view.inactiveButtons();
	}
	
	/**
	 * Activa la connexió com a usuari convidat al sistema
	 */
	public void onGuest(){
		this.isGuest = 1;
	}
	
	/**
	 * Aques procediment ens permet omplir la taula de jugadors que es mostra a la opció ranking
	 * del menú principal del programa.
	 * @param table: Variable tipus JTable que mostra el ranking dels jugadors
	 */
	public void setTableView(JTable table){
		Object[] object = new Object[2];
		rankingView.resetTableRegister();
		for(int i=0;i<table.getRowCount();i++){
			object[0] = table.getValueAt(i, 0);
			object[1] = table.getValueAt(i, 1);
			rankingView.setDataTableRegister(object);
		}
	}
	
	ButtonController getButtonController(){
		return this;
	}
}
