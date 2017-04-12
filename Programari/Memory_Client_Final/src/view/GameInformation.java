package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameInformation extends JFrame{
	
	/**
	 * GameInformation és una classe que s'encarrega de generar una finestra gràfica on es mostri la informació
	 * de la distribució de punts segons el joc i la dificultat. 
	 */
	private JPanel jpPrincipal;
	private JLabel jtInformation;
	private JLabel jtTitle;
	
	public GameInformation(){
		
		jpPrincipal = new JPanel(new FlowLayout());
		((FlowLayout)jpPrincipal.getLayout()).setAlignment(FlowLayout.CENTER);
		JPanel jpSetInfo = new JPanel(new GridLayout(9,1));
		jtInformation = new JLabel();
		jtInformation.setFont(new Font("Futura", Font.PLAIN, 25));
		jtInformation.setText("Classic Mode");
		JLabel jtInformation2 = new JLabel();
		jtInformation2.setText("\t 10 Points Match + 10 Points Extra per Second");
		jtInformation2.setFont(new Font("Futura", Font.PLAIN, 20));
		JLabel jtInformation3 = new JLabel();
		jtInformation3.setText("Focus Mode:");
		jtInformation3.setFont(new Font("Futura", Font.PLAIN, 25));
		JLabel jtInformation4 = new JLabel();
		jtInformation4.setText("\t 10 Points Match + 10 Points Extra per Second");
		jtInformation4.setFont(new Font("Futura", Font.PLAIN, 20));
		JLabel jtInformation5 = new JLabel();
		jtInformation5.setText("Machine Mode:");
		jtInformation5.setFont(new Font("Futura", Font.PLAIN, 25));
		JLabel jtInformation6 = new JLabel();
		jtInformation6.setText("\t Easy: 10 Points Match + 10 Bonus Points");
		jtInformation6.setFont(new Font("Futura", Font.PLAIN, 20));
		JLabel jtInformation7 = new JLabel();
		jtInformation7.setText("\t Normal: 10 Points Match + 20 Bonus Points");
		jtInformation7.setFont(new Font("Futura", Font.PLAIN, 20));
		JLabel jtInformation8 = new JLabel();
		jtInformation8.setText("\t Hard: 10 Points Match + 30 Bonus Points");
		jtInformation8.setFont(new Font("Futura", Font.PLAIN, 20));
		jtTitle = new JLabel();
		jtTitle.setText("**MEMOTOURNAMENT GAME INFORMATION**");
		jtTitle.setFont(new Font("Futura", Font.BOLD, 30));
		
		jpSetInfo.add(jtTitle);
		jpSetInfo.add(jtInformation);
		jpSetInfo.add(jtInformation2);
		jpSetInfo.add(jtInformation3);
		jpSetInfo.add(jtInformation4);
		jpSetInfo.add(jtInformation5);
		jpSetInfo.add(jtInformation6);
		jpSetInfo.add(jtInformation7);
		jpSetInfo.add(jtInformation8);

		jpPrincipal.add(jpSetInfo, BorderLayout.PAGE_END);
		
		this.getContentPane().add(jpPrincipal, BorderLayout.CENTER);
		
		this.setSize(750, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("**MemoTournament Client**");
	}
	}


