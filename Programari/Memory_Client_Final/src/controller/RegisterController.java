package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import network.Client;
import network.ServerConnection;
import view.Registration;
import view.VentanaError;

public class RegisterController implements ActionListener{

	/**
	 * La classe RegisterController s'encarrega de gestionar la informació de la finestra de registre que apareix
	 * al intentar registrar-se com a nou usuari al menú principal de client. En cas de que algún dels camps estigui buit
	 * mostrarà un error i no permetrà seguir amb el procés de registre fins que s'omplin totes les dades. Un cop inserides 
	 * totes les dades es crearà un usuari i s'enviarà al servidor, on es comprovarà de que aquest no existeixi.  
	 */
	private Registration registration;
	private ButtonController buttonController;
	private ServerConnection sConnect;
	private Client client;
	
	public RegisterController(Registration registration, ButtonController buttonController,ServerConnection sConnect,Client client) {
		this.buttonController = buttonController;
		this.registration = registration;
		this.sConnect = sConnect;
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

		if (e.getActionCommand().equals("Registration")){
			int numeroCampsBuits = 0;
			int aux_1 = 0;
			int aux_2 = 0;
			int aux_3 = 0;
			int aux_4 = 0;
			String[] camps = registration.getCampsRegistration();
			Frame frame = new Frame();
			boolean trobatLletra=false,trobatNumero=false;
			String contra = camps[3];
			
			if(camps[0].isEmpty() && camps[1].isEmpty() && camps[2].isEmpty() && camps[3].isEmpty()){
				VentanaError ventanaError = new VentanaError("Error! No hay ningun campo lleno!", "Registration Error");
				ventanaError.setVisible(true);
				camps[0]=null;
			}else{
								
				for(int i=0;i<4;i++){
					if(camps[i].isEmpty()){
						numeroCampsBuits++;
						switch(i){
							case 0: aux_1 = 1;
							case 1: aux_2 = 1;
							case 2: aux_3 = 1;
							case 3: aux_4 = 1;
						 }
					  }else{
						  switch(i){
							case 0: aux_1 = 0;
							case 1: aux_2 = 0;
							case 2: aux_3 = 0;
							case 3: aux_4 = 0;
						  }
					  }
					}
				
				if (numeroCampsBuits == 1){
					String cadenaMensaje = "Error. El siguiente campo está vacío:";
					if (aux_1 != 0){
						cadenaMensaje += " Name";
					}
					if (aux_2 != 0){
						cadenaMensaje += " Surname";
					}
					if (aux_3 != 0){
						cadenaMensaje += " Nickname";
					}
					if (aux_4 != 0){
						cadenaMensaje += " Password";
					}
					VentanaError ventanaError = new VentanaError(cadenaMensaje, "Registration Error");
					ventanaError.setVisible(true);
				}else{
					if(numeroCampsBuits != 0){
						VentanaError ventanaError = new VentanaError("Error. Hay " + numeroCampsBuits + " campos sin Rellenar", "Registration Error");
						ventanaError.setVisible(true);
					}
					if ((aux_1 == 0) && (aux_2==0) && (aux_3==0) && (aux_4==0)){
						if(contra.length()==6){
							for(int i =0;i<6;i++){
								if(trobatLletra==false || trobatNumero==false){
									char letra = contra.charAt(i);
									int casella = (int)letra;
									if((casella<91 && casella>64) || (casella<123 && casella>96)){
										trobatLletra=true;
									}else if(casella<58 && casella>47){
										trobatNumero=true;
									}
								}
							}
							if(trobatLletra==true && trobatNumero==true){
								registration.setVisible(false);
								String s = new String();
								Client client = new Client(camps[0],camps[2],camps[1],camps[3],0,0);
								sConnect.sendMessage(0,client);
							}else{
								JOptionPane.showMessageDialog(frame, "Error: La contrase�a ha de contener numeros i letras", "Error", JOptionPane.ERROR_MESSAGE);
							}
							trobatNumero = false;
							trobatLletra = false;
						}else{
							JOptionPane.showMessageDialog(frame, "Error: La contrase�a ha de tener 6 caracteres", "Error", JOptionPane.ERROR_MESSAGE);

						}
					}
				}
			}
				
		}
	}
	
	public void setVista(Registration registration){
		this.registration = registration;
	}

}
