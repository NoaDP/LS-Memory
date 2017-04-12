package Memo1_2;

	import javax.swing.JButton;

	@SuppressWarnings("serial")
	public class CardFocus extends JButton{
		
		/**
		 * La classe CardFocus és la classe que conté totes les dades referents a les cartes que s'emplearàn al llarg del 
		 * joc en mode "Focus". Aquestes emmagatzemen una id, la qual ens permetrà emparellar-la amb una altre amb aquesta
		 * mateixa id, i un booleà que estableix si la carta ha estat emparellada o no. 
		 */
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

