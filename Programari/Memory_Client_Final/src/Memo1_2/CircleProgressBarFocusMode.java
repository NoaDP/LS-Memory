package Memo1_2;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import network.Client;

public class CircleProgressBarFocusMode extends JPanel {
	
	/**
	 * La classe CircleProgressBarFocusMode ens permet crear un cercle que controla el temps de partida que queda.Aquest
	 * cercle es va tancan en funció del temps que ha passat fins al moment. 
	 */
	private BoardFocusMode board;

	private final static int MAX_DRAW_PROGRESS = 100;
	private final static int FADE_TIME = 5;
	private static final int FRACTION_OF_OUTER_RING = 5;

	private int progress = 0;
	public int message = 1;
	private int close = 0;
	public boolean fin = false;
	public boolean win = false;
	private Client client;
	private int points;
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (progress <= FADE_TIME + MAX_DRAW_PROGRESS) {
			// Here you paint your circle progress bar. Use the progress
			// variable to paint
			// as much progress as it indicates.

			int progressToDraw = Math.min(progress, MAX_DRAW_PROGRESS);
			// Clear the background
			g2.setColor(getBackground());
			g2.fill(g.getClip());

			Color drawColor = getForeground();

			// Fade the progress out when it is fully complete
			if (progress > MAX_DRAW_PROGRESS) {
				/*int fadeAmount = FADE_TIME - (progress - MAX_DRAW_PROGRESS);
				drawColor = new Color(drawColor.getRed(), drawColor.getGreen(),drawColor.getBlue(),(int) ((((float) fadeAmount) / FADE_TIME) * 255));*/
				fin = true;
				//board.enableClick();
				board.isGameEnd(this);
				
			}
			

			// Fill in the arc
			g2.setColor(Color.orange);
			int angle = -(int) (((float) progressToDraw / MAX_DRAW_PROGRESS) * 360);
			g.fillArc(0, 0, getWidth()-10, getHeight()-10, 90, angle);

			// Borra el cercle de dins
			g2.setColor(getBackground());
			g.fillArc((getWidth()-10) / FRACTION_OF_OUTER_RING / 2, (getHeight()-10)
					/ FRACTION_OF_OUTER_RING / 2, (getWidth()-10)
					* (FRACTION_OF_OUTER_RING - 1) / FRACTION_OF_OUTER_RING,
					(getHeight()-10) * (FRACTION_OF_OUTER_RING - 1)
					/ FRACTION_OF_OUTER_RING, 90, angle);

			// Pinta el cercle de fora
			g2.setColor(Color.red.darker());

			// Pinta el cercle de dins
			g.drawOval((getWidth()-10) / FRACTION_OF_OUTER_RING / 2, (getHeight()-10)
					/ FRACTION_OF_OUTER_RING / 2, (getWidth()-10)
					* (FRACTION_OF_OUTER_RING - 1) / FRACTION_OF_OUTER_RING,
					(getHeight()-10) * (FRACTION_OF_OUTER_RING - 1)
					/ FRACTION_OF_OUTER_RING);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		
		//Si el jugador guanya desapareix el temps
		if (win){
			return new Dimension(0,0);
		}
		
		return new Dimension(100, 100);
	}

	@Override
	public Dimension getMinimumSize() {
		// If this component is put in a smaller aera, how small can it get
		// before you just let the
		// the aera clip your component off
		return new Dimension(50, 50);
	}

	// Setter for progress. If you want to increment externally as is usual with
	// most progress bars, use this.
	public void setProgress(int progress) {
		this.progress = progress;
		if (progress>0){
			repaint();
		}
		
	}

	public int getProgress() {
		return progress;
	}

	/**
	 * Metode que mostrara el missatge si el jugador a perdut, es a dir, si el temps s'ha esgotat
	 */
	public void missatge(){
		if (!win){
			JOptionPane.showMessageDialog(this, "Has perdut!");
			setPoints(board.getPoints());
			setClient(board.getClient());
			client.setPunts(points);
			board.sendServer(client);
		}
		//System.exit(0);
	}

	/**
	 * Metode que controla el progres del increment del temps.
	 * @param t : indica el temps
	 */

	// If you simply want the progress to build up over a set time, use this
	public void incrementOverTime(int t) {
		// create a timer that fires every 1200 milliseconds
		final Timer timer = new Timer(t, null);
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setProgress(progress + 1);
				if (progress >= MAX_DRAW_PROGRESS + FADE_TIME && close == 0 ) {
					timer.stop();
					
					missatge();
					message = 0;
				}
				if (close == 1){
					timer.stop();
				}
			}
		});
		timer.start();
	}
	
	public void setBoard(BoardFocusMode board){
		this.board = board;
	}
	
	/**
	 * Metode que controla que el joc no hagi acabat, també controla que quan l'usuari tanqui la finestra, el temps 
     * s'aturi.
	 */
	public void isEnd(){
		fin = true;
		this.close = 1;
		
	}
	
	public void setClient (Client client){
		this.client = client;
	}
	public void setPoints(int points){
		this.points = points;
	}
} 

