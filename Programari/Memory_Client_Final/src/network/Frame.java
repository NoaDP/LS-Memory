package network;

import java.io.Serializable;

public class Frame implements Serializable{
	
	/**
	 * Aquesta és la classe empleada per a enviar la informació necessària per a realitzar la connexió amb el servidor. La classe Frame
	 * conté la serialVersionUID la qual permet que només l'usuari sigui capaç de realitzar la connexió amb el servidor, una id única, un client
	 * i una serie de variables fixes que defineixen el codi empleat per el servidor per a el reconeixement de les differents opcions del menú i la
	 * gestió de la base de dades.
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Client object;
	
	public Frame(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Client object) {
		this.object = object;
	}
	
	public void setFrame(int id, Client object){
		this.id = id;
		this.object = object;
	}
	
	protected static final int SQL_ADD=0;
	protected static final int SQL_RANK=1;
	protected static final int LOGIN=2;
	protected static final int CONCENTRACIO=3;
	protected static final int MEMORIA=4;
	protected static final int VSMACHINE=5;
	protected static final int ELIMINADO=6;

}
