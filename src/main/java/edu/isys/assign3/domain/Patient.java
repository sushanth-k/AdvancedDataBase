package edu.isys.assign3.domain;

/**
 * This class represents a patient in the pharmacy system.
 */
public class Patient extends Entity {

	private String mercyId;
	private String mercyClinic;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	public Patient() {}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMercyClinic() {
		return mercyClinic;
	}

	public String getMercyId() {
		return mercyId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMercyClinic(String mercyClinic) {
		this.mercyClinic = mercyClinic;
	}

	public void setMercyId(String mercyId) {
		this.mercyId = mercyId;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
