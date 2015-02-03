package edu.isys.assign3.implementation;
import java.util.List;
import edu.isys.assign3.domain.Record;
import edu.isys.assign3.repository.RecordRepository;
import edu.isys.assign3.util.JsonUtil;

public class EastRecordRepositoryImpl implements RecordRepository {
			
	List<Record> recList = null; 
	
	public Record get(String mercyId) {
		Record whrRecord = new Record();			
		recList = JsonUtil.loadRecordsFromJson();
		for(Record record:recList)
		{
			if(record.getMercyId().equals(mercyId))
			{
				whrRecord = record;
			}
		}		
		return whrRecord;		
	}

}

