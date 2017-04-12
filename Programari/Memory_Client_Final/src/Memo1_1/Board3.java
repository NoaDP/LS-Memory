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

public class Board3 extends Board{

	/**
	 * Constructor de la classe Board2 heredat de la classe Board. Genera el taulell de joc. L'unic metode que implementar� de manera diferent sera el doTurn doncs es on es carregar�n les seves imatges (les imatges varien segon el mode de dificultat).
	 * @param files : Indica el numero de files que tindra el taulell en mode normal (Hard Mode).
	 * @param columnes : Indica el numero de columnes que tindra el taulell depenent del mode normal (Hard Mode).
	 * @param pairs : Indica el numero de parelles de cartes depenent del mode normal (Hard Mode).
	 * @param temps : Temps que durara el joc depenent del mode normal (Hard Mode).
	 */
	public Board3 (int files, int columnes, int pairs, int temps,int isGuest,ServerConnection sConnect,Client client,ButtonController controler) {
		super(files, columnes, pairs, temps,isGuest,sConnect,client,controler);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Sobreescriu el metode doTurn de la classe mare que carrega les imatges a les cartes (JButtons) segons el seu Id (enter) i les mostra.
	 */
	@Override
	public void doTurn(){

		//primera carta que seleccionen
		if (c1 == null && c2 == null){
			c1 = selectedCard;
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


			//c1.setText(String.valueOf(c1.getId()));
		}

		//Al pulsar la segona carta es mostren les dues durant un cert temps 
		if (c1 != null && c1 != selectedCard && c2 == null){
			c2 = selectedCard;
			switch (c2.getId()){
			case 0:
				try{
					File file = new File("resources/d0.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 1:
				try{
					File file = new File("resources/d1.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 2:
				try{
					File file = new File("resources/d2.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 3:
				try{
					File file = new File("resources/d3.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 4:
				try{
					File file = new File("resources/d4.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 5:
				try {
					File file = new File("resources/d5.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}

				break;
			case 6:
				try {
					File file = new File("resources/d6.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}

				break;
			case 7:
				try {
					File file = new File("resources/d7.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}
				break;
			case 8:
				try {
					File file = new File("resources/d8.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}
				break;
			case 9:
				try {
					File file = new File("resources/d9.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}
				break;
			case 10:
				try{
					File file = new File("resources/d10.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;

			case 11:
				try{
					File file = new File("resources/d11.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 12:
				try{
					File file = new File("resources/d12.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 13:
				try{
					File file = new File("resources/d13.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			case 14:
				try{
					File file = new File("resources/d14.jpg");
					Image i2 = ImageIO.read(file);
					Image resize = i2.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
					c2.setIcon(new ImageIcon(resize));
				} catch (IOException ex){}	
				break;
			}	
			t.start();


		}
	}
	public boolean EndGame(){
    	return (this.isGameEnd(clock) || this.isGameWon(clock));
    }

}


