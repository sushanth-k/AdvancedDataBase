package edu.isys.assign3.implementation;

import edu.isys.assign3.domain.Record;
import edu.isys.assign3.repository.RecordRepository;

public class MasterRecordRepositoryImpl implements RecordRepository {	
	RecordRepository recordRepository;
	Record patientRecord = null;
	
	public Record get(String mercyId) {
		
	if(mercyId.startsWith("W"))
	{
		recordRepository = new WestRecordRepositoryImpl();
	} else if(mercyId.startsWith("E")){
		recordRepository = new EastRecordRepositoryImpl();
	}
	else if(mercyId.startsWith("N")){
		recordRepository = new NorthRecordRepositoryImpl();
	}
	patientRecord = recordRepository.get(mercyId);
	return patientRecord;
	}
}
