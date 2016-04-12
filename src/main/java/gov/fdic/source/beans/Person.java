package gov.fdic.source.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Person {
	private int id;
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@JsonIgnore
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
