package Memo_2;

public class GiraCartesThread extends Thread {
	
	/**
	 * La classe GiraCartesThread és l'encarregada de tornar a col·locar les cartes boca a vall un cop han estat
	 * girades per la màquina. Abans de girar les cartes, aquest s'espera 2 segons per a que l'usuari pugui veure 
	 * quines cartes han estat girades, i despres crida al board per a que torni a treure el dibuix i després anul·li 
	 * la selecció de les cartes c1 i c2.
	 */
	private BoardMachine board;
	
	public GiraCartesThread(BoardMachine board) {
		this.board = board;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		board.setC1Blank();
        board.setC2Blank();
        board.resetCards();
	}

}
