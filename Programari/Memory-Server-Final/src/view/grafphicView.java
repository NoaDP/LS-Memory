package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class grafphicView extends JComponent {
	
	
	/**
	 * La classe graphicView és l'encarregada de pintar la gràfica que descriu la puntuació rebuda fins el moemnt dels usuaris. 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> memoria;
	private ArrayList<String> concentracio;
	
	public grafphicView(ArrayList<String> memoria,ArrayList<String> concentracio){
		this.memoria = memoria;
		this.concentracio = concentracio;
		setPreferredSize(new Dimension(600,600));
	}
	
	public ArrayList<String> getMemoria() {
		return memoria;
	}

	public void setMemoria(ArrayList<String> memoria) {
		this.memoria = memoria;
	}

	public ArrayList<String> getConcentracio() {
		return concentracio;
	}

	public void setConcentracio(ArrayList<String> concentracio) {
		this.concentracio = concentracio;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		float[] dash1 = { 2f, 0f, 2f };

		int partides=0,X=50,max=0,num=0,llargada=0;
		int[] puntsX = new int[memoria.size()];
		int[] puntsY = new int[memoria.size()];
		llargada = puntsX.length;
		for(int i=0;i<memoria.size();i++){
			X=X+50;
			puntsX[i] = X;
			num = Integer.parseInt(memoria.get(i));
			puntsY[i] = num;
			if(max<num){
				max=num;
			}
		}
		
		BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, 
			    BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f );
		g2.setStroke(bs1);
		
		//diferentes ejes 
		g.setColor(Color.GRAY);
		for(int i=50; i<350;i+=50){
			g2.drawLine(50,i,500,i);
		}
		
		g.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(50,30, 50, 350);//eje y
		g2.drawLine(50,350, 500, 350);//eje x

		g2.setColor(Color.RED);
		
		if(!memoria.isEmpty()){
			if(llargada>1){	
				for(int i=1; i<llargada;i++){
					g2.drawLine(puntsX[i-1],350-puntsY[i-1], puntsX[i], 350-puntsY[i]);

				}
			}else{
				g2.fillOval(puntsX[0], 350-puntsY[0], 10, 10);
			}
		}
		
		g.setColor(Color.BLACK);
		g.drawString("Puntuaci�", 35,15);
		g.drawString("Partides", 510, 380);
		
		String s;
		
		for(int k=50;k<500;k+=50){
			s = Integer.toString(partides);
			g.drawString(s,k,370);
			partides++;
		}
		
		partides = 0;
		
		for(int k=0;k<400;k+=50){
			s = Integer.toString(partides);
			g.drawString(s, 25, 350-k);
			partides+=50;
		}
	}
		
		

}
