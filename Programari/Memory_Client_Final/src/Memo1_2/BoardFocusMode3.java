package Memo1_2;
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

public class BoardFocusMode3 extends BoardFocusMode{

	/**
	 * La classe BoardFocusMode3 és una classe que hereta de la classe BoardFocusMode i és l'encarregada de crear i 
	 * modificar els elements que componen el tauler de joc del mode "Focus" en dificultat difícil.
	 */
	public BoardFocusMode3 (int files, int columnes, int pairs, int temps,int isGuest,ServerConnection sConnect,Client client,ButtonController controler, int level) {
		super(files, columnes, pairs, temps,isGuest,sConnect,client,controler, level);
		// TODO Auto-generated constructor stub
		for (int j = 0; j < this.getCards().size(); j++){
			switch (this.getCards().get(j).getId()){
			case 0:
				try{
					File file = new File("resources/0.jpg");
					Image i = ImageIO.read(file);
					Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					this.getCards().get(j).setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 1:
				try{
					File file = new File("resources/1.jpg");
					Image i = ImageIO.read(file);
					Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					this.getCards().get(j).setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 2:
				try{
					File file = new File("resources/2.jpg");
					Image i = ImageIO.read(file);
					Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					this.getCards().get(j).setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 3:
				try{
					File file = new File("resources/3.jpg");
					Image i = ImageIO.read(file);
					Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					this.getCards().get(j).setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 4:
				try{
					File file = new File("resources/4.jpg");
					Image i = ImageIO.read(file);
					Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					this.getCards().get(j).setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 5:
				try{
					File file = new File("resources/5.jpg");
					Image i = ImageIO.read(file);
					Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					this.getCards().get(j).setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 6:
				try{
					File file = new File("resources/6.jpg");
					Image i = ImageIO.read(file);
					Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					this.getCards().get(j).setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 7:
				try{
					File file = new File("resources/d7.jpg");
					Image i = ImageIO.read(file);
					Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					this.getCards().get(j).setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 8:
				try{
					File file = new File("resources/8.jpg");
					Image i = ImageIO.read(file);
					Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					this.getCards().get(j).setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 9:
				try{
					File file = new File("resources/9.jpg");
					Image i = ImageIO.read(file);
					Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					this.getCards().get(j).setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
				case 10:
					try{
						File file = new File("resources/d10.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						this.getCards().get(j).setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;

				case 11:
					try{
						File file = new File("resources/d11.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						this.getCards().get(j).setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 12:
					try{
						File file = new File("resources/d12.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						this.getCards().get(j).setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 13:
					try{
						File file = new File("resources/d13.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						this.getCards().get(j).setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
				case 14:
					try{
						File file = new File("resources/d14.jpg");
						Image i = ImageIO.read(file);
						Image resize = i.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
						this.getCards().get(j).setIcon(new ImageIcon(resize));
					} catch (IOException ex){}	
					break;
			}

		}
		GiraCartesFocus giraCartesFocus = new GiraCartesFocus();
		giraCartesFocus.setCards(this.getCards());
		giraCartesFocus.setTime(5000);
		new Thread(giraCartesFocus).start();
	}
	
	/**
	 * El procediment windowClosing és l'encarregat de dur a terme accions predeterminades en el moment que es tanca
	 * la finsestra gràfica. Un cop es tanqui, el rellotge del joc s'aturarà.
	 */
	public void windowClosing(WindowEvent e){
    	clock.isEnd();
        //this.dispose();
	}

	/**
     *  Al igual que a BoardFocusMode el procediment doTurn s'encarrega de simular el moviment de girar les dues cartes seleccionades
     *  per l'usuari per tal de comprovar si aquestes són iguals o no. Un cop voltejades, es crida a la classe encarregada de comprovar 
     *  si les cartes seleccionades són iguals.
     */
	@Override
	public void doTurn(){

		/*//Comprovem que el temps no s'hagi acabat
    	if (this.isGameEnd(clock)){
            //JOptionPane.showMessageDialog(this, "Has guanyat!");
            //System.exit(0);
        	System.out.println("Entra");
        }*/

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


}


