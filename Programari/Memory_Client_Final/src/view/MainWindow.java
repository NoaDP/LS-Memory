package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	
	/**
	 * La classe MainWindow és la encarregada de crear la finestra gràfica que contindrà el menú principal del programa
	 * client. Aquest mostrarà 7 opcions diferents les quals podràn ser seleccionades per l'usuari mitjançant diferents botons.
	 */
	private JButton jbRegister;
	private JButton jbSessionOn;
	private JButton jbSessionOff;
	private JButton jbGuest;
	private JButton jbM1;
	private JButton jbM2;
	private JButton jbRanking;
	private JLabel  jtTitle;
	private JButton jbInformation;
	
	public MainWindow (){
		
		//creem el panell del menu principal
		jbRegister = new JButton();
		jbSessionOn = new JButton();
		jbSessionOff = new JButton();
		jbGuest = new JButton();
		jbM1 = new JButton();
		jbM2 = new JButton();
		jbRanking = new JButton();
		jbInformation = new JButton();
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		Icon icon = new ImageIcon("resources/LOGO_DPO.png");
		JLabel jtTitle = new JLabel("", icon, JLabel.CENTER);
		//this.add(jtTitle);
		
		JPanel logo = new JPanel();
	    logo.add(jtTitle);
	    gbc.gridy = 1;
	    gbc.gridx = 0;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.weighty = 1;
	    this.add(logo, gbc);
		
	    JPanel jpButtons = new JPanel();
	    jpButtons.setLayout(new GridLayout(8,1));
	    
		jbRegister.setFont(new Font("Futura", Font.PLAIN, 20));
		jbRegister.setText("Register");
		jbRegister.setSize(10, 20);
		jpButtons.add(jbRegister);
		
		jbSessionOn.setFont(new Font("Futura", Font.PLAIN, 20));
		jbSessionOn.setText("Log in");
		jpButtons.add(jbSessionOn);
		
		jbGuest.setFont(new Font("Futura", Font.PLAIN, 20));
		jbGuest.setText("Enter as Guest");
		jpButtons.add(jbGuest);
		
		jbM1.setFont(new Font("Futura", Font.PLAIN, 20));
		jbM1.setText("Time trial");
		jpButtons.add(jbM1);
		
		jbM2.setFont(new Font("Futura", Font.PLAIN, 20));
		jbM2.setText("Vs Machine");
		jpButtons.add(jbM2);
		
		jbRanking.setFont(new Font("Futura", Font.PLAIN, 20));
		jbRanking.setText("Ranking");
		jpButtons.add(jbRanking);
		
		jbInformation.setFont(new Font("Futura", Font.PLAIN, 20));
		jbInformation.setText("Game Information");
		jpButtons.add(jbInformation);
		
		jbSessionOff.setFont(new Font("Futura", Font.PLAIN, 20));
		jbSessionOff.setText("Log out");
		jpButtons.add(jbSessionOff);
		
		gbc.gridy = 1;
	    gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weighty = 1;
		this.add(jpButtons,gbc);
		
		jbRegister.setFocusable(false);
		jbSessionOn.setFocusable(false);
		jbSessionOff.setFocusable(false);
		jbGuest.setFocusable(false);
		jbM1.setFocusable(false);
		jbM2.setFocusable(false);
		jbRanking.setFocusable(false);
		jbInformation.setFocusable(false);
		
		
		jbSessionOff.setEnabled(false);
		jbM1.setEnabled(false);
		jbM2.setEnabled(false);
		jbRanking.setEnabled(false);
		jbInformation.setEnabled(false);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750, 480);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("**MemoTournament Client**");
	}
	
	/**
	 * El procediment RegisterController s'encarrega d'assignar l'etiqueta "Register" i un listener al botó jbRegister, 
	 * per tal de que, en cas de que l'usuari el premi, s'executi la finestra gràfica pertinent a través de la classe 
	 * ButtonController.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void RegisterController(ActionListener controller) {
		jbRegister.addActionListener(controller);
		jbRegister.setActionCommand("Register");
		System.out.println("Entra");
	}
	
	/**
	 * El procediment LogInController s'encarrega d'assignar l'etiqueta "Log in" i un listener al botó jbSessionOn, 
	 * per tal de que, en cas de que l'usuari el premi, s'executi a través de la classe ButtonController la finestra 
	 * gràfica dedicada al registre de usuaris.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void LogInController(ActionListener controller) {
		jbSessionOn.addActionListener(controller);
		jbSessionOn.setActionCommand("Log in");
		System.out.println("Entra");
	}
	
	/**
	 * El procediment LogOutController s'encarrega d'assignar l'etiqueta "Log out" i un listener al botó jbSessionOff, 
	 * per tal de que, en cas de que l'usuari el premi, aquest es desconecti del menú del joc.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void LogOutController(ActionListener controller) {
		jbSessionOff.addActionListener(controller);
		jbSessionOff.setActionCommand("Log out");
	}
	
	/**
	 * El procediment TimeTrialController s'encarrega d'assignar l'etiqueta "Time trial" i un listener al botó jbM1, 
	 * per tal de que, en cas de que l'usuari el premi, mitjançant el control a través de la classe ButtonController, es mostri
	 * una nova finestra que permeti seleccionar el mètode de joc al que desitja jugar l'usuari.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void TimeTrialController(ActionListener controller) {
		jbM1.addActionListener(controller);
		jbM1.setActionCommand("Time trial");
	}
	
	/**
	 * El procediment GuestController s'encarrega d'assignar l'etiqueta "Guest" i un listener al botó jbGuest, 
	 * per tal de que, en cas de que l'usuari el premi, el sistema reconeixi a través de la classe ButtonController que
	 * l'usuari ha accedit al sistema com a usuari convidat.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void GuestController(ActionListener controller) {
		jbGuest.addActionListener(controller);
		jbGuest.setActionCommand("Guest");
	}

	
	/**
	 * El procediment MachineController s'encarrega d'assignar l'etiqueta "Vs Machine" i un listener al botó jbM2, 
	 * per tal de que, en cas de que l'usuari el premi, la classe ButtonController s'encarregui de que es mostri una 
	 * nova finestra mitjançant la qual l'usuari pugui seleccionar el nivell de dificultat amb el que vol jugar.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void MachineController(ActionListener controller) {
		jbM2.addActionListener(controller);
		jbM2.setActionCommand("Vs Machine");
		System.out.println("Entra");
	}
	
	/**
	 * El procediment InformationController s'encarrega d'assignar l'etiqueta "Information" i un listener al botó jbInformation, 
	 * per tal de que, en cas de que l'usuari el premi, es mostri una finestra gràfica amb la informació relativa a la puntuació.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void InformationController(ActionListener controller) {
		jbInformation.addActionListener(controller);
		jbInformation.setActionCommand("Information");
	}
	
	/**
	 * El procediment RankingController s'encarrega d'assignar l'etiqueta "Ranking" i un listener al botó jbRanking, 
	 * per tal de que, en cas de que l'usuari el premi, es mostri una finestra gràfica amb la taula del rànking del joc.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void RankingController(ActionListener controller) {
		jbRanking.addActionListener(controller);
		jbRanking.setActionCommand("Ranking");
	}
	
	/**
	 * El pricediment ActivateButtons s'encarrega de activar els botons que abans de la connexió del usuari al programa client
	 * hauríen de romandre desactivats. Això permetrà a l'usuari accedir als diferents mètodes de joc, a la informació del joc, 
	 * al rànquing de la competició i a la opció de desconneixió.
	 */
	public void ActivateButtons(){
		jbSessionOff.setEnabled(true);
		jbM1.setEnabled(true);
		jbM2.setEnabled(true);
		jbRanking.setEnabled(true);
		jbInformation.setEnabled(true);
	}
	
	/**
	 * El procediment Occult s'encarrega de desactivar els botons que permeten conectar-se com a usuari registrat o com a usuari
	 * convidat al sistema. 
	 */
	public void Occult(){
		jbRegister.setEnabled(false);
		jbSessionOn.setEnabled(false);
		jbGuest.setEnabled(false);
	}
	
	/**
	 * El procediment Disable s'encarrega d'activar i desactivar els botons pertinents per tal de forçar una desconexió del sistema.
	 * És a dir, força que els únics botons accesibles siguin els dedicats a l'inici de sessió.
	 */
	public void Disable(){
		jbRegister.setEnabled(true);
		jbSessionOn.setEnabled(true);
		jbSessionOff.setEnabled(false);
		jbGuest.setEnabled(true);
		jbM1.setEnabled(false);
		jbM2.setEnabled(false);
		jbRanking.setEnabled(false);
		jbInformation.setEnabled(false);
	}
	
	/**
	 * El procediment inactivateButtons és l'encarregat de desactivar totes les opcions del menú principal, per a que l'usuari
	 * no hi pugui accedir a cap d'elles. 
	 */
	public void inactiveButtons(){
		jbRegister.setEnabled(false);
		jbSessionOn.setEnabled(false);
		jbSessionOff.setEnabled(false);
		jbGuest.setEnabled(false);
		jbM1.setEnabled(false);
		jbM2.setEnabled(false);
		jbRanking.setEnabled(false);
		jbInformation.setEnabled(false);
	}
}

