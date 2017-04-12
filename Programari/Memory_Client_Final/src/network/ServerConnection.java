package network;

import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import controller.ButtonController;
import view.VentanaError;


public class ServerConnection {

	/**
	 * La classe ServerConnection, tal i com el seu nom indica és l'encarregada de dur a terme la connexió del nostre programa
	 * amb el programa servidor. 
	 */
	private Socket socket;
	private ObjectOutputStream ooStream;
	private DataInputStream oiStream;
	private ButtonController controller;
	private static String IP;
	private static int PORT;
	private Client client;
	
	public ServerConnection(ButtonController controller,Client client,int PORT,String IP){
		this.controller = controller;
		this.client = client;
		this.IP = IP;
		this.PORT = PORT;
	}
	
	/**
	 * El procedimetn sendMessage és l'encarregat de enviar un missatge al servidor cada cop que el nostre programa intenta
	 * accedir-hi. Per una altra banda, aquest procediment també s'encarrega de rebre un missatge de resposta cada cop que s'ha
	 * donat una connexió.
	 * @param idEnvio: 
	 * @param client: variable tipus Client que conté les dades de l'usuari que està connectat en el moment de la connexió del nostre
	 * programa amb el servidor
	 */
	public void sendMessage(int idEnvio, Client client){
		Frame frame = new Frame();
		try {
			socket = new Socket (IP,PORT);
			if(socket.getInetAddress().isReachable(2)){
				try{
					ooStream = new ObjectOutputStream(socket.getOutputStream());
					oiStream = new DataInputStream(socket.getInputStream());
					switch(idEnvio){
					case 0:
						frame.setFrame(idEnvio,client);
						
						break;
					case 1:
						frame.setFrame(idEnvio,client);
						break;
					case 2:
						frame.setFrame(idEnvio,client);
						break;
					case 3:
						frame.setFrame(idEnvio,client);
						break;
					case 4:
						frame.setFrame(idEnvio,client);
						break;
					case 5:
						frame.setFrame(idEnvio,client);
						break;
					case 6:
						frame.setFrame(idEnvio,client);
						break;
						
					}
					ooStream.writeObject(frame);
					int entrada = oiStream.readInt();
					switch(entrada){
					case 0:
						  JOptionPane.showMessageDialog(new JPanel(), "Error el nickname ja esta sent utilitzat", "Error Nickname", JOptionPane.ERROR_MESSAGE);
						  break;
					case 1:
						 JOptionPane.showMessageDialog(new JPanel(), "T'has registrat amb exit!", "Regitre Usuari", JOptionPane.INFORMATION_MESSAGE);
						 this.client.setNickname(client.getNickname());
						 this.client.setContrasena(client.getContrasena());
						 controller.OccultButtons();
						 controller.setButtonsActive();
						 break;
					case 2:
						 JOptionPane.showMessageDialog(new JPanel(), "T'has loguejat amb exit!", "Login", JOptionPane.INFORMATION_MESSAGE);
						 this.client.setNickname(client.getNickname());
						 this.client.setContrasena(client.getContrasena());
						 controller.OccultButtons();
						 controller.setButtonsActive();
	
						break;
					case 3:
						 JOptionPane.showMessageDialog(new JPanel(), "Contrase�a o Usuari Incorrectes", "Login", JOptionPane.WARNING_MESSAGE);
						 break;
					case 4:
						 JOptionPane.showMessageDialog(new JPanel(), "Error en conectarse a la BD", "Error", JOptionPane.ERROR_MESSAGE);
						 break;
					case 5:
						 JOptionPane.showMessageDialog(new JPanel(), "Actualment estas bloquejat!", "Blocked", JOptionPane.WARNING_MESSAGE);
						 controller.disableButtons();
						break;
					case 6:
						 JOptionPane.showMessageDialog(new JPanel(), "Punts Actualitzats!", "Points", JOptionPane.INFORMATION_MESSAGE);
						 controller.setButtonsActive();
						 break;
					case 7:
						ObjectInputStream objectStream= new ObjectInputStream(socket.getInputStream());
						try {
							Client client2 = (Client)objectStream.readObject();
							controller.setTableView(client2.getJTable());
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 8:
					 JOptionPane.showMessageDialog(new JPanel(), "La partida no esta comen�ada, encara no pots jugar!", "Inici Partida", JOptionPane.INFORMATION_MESSAGE);
					 break;
					case 9:
						 JOptionPane.showMessageDialog(new JPanel(), "No et pots entrar, la partida esta començada o no hi ha hora d'inici!", "Registrar", JOptionPane.INFORMATION_MESSAGE);
						 break;
						
					}
					socket.close();
				}catch (IOException e){
					//System.out.println("SERVER CONNECTION ERROR (message not sent)");
					JOptionPane.showMessageDialog(new JPanel(), "Error en la connexio amb el servidor", "Error", JOptionPane.ERROR_MESSAGE);
	
				}
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(new JPanel(), "Error el servidor no est� ences!", "Error", JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(new JPanel(), "Error el servidor no est� ences!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}