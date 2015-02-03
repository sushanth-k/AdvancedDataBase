package edu.isys.assign3.implementation;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import edu.isys.assign3.domain.Patient;
import edu.isys.assign3.repository.PatientRepository;
import edu.isys.assign3.util.ConnectionManager;

public class PatientRepositoryImpl implements PatientRepository {
	List<Patient> patients = null;
	private ResultSet result = null;	
	private CallableStatement callableStatement = null;
	public List<Patient> get(String firstName, String lastName) {
		patients = new ArrayList<Patient>();
		Patient patientLoc = new Patient();
		try{
		
		ConnectionManager conn = new ConnectionManager();		
		String firstNameWildCard = "%" + firstName + "%";
		String lastNameWildCard = "%" + lastName + "%";
		String query_str  = "Select * from Patient where firstName like '" 
								+ firstNameWildCard +"'and lastName like '" + lastNameWildCard + "'";
				
		callableStatement = conn.getConnection().prepareCall(query_str);		
		result = callableStatement.executeQuery();
		while(result.next())
		{
			patientLoc.setId(result.getInt(1));
			patientLoc.setMercyId(result.getString(2));
			patientLoc.setMercyClinic(result.getString(3));
			patientLoc.setFirstName(result.getString(4));
			patientLoc.setLastName(result.getString(5));		
			patientLoc.setPhoneNumber(result.getString(6));
			
			patients.add(patientLoc);
		}
		//conn.closeConnection();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 
		return patients;
		
	}
	
	public Patient getId(int id) {		
		Patient patientLoc = new Patient();
		try{
		
		ConnectionManager conn = new ConnectionManager();				
		String query_str ="Select * from Patient where id = ?";
		callableStatement = conn.getConnection().prepareCall(query_str);
		callableStatement.setInt(1, id);
		result = callableStatement.executeQuery();
		while(result.next())
		{
			patientLoc.setId(result.getInt(1));
			patientLoc.setMercyId(result.getString(2));
			patientLoc.setMercyClinic(result.getString(3));
			patientLoc.setFirstName(result.getString(4));
			patientLoc.setLastName(result.getString(5));		
			patientLoc.setPhoneNumber(result.getString(6));		
		}
		//conn.closeConnection();
	}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 
		return patientLoc;		
	}

}
