package network;

import java.io.Serializable;

import javax.swing.JTable;

public class Client implements Serializable {

	/**
	 * La classe Client és la que conté tota la informació referent al client que es conecta al servidor. Aquesta s'encarrega 
	 * d'emmagatzemar el nickname empleat per l'usuari, el nom, el cognom, la contrasenya, els punts i un booleà indicant si aquest
	 * està bloquejat o no. 
	 */
	private static final long serialVersionUID = 1L;

	private String nickname;
	private String nom;
	private String cognoms;
	private String contrasena;
	private int punts;
	private int blocked=0;
	private int modoJuego;
	private JTable table;
	
	public Client(){}
	
	public Client(String nom,String nickname,String cognoms,String contrasena,int punts,int blocked){
		this.cognoms = cognoms;
		this.nom = nom;
		this.punts = punts;
		this.nickname = nickname;
		this.contrasena = contrasena;
		this.blocked = blocked;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCognoms() {
		return cognoms;
	}
	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getPunts() {
		return punts;
	}
	public void setPunts(int punts) {
		this.punts = punts;
	}
	
	public int getBlocked(){
		return blocked;
	}
	
	public void setBlocked(int blocked){
		this.blocked = blocked;
	}
	
	public int getModo(){
		return this.modoJuego;
	}
	
	public void setModo(int modoJuego){
		this.modoJuego = modoJuego;
	}

	public void setJTable(JTable table){
		this.table = table;
	}
	
	public JTable getJTable(){
		return table;
	}
}
