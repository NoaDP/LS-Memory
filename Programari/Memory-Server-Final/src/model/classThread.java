package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.ButtonController;

public class classThread extends Thread{
	
	/**
	 * La classe classThread és l'encarregada de controlar el transcors del temps inserit a la finestra gràfica.
	 */
	private ButtonController controlador;
	private boolean running;
	private Date time;
	private Date tempsDurada;
	private String[] tempsTotal;

	public classThread(ButtonController controlador){
		this.controlador = controlador;
		running = false;
		time = new Date();
		tempsDurada = new Date();
	}
	
	@Override
	public void run() {
		running =true;
		
		while(running){
			
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			long l = time.getTime() - date.getTime();
			Date d = new Date(l);
			if(controlador.getinitPartida() == false){
				if(d.getHours()>2){
					d.setHours((time.getHours() - date.getHours())-1);
				}else{
					d.setHours(00);
				}
				controlador.updateHoraInici(d);
			}else{
				if(tempsDurada.getSeconds()!=0){
					tempsDurada.setSeconds(tempsDurada.getSeconds()-1);
				}else{
					tempsDurada.setSeconds(59);
					tempsDurada.setMinutes(tempsDurada.getMinutes()-1);
				}
				controlador.updateMinutsDurada(tempsDurada);
			}
			
			
			if((d.getHours() == 0 && d.getMinutes() == 0 && d.getSeconds() == 0) || (tempsDurada.getMinutes() == 0 && tempsDurada.getSeconds() == 0)){
				if(controlador.getinitPartida()==false){
					controlador.iniciarPartida();
					controlador.setCanRegist(false);
					try {
						tempsDurada = controlador.getTimePartida(tempsTotal);
						tempsDurada.setHours(00);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					controlador.aturarPartida();
					controlador.enableTabs();
					running = false;
					this.stop();
				}
			}
			
			try{
				Thread.sleep(1000);
			
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Funció que retorna un booleà indicant si la classe en el moment en el que es crida està executant el run o no.
	 * @return resultat de si s'està executant el run o no.
	 */
	public boolean isRunning(){
		return running;
	}
	
	public String[] getTempsTotal() {
		return tempsTotal;
	}

	public void setTempsTotal(String[] tempsTotal) {
		this.tempsTotal = tempsTotal;
	}
	
	public void setRunning(boolean status){
		running = status;
	}
	
	public void setDate(Date time){
		this.time=time;
	}
	
}
