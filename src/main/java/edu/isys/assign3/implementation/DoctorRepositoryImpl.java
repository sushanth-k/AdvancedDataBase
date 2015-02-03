package edu.isys.assign3.implementation;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.isys.assign3.domain.Doctor;
import edu.isys.assign3.repository.DoctorRepository;
import edu.isys.assign3.util.ConnectionManager;

public class DoctorRepositoryImpl implements  DoctorRepository {

	//private Statement statement = null;
	private ResultSet result = null;	
	private CallableStatement callableStatement = null;
	List<Doctor> doctorList = null;
	public Doctor get(int id) {
		Doctor doctorLoc = new Doctor();
		try{
		
		ConnectionManager conn = new ConnectionManager();			
		String query_str = "SELECT * FROM DOCTOR WHERE ID = ?";	
		callableStatement = conn.getConnection().prepareCall(query_str);
		callableStatement.setInt(1, id);
		result = callableStatement.executeQuery();
		while(result.next())
		{	
			doctorLoc.setId(id);
			doctorLoc.setDeaNumber(result.getString(2));
			doctorLoc.setFirstName(result.getString(3));
			doctorLoc.setLastName(result.getString(4));
			doctorLoc.setDegree(result.getString(5));
			doctorLoc.setPhoneNumber(result.getString(6));
		}
		//conn.closeConnection();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 		
		return doctorLoc;
		
	}

	public List<Doctor> getAll() {		
		Doctor doctorLoc = new Doctor();
		doctorList = new ArrayList<Doctor>(); 
		try{
		
		ConnectionManager conn = new ConnectionManager();		
		String query_str = "SELECT * FROM DOCTOR";	
		callableStatement = conn.getConnection().prepareCall(query_str);		
		result = callableStatement.executeQuery();
		while(result.next())
		{
			doctorLoc.setId(result.getInt(1));
			doctorLoc.setDeaNumber(result.getString(2));
			doctorLoc.setFirstName(result.getString(3));
			doctorLoc.setLastName(result.getString(4));
			doctorLoc.setFirstName(result.getString(5));
			doctorLoc.setPhoneNumber(result.getString(6));
			doctorList.add(doctorLoc);
		}
		//conn.closeConnection();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 
		return doctorList;		
	}

}
