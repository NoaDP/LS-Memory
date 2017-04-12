package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LogIn extends JFrame {
	
	/**
	 * La classe LogIn s'encarrega de generar una finestra gràfica amb dos camps de text que serviràn per a inserir
	 * la informació del usuari que es vol registrar. Aquesta mostra un camp de "Nickname" i una altre de "Password".
	 * També mostra un botó que permet enviar les dades.  
	 */
	private JButton jbStart;
	private JLabel jtTSession;
	private JLabel jtNickname;
	private JLabel jtPassword;
	private JTextField jtUser;
	private JTextField jtUserP;
	private JPanel jpLogIn;
	
	public LogIn(){
		jbStart = new JButton();
		jtTSession = new JLabel();
		jtPassword = new JLabel();
		jtNickname = new JLabel(); 
		jtUser = new JTextField();
		jtUserP = new JTextField();
		
		jpLogIn = new JPanel(new GridLayout(6,1));
		jtTSession.setText("START SESSION");
		jtTSession.setFont(new Font("FUTURA", Font.PLAIN, 25));
		jtTSession.setHorizontalAlignment(SwingConstants.CENTER);
		jtNickname.setText("Enter your Nickname:");
		jtPassword.setText("Enter your Password");
		jbStart.setText("LogIn");
				
		jpLogIn.add(jtTSession);
		jpLogIn.add(jtNickname);
		jpLogIn.add(jtUser);
		jpLogIn.add(jtPassword);
		jpLogIn.add(jtUserP);
		jpLogIn.add(jbStart);
		
		this.getContentPane().add(jpLogIn, BorderLayout.CENTER);
		
		this.setSize(350, 200);
		this.setLocationRelativeTo(null);
		this.setTitle("**LogIn**");
		
	}
	
	/**
	 * 
	 * @param controller
	 */
	public void LoginControl (ActionListener controller){
		jbStart.addActionListener(controller);
		jbStart.setActionCommand("LogIn");
		//this.jpLogIn.setVisible(false);
	}
	
	/**
	 * La funció Blancs s'encarrega de comprovar que tots els camps de text estiguin omplerts. En cas de que 
	 * hi hagui algún camp en blanc, aquest retornarà true, en cas contrari retornarà false.
	 * @return: variable del tipus boolean que descriu si hi ha camps blancs o no.
	 */
	public boolean Blancs (){
		if (this.typedNickname().isEmpty()) return true;
		if (this.typedPassword().isEmpty()) return true;
		return false;
	}
	
	/**
	 * La funció typedNickname s'encarrega de retornar la String inserida en el camp de text jtUser.
	 * @return: String amb el nickname inserit per l'usuari
	 */
	public String typedNickname (){
		return jtUser.getText();
	}
	
	/**
	 * La funció typedPassword s'encarrega de retornar la String inserida en el camp de text jtUserP.
	 * @return: String amb el password inserit per l'usuari
	 */
	public String typedPassword(){
		return jtUserP.getText();
	}
	
	
}
