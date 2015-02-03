package edu.isys.assign3;

import java.util.List;

import junit.framework.TestCase;
import edu.isys.assign3.domain.Doctor;
import edu.isys.assign3.domain.Medication;
import edu.isys.assign3.domain.Patient;
import edu.isys.assign3.domain.Pharmacist;
import edu.isys.assign3.domain.Prescription;
import edu.isys.assign3.domain.Record;
import edu.isys.assign3.domain.Visit;
import edu.isys.assign3.implementation.DoctorRepositoryImpl;
import edu.isys.assign3.implementation.MasterRecordRepositoryImpl;
import edu.isys.assign3.implementation.MedicationRepositoryImpl;
import edu.isys.assign3.implementation.ParmacistRepositoryImpl;
import edu.isys.assign3.implementation.PatientRepositoryImpl;
import edu.isys.assign3.implementation.PrescriptionRepositoryImpl;

public class TestAllData extends TestCase{	
	//Start-The doctor implementation test
	
	public void testDoctor() {
		DoctorRepositoryImpl docImpl = new DoctorRepositoryImpl();
		Doctor doc = new Doctor();		
		doc = docImpl.get(1);		
		assertEquals(doc.getDeaNumber(),"12904587J2");
		System.out.println(doc.getDeaNumber());
		
	}
	
	public void testAllDoctor() {
		DoctorRepositoryImpl docImpl = new DoctorRepositoryImpl();		
		List<Doctor> docList = docImpl.getAll();
		int expectedSize=4;			
		int actualSize=docList.size();
		assertEquals(expectedSize, actualSize);	
		System.out.println("Doc list size-"+actualSize);
		}
	//End-The doctor implementation test
	//Start-The Medication implementation test
	
	public void testMedication() {
		MedicationRepositoryImpl medImpl = new MedicationRepositoryImpl();
		Medication med = new Medication();		
		med = medImpl.get(1);	
		assertEquals(med.getCommonName(),"Ziagen");
		System.out.println(med.getCommonName());
		
	}
	
	public void testAllMedication() {
		MedicationRepositoryImpl medImpl = new MedicationRepositoryImpl();		
		List<Medication> medList = medImpl.getAll();
		int expectedSize=6;			
		int actualSize=medList.size();
		assertEquals(expectedSize, actualSize);	
		System.out.println("Med list size-"+actualSize);
		}
	//End-The Medication implementation test
	//Start-The Pharmacist implementation test
	
	public void testPharamacist() {
		ParmacistRepositoryImpl pharmImpl = new ParmacistRepositoryImpl();
		Pharmacist pharm = new Pharmacist();		
		pharm = pharmImpl.get(1);	
		assertEquals(pharm.getDeaNumber(),"098098B321");
		System.out.println(pharm.getDeaNumber());		
	}
	
	public void testAllPharamacist() {
		ParmacistRepositoryImpl pharmImpl = new ParmacistRepositoryImpl();
		List<Pharmacist> pharmList = pharmImpl.getAll();	
		int expectedSize=4;			
		int actualSize=pharmList.size();
		assertEquals(expectedSize, actualSize);	
		System.out.println("Size of pharmacist-"+actualSize);		
	}
	//End-The Pharmacist implementation test
	//Start-The Patient implementation test
	
	public void testPatient() {
		PatientRepositoryImpl patImpl = new PatientRepositoryImpl();
		Patient pat = new Patient();	
		List<Patient> patList = patImpl.get("Matt","Manley");			
		pat = patList.get(0);
		assertEquals(pat.getFirstName(),"Matt");
		System.out.println("Patient select ="+pat.getFirstName()+","+pat.getLastName());		
	}
	
	public void testPatientId() {
		PatientRepositoryImpl patImpl = new PatientRepositoryImpl();
		Patient pat; 	
		pat = patImpl.getId(5);			
		assertEquals(pat.getFirstName(),"Julie");
		System.out.println("Patient select with ID ="+pat.getFirstName()+","+pat.getLastName());		
	}
	//End-The Patient implementation test
	
	public void testPrescription() {
		PrescriptionRepositoryImpl presImpl = new PrescriptionRepositoryImpl();
		Prescription pres = new Prescription();	
		List<Prescription> presList = presImpl.getForPatient(2);		
		int expectedSize=3;			
		int actualSize=presList.size();
		assertEquals(expectedSize, actualSize);		
		//To display the prescriptions
				for(int i=0; i<actualSize;i++){
					pres = presList.get(i);
					System.out.println("The prescription details for patient " + 
							pres.getPatient().getFirstName() + "is " +"-" +pres.getMedication().getCommonName() +"-" 
											+ pres.getPharmacist().getFirstName() +  "-" + pres.getStatus().getDescription() +"-" 
											+ pres.getId());			
	}
	}
	public void testInsertPrescription(){
		PrescriptionRepositoryImpl presImpl = new PrescriptionRepositoryImpl();
		Prescription pres = new Prescription();	
		List<Prescription> presList = presImpl.getForPatient(2);
		pres = presList.get(0);	
		presImpl.put(pres);	
	
	}
	
	public void testRecord() {
		MasterRecordRepositoryImpl mRecImpl = new MasterRecordRepositoryImpl();
		Record rec = new Record();	
		Visit visit = new Visit();
		List<Visit> visitList = null;
		rec = mRecImpl.get("W000000001");
		visitList = rec.getVisits();
		int expectedSize=2;			
		int actualSize=visitList.size();
		assertEquals(expectedSize, actualSize);	
		for(int i=0; i<actualSize;i++){
			visit = visitList.get(i);
			System.out.println("The EHR details for patient from west clinic " + 
					rec.getName() + "- " +"-" +rec.getClinic() +"-" 
									+ rec.getMercyId()+  "-" + rec.getGroupId() +"-" 
									+ rec.getInsurance() +  "-" +visit.getDiagnosis()+"-"
									+ visit.getDoctor()+"-"+visit.getNotes());
		}
				
	}
	
	public void testJsonRecord() {
		MasterRecordRepositoryImpl mRecImpl = new MasterRecordRepositoryImpl();
		Record rec = new Record();	
		Visit visit = new Visit();
		List<Visit> visitList = null;
		rec = mRecImpl.get("E000000011");
		visitList = rec.getVisits();
		int expectedSize=5;			
		int actualSize=visitList.size();
		assertEquals(expectedSize, actualSize);	
		for(int i=0; i<actualSize;i++){
			visit = visitList.get(i);
			System.out.println("The EHR details for patient from East clinic " + 
					rec.getName() + "- " +"-" +rec.getClinic() +"-" 
									+ rec.getMercyId()+  "-" + rec.getGroupId() +"-" 
									+ rec.getInsurance() +  "-" +visit.getDiagnosis()+"-"
									+ visit.getDoctor()+"-"+visit.getNotes());
		}
				
	}
	public void testCassandraRecord() {
		MasterRecordRepositoryImpl mRecImpl = new MasterRecordRepositoryImpl();
		Record rec = new Record();	
		Visit visit = new Visit();
		List<Visit> visitList = null;
		rec = mRecImpl.get("N000000001");
		visitList = rec.getVisits();
		int expectedSize=2;			
		int actualSize=visitList.size();
		assertEquals(expectedSize, actualSize);	
		for(int i=0; i<actualSize;i++){
			visit = visitList.get(i);
			System.out.println("The EHR details for North clinic patient " + 
					rec.getName() + "--" + rec.getMercyId()+  "-" + rec.getGroupId() +"-" 
									+ rec.getInsurance() +  "-" +visit.getDiagnosis()+"-"
									+ visit.getDoctor()+"-"+visit.getNotes());
		}
				
	}
}
