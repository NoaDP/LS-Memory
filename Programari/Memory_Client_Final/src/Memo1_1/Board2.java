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

public class Board2 extends Board{

	/**
	 * Constructor de la classe Board2 heredat de la classe Board. Genera el taulell de joc. L'unic metode que implementara de manera diferent sera el doTurn doncs es on es carregaran les seves imatges (les imatges varien segon el mode de dificultat).
	 * @param files : Indica el numero de files que tindra el taulell en mode normal (Normal Mode).
	 * @param columnes : Indica el numero de columnes que tindra el taulell depenent del mode normal (Normal Mode).
	 * @param pairs : Indica el numero de parelles de cartes depenent del mode normal (Normal Mode).
	 * @param temps : Temps que durara el joc depenent del mode normal (Normal Mode).
	 */
	public Board2 (int files, int columnes, int pairs, int temps,int isGuest,ServerConnection sConnect,Client client,ButtonController controler) {
		super(files, columnes, pairs, temps,isGuest,sConnect,client,controler);
		// TODO Auto-generated constructor stub
	}

	/**
	 *  Sobreescriu el metode doTurn de la classe mare que carrega les imatges a les cartes (JButtons) segons el seu Id (enter) i les mostra.
	 */
	@Override
	public void doTurn(){

		//primera carta que seleccionen
		if (c1 == null && c2 == null){
			c1 = selectedCard;
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


		}

		//Al pulsar la segona carta es mostren les dues durant un cert temps 
		if (c1 != null && c1 != selectedCard && c2 == null){
			c2 = selectedCard;
			switch (c2.getId()){
			case 0:
				try{
					File file = new File("resources/0.jpg");
					Image i55 = ImageIO.read(file);
					c2.setIcon(new ImageIcon(i55));
				} catch (IOException ex){}	
				break;
			case 1:
				try{
					File file = new File("resources/1.jpg");
					Image i55 = ImageIO.read(file);
					c2.setIcon(new ImageIcon(i55));
				} catch (IOException ex){}	
				break;
			case 2:
				try{
					File file = new File("resources/2.jpg");
					Image i55 = ImageIO.read(file);
					c2.setIcon(new ImageIcon(i55));
				} catch (IOException ex){}	
				break;
			case 3:
				try{
					File file = new File("resources/3.jpg");
					Image i55 = ImageIO.read(file);
					c2.setIcon(new ImageIcon(i55));
				} catch (IOException ex){}	
				break;
			case 4:
				try{
					File file = new File("resources/4.jpg");
					Image i55 = ImageIO.read(file);
					c2.setIcon(new ImageIcon(i55));
				} catch (IOException ex){}	
				break;
			case 5:
				try {
					File file = new File("resources/5.jpg");
					Image i55 = ImageIO.read(file);
					c2.setIcon(new ImageIcon(i55));
				} catch (IOException ex){}

				break;
			case 6:
				try {
					File file = new File("resources/6.jpg");
					Image i55 = ImageIO.read(file);
					c2.setIcon(new ImageIcon(i55));
				} catch (IOException ex){}

				break;
			case 7:
				try {
					File file = new File("resources/7.jpg");
					Image i55 = ImageIO.read(file);
					c2.setIcon(new ImageIcon(i55));
				} catch (IOException ex){}
				break;
			case 8:
				try {
					File file = new File("resources/8.jpg");
					Image i55 = ImageIO.read(file);
					c2.setIcon(new ImageIcon(i55));
				} catch (IOException ex){}
				break;
			case 9:
				try {
					File file = new File("resources/9.jpg");
					Image i55 = ImageIO.read(file);
					c2.setIcon(new ImageIcon(i55));
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


