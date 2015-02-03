package edu.isys.assign3.implementation;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import edu.isys.assign3.domain.Pharmacist;
import edu.isys.assign3.repository.ParmacistRepository;
import edu.isys.assign3.util.ConnectionManager;

public class ParmacistRepositoryImpl implements ParmacistRepository {
	private ResultSet result = null;	
	private CallableStatement callableStatement = null;
	List<Pharmacist> pharmacistList = null;
	public Pharmacist get(int id) {
		Pharmacist pharmacistLoc = new Pharmacist();
		try{
		
		ConnectionManager conn = new ConnectionManager();		
		String query_str = "SELECT * FROM Pharmacist WHERE ID = ?";	
		callableStatement = conn.getConnection().prepareCall(query_str);
		callableStatement.setInt(1, id);
		result = callableStatement.executeQuery();
		while(result.next())
		{
			pharmacistLoc.setId(id);
			pharmacistLoc.setDeaNumber(result.getString(2));
			pharmacistLoc.setFirstName(result.getString(3));
			pharmacistLoc.setLastName(result.getString(4));		
			pharmacistLoc.setPhoneNumber(result.getString(5));	
		}
		//conn.closeConnection();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 
		return pharmacistLoc;
	}

	public List<Pharmacist> getAll() {
		pharmacistList = new ArrayList<Pharmacist>();
		Pharmacist pharmacistLoc = new Pharmacist();
		try{
		
		ConnectionManager conn = new ConnectionManager();		
		String query_str = "SELECT * FROM Pharmacist ";	
		callableStatement = conn.getConnection().prepareCall(query_str);		
		result = callableStatement.executeQuery();
		while(result.next())
		{
			pharmacistLoc.setId(result.getInt(1));
			pharmacistLoc.setDeaNumber(result.getString(2));
			pharmacistLoc.setFirstName(result.getString(3));
			pharmacistLoc.setLastName(result.getString(4));		
			pharmacistLoc.setPhoneNumber(result.getString(5));
			pharmacistList.add(pharmacistLoc);
		}
		//conn.closeConnection();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 
		return pharmacistList;
	}

}
