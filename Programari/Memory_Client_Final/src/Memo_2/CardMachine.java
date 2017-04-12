package Memo_2;
import javax.swing.JButton;

	/**
	 *La classe CardMachine conté les dades associades a les cartes que es faràn servir durant la modalitat de joc 
	 *vs. Machine. Una carta conté una id, i un booleà que definirà si aquesta carta ha estat emparellada o no. 
	 *
	 */
	@SuppressWarnings("serial")
	public class CardMachine extends JButton{
	    private int id;
	    private boolean matched = false;


	    public void setId(int id){
	        this.id = id;
	    }

	    public int getId(){
	        return this.id;
	    }


	    public void setMatched(boolean matched){
	        this.matched = matched;
	    }

	    public boolean getMatched(){
	        return this.matched;
	    }
	}

