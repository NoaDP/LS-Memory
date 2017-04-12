package Memo1_2;

import java.util.List;

public class GiraCartesFocus implements Runnable{

	/**
	 * GiraCartesFocus és la classe encarregada de tornar a girar les cartes i col·locar-les bocavall tras passar
	 * el temps especificat segons la dificultat seleccionada per l'usuari.
	 */
	private List<CardFocus> cards;
	private int time;
	public GiraCartesFocus(){
	}
	
	public void setCards(List<CardFocus> cards){
		this.cards = cards;
	}
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * El procediment run s'encarrega de treure les imatges a les cartes un cop ja ha passat el temps assignat.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try{
			Thread.sleep(time);
		}catch (InterruptedException e){
			e.printStackTrace();
		}

		int comptador = cards.size();
		for (int i = 0; i < cards.size(); i++){
			cards.get(i).setIcon(null);
		}
	}

}
