package model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Configuracio {
	private String nom;
	private int codi;
	private double saldo;

	public Configuracio(){}
	
	public Configuracio(String nom, int codi, double saldo){
		this.nom = nom;
		this.codi = codi;
		this.saldo = saldo;
	}
	
	public String[] llegir(){
		String cadena = "";
		String cadenaTotal = "";
		FileReader f;
		BufferedReader b = null;
		
		//Llegim el fitxer utilitzant la llibreria Json
		try {
			
			f = new FileReader("Configuracio.json");
			b = new BufferedReader(f);
			
			while ((cadena = b.readLine()) != null){
				cadenaTotal = cadenaTotal + cadena;
				
			}
			
			b.close();
			
			Gson gson = new GsonBuilder().create();
			
			JsonObject objecte = new JsonObject();
			
			objecte = (JsonObject) gson.fromJson(cadenaTotal, JsonObject.class);
			String[] info = new String[2];
			
			info[0] = objecte.get("IP").getAsString();
			info[1] = objecte.get("PortSocket").getAsString();
			
			return info;
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}