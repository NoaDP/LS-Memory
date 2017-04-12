package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Registration extends JFrame{
	
	/**
	 * La classe Registration és la classe encarregada de generar una finestra gràfica on es mostraràn una sèrie
	 * de box de text les quals serveixen per a realitzar el procés de creació d'un nou usuari. L'usuari podrà
	 * inserir la informaió dels camps: "Name", "Surname", "Nickname" i "Password". Un cop omplerts els camps,
	 * l'usuari podrà prèmer el botó "Register".
	 */
	private JTextField jtfName;
	private JTextField jtfSurname;
	private JTextField jtfNickname;
	private JTextField jtfPassword;
	private JButton jbRegister;
	
	public  Registration(){
		//Creem el panell de registre
		jtfName = new JTextField();
		jtfSurname = new JTextField();
		jtfNickname = new JTextField();
		jtfPassword = new JTextField();
		JLabel jtTRegister = new JLabel();
		jbRegister = new JButton();
		jbRegister.setForeground(Color.white);
		jbRegister.setBackground(Color.black);
		JPanel jpReg = new JPanel(new GridLayout(6,1));
		JPanel jpName = new JPanel(new FlowLayout());
		JPanel jpSurname = new JPanel(new FlowLayout());
		JPanel jpNickname= new JPanel(new FlowLayout());
		JPanel jpPassword= new JPanel(new FlowLayout());
		JPanel jpButton= new JPanel(new FlowLayout());
		((FlowLayout) jpName.getLayout()).setAlignment(FlowLayout.LEFT);
		
		
		jtTRegister.setText("REGISTRATION");
		jtTRegister.setFont(new Font("FUTURA", Font.PLAIN, 25));
		jtTRegister.setHorizontalAlignment(SwingConstants.CENTER);
		
		jpReg.add(jtTRegister);
		
		jpName.add(new JLabel("Name: "));
		jtfName.setPreferredSize(new Dimension(325, 28));
		jpName.add(jtfName);
		((FlowLayout) jpSurname.getLayout()).setAlignment(FlowLayout.LEFT);
		jpSurname.add(new JLabel("Surname: "));
		jtfSurname.setPreferredSize(new Dimension(320, 28));
		jpSurname.add(jtfSurname);
		((FlowLayout) jpNickname.getLayout()).setAlignment(FlowLayout.LEFT);
		jpNickname.add(new JLabel("Nickname: "));
		jtfNickname.setPreferredSize(new Dimension(328, 28));
		jpNickname.add(jtfNickname);
		((FlowLayout) jpPassword.getLayout()).setAlignment(FlowLayout.LEFT);
		jpPassword.add(new JLabel("Password: "));
		jtfPassword.setPreferredSize(new Dimension(328, 28));
		jpPassword.add(jtfPassword);
		
		((FlowLayout) jpButton.getLayout()).setAlignment(FlowLayout.RIGHT);
		jbRegister.setText("Register");
		jpButton.add(jbRegister);
		
		jbRegister.setContentAreaFilled(false);
		//jbRegister.setOpaque(true);
		
		jpReg.add(jpName);
		jpReg.add(jpSurname);
		jpReg.add(jpNickname);
		jpReg.add(jpPassword);
		jpReg.add(jpButton);
				
		this.getContentPane().add(jpReg, BorderLayout.CENTER);
		this.setSize(420, 300);
		this.setLocationRelativeTo(null);
		this.setTitle("**Registration**");

	}
	
	/**
	 * El procediment RegisterControl permet assignar la etiqueta "Registration" i un controlador al botó
	 * jbRegister per a que, quan l'usuari el premi s'executin les accions pertinents al controlador.
	 * @param controller: paràmetre del tipus ActionListener que permetrà que la acció del botó es realitzi.
	 */
	public void RegisterControl (ActionListener controller){
		jbRegister.addActionListener(controller);
		jbRegister.setActionCommand("Registration");
//		jbRegister.setVista(this);
	}
	
	/**
	 * El procediment getCampsRegistration s'encarrega de retornar la informació inserida en els camps de text de 
	 * la finestra.
	 * @return: Array de String amb la informació dels camps omplerts.
	 */
	public String[] getCampsRegistration(){
		String[] campsRegistration = new String[4];
		campsRegistration[0] = jtfName.getText().toString();
		campsRegistration[1] = jtfSurname.getText().toString();
		campsRegistration[2] = jtfNickname.getText().toString();
		campsRegistration[3] = jtfPassword.getText().toString();
		return campsRegistration;
	}
}
