package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ButtonController;

public class DedicatedServer implements Runnable{

	private Socket sClient;
	private ObjectInputStream objectInputStream;
	private DataOutputStream objectOutputStream;
	private ButtonController controler;
	
	public DedicatedServer(Socket sClient,ButtonController controler){
		this.sClient = sClient;
		this.controler = controler;
	}
	
	@Override
	public void run() {
		try {
			objectOutputStream = new DataOutputStream(sClient.getOutputStream());
			objectInputStream = new ObjectInputStream(sClient.getInputStream());
			Frame frame = (Frame)objectInputStream.readObject();
			Client client;
			switch(frame.getId()){
			
			case Frame.SQL_ADD:
				if(controler.getCanRegist()==true){
					client = frame.getObject();
					if(controler.inserirUsuariCLient(client.getNom(), client.getNickname(), client.getContrasena())){
						controler.actualitzaUsuari();
						objectOutputStream.writeInt(1);
					}else{
						objectOutputStream.writeInt(0);
					}
				}else{
					objectOutputStream.writeInt(9);
				}
				break;
				
			case Frame.SQL_RANK:
				if(controler.getinitPartida() || controler.getCanRegist()){
					JTable table = controler.getTableView();
					System.out.println(table);
					Client client2 = new Client();
					client2.setJTable(table);
					objectOutputStream.writeInt(7);
					ObjectOutputStream ooStream = new ObjectOutputStream(sClient.getOutputStream());
					ooStream.writeObject(client2);
				}else{
					objectOutputStream.writeInt(9);
				}
				break;
				
			case Frame.LOGIN:
				if(controler.getinitPartida() || controler.getCanRegist()){
					client = frame.getObject();
					objectOutputStream.writeInt(controler.loginClient(client.getNickname(), client.getContrasena()));
				}else{
					objectOutputStream.writeInt(9);
				}
				break;
			case Frame.CONCENTRACIO:
				if(controler.getinitPartida()){
					client = frame.getObject();
					objectOutputStream.writeInt(controler.updatePuntos(client.getPunts(),client.getNickname(),client.getModo()));
					controler.updateTable();
				}else{
					objectOutputStream.writeInt(8);
				}
				break;
				
			case Frame.MEMORIA:
				if(controler.getinitPartida()){
					client = frame.getObject();
					objectOutputStream.writeInt(controler.updatePuntos(client.getPunts(),client.getNickname(),client.getModo()));
					controler.updateTable();
					controler.setNumPartides(controler.getNumPartides() + 1);
				}else{
					objectOutputStream.writeInt(8);
				}
				break;
				
			case Frame.VSMACHINE:
				client = frame.getObject();
				objectOutputStream.writeInt(controler.updatePuntos(client.getPunts(),client.getNickname(),client.getModo()));
				controler.updateTable();
				controler.setNumPartides(controler.getNumPartides() + 1);

				//controler.sumarPuntuacion()
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
