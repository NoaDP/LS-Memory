package com.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.utils.*;

public class Main {
	static Operacions operacions = new Operacions();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResultSet consulta;
		//Enviem a la nova instància ConectorDB usuari, password, BBDD i port per iniciar els paràmetres de connexió
		ConectorDB conn = new ConectorDB("root", "javivi1996", "memotournament_bbdd", 3306);
		//Ens connectem a la BBDD
		conn.connect();
		//Inserim un registre a la BBDD
		operacions.inserirUserBBDD("Rafael","Garcia","Rafanator36","123f56",conn);
		operacions.inserirUserBBDD("MAnel","MAnchon","Manolo","123f56",conn);
		operacions.inserirUserBBDD("Juan","","Juan","123f56",conn);
		//Modifiquem un registre de la BBDD
		//operacions.updateUserBBDD("nickname='Rafanator'", "id_usuari='1'", conn);
		//Eliminem un registre de la BBDD
		//operacions.deleteUserBBDD("clau_usuari = '123456'",conn);
		//Seleccionem registres de la BBDD
		//operacions.selectUsersBBDD(conn);
		//Ens desconectem de la BBDD una vegada no la necessitem
		//////////////////////////////////////////////////////////////////////
		operacions.inserirPartidaBBDD("Rafanator36", 300, "concentracio", conn);
		operacions.inserirPartidaBBDD("Manolo", 500, "concentracio", conn);
		//operacions.inserirPartidaBBDD("Juan", 1000, "concentracio", conn);
		operacions.inserirPartidaBBDD("Rafanator36", 100, "concentracio", conn);
		//operacions.deletePartidaBBDD("puntuacio = '1000'", conn);
		//operacions.updatePartidaBBDD("id_partida = 3", "id_partida = 2", conn);
		//operacions.selectPartidasSpecificBBDD("puntuacio > '100'", "'puntuacio'", conn);
		operacions.selectUsersBBDD(conn);
		//////////////////////////////////////////////////////////////////////
		conn.disconnect();		

	}

}
