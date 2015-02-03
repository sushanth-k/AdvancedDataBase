package edu.isys.assign3.implementation;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import edu.isys.assign3.domain.Record;
import edu.isys.assign3.domain.Visit;
import edu.isys.assign3.repository.RecordRepository;
import edu.isys.assign3.util.ConnectionManager;

public class WestRecordRepositoryImpl implements RecordRepository {
	private ResultSet result = null;	
	private CallableStatement callableStatement = null;
	List<Visit> visitList = null;
	
	public Record get(String mercyId) {
		Record ehrRecord = new Record();
		//Visit  visit     = new Visit();	
		visitList = new ArrayList<Visit>();
		int i =0;
		ConnectionManager conn = new ConnectionManager();
		try{
		String query_str = "SELECT * FROM PERSON JOIN VISIT ON PERSON.ID = VISIT.PERSON_ID WHERE PERSON.EHR_NUMBER = ? ";	
		callableStatement = conn.getConnectionWest().prepareCall(query_str);
		callableStatement.setString(1, mercyId);
		result = callableStatement.executeQuery();
		while(result.next())
		{	
			ehrRecord.setClinic("West");
			ehrRecord.setGender(result.getString(5));
			ehrRecord.setGroupId(result.getString(7));
			ehrRecord.setInsurance(result.getString(6));
			ehrRecord.setMercyId(result.getString(2));
			ehrRecord.setName(result.getString(3)+result.getString(4));	
			
			Visit  visit     = new Visit();
			visit.setId(result.getInt(8));
			visit.setDate(result.getDate(10));
			visit.setDiagnosis(result.getString(12));
			visit.setDoctor(result.getString(11));
			visit.setNotes(result.getString(13));			
			visitList.add(i, visit);
			i++;
			
		}		
		ehrRecord.setVisits(visitList);
		//conn.closeConnectionWest();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    } 		
		return ehrRecord;		
	}
}
