package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.Frame;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Operacions {
	
	/**
	 * La classe Operacions és l'encarregada de realitzar les diferents operacions referents a la base de dades que conté la 
	 * informació del usuaris. 
	 */
	private ArrayList<String> al = new ArrayList<String>();
	
	public Operacions(){}
	
	/** 
	 * La funció inserirUserBBDD permet inserir un nou usuari a la base de dades a partir del menú del menú principal del nostre
	 * programa. Aquest reb tota la informació del client i afeigeix una línia sencera a la taula Usuari.
	 * @param nickname: És una variable tipus String que conté el nickname de l'usuari 
	 * @param clau_usuari:És una String que conté la contrasenya de l'usuari
	 * @param nom: És una String que conté el nom de l'usuari
	 * @param conn: Variable tipus ConnectorDB que ens permet realitzar la connexió amb la base de dades
	 * @return: retorna un boolean que determina l'èxit de la connexió.
	 */
	public boolean inserirUserBBDD(String nickname,String clau_usuari, String nom, ConectorDB conn){
		String insert= new String();
		insert = "INSERT INTO Usuari (nickname,clau_usuari,nom,puntuacio_total,bloqued) VALUES ('" + nickname + "','" + clau_usuari + "','"+ nom + "','0','0')";
		//conn.insertQuery(insert);
		return conn.insertQuery(insert);
			
	}
	
	/** 
	 * La funció inserirUserBBDD permet inserir un nou usuari a la base de dades a partir del menú del menú principal del 
	 * programa client. Aquest reb tota la informació del client que s'ha registrat i afeigeix una línia sencera a la taula Usuari.
	 * @param nickname: És una variable tipus String que conté el nickname de l'usuari 
	 * @param clau_usuari:És una String que conté la contrasenya de l'usuari
	 * @param nom: És una String que conté el nom de l'usuari
	 * @param conn: Variable tipus ConnectorDB que ens permet realitzar la connexió amb la base de dades
	 * @return: retorna un boolean que determina l'èxit de la connexió.
	 */
	public boolean inserirUserClientBBDD(String nickname,String clau_usuari, String nom, ConectorDB conn){
		String insert= new String();
		insert = "INSERT INTO Usuari (nickname,clau_usuari,nom,puntuacio_total,bloqued) VALUES ('" + nickname + "','" + clau_usuari + "','"+ nom + "','0','0')";
		//conn.insertQuery(insert);
		return conn.insertQueryUsuari(insert);
			
	}
	
	/**
	 * Com el seu nom indica, aquesta funció permet eliminar un usuari de la base de dades. Aquest reb el nickname, el qual és
	 * la primary key de la taula usuari i l'utilitza per a localitzar l'usuari dintre de la taula. 
	 * @param nickname: És una variable tipus String que conté el nickname de l'usuari 
	 * @param conn: Variable tipus ConnectorDB que ens permet realitzar la connexió amb la base de dades
	 * @return: variable tipus boolean que determina si la operació s'ha realitzat amb èxit o no
	 */
	public boolean deleteUserBBDD(String nickname, ConectorDB conn){
		String delete = new String();
		delete = "DELETE FROM Usuari WHERE nickname ='"+nickname+"'";
		return conn.deleteQuery(delete);
	}
	

	/**
	 * EL procediment updateUserBBDD permet realitzar una modificació de la base de dades. 
	 * @param condicio: 
	 * @param set:
	 * @param conn: Variable ConectorDB que serveix per connectar amb la base de dades.
	 */
	public void updateUserBBDD(String condicio, String set,ConectorDB conn){
		String update = new String();
		update = "UPDATE Usuari SET "+ set+ " WHERE " + condicio;
		conn.updateQuery(update);
	}
	
	public ArrayList<Object[]> selectUsersBBDD(ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Usuari");
		String data = new String();
		ArrayList<Object[]> arrayObjetos = new ArrayList<>();
		
		int primera = 0;
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els par�metres desitjats
			while (consulta.next()){
				
				if((int)consulta.getObject("bloqued")==1){
					Object[] object = {consulta.getObject("nickname"),consulta.getObject("nom"),"Bloquejat"};
					arrayObjetos.add(object);
				}else{
					Object[] object = {consulta.getObject("nickname"),consulta.getObject("nom"),"No Bloquejat"};
					arrayObjetos.add(object);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problema al recuperar les dades...");
		}
		for(int i=0; i<arrayObjetos.size();i++){
			System.out.println(arrayObjetos.get(i));

		}
		//data = "{"+data+"}";
		return arrayObjetos;
	}
	
	public ArrayList<Object[]> selectUsersPointsBBDD(ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Usuari ORDER BY puntuacio_total DESC");
		String data = new String();
		ArrayList<Object[]> arrayObjetos = new ArrayList<>();
		
		int primera = 0;
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els par�metres desitjats
			while (consulta.next()){
				Object[] object = {consulta.getObject("nickname"),consulta.getObject("puntuacio_total")};
				arrayObjetos.add(object);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problema al recuperar les dades...");
		}
		for(int i=0; i<arrayObjetos.size();i++){
			System.out.println(arrayObjetos.get(i));

		}
		//data = "{"+data+"}";
		return arrayObjetos;
	}
	
	/**
	 * La funció selectNameUsersBBDD ens permet seleccionar el nom de l'usuari 
	 * @param conn: Variable ConectorDB que serveix per connectar amb la base de dades.
	 * @return: array de String que conté els nicknames de tots els usuaris.
	 */
	public ArrayList<String> selectNameUsersBBDD(ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Usuari");
		ArrayList<String> nicknames = new ArrayList<>();
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els par�metres desitjats
			while (consulta.next()){
				nicknames.add(consulta.getObject("nickname").toString());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return nicknames;
	}
	
	public void inserirPartidaBBDD(String nickname, int puntuacio, String modalitat, ConectorDB conn){
		String insert= new String();
		insert = "INSERT INTO Partida (nickname,puntuacio,modalitat) VALUES ('" + nickname +"','"+puntuacio+"','" + modalitat + "');";
		conn.insertQuery(insert);
	}
	
	public void deletePartidaBBDD(String condicio, ConectorDB conn){
		String delete = new String();
		delete = "DELETE FROM Partida WHERE " + condicio;
		conn.deleteQuery(delete);
	}
	
	public void updatePartidaBBDD(String condicio, String set,ConectorDB conn){
		String update = new String();
		update = "UPDATE Partida SET "+ set+ " WHERE " + condicio;
		conn.updateQuery(update);
	}
	
	public ArrayList<String> selectPartidasMemoriaBBDD(String nickname,ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Partida WHERE modalitat='Memoria' ORDER BY data_partida ASC");
		ArrayList<String> memoria = new ArrayList<>();
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els par�metres desitjats
			while (consulta.next()){
				if(nickname.equals(consulta.getObject("nickname").toString())){
					memoria.add(consulta.getObject("puntuacio").toString());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memoria;
	}
	
	public ArrayList<String> selectPartidasConcentracioBBDD(String nickname,ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Partida WHERE modalitat='Concentracio' ORDER BY data_partida ASC");
		ArrayList<String> concentracio = new ArrayList<>();
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els par�metres desitjats
			while (consulta.next()){
				if(nickname.equals(consulta.getObject("nickname").toString())){
					concentracio.add(consulta.getObject("puntuacio").toString());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return concentracio;
	}
	public void selectPartidasSpecificBBDD(String condicion,String orden, ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Partida WHERE nickname='" + condicion + "' AND " + orden + "DESC");
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els par�metres desitjats
			while (consulta.next())
			{
			    //Per recuperar el valor utilitzem la funci� .getObject() amb el nom del camp a recuperar
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public int selectUserSpecificBBDD(String nickname,String contrasena, ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Usuari");
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els par�metres desitjats
			
			while (consulta.next())
			{
				String nick = consulta.getObject("nickname").toString();
				String contra=consulta.getObject("clau_usuari").toString();
				if(nick.equals(nickname) && contra.equals(contrasena)){
					if((int)consulta.getObject("bloqued")==1){
						return 5;
					}
					return 2;
				}

			    //Per recuperar el valor utilitzem la funci� .getObject() amb el nom del camp a recuperar
				//System.out.println (consulta.getObject("id_partida") + " " + consulta.getObject("nickname")+ " " + consulta.getObject("puntuacio")+" " + consulta.getObject("modalitat") + " " + consulta.getObject("data_partida"));
			}
			return 3;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 4;
		}
	}
	
	/**
	 * El procediment blockUserBBDD com el seu nom indica ens permet bloquejar a un dels usuaris existents a la base de dades.
	 * Per tal de dur-ho a terme busquem l'usuari a bloquejar mitjançant el nickname i després canviem el valor de la columna 
	 * bloqued a 1, per tal establir que aquest usuari està bloquejat.
	 * @param nickname: És una variable tipus String que conté el nickname de l'usuari 
	 * @param conn: Variable ConectorDB que serveix per connectar amb la base de dades.
	 */
	public void blockUserBBDD(String nickname,ConectorDB conn){
		String update = new String();
		update = "UPDATE Usuari SET bloqued='1' WHERE nickname='" + nickname+"'";
		conn.updateQuery(update);
	}
	
	/**
	 * El procediment disblockUserBBDD ens permet desbloquejar un usuari que estava prèviament bloquejat, és a dir, canviar
	 * el paràmetre de la taula que defineix si un usuari està bloquejat o no. En aquest cas se li passa el valor 0 a la base de
	 * dades i es busca l'usuari a desbloquejar mitjançant el nickname que li passem. 
	 * @param nickname: És una variable tipus String que conté el nickname de l'usuari 
	 * @param conn: Variable ConectorDB que serveix per connectar amb la base de dades.
	 */
	public void disblockUserBBDD(String nickname,ConectorDB conn){
		String update = new String();
		update = "UPDATE Usuari SET bloqued='0' WHERE nickname='" + nickname+"'";
		conn.updateQuery(update);
	}
	
	/**
	 * Aquesta funció ens permet augmentar la puntuació d'un usuari a la base de dades. Aquesta reb el nickname de l'usuari,
	 * la quantitat de punts a sumar i la variable de connexió amb la base de dades. 
	 * @param nickname: És una variable tipus String que conté el nickname de l'usuari 
	 * @param points: Variable tipus int que conté la puntuació obtinguda per l'usuari
	 * @param conn: Variable ConectorDB que serveix per connectar amb la base de dades.
	 * @return variable tipus int que determina si la operació s'ha realitzat amb èxit.
	 */
	public int sumarPuntosBBDD(String nickname,int points,int modoJuego, ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Usuari");
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els par�metres desitjats
			
			while (consulta.next())
			{
				String nick = consulta.getObject("nickname").toString();
				if(nick.equals(nickname)){
					if((int)consulta.getObject("bloqued")==1){
						return 5;
					}else{
						int puntos = (int)consulta.getObject("puntuacio_total");
						puntos = puntos + points;
						String update = new String();
						update = "UPDATE Usuari SET puntuacio_total='"+ puntos +"' WHERE nickname='" + nickname+"'";
						conn.updateQuery(update);
						String insert= new String();
						if(modoJuego == 1){
							insert = "INSERT INTO Partida (nickname,puntuacio,modalitat) VALUES ('" + nickname +"','"+points+"','Memoria');";
							conn.insertQuery(insert);
						}else if(modoJuego==2){
							insert = "INSERT INTO Partida (nickname,puntuacio,modalitat) VALUES ('" + nickname +"','"+points+"','Concentracio');";
							conn.insertQuery(insert);
						}
						
					}
				}
			}
			return 6;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 7;
		}
	}
	
	public void dropDataBase(ConectorDB conn){
		boolean consulta2 =conn.deleteQuery("DELETE FROM Partida WHERE puntuacio>'-1'");
		boolean consulta3 =conn.deleteQuery("DELETE FROM Usuari WHERE puntuacio_total>'-1'");
	}
}
