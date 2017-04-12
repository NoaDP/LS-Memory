package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Memo1_1.Board;

public class TimeTrial extends JFrame {
	
	/**
	 * La classe TimeTrial és l'encarregada de generar una finestra gràfica mitjançant la qual l'usuari podrà 
	 * seleccionar el mètode de joc al que vol jugar: el mode "Focus" o el mode "Classic". 
	 */
	private JButton jbClassic;
	private JButton jbFocus;
	private JLabel jtTSession;

	
	public TimeTrial(){
		jbClassic = new JButton();
		jbFocus = new JButton();
		jtTSession = new JLabel(); 
		
		JPanel jpTimeTrial = new JPanel(new GridLayout(3,1));
		jtTSession.setText("WELCOME TO TIME TRIAL!");
		jtTSession.setFont(new Font("FUTURA", Font.PLAIN, 25));
		jtTSession.setHorizontalAlignment(SwingConstants.CENTER);
		jbClassic.setText("Classic Mode");
		jbFocus.setText("Focus Mode");
		
		
		jpTimeTrial.add(jtTSession);
		jpTimeTrial.add(jbClassic);
		jpTimeTrial.add(jbFocus);
		
		this.getContentPane().add(jpTimeTrial, BorderLayout.CENTER);
		
		this.setSize(350, 200);
		this.setLocationRelativeTo(null);
		this.setTitle("**TimeTrial**");
		
	}
	
	/**
	 * El procediment FocusController s'encarrega d'assignar l'etiqueta "Classic" i un listener al botó jbFocus, 
	 * per tal de que, en cas de que l'usuari el premi, s'executi alguna acció a través del controlador.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void FocusController(ActionListener controller) {
		jbFocus.setActionCommand("Focus");
		jbFocus.addActionListener(controller);
	}
	
	/**
	 * El procediment ClassicController s'encarrega d'assignar l'etiqueta "Classic" i un listener al botó jbClassic, 
	 * per tal de que, en cas de que l'usuari el premi, s'executi alguna acció a través del controlador.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void ClassicController(ActionListener controller) {
		jbClassic.setActionCommand("Classic");
		jbClassic.addActionListener(controller);
	
	}
	
	/**
	 * Aquest procediment ens permet que, en tancar la finestra, aquesta no es mostri més.
	 */
	public void closeWindow(){
		this.setVisible(false);
	}
	
}	