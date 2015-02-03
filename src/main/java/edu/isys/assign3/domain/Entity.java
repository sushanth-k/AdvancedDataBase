package edu.isys.assign3.domain;

public abstract class Entity {

	protected int id = 0;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isNew() {
		return (id == 0);
	}
	
}