package edu.isys.assign3.implementation;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.isys.assign3.domain.Prescription;
import edu.isys.assign3.domain.Status;
import edu.isys.assign3.repository.PrescriptionRepository;
import edu.isys.assign3.util.ConnectionManager;


public class PrescriptionRepositoryImpl implements PrescriptionRepository {
	private ResultSet result = null;	
	private CallableStatement callableStatement = null;
	List<Prescription> prescriptionList = null;
	private DoctorRepositoryImpl doc = new DoctorRepositoryImpl();
	private PatientRepositoryImpl pat = new PatientRepositoryImpl();
	private MedicationRepositoryImpl med = new MedicationRepositoryImpl();
	private ParmacistRepositoryImpl pharm = new ParmacistRepositoryImpl();
	
	public List<Prescription> getForPatient(int patientId) {
		Prescription prescriptionLoc = new Prescription();
		prescriptionList = new ArrayList<Prescription>();
		try{
		
		ConnectionManager conn = new ConnectionManager();	
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.YEAR, 2014 );
	    cal.set( Calendar.MONTH, Calendar.JANUARY );
	    cal.set( Calendar.DATE, 1 );  
	    java.sql.Date new_date = new java.sql.Date(cal.getTime().getTime());	
	    
		String query_str = "SELECT * FROM Prescription WHERE patient_Id = ?";	
		callableStatement = conn.getConnection().prepareCall(query_str);
		callableStatement.setInt(1, patientId);
		result = callableStatement.executeQuery();
		while(result.next())
		{
			prescriptionLoc.setId(result.getInt(1));
			prescriptionLoc.setCreated(new_date);			
			prescriptionLoc.setDoctor(doc.get(result.getInt(3)));
			prescriptionLoc.setDose(result.getDouble(7));
			prescriptionLoc.setMedication(med.get(result.getInt(5)));
			prescriptionLoc.setPatient(pat.getId(result.getInt(2)));
			prescriptionLoc.setPharmacist(pharm.get(result.getInt(4)));
			switch(result.getInt(6)){				
				case 1: prescriptionLoc.setStatus(Status.FILLED);
					break;
				case 2: prescriptionLoc.setStatus(Status.DENIED);
					break;
				case 3: prescriptionLoc.setStatus(Status.OVERRULED);
					break;
				default: prescriptionLoc.setStatus(Status.FILLED);
			}
			
			//conn.closeConnection();
			prescriptionList.add(prescriptionLoc);
		}
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 
		return prescriptionList;
		
	}
	

	public void put(Prescription prescription) {
		
		try{			
		ConnectionManager conn = new ConnectionManager();
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.YEAR, 2014 );
	    cal.set( Calendar.MONTH, Calendar.JANUARY );
	    cal.set( Calendar.DATE, 1 );  
	    java.sql.Date new_date = new java.sql.Date(cal.getTime().getTime());
	    
		String query_str = "INSERT INTO Prescription(patient_id,doctor_id,pharmacist_id,medication_id,status_id,"+
							"dose_mg,created_date,modified_date,notes)"+"values(?,?,?,?,?,?,?,?,?)";	
		
		callableStatement = conn.getConnection().prepareCall(query_str);
		callableStatement.setInt(1, prescription.getPatient().getId());		
		callableStatement.setInt(2, prescription.getDoctor().getId());
		callableStatement.setInt(3, prescription.getPharmacist().getId());
		callableStatement.setInt(4, prescription.getMedication().getId());
		callableStatement.setInt(5, prescription.getStatus().getId());
		callableStatement.setDouble(6, prescription.getDose());
		callableStatement.setDate(7, new_date);
		callableStatement.setDate(8, new_date);
		callableStatement.setString(9, prescription.getNotes());
		
		callableStatement.executeUpdate();
		//conn.closeConnection();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    }
		
	}

}
