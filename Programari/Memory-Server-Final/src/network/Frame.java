package network;

import java.io.Serializable;

public class Frame implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Client object;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Client getObject() {
		return object;
	}
	public void setObject(Client object) {
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
