package Memo1_1;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.ButtonController;
import network.Client;
import network.ServerConnection;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Board extends JFrame implements WindowListener{

	protected List<Card> cards;
	protected Card selectedCard;
	protected Card c1;
	protected Card c2;
	protected Timer t;
	protected CircleProgressBar clock;
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


	/**
	 * Aquest es el constructor de la classe Board. La seva finalitat es generar el taulell de joc del mode "Classic". 
	 * Es utilitzat com a super per les classes Board2 i Board3. Aquesta classe implementaraà el mode fàcil (Easy Mode). 
	 * @param files : Indica el numero de files que tindra el taulell depenent del mode de dificultat. 
	 * @param columnes : Indica el numero de columnes que tindra el taulell depenent del mode de dificultat.
	 * @param pairs : Indica el numero de parelles de cartes depenent del mode de dificultat.
	 * @param temps : Temps que durara el joc depenent del mode de dificultat.
	 */
	public Board(int files,int columnes, int pairs, int temps,int isGuest,ServerConnection sConnect,Client client,ButtonController controler){



		List<Card> cardsList = new ArrayList<Card>();
		List<Integer> cardVals = new ArrayList<Integer>();
		this.isGuest = isGuest;
		this.sConnect = sConnect;
		this.client = client;
		this.controler = controler;
		client.setModo(1);

		for (int i = 0; i < pairs; i++){
			cardVals.add(i);
			cardVals.add(i);
		}
		Collections.shuffle(cardVals);
		
		for (int val : cardVals){
			final Card c = new Card();
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
		this.cards = cardsList;
		//Inicia
		t = new Timer(750, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				checkCards();
			}
		});

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

		clock = new CircleProgressBar(client);
		clock.setBoard(this);
		clock.incrementOverTime(temps);
		jpClock.add(clock);   

		//jpClock = new JPanel();
		//JPanel jpPoints = new JPanel();

		superior.add(jtUsuari);
		superior.add(jpClock);
		superior.add(jtPuntuacio);

		jpAux.add(superior);

		this.getContentPane().add(jpAux, BorderLayout.PAGE_START);
		this.getContentPane().add(pane, BorderLayout.CENTER);


		for (Card c : cards){
			pane.add(c);
		}

		setTitle("Memory Match"); 
		
		addWindowListener(this);
	
	}
	
	/**
	* Metode que controla el tancament de la finestra
	*/
	public void windowClosing(WindowEvent e){
    	clock.isEnd();
        //this.dispose();
	}
	
	/**
	 * Metode que carrega les imatges a les cartes (JButtons) segons el seu Id (enter) i les mostra.
	 */
	public void doTurn(){

		//primera carta que seleccionen
		if (c1 == null && c2 == null){
			c1 = selectedCard;
			switch (c1.getId()){
			case 0:
				try{
					File file = new File("resources/c0.jpg");
					Image i = ImageIO.read(file);
					c1.setIcon(new ImageIcon(i));
				} catch (IOException ex){}	
				break;
			case 1:
				try{
					File file = new File("resources/c1.jpg");
					Image i = ImageIO.read(file);
					c1.setIcon(new ImageIcon(i));
				} catch (IOException ex){}	
				break;
			case 2:
				try{
					File file = new File("resources/c2.jpg");
					Image i = ImageIO.read(file);
					c1.setIcon(new ImageIcon(i));
				} catch (IOException ex){}	
				break;
			case 3:
				try{
					File file = new File("resources/c3.jpg");
					Image i = ImageIO.read(file);
					c1.setIcon(new ImageIcon(i));
				} catch (IOException ex){}	
				break;
			case 4:
				try{
					File file = new File("resources/c4.jpg");
					Image i = ImageIO.read(file);
					c1.setIcon(new ImageIcon(i));;
				} catch (IOException ex){}	
				break;

			}
			//c1.setText(String.valueOf(c1.getId()));
		}

		//Al pulsar la segona carta es mostren les dues durant un cert temps 
		if (c1 != null && c1 != selectedCard && c2 == null){
			c2 = selectedCard;
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
			//c2.setText(String.valueOf(c2.getId()));
			//c2.setIcon(icon);
			t.start();            

		}
	}

	/**
	 * Metode que comprova la coincidencia de les cartes, si les cartes coincideixen desabilitara el botons i el marcara com coincidents.
	 */
	public void checkCards(){

		//Les cartes coincideixen
		if (c1.getId() == c2.getId()){//match condition
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
	 * Metode que comprova que el joc s'hagi guanyat (totes les parelles de cartes s'han trobat) o no. Si ha guanyat es sumara un bonus de punts (temps restant).
	 * @param c : Rep el temporitzador per tal de poder extreure el progres (temps) i calcular el bonus per temps restant.
	 * @return : Retorna un boolea true si ha guanyat i false si ha perdut.
	 */
	public boolean isGameWon(CircleProgressBar c){
		int t = (100 - c.getProgress())*2;
		for(Card card: this.cards){
			if (card.getMatched() == false){
				return false;
			}
		}
		points = points + t;
		jtPuntuacio.setText("Punts "+ points);
		//client.setPunts(points);

		c.win = true;
		return true;
	}

	/**
	 * Metode que comprava que el joc no s'hagi acabat. Es contempla nomes que el joc s'acaba quan el temps s'acaba.
	 * @param c : Rep el temporitzador per tal de poder extreure el boolea fin, que s'activa quan el progres supera el temps limit.
	 * @return Retorna true si el joc a finalitzat i false si no ho ha fet.
	 */
	public boolean isGameEnd(CircleProgressBar c){
		int end = c.message;
		boolean fin = c.fin;

		//end == 0 
		if (fin){
			for(Card carta: this.cards){
				carta.setEnabled(false); 
			}    
			client.setPunts(points);
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
	
	public void sendServer(Client client){
		sConnect.sendMessage(4, client);
	}



}
