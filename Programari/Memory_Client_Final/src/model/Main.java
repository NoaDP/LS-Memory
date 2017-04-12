package model;

import javax.swing.SwingUtilities;

import controller.ButtonController;
import controller.GuestController;
import controller.TrialController;
import network.Client;
import view.LogIn;
import view.TimeTrial;
import view.MainWindow;
import view.Registration;

public class Main {

	/** 
	 * La classe Main és la classe principal del programa client de MemoTournament. Aquesta classe s'encarrega 
	 * d'inicialitzar el controlador de la finestra gràfica principal i la finestra. També és l'encarregada de 
	 * la inicialització de la resta de controladors. 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
			
			@Override
			public void run(){
				
				Configuracio configuracio = new Configuracio();
				String[] info = configuracio.llegir();				
				
				MainWindow view = new MainWindow();
				TimeTrial tt = new TimeTrial();
				view.setVisible(true);
				Client client = new Client();
				ButtonController controller = new ButtonController(view,client,Integer.parseInt(info[1]),info[0]);
				GuestController guest = new GuestController(controller);
				view.RegisterController(controller);
				view.LogInController(controller);
				view.LogOutController(controller);
				view.TimeTrialController(controller);
				view.GuestController(controller);
				view.MachineController(controller);
				view.InformationController(controller);
				view.RankingController(controller);
			}
		}
		);
	}
}