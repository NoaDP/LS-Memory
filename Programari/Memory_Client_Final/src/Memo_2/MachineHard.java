package Memo_2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;


public class MachineHard {
	
	/**
	 * El procediment tira és l'encarregat de seleccionar les cartes de forma parcialment aleatòria, girar-es i 
	 * després comprovar si aquestes són iguals o no. Primerament es barrejen les cartes que queden de forma 
	 * aleatòria i es selecciona la primera de la llista.Un cop seleccionada se selecciona la segona. En cas de que
	 * el comptador no hagui arribat a 0 es buscarà la seva parella, en cas contrari la segona es selecciona de forma
	 * aleatòria. Un cop seleccionades s'envien a MachineBoard per a que se li assignin les imatges corresponents
	 * segons la id i el nivell. Un cop seleccionades es comproven si són iguals. En cas de que ho siguin la màquina 
	 * tornarà a tirar, és a dir, repetirà el procés de selecció de cartes. En cas contrari, es crida a la classe 
	 * GiraCartesThread per a que torni a col·locar les cartes en la seva posició original i després acaba el seu torn.  
	 */
	private List<CardMachine> cards;
	private CardMachine c1;
	private CardMachine c2;
	private BoardMachine board;
	
	public MachineHard(){}
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
	 * El procediment tira és l'encarregat d'efectuar el moviment de la màquina. Aquest reb un comptador que defineix
	 * si la selecció de les cartes ha de ser amb memòria o bé totalmet aleatòri. Primer es barrejen les cartes de la
	 * llista de cartes i després és selecciona la primera. Si el comptador ha arribat a 0, tal i com en el mode
	 * "Easy", la segona serà la segona carta de la llista, en cas contrari, es buscarà la parella de la carta seleccionada
	 * anteriorment i se li assignarà. Un cop seleccionades es comprova si aquestes són iguals i si encara en queden cartes. 
	 * Si són iguals  i encara queden cartes a jugar. La màquina tornarà a tirar i seleccionarà les dues següents cartes de 
	 * forma totalment aleatòria. Aquest procés es repetirà fins a que les cartes seleccionades siguin differents. En cas de 
	 * que siguin differents, es cridarà a la classe GiraCartesThread per a que les torni a la seva posició original. 
	 * @param comptador: Variable tipus int que defineix durant quants torns ha de durar la memoria de la màquina
	 */

	public void tira(int comptador) {
		// TODO Auto-generated method stub
		
		Collections.shuffle(cards);
		board.setSelectedCard(cards.get(0));
		if (c1 == null && c2 == null){
	            c1 = cards.get(0);
	     }
		
		board.setC1(c1);
		
		if (c1 != null && c1 != cards.get(1) && c2 == null && (comptador > 0)){
            for (int i = 0; i < cards.size() ; i++){
            	if ((cards.get(i).getId() == c1.getId()) && (cards.get(i) != c1)){
            		c2 = cards.get(i);
    	    		board.setC2(c2);
            	}
            }
		}else{
			if (c1 != null && c1 != cards.get(1) && c2 == null && (comptador <= 0)){
				c2 = cards.get(1);
	    		board.setC2(c2);
			}
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
	
	
	


