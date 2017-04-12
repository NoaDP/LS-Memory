package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.ButtonController;

public class Network implements Runnable{

	private int clientPort;
	private String IP;
	private ServerSocket sServer;
	private Socket sClient;
	private boolean isOn = false;
	private ButtonController BC;
	
	public Network(int clientPort,ButtonController BC,String IP){
		this.clientPort = clientPort;
		this.IP = IP;
		this.BC = BC;
		
		try {
			sServer = new ServerSocket(clientPort);
			
		} catch (IOException e) {
		}
	}
	
	@Override
	public void run() {
		while(isOn==true){
			try {
				sClient = sServer.accept();
				new Thread(new DedicatedServer(sClient,BC)).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		}
	}
	
	public void turnOn(){
		this.isOn = true;
	}
	
	public void turnOff(){
		this.isOn = false;
	}
	
	public boolean getIsOn(){
		return this.isOn;
	}
	

}
