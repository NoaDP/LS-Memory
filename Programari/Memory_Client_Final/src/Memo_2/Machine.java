package Memo_2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import network.Client;
import network.ServerConnection;


public class Machine {
	
	/**
	 * La classe Machine és l'encarregada de gestionar els moviments que ha de realitzar la màquina durant el seu
	 * torn en mode "Easy". Primer s'encarregarà de seleccionar dues cartes de forma totalment aleatòria, i després 
	 * comprovarà si aquestes són iguals o no. 
	 */
	private List<CardMachine> cards;
	private CardMachine c1;
	private CardMachine c2;
	private BoardMachine board;
	
	public Machine(){}
	public List<CardMachine> getCards() {
		return cards;
	}

	public void setCards(List<CardMachine> cards) {
		this.cards = cards;
	}
	
	public void setBoard (BoardMachine b){
		this.board = b;
	}
	

	/**
	 * El procediment tira és l'encarregat de seleccionar les cartes de forma aleatòria, girar-es i després comprovar si
	 * aquestes són iguals o no. Primerament es barrejen les cartes que queden de forma aleatòria i es seleccionen les dos 
	 * primeres de la llista. Un cop seleccionades s'envien a MachineBoard per a que se li assignin les imatges corresponents
	 * segons la id i el nivell. Un cop seleccionades es comproven si són iguals. En cas de que ho siguin la màquina tornarà a
	 * tirar, és a dir, repetirà el procés de selecció de cartes. En cas contrari, es crida a la classe GiraCartesThread per a 
	 * que torni a col·locar les cartes en la seva posició original i després acaba el seu torn. 
	 */
	public void tira() {
		// TODO Auto-generated method stub
		Collections.shuffle(cards);
		board.setSelectedCard(cards.get(0));
		if (c1 == null && c2 == null){
	            c1 = cards.get(0);
	     }
		
		board.setC1(c1);
		
		if (c1 != null && c1 != cards.get(1) && c2 == null){
            c2 = cards.get(1);
            board.setC2(c2);
   		}
		
        if (c1.getId() == c2.getId()){
  			
        	board.MachineEquals();
        	
	  		while (c1.getId() == c2.getId()){
	  			if (cards.size()<3){
	  				board.MachineWin();
	  			}
	  			Collections.shuffle(cards);
	  			c1 = cards.get(0);
		           
	            board.setC1(c1);
	            c2 = cards.get(1);  
		        board.setC2(c2);
		        if (c1.getId() == c2.getId()){
		           	board.MachineEquals();
		        }else{
	  				 new GiraCartesThread(board).start();
	  				}
	  			}
	  			
  			}else{
  				if (board.isGameLost()){
  					board.MachineWin();
  				}else{
  			     	 new GiraCartesThread(board).start();
  				}
  			}
            
		}//tira
	    
}
	
	
	


