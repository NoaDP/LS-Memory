package model;

import java.awt.Frame;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import controller.ButtonController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GestioPartida {
	
	/**
	 * La classe GestioPartida com el seu nom indica, és l'encarregada de gestionar els moviments de l'usuari al llard del menú del
	 * nostre programa i del-de el programa client. Per tal de aconseguir gestionar-les s'encarrega també de realitzar les connexions amb
	 * el servidor i accedir al contingut de la base de dades. 
	 */
	static private ConectorDB conn;
	
	private Date data;
	private ButtonController controler;
	
	public GestioPartida(ConectorDB conn){
		this.conn = conn;
	}
	
	public void setDate(Date data){
		this.data = data;
	}
	
	public Date getTimeIniciPartida(String[] s) throws ParseException{
    	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		String data = (s[0]+":"+s[1]+":"+s[2]);
		d = dateFormat.parse(data);
		Date date = new Date();
		if(d.getHours() == date.getHours() && d.getMinutes()<=date.getMinutes()){
			return null;
		}
	
		return d;
	}
	

	/**
	 * Funció encarregada de comprovar si la informació inserida als camps de temps del menú principal és vàlida o no.
	 * @param s: String que conté les dades obtingudes dels camps de temps del menú principal del programa
	 * @return: String de sortida amb l'hora col·locada de forma correcte.
	 */
	public String[] comprovacioTemps(String[] s){
		Frame frame = new Frame();
		int minuts_Inici, minuts_Durada, hores_Inici,segons_Inici,segons_Durada;
		boolean totOk=true;
		
		for(int i=0; i<4;i++){
			if(s[i].length()>2){
				totOk=false;
			}
		}
		if(totOk==true){
			if(s[0].isEmpty() || s[1].isEmpty() || s[2].isEmpty()){
				JOptionPane.showMessageDialog(frame,"Falta algun camp de l'hora!","Warning",JOptionPane.WARNING_MESSAGE);
				s[0]=null;
				return s;
	
			}else{
				if(s[3].isEmpty() || s[4].isEmpty()){
					JOptionPane.showMessageDialog(frame,"Falta algun camp de la durada del joc!","Warning",JOptionPane.WARNING_MESSAGE);
					s[0]= null;
					return s;
	
				}else{
					minuts_Durada = Integer.parseInt(s[3]);
					minuts_Inici = Integer.parseInt(s[1]);
					hores_Inici = Integer.parseInt(s[0]);
					segons_Inici = Integer.parseInt(s[2]);
					segons_Durada = Integer.parseInt(s[4]);
					
					if(hores_Inici>24 || hores_Inici<0){
						JOptionPane.showMessageDialog(frame,"Error en l'hora de l'hora d'inici!","Warning",JOptionPane.WARNING_MESSAGE);
						s[0] = null;
					}else if(minuts_Inici>60 || minuts_Inici<0){
						JOptionPane.showMessageDialog(frame,"Error en els minuts de l'hora d'inici!","Warning",JOptionPane.WARNING_MESSAGE);
						s[0] = null;
					}else if(minuts_Durada>60 || minuts_Durada<0){
						JOptionPane.showMessageDialog(frame,"Error en els minuts de durada del joc!","Warning",JOptionPane.WARNING_MESSAGE);
						s[0] = null;
					}else if(segons_Inici>60 || segons_Inici<0){
						JOptionPane.showMessageDialog(frame, "Error en els segons d'inici!", "Warning", JOptionPane.WARNING_MESSAGE);
						s[0]=null;
					}else if(segons_Durada>60 || segons_Durada<0){
						JOptionPane.showMessageDialog(frame, "Error en els segons de partida!", "Warning", JOptionPane.WARNING_MESSAGE);
						s[0]=null;
					}
					return s;
				}
			}
		}else{
			s[0]=null;
			JOptionPane.showMessageDialog(frame, "Error has introduit m�s dels caracters necessaris!", "Warning", JOptionPane.WARNING_MESSAGE);
			return s;
		}
	}
	
	/**
	 * Aquest procediment és l'encarregat de gestionar la inserció de un nou usuari a la base de dades a través del nostre programa.
	 * En primer lloc es realitza un connexió amb el servidor per tal de contactar amb la base de dades i poder inserir les dades que li
	 * passem.
	 * @param nickname: String que conté el nickname del nou usuari a enregistrar
	 * @param nom: String que conté el nom de l'usuari a enregistrar
	 * @param contrasena: String que conté la contrasenya de l'usuari a enregistrar
	 */
	public void inserirUsuari(String nickname, String nom, String contrasena){
		Operacions op = new Operacions();
		String contra;
		boolean trobatLletra=false,trobatNumero=false;
		contra = contrasena;
		
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
				conn.connect();
				if(op.inserirUserBBDD(nickname,contrasena,nom, conn)){
					controler.cleanVistaRegister();
					//ArrayList<Object[]> data = op.selectUsersBBDD(conn);
					//controler.setDataTableRegister(data);
					actualizarTablas();
					controler.actualitzaUsuari();
				}
				conn.disconnect();
			}else{
				Frame frame = new Frame();
				JOptionPane.showMessageDialog(frame, "Error: La contrase�a ha de contener numeros i letras", "Error", JOptionPane.ERROR_MESSAGE);

			}
			trobatNumero = false;
			trobatLletra = false;
		}else{
			Frame frame = new Frame();
			JOptionPane.showMessageDialog(frame, "Error: La contrase�a ha de tener 6 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
		
	public void setController(ButtonController controler){
		this.controler = controler;
	}
	
	public Object[][] fillObject(ArrayList<Object[]> object){
		Object[][] objetos = new Object[object.size()][2];
		for(int i=0;i<object.size();i++){
			Object[] arrayObject = object.get(i);
			objetos[i][0] = arrayObject[0];
			objetos[i][1] = arrayObject[1];
		}
		return objetos;
	}
	
	/**
	 * Aquest procediment com el seu nom indica, és l'encarregat de eliminar un usuari de la base de dades. Per tal de dur-ho
	 * a terme, aques estableix una connexió amb el servidor i li envía el nickname de l'usuari a eliminar per tal de aquest 
	 * sigui eliminat de la base de dades.
	 * @param nickname: String que conté el nickname de l'usuari a eliminar.
	 */
	public void eliminarUsuari(String nickname){
		  Operacions operacions = new Operacions();
		  Frame frame = new Frame();
		  conn.connect();
		  if(operacions.deleteUserBBDD(nickname, conn)){
			  JOptionPane.showMessageDialog(frame, "L'usuari ha sigut eliminat satisfactoriament!", "Usuari Eliminat", JOptionPane.INFORMATION_MESSAGE);
			  conn.disconnect();
			  actualizarTablas();
		  }else{
			  JOptionPane.showMessageDialog(frame, "L'usuari no ha sigut eliminat correctament!", "Error", JOptionPane.ERROR_MESSAGE);
			  conn.disconnect();
		  }
	}
	
	public void actualizarTablas(){
		Operacions operacions = new Operacions();
		conn.connect();
		ArrayList<Object[]> data = operacions.selectUsersBBDD(conn);
		controler.setDataTableRegister(data);
		ArrayList<Object[]> points = operacions.selectUsersPointsBBDD(conn);
		controler.setDataTablePoints(points);
		ArrayList<String> nicknames = operacions.selectNameUsersBBDD(conn);
		controler.setDataComboBox(nicknames);
		conn.disconnect();
	}
	
	/**
	 * Aquesta funció és l'encarregada d'establir una connexió amb el servidor per tal d'inserir un nou usuari des-de el
	 * programa Client a la base de dades.
	 * @param nickname: String que conté el Nickname del nou usuari que es vol registrar.
	 * @param nom: String que conté el nom del nou usuari que es vol registrar.
	 * @param contrasena: String que conté la contrasena del nou usuari que es vol registrar.
	 * @return: resultat de la connexió amb el servidor.
	 */
	public boolean inserirUsuariClient(String nickname, String nom, String contrasena){
		Operacions op = new Operacions();
		conn.connect();
		if(op.inserirUserClientBBDD(nickname,contrasena,nom, conn)){
			actualizarTablas();
			conn.disconnect();
			return true;
		}else{
			conn.disconnect();
			return false;
		}
	}
	
	/**
	 * Aquesta funció s'encarrega de realitzar una connexió amb el servidor per tal de poder accedir a la base de dades i 
	 * realitzar l'inici de sessió al torneig com a usuari convidat.
	 * @param nickname: Variable del tipus String que conté el Nickname de l'usuari que vol iniciar sessió.
	 * @param contrasena: Variable del tipus String que conté la contrasenya de l'usuari que vol iniciar sessió.
	 * @return: resultat de la connexió amb el servidor.
	 */
	public int loginClient(String nickname,String contrasena){
		Operacions op = new Operacions();
		conn.connect();
		int num = op.selectUserSpecificBBDD(nickname, contrasena, conn);
		conn.disconnect();
		return num;
	}
	
	/**
	 * Aquest procediment realitza una connexió amb el servidor del programa per tal de canviar l'estat de l'usuari especificat
	 * mitjaçant el nickname de la base de dades. En aquest cas ens permet caviar l'estat d'un usuari de desbloquejat a bloquejat.
	 * @param nickname: Variable tipus String que conté el nickname de l'usuari registrat.
	 */
	public void blockUser(String nickname){
		Operacions op = new Operacions();
		conn.connect();
		op.blockUserBBDD(nickname, conn);
		conn.disconnect();
	}
	
	/**
	 * Aquest procediment realitza una connexió amb el servidor del programa per tal de canviar l'estat de l'usuari especificat
	 * mitjaçant el nickname de la base de dades. En aquest cas ens permet caviar l'estat d'un usuari de bloquejat a desbloquejat.
	 * @param nickname: Variable tipus String que conté el nickname de l'usuari registrat.
	 */
	public void disblockUser(String nickname){
		Operacions op = new Operacions();
		conn.connect();
		op.disblockUserBBDD(nickname, conn);
		conn.disconnect();
	}
	
	/**
	 * actualitzarPunts s'encarrega de realitzar una connexió amb el servidor del programa i modificar la puntuació del usuari
	 * especificat a partir del nickname de la base de dades. 
	 * @param points: Variable tipus int que conté la puntuació obtinguda per l'usuari en la última partida.
	 * @param nickname: Variable tipus String que conté el nickname de l'usuari registrat.
	 * @return retorna el valor obtingut al realitzar la connexió amb la bbdd.
	 */
	public int actualitzarPunts(int points,String nickname,int modoJuego){
		Operacions op = new Operacions();
		conn.connect();
		 int num = op.sumarPuntosBBDD(nickname, points,modoJuego, conn);
		conn.disconnect();
		return num;
	}
	
	public ArrayList<String> selecPartidasMemoriaUser(String nickname){
		Operacions op = new Operacions();
		conn.connect();
		ArrayList<String> memoria = op.selectPartidasMemoriaBBDD(nickname, conn);
		conn.disconnect();
		return memoria;
	}
	
	public ArrayList<String> selectPartidasConcentracioUser(String nickname){
		Operacions op = new Operacions();
		conn.connect();
		ArrayList<String> concentracio = op.selectPartidasConcentracioBBDD(nickname, conn);
		conn.disconnect();
		return concentracio;
	}
	
	public void dropDataBase(){
		Operacions op = new Operacions();
		conn.connect();
		op.dropDataBase(conn);
		conn.disconnect();
	}
	

}
