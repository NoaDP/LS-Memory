package com.main;

import com.db.utils.ConectorDB;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Operacions {
	public Operacions(){}
	public void inserirUserBBDD(String nom_usuari, String apellidos,String nickname,String clau_usuari, ConectorDB conn){
		String insert= new String();
		insert = "INSERT INTO Usuari (nom_usuari,apellidos,nickname,clau_usuari) VALUES ('" + nom_usuari +"','"+apellidos+"','" + nickname +"','" + clau_usuari + "')";
		conn.insertQuery(insert);
	}
	public void deleteUserBBDD(String condicio, ConectorDB conn){
		String delete = new String();
		delete = "DELETE FROM Usuari WHERE " + condicio;
		conn.deleteQuery(delete);
	}
	public void updateUserBBDD(String condicio, String set,ConectorDB conn){
		String update = new String();
		update = "UPDATE Usuari SET "+ set+ " WHERE " + condicio;
		conn.updateQuery(update);
	}
	public void selectUsersBBDD(ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Usuari");
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els paràmetres desitjats
			while (consulta.next())
			{
			    //Per recuperar el valor utilitzem la funció .getObject() amb el nom del camp a recuperar
				System.out.println (consulta.getObject("nom_usuari") /*+ " " + consulta.getObject("nickname")+ " " + consulta.getObject("clau_usuari")*/);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problema al recuperar les dades...");
		}
	}
	public void inserirPartidaBBDD(String nickname, int puntuacio, String modalitat, ConectorDB conn){
		String insert= new String();
		insert = "INSERT INTO Partida (nickname,puntuacio,modalitat) VALUES ('" + nickname +"','"+puntuacio+"','" + modalitat + "');";
		System.out.println(insert);
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
	public void selectPartidasBBDD(ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Partida");
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els paràmetres desitjats
			while (consulta.next())
			{
			    //Per recuperar el valor utilitzem la funció .getObject() amb el nom del camp a recuperar
				System.out.println (consulta.getObject("id_partida") + " " + consulta.getObject("nickname")+ " " + consulta.getObject("puntuacio")+" " + consulta.getObject("modalitat") + " " + consulta.getObject("data_partida"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problema al recuperar les dades...");
		}
	}
	public void selectPartidasSpecificBBDD(String condicion,String orden, ConectorDB conn){
		ResultSet consulta =conn.selectQuery("SELECT * FROM Partida WHERE " + condicion + " ORDER BY " + orden + "DESC");
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els paràmetres desitjats
			while (consulta.next())
			{
			    //Per recuperar el valor utilitzem la funció .getObject() amb el nom del camp a recuperar
				System.out.println (consulta.getObject("id_partida") + " " + consulta.getObject("nickname")+ " " + consulta.getObject("puntuacio")+" " + consulta.getObject("modalitat") + " " + consulta.getObject("data_partida"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problema al recuperar les dades...");
		}
	}
}
