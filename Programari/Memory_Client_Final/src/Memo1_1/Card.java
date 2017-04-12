package Memo1_1;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Card extends JButton{
    private int id;
    private boolean matched = false;


    /**
     * Constructor de la classe Card que hereda els metodes de la classe JButton
     */
    public Card() {
    	
    	super();
    	setFocusable(false);
    }
    
    /**
     * Constructor de la classe Card que selecciona la carta sense "marcar" el boto.
     * @param icon
     */
    public Card(ImageIcon icon){
    	
    	super(icon);
    	setFocusable(false);

    }

    /**
     * Setter que inicialitza el id de la carta
     * @param id : es un numero enter que indica la id de la carta. 
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Getter que retorna el id de la carta
     * @return
     */
    public int getId(){
        return this.id;
    }

    /**
     * Metode que marca la carta com enparellada
     * @param matched : Boolea que indica si la carta esta enparellada o no.
     */
    public void setMatched(boolean matched){
        this.matched = matched;
    }

    /**
     * Metode que retorna un bollea true si la carta esta enparellada o false si no ho esta.
     * @return True si la carta esta enparellada o false si no ho esta.
     */
    public boolean getMatched(){
        return this.matched;
    }
    
 
}