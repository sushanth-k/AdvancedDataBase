package edu.isys.assign3.implementation;
import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.PreparedStatement;

import edu.isys.assign3.domain.Record;
import edu.isys.assign3.domain.Visit;
import edu.isys.assign3.repository.RecordRepository;
import edu.isys.assign3.util.ConnectionManager;

public class NorthRecordRepositoryImpl implements RecordRepository{
	Session session = null;	
	PreparedStatement prepStatement = null, prepStatementVisits;
	Record ehrRecord = new Record();
	ResultSet resultSet = null,resultSetVisits;
	List<Visit> visitList = new ArrayList<Visit>();
	public Record get(String mercyId){		
		try{
			String query_string = "select * from isys622.patients where ehr_number =?";
			String query_String_Visits = "select * from isys622.patient_visits where ehr_number =?";
			ConnectionManager connect = new ConnectionManager();
			session = connect.getSessionNorth();		
			
			prepStatementVisits = session.prepare(query_String_Visits);
			resultSetVisits = session.execute(prepStatementVisits.bind(mercyId));
			dataPatientVisits(resultSetVisits.all());
			
			prepStatement = session.prepare(query_string);						
			resultSet = session.execute(prepStatement.bind(mercyId));			
			dataPatient(resultSet.one(),mercyId);
			connect.closeSessionNorth();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    }
		return ehrRecord;
	}	
	private void dataPatient(Row row,String mercyId){
		String name = row.getString(1)+" "+row.getString(5);
		ehrRecord.setMercyId(mercyId);
		ehrRecord.setName(name);
		ehrRecord.setGender(row.getString(2));
		ehrRecord.setInsurance(row.getString(3));
		ehrRecord.setGroupId(row.getString(4));
		ehrRecord.setVisits(visitList);
	}	
	private void dataPatientVisits(List<Row> row){		
		for(Row r:row){
			Visit  visit     = new Visit();			
			visit.setDate(r.getDate(1));
			visit.setDiagnosis(r.getString(2));
			visit.setDoctor(r.getString(3));
			visit.setNotes(r.getString(4));			
			visitList.add(visit);			
		}
		
	}	
			
}

