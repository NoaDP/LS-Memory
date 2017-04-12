package controller;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Memo1_1.Board;
import Memo1_1.Board2;
import Memo1_1.Board3;
import Memo1_2.BoardFocusMode;
import Memo1_2.BoardFocusMode2;
import Memo1_2.BoardFocusMode3;
import Memo1_2.GiraCartesFocus;
import Memo_2.BoardMachine;
import network.Client;
import network.ServerConnection;
import view.Level;

public class LevelController implements ActionListener{
	

	/**
	 * La classe LevelController s'encarrega de gestionar la informació rebuda a través de la classe Level del 
	 * package view. Segons el nivell seleccionat a la finestra gràfica, el mètode de joc (Classic,Focus o vs. Machine)
	 * a través de la variable "mode" i la variable "isGuest" la qual defineix si s'ha conectat com a usuari covidat o
	 * com usuari registrat a la base de dades, activarà un dels jocs en el nivell de dificultat seleccionat, també reb com a 
	 * paràmetres la connexió amb el servidor, ja que en acabar la partida s'haurà de realitzar una connexió i passar el punts obtinguts;
	 * el controlador de la finestra principal del menú client, ja que mentres s'està jugant els botons del menú principal hauràn de 
	 * romandre desactivats, i la variable client, la qual conté la informació de l'usuari que està jugant.
	 * @param level: Variable tipus int que defineix la dificultat del joc al que es vol jugat
	 * @param mode: Variable tipus int que defineix a quin mode de joc es vol jugar: Classic, Focus o vs.Machine
	 * @param isGuest: Variable tipus int que determina si l'usuari s'ha registrat com a usuari convidat o bé ha entrat com a usuari
	 * registrat
	 */
	private Level level;
	private int mode;
	private int isGuest;
	private int earnedPoints;
	private ServerConnection sConnect;
	private Client client;
	private ButtonController controler;
	
	public LevelController (Level level, int mode, int isGuest,ServerConnection sConnect,Client client,ButtonController controler){
		this.level = level;
		this.mode = mode;
		this.isGuest = isGuest;
		this.sConnect = sConnect;
		this.client = client;
		this.controler = controler;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		earnedPoints = 0;
		// TODO Auto-generated method stub
		switch (mode){
			case 1:
				if (e.getActionCommand().equals("Easy")){
					Board b = new Board(2,5,5,300,isGuest,sConnect,client,controler);
	                b.setPreferredSize(new Dimension(750,450)); //need to use this instead of setSize
	                b.setResizable(false);
	                b.setLocation(500, 250);
	                b.pack();
	                b.setVisible(true);
					this.level.setVisible(false);
					

				}
				
				if (e.getActionCommand().equals("Normal")){
					Board2 b = new Board2(4,5,10,600,isGuest,sConnect,client,controler);
	                b.setPreferredSize(new Dimension(720,720)); //need to use this instead of setSize
	                b.setResizable(false);
	                b.setLocation(500, 250);
	                b.pack();
	                b.setVisible(true);
					this.level.setVisible(false);
					
				}
				
				if (e.getActionCommand().equals("Hard")){
					Board3 b = new Board3(6,5,15,1200,isGuest,sConnect,client,controler);
	                b.setPreferredSize(new Dimension(610,635)); //need to use this instead of setSize
	                b.setResizable(false);
	                b.setLocation(500, 250);
	                b.pack();
	                b.setVisible(true);
					this.level.setVisible(false);
					
				}
				break;
			case 2: 
				if(e.getActionCommand().equals("Easy")){
					BoardFocusMode bFocus = new BoardFocusMode(2,5,5,300,isGuest,sConnect,client,controler, 0);
					bFocus.setPreferredSize(new Dimension(750,450)); //need to use this instead of setSize
					bFocus.setResizable(false);
					bFocus.setLocation(500, 250);
					bFocus.pack();
					bFocus.setVisible(true);
					bFocus.setLevel(0);
					this.level.setVisible(false);
					
			        /*GiraCartesFocus giraCartesFocus = new GiraCartesFocus();
			        giraCartesFocus.setCards(bFocus.getCards());
			        giraCartesFocus.setTime(5000);
			        new Thread(giraCartesFocus).start();*/
					}
				
				if(e.getActionCommand().equals("Normal")){
					BoardFocusMode2 bFocus2 = new BoardFocusMode2(4,5,10,600,isGuest,sConnect,client,controler, 1);
					bFocus2.setPreferredSize(new Dimension(720,720)); //need to use this instead of setSize
					bFocus2.setResizable(false);
					bFocus2.setLocation(500, 250);
					bFocus2.pack();
					bFocus2.setVisible(true);
					bFocus2.setLevel(1);
					this.level.setVisible(false);
					
					}
				
				if(e.getActionCommand().equals("Hard")){
					BoardFocusMode3 bFocus3 = new BoardFocusMode3(6,5,15,1200,isGuest,sConnect,client,controler, 2);
					bFocus3.setPreferredSize(new Dimension(610,635)); //need to use this instead of setSize
					bFocus3.setResizable(false);
					bFocus3.setLocation(500, 250);
					bFocus3.pack();
					bFocus3.setVisible(true);
					bFocus3.setLevel(2);
					this.level.setVisible(false);
					
					}
				break;
			case 3:
				if(e.getActionCommand().equals("Easy")){
					BoardMachine boardMachine = new BoardMachine(2,5,5,sConnect,client,isGuest,controler);
			        boardMachine.setPreferredSize(new Dimension(750,400)); //need to use this instead of setSize
			        boardMachine.setResizable(false); 
			        boardMachine.setLocation(750,450);
			        boardMachine.pack();
			        boardMachine.setController(controler);
			        boardMachine.setLevel(0);
			        boardMachine.setPoints();
			        boardMachine.setVisible(true);
					this.level.setVisible(false);
					
			        
				}
				
				if(e.getActionCommand().equals("Normal")){
					BoardMachine boardMachineNormal = new BoardMachine(4,5,10,sConnect,client,isGuest,controler);
					boardMachineNormal.setPreferredSize(new Dimension(720,720)); //need to use this instead of setSize
					boardMachineNormal.setResizable(false); 
					boardMachineNormal.setLocation(700, 250);
					boardMachineNormal.pack();
			        boardMachineNormal.setController(controler);
					boardMachineNormal.setLevel(1);
					boardMachineNormal.setPoints();
					boardMachineNormal.setVisible(true);
					this.level.setVisible(false);
					

				}
				
				if(e.getActionCommand().equals("Hard")){
					BoardMachine boardMachine = new BoardMachine(6,5,15,sConnect,client,isGuest,controler);
			        boardMachine.setPreferredSize(new Dimension(750,600)); //need to use this instead of setSize
			        boardMachine.setResizable(false); 
			        boardMachine.setLocation(700, 250);
			        boardMachine.pack();
			        boardMachine.setController(controler);
			        boardMachine.setLevel(2);
			        boardMachine.setPoints();
			        boardMachine.setVisible(true);
					this.level.setVisible(false);
					

				}
			}

	}
	
	/**
	 * getEarnedPoints s'encarrega de retornar la puntuació rebuda al final del joc que ha sigut activat. 
	 * @return: Variable tipus int que conté la puntuació obtinguda per l'usuari en l'ultim joc
	 */
	public int getEarnedPoints(){
		return earnedPoints;
	}
}
