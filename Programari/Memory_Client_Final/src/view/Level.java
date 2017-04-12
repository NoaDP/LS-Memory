package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.ButtonController;

public class Level extends JFrame implements WindowListener{
	
	/**
	 * La classe Level s'encarrega de generar una finestra gràfica amb tres botons per a que l'usuari pugui 
	 * seleccionar la dificultat en la que vol jugar: "Easy", "Normal" o "Hard".
	 */
	private JButton jbEasy;
	private JButton jbNormal;
	private JButton jbHard;
	private JLabel jtDifficulty; 
	private ButtonController controller;
	
	public Level(){
	
	jbEasy = new JButton();
	jbNormal = new JButton();
	jbHard = new JButton();
	jtDifficulty = new JLabel();
	
	JPanel jpLevelSelection = new JPanel(new GridLayout(4,1));
	jtDifficulty.setText("Select Difficulty");
	jtDifficulty.setFont(new Font("FUTURA", Font.PLAIN, 25));
	jtDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
	jbEasy.setText("Easy Mode");
	jbNormal.setText("Normal Mode");
	jbHard.setText("Hard Mode");

	
	
	jpLevelSelection.add(jtDifficulty);
	jpLevelSelection.add(jbEasy);
	jpLevelSelection.add(jbNormal);
	jpLevelSelection.add(jbHard);
	
	this.getContentPane().add(jpLevelSelection, BorderLayout.CENTER);
	
	this.setSize(450, 300);
	this.setLocationRelativeTo(null);
	this.setTitle("**TimeTrial**");
	addWindowListener(this);

	}
	
	/**
	 * Aquest procediment permet afegir la etiqueta "Easy" i un controlador al botó per tal de que, quan l'usuari
	 * el premi, el sistema reconegui que aquest ha estat seleccionat.
	 * @param controller: Variable del tipus ActionController que ens permet afegir la etiqueta "Easy" al
	 * botó per tal de que aquest pugui ser reconegut al ser pres.
	 */
	public void EasyController(ActionListener controller) {
		jbEasy.addActionListener(controller);
		jbEasy.setActionCommand("Easy");
	}
	
	/**
	 * Aquest procediment permet afegir la etiqueta "Normal" i un controlador al botó per tal de que, quan l'usuari
	 * el premi, el sistema reconegui que aquest ha estat seleccionat.
	 * @param controller: Variable del tipus ActionController que ens permet afegir la etiqueta "Normal" al
	 * botó per tal de que aquest pugui ser reconegut al ser pres.
	 */
	public void NormalController(ActionListener controller) {
		jbNormal.addActionListener(controller);
		jbNormal.setActionCommand("Normal");
	}
	
	/**
	 * Aquest procediment permet afegir la etiqueta "Hard" i un controlador al botó per tal de que, quan l'usuari
	 * el premi, el sistema reconegui que aquest ha estat seleccionat.
	 * @param controller: Variable del tipus ActionController que ens permet afegir la etiqueta "Hard" al
	 * botó per tal de que aquest pugui ser reconegut al ser pres.
	 */
	public void HardController(ActionListener controller) {
		jbHard.addActionListener(controller);
		jbHard.setActionCommand("Hard");
	}
	
	public void setController(ButtonController controller){
		this.controller = controller;
	}
	
	/**
	 * Aquest procediment ens permet tornar a activar els botons pertinents de la finestra principal
	 * un cop la finestra level es tanca.
	 */
	public void windowClosing(WindowEvent e){
		System.out.println("cierra");
    	controller.setButtonsActive();
        //this.dispose();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("cierra");
    	controller.setButtonsActive();
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
