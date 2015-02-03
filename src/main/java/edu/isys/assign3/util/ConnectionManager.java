package edu.isys.assign3.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;


public class ConnectionManager {
	private Connection connect,connectWest = null;
	Session session = null;
	Cluster cluster;
	public ConnectionManager(){};
	public Connection getConnection() throws ClassNotFoundException{	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/mercy_pharm","isys622","p@ssw0rd");		
	}		
	catch (Exception e) 
    {
		e.printStackTrace();
    }    
	return connect;	
	}
	public void closeConnection() throws SQLException
	{
		connect.close();
	}
	public Connection getConnectionWest() throws ClassNotFoundException{	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connectWest = DriverManager.getConnection("jdbc:mysql://localhost/mercy_west_ehr","isys622","p@ssw0rd");		
		}		
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 	  
		return connectWest;	
		}
	public void closeConnectionWest() throws SQLException
	{
		connectWest.close();
	}
	public Session getSessionNorth() throws ClassNotFoundException{	
		
		try{
			String node = "127.0.0.1";
			String keyspace = "isys622";
			cluster = Cluster.builder().addContactPoint(node).build();				
			session = cluster.connect(keyspace);			
		}		
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 	  
		return session;	
		}
	public void closeSessionNorth() throws SQLException
	{
		session.close();
		cluster.close();
	}
	
}

