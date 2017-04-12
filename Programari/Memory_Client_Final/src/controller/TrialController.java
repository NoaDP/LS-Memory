package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import network.Client;
import network.ServerConnection;
import view.Level;
import view.TimeTrial;

public class TrialController implements ActionListener{
	
	/**
	 * La classe TrialController s'enacrrega de gestionar la informació obtinguda a través de la finestra TimeTrial del menú principal
	 * del programa Client. Aqusta classe reb com a paràmetres la finestra TimeTrial, la connexió amb el servidor, el client que vol jugar, 
	 * i el controlador principal del programa client. En funció del mètode de joc seleccionat, es generarà una nova finestra amb la selecció de
	 * la dificultat de joc. 
	 */
	private TimeTrial view;
	private int isGuest;
	private ServerConnection sConnect;
	private Client client;
	private ButtonController controler;
	
	public TrialController (TimeTrial tt, int isGuest,ServerConnection sConnect,Client client,ButtonController controler){
		this.view = tt;
		this.isGuest = isGuest;
		this.sConnect=sConnect;
		this.client = client;
		this.controler = controler;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Focus")){
			Level level = new Level();
			level.setVisible(true);
			level.setController(controler);
			LevelController levelController = new LevelController(level, 2, isGuest,sConnect,client,controler);
			level.EasyController(levelController);
			level.NormalController(levelController);
			level.HardController(levelController);
			view.closeWindow();

		}
		if(e.getActionCommand().equals("Classic")){
			Level level = new Level();
			level.setVisible(true);
			level.setController(controler);
			LevelController levelController = new LevelController(level, 1, isGuest,sConnect,client,controler);
			level.EasyController(levelController);
			level.NormalController(levelController);
			level.HardController(levelController);
			view.closeWindow();

		}

		
	}

}
