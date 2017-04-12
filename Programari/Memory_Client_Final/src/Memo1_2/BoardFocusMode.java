package Memo1_2;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import Memo1_1.Card;
import controller.ButtonController;
import network.Client;
import network.ServerConnection;

public class BoardFocusMode extends JFrame implements WindowListener{

	/**
	 * La classe BoardFocusMode és l'encarregada de crear i modificar els elements que componen el tauler de joc del mode
	 * "Focus" en dificultat fàcil.
	 */
	protected ArrayList<CardFocus> cardsList;
	protected CardFocus selectedCard;
	protected CardFocus c1;
	protected CardFocus c2;
	protected Timer t;
	protected CircleProgressBarFocusMode clock;
	public int points = 0;
	protected JLabel jtPuntuacio;
	protected JLabel jtUsuari;
	protected int end = 0;
	protected JPanel pane;
	protected JPanel superior;
	protected JPanel jpClock;
	protected int columnes ;
	protected int files;
	private ServerConnection sConnect;
	private Client client;
	private ButtonController controler;
	private int isGuest;
	private int level;

	public BoardFocusMode(int files,int columnes, int pairs, int temps,int isGuest,ServerConnection sConnect,Client client,ButtonController controler ,int level){

		cardsList = new ArrayList<CardFocus>();
		ArrayList<CardFocus> cardsListAux = new ArrayList<CardFocus>();
		List<Integer> cardVals = new ArrayList<Integer>();
		this.isGuest = isGuest;
		this.sConnect = sConnect;
		this.client = client;
		this.controler = controler;
		client.setModo(2);
		
		for (int i = 0; i < pairs; i++){
			cardVals.add(i);
			cardVals.add(i);
		}
		Collections.shuffle(cardVals);
		
		for (int val : cardVals){
			final CardFocus c = new CardFocus();
			c.setId(val);
			c.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					selectedCard = c;
					isGameEnd(clock);
					doTurn();
				}
			});
			cardsList.add(c);
		}
		this.cardsList = cardsList;
		//Inicia
		t = new Timer(750, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				checkCards();
			}
		});
		
		
		for(int i = 0; i< cardsList.size(); i++){
			switch (cardsList.get(i).getId()){
			case 0:
				try{
					File file = new File("resources/c0.jpg");
					Image i55 = ImageIO.read(file);
					cardsList.get(i).setIcon(new ImageIcon(i55));
				} catch (IOException ex){}	
				break;
			case 1:
				try{
					File file = new File("resources/c1.jpg");
					Image i55 = ImageIO.read(file);
					cardsList.get(i).setIcon(new ImageIcon(i55));
				} catch (IOException ex){}	
				break;
			case 2:
				try{
					File file = new File("resources/c2.jpg");
					Image i55 = ImageIO.read(file);
					cardsList.get(i).setIcon(new ImageIcon(i55));
				} catch (IOException ex){}	
				break;
			case 3:
				try{
					File file = new File("resources/c3.jpg");
					Image i55 = ImageIO.read(file);
					cardsList.get(i).setIcon(new ImageIcon(i55));
				} catch (IOException ex){}	
				break;
			case 4:
				try{
					File file = new File("resources/c4.jpg");
					Image i55 = ImageIO.read(file);
					cardsList.get(i).setIcon(new ImageIcon(i55));
				} catch (IOException ex){}	
				break;

			}	
		}
		GiraCartesFocus giraCartesFocus = new GiraCartesFocus();
        giraCartesFocus.setCards(this.getCards());
        giraCartesFocus.setTime(5000);
        Thread threadAux = new Thread(giraCartesFocus);
        threadAux.start();
		
        t.setRepeats(false);


		pane = new JPanel();
		pane.setLayout(new GridLayout(files, columnes));

		jtPuntuacio = new JLabel();
		jtPuntuacio.setText("Punts "+ points);
		jtPuntuacio.setFont(new Font("Futura", Font.BOLD, 30));
		jtUsuari= new JLabel();
		if (isGuest != 1)jtUsuari.setText("  User: " + client.getNickname());
		if (isGuest == 1)jtUsuari.setText("  User: Guest");
		jtUsuari.setFont(new Font("Futura", Font.BOLD, 30));


		JPanel jpAux = new JPanel(new BorderLayout());
		superior = new JPanel();
		superior.setLayout(new GridLayout(1, 3));

		jpClock = new JPanel();

		clock = new CircleProgressBarFocusMode();
		clock.setBoard(this);
		clock.incrementOverTime(temps);
		jpClock.add(clock);   

		superior.add(jtUsuari);
		superior.add(jpClock);
		superior.add(jtPuntuacio);

		jpAux.add(superior);

		this.getContentPane().add(jpAux, BorderLayout.PAGE_START);
		this.getContentPane().add(pane, BorderLayout.CENTER);


		for (CardFocus c : cardsList){
			pane.add(c);
		}

		setTitle("Memory Match"); 
		
		addWindowListener(this);
		
	}
	

	/**
	 * El procediment windowClosing és l'encarregat de dur a terme accions predeterminades en el moment que es tanca
	 * la finsestra gràfica. Un cop es tanqui, el rellotge del joc s'aturarà.
	 */

	public void windowClosing(WindowEvent e){
    	clock.isEnd();
	}
	

	/**
     * El procediment doTurn s'encarrega de simular el moviment de girar les dues cartes seleccionades per l'usuari per tal de
     * comprovar si aquestes són iguals o no. Un cop voltejades, es crida a la classe encarregada de comprovar si les cartes 
     * seleccionades són iguals.
     */
	public void doTurn(){

		//primera carta que seleccionen
		if (c1 == null && c2 == null){
			c1 = selectedCard;
			
		}

		//Al pulsar la segona carta es mostren les dues durant un cert temps 
		if (c1 != null && c1 != selectedCard && c2 == null){
			c2 = selectedCard;
			t.start();            

		}
	}


	/**
     * El procediment checkCards s'encarrega de comprovar si les cartes seleccionades durant doTurn tenen la mateixa id, és a dir,
     * són iguals, o no. En cas de que les id coincideixi, les cartes, programades com a botons es desactiven per a que no puguin 
     * ser seleccionades i el seu dibux queda descativat, apareixent més fosc a al finestra, tot seguit s'eliminen de la llista de 
     * cartes de la classe per a que, durant el torn de la màquina aquesta no les pugui voltejar accidentalment. Després s'actualitzen
     * els punts de l'usuari i, en cas de que no quedin més cartes es mostra una finestra dient que s'ha acabat la partida.
     */
	public void checkCards(){

		//Les cartes coincideixen
		if (c1.getId() == c2.getId()){//match condition
			if(level == 0){
				switch (c1.getId()){
				case 0:
					try{
						File file = new File("resources/c0.jpg");
						Image i55 = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i55));
					} catch (IOException ex){}	
					break;
				case 1:
					try{
						File file = new File("resources/c1.jpg");
						Image i55 = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i55));
					} catch (IOException ex){}	
					break;
				case 2:
					try{
						File file = new File("resources/c2.jpg");
						Image i55 = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i55));
					} catch (IOException ex){}	
					break;
				case 3:
					try{
						File file = new File("resources/c3.jpg");
						Image i55 = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i55));
					} catch (IOException ex){}	
					break;
				case 4:
					try{
						File file = new File("resources/c4.jpg");
						Image i55 = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i55));
					} catch (IOException ex){}	
					break;

				}	
				
				switch (c2.getId()){
				case 0:
					try{
						File file = new File("resources/c0.jpg");
						Image i55 = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i55));
					} catch (IOException ex){}	
					break;
				case 1:
					try{
						File file = new File("resources/c1.jpg");
						Image i55 = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i55));
					} catch (IOException ex){}	
					break;
				case 2:
					try{
						File file = new File("resources/c2.jpg");
						Image i55 = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i55));
					} catch (IOException ex){}	
					break;
				case 3:
					try{
						File file = new File("resources/c3.jpg");
						Image i55 = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i55));
					} catch (IOException ex){}	
					break;
				case 4:
					try{
						File file = new File("resources/c4.jpg");
						Image i55 = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i55));
					} catch (IOException ex){}	
					break;

				}	
			}
			if (level == 1){
				switch (c1.getId()){
				case 0:
					try{
						File file = new File("resources/0.jpg");
						Image i = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 1:
					try{
						File file = new File("resources/1.jpg");
						Image i = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 2:
					try{
						File file = new File("resources/2.jpg");
						Image i = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 3:
					try{
						File file = new File("resources/3.jpg");
						Image i = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 4:
					try{
						File file = new File("resources/4.jpg");
						Image i = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i));;
					} catch (IOException ex){}	
					break;
				case 5:
					try{
						File file = new File("resources/5.jpg");
						Image i = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 6:
					try{
						File file = new File("resources/6.jpg");
						Image i = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 7:
					try{
						File file = new File("resources/d7.jpg");
						Image i = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 8:
					try{
						File file = new File("resources/8.jpg");
						Image i = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 9:
					try{
						File file = new File("resources/9.jpg");
						Image i = ImageIO.read(file);
						c1.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				}
				switch (c2.getId()){
				case 0:
					try{
						File file = new File("resources/0.jpg");
						Image i = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 1:
					try{
						File file = new File("resources/1.jpg");
						Image i = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 2:
					try{
						File file = new File("resources/2.jpg");
						Image i = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 3:
					try{
						File file = new File("resources/3.jpg");
						Image i = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 4:
					try{
						File file = new File("resources/4.jpg");
						Image i = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i));;
					} catch (IOException ex){}	
					break;
				case 5:
					try{
						File file = new File("resources/5.jpg");
						Image i = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 6:
					try{
						File file = new File("resources/6.jpg");
						Image i = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 7:
					try{
						File file = new File("resources/d7.jpg");
						Image i = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 8:
					try{
						File file = new File("resources/8.jpg");
						Image i = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				case 9:
					try{
						File file = new File("resources/9.jpg");
						Image i = ImageIO.read(file);
						c2.setIcon(new ImageIcon(i));
					} catch (IOException ex){}	
					break;
				}


			}
			
			if (level == 2){
				switch (c1.getId()){
				case 0:
					try{
						File file = new File("resources/d0.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 1:
					try{
						File file = new File("resources/d1.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 2:
					try{
						File file = new File("resources/d2.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 3:
					try{
						File file = new File("resources/d3.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 4:
					try{
						File file = new File("resources/d4.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 5:
					try{
						File file = new File("resources/d5.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 6:
					try{
						File file = new File("resources/d6.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 7:
					try{
						File file = new File("resources/d7.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 8:
					try{
						File file = new File("resources/d8.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 9:
					try{
						File file = new File("resources/d9.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;

				case 10:
					try{
						File file = new File("resources/d10.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;

				case 11:
					try{
						File file = new File("resources/d11.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 12:
					try{
						File file = new File("resources/d12.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 13:
					try{
						File file = new File("resources/d13.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 14:
					try{
						File file = new File("resources/d14.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c1.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				}
				
				switch (c2.getId()){
				case 0:
					try{
						File file = new File("resources/d0.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 1:
					try{
						File file = new File("resources/d1.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 2:
					try{
						File file = new File("resources/d2.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 3:
					try{
						File file = new File("resources/d3.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 4:
					try{
						File file = new File("resources/d4.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 5:
					try{
						File file = new File("resources/d5.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 6:
					try{
						File file = new File("resources/d6.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 7:
					try{
						File file = new File("resources/d7.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 8:
					try{
						File file = new File("resources/d8.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 9:
					try{
						File file = new File("resources/d9.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;

				case 10:
					try{
						File file = new File("resources/d10.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;

				case 11:
					try{
						File file = new File("resources/d11.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 12:
					try{
						File file = new File("resources/d12.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 13:
					try{
						File file = new File("resources/d13.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 14:
					try{
						File file = new File("resources/d14.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						c2.setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				}


			}
				
			c1.setEnabled(false); //disables the button
			c2.setEnabled(false);
			c1.setMatched(true); //flags the button as having been matched
			c2.setMatched(true);
			if (c1.getMatched() == true && c1.getMatched()) {
				points += 10;
				//System.out.println("Punts:" +points);
				jtPuntuacio.setText("Punts "+points);
			}
			if (this.isGameWon(clock)){
				clock.incrementOverTime(-100);
				JOptionPane.showMessageDialog(this, "Has guanyat!");
				if(isGuest != 1){
					sendServer(client);
				}
				this.setVisible(false);

			}
		}else{
			//Amaga les imatges que no son parelles
			c1.setIcon(null);
			c2.setIcon(null);
		}
		c1 = null; //reset c1 and c2
		c2 = null;
	}

	/**
	 * La funció isGameWon s'encarrega d'avaluar si el joc ha acabat o no. Per això comprova si totes les cartes han estat
	 * emparellades o no. En cas de que haguin estat emparellades retornarà un true, calcularà la puntuació final obtinguda
	 * per l'usuari i acabarà amb el funcionament del rellotje. En cas contrari, retornarà un false;
	 * @param c: variable tipus CircleProcessBarFocusMode que serveix per mostrar i calcular el temps que ha passat del
	 * joc i el que encara queda. 
	 * @return: Varible tipus boolea que defineix si el joc ha acabat o no.
	 */
	public boolean isGameWon(CircleProgressBarFocusMode c){
		int t = (100 - c.getProgress())*2;
		for(CardFocus card: this.cardsList){
			if (card.getMatched() == false){
				return false;
			}
		}
		points = points + t;
		jtPuntuacio.setText("Punts "+ points);
		client.setPunts(points);
		c.win = true;
		return true;
	}

	/**
	 * Aquesta funció s'encarrega de acabar la partida en cas de que el temps s'hagui esgotat. En cas de que es doni, desactivarà
	 * totes les cartes per a que el usuari no pugui continuar jugant.
	 * @param c: variable tipus CircleProcessBarFocusMode que serveix per mostrar i calcular el temps que ha passat del
	 * joc i el que encara queda. 
	 * @return: Variable tipus boolea que se encarrega de definir si la partida s'ha acabat o no
	 */
	public boolean isGameEnd(CircleProgressBarFocusMode c){
		int end = c.message;
		boolean fin = c.fin;
		
		//end == 0 
		if (fin){
			for(CardFocus carta: this.cardsList){
				carta.setEnabled(false); 
			}   

			return true;       
		}
		return false;

	}

	public void enableClick(){
		this.pane.disable();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public List<CardFocus> getCards(){
		return cardsList;
	}
	
	public void sendServer(Client client){
		sConnect.sendMessage(4, client);
	}

	public void setLevel(int level){
		this.level = level;
	}

	public int getPoints(){
		return this.points;
	}
	
	public Client getClient(){
		return this.client;
	}
}
