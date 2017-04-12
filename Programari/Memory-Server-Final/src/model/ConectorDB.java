package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.Frame;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConectorDB {
	
	/**
	 * La classe Conector DB és l'encarregada d'establir la connexió amb la basde de dades que conté la informació dels clients i
	 * a més, de modificar aquestes dades.
	 */
	static String userName;
	static String password;
	static String db;
	static int port;
	static String url = "jdbc:mysql://localhost";
	public static Connection conn = null;
	static Statement s;
    
	public ConectorDB(String usr, String pass, String db, int port) {
		ConectorDB.userName = usr;
		ConectorDB.password = pass;
		ConectorDB.db = db;
		ConectorDB.port = port;
		ConectorDB.url += ":"+port+"/";
		ConectorDB.url += db;
	}

	/**
	 * El procediment connect s'encarrega de connectar-se amb el la base de dades. Per tal de dur-ho a terme primer li envia
	 * una petició a la base de dades. En case de que la resposta retorni un valor no null, significa que la connexió s'ha realitzat
	 * amb èxit.
	 */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection) DriverManager.getConnection(url, userName, password);
            if (conn != null) {
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex) {
        }

    }
    
    /**
     * Aquesta funció s'encarrega de realitzar una petició de connexió amb la base de dades per tal de registrar un nou usuari
     * en la bbdd. En cas de que la connexió es compleixi correctament aquesta funció retornarà true, en cas contrari retornarà
     * false.
     * @param query: Variable tipus String que conté la petició a realitzar a la bbdd.
     * @return resultat de la solicitud amb la bbdd.
     */
    public boolean insertQuery(String query){
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
        	Frame frame = new Frame();
			JOptionPane.showMessageDialog(frame, "Error: L'usuari ja existeix a la base de dades.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    /**
     * Aquesta funció permet realitzar una petició per tal de registrar un nou usuari en la bbdd des-de el programa client. 
     * En cas de que la petició sigui acceptada, la funció retornarà un true, en cas contrari un false.
     * @param query: Variable tipus String que conté la petició a realitzar a la bbdd.
     * @return resultat de la solicitud amb la bbdd.
     */
    public boolean insertQueryUsuari(String query){
        try {
        	System.out.println("QUERY: "+query);
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
			return false;
        }
        return true;
    }
    
    
    public void updateQuery(String query){
    	 try {
             s =(Statement) conn.createStatement();
             s.executeUpdate(query);

         } catch (SQLException ex) {
         }
    }
    
    public boolean deleteQuery(String query){
    	 try {
             s =(Statement) conn.createStatement();
             s.executeUpdate(query);
             return true;
             
         } catch (SQLException ex) {
             return false;
         }
    	
    }
    
    public boolean createQuery(String query){
   	 try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);
            return true;
            
        } catch (SQLException ex) {
            return false;
        }
   	
   }
    
    public static ResultSet selectQuery(String query){
    	ResultSet rs = null;
    	 try {
             s =(Statement) conn.createStatement();
             rs = s.executeQuery (query);
             
         } catch (SQLException ex) {
         }
		return rs;
    }
    

    /**
     * Tal i com el seu nom indica, aques procediment és l'encarregat de realitzar la desconnexió amb la base de 
     * dades.
     */
    public void disconnect(){
    	try {
			conn.close();
		} catch (SQLException e) {
		}
    }

}
