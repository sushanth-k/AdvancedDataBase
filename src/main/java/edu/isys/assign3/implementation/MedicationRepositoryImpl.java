package edu.isys.assign3.implementation;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import edu.isys.assign3.domain.Medication;
import edu.isys.assign3.repository.MedicationRepository;
import edu.isys.assign3.util.ConnectionManager;

public class MedicationRepositoryImpl implements MedicationRepository {
	private ResultSet result = null;	
	private CallableStatement callableStatement = null;
	List<Medication> medicationList = null;
	
	public Medication get(int id) {
		Medication medicationLoc = new Medication();
		try{
		
		ConnectionManager conn = new ConnectionManager();		
		String query_str = "SELECT * FROM MEDICATION WHERE ID = ?";	
		callableStatement = conn.getConnection().prepareCall(query_str);
		callableStatement.setInt(1, id);
		result = callableStatement.executeQuery();
		while(result.next())
		{
			medicationLoc.setName(result.getString(2));
			medicationLoc.setCommonName(result.getString(3));
			medicationLoc.setDescription(result.getString(4));
			medicationLoc.setId(result.getInt(1));
		}
		//conn.closeConnection();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 
		return medicationLoc;
	}

	public List<Medication> getAll() {
		Medication medicationLoc = new Medication();
		medicationList = new ArrayList<Medication>();
		try{
		
		ConnectionManager conn = new ConnectionManager();		
		String query_str = "SELECT * FROM MEDICATION";	
		callableStatement = conn.getConnection().prepareCall(query_str);		
		result = callableStatement.executeQuery();
		while(result.next())
		{
			medicationLoc.setName(result.getString(2));
			medicationLoc.setCommonName(result.getString(3));
			medicationLoc.setDescription(result.getString(4));
			medicationList.add(medicationLoc);
		}
		//conn.closeConnection();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 
		return medicationList;
	}

}
