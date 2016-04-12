package gov.fdic.source.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.ListIterator;

public class SupervisoryActivity {
	private Date startDate;
	private Date endDate;
	private String description;
	private String orgId;
	private Person examinerInCharge;
	private ArrayList<Person> team;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Person getExaminerInCharge() {
		return examinerInCharge;
	}
	public void setExaminerInCharge(Person examinerInCharge) {
		this.examinerInCharge = examinerInCharge;
	}
	public ArrayList<Person> getTeam() {
		return team;
	}
	public void setTeam(ArrayList<Person> team) {
		this.team = team;
	}
	public void addPersonToTeam(Person person) {
		team.add(person);
	}
	public void removePersonFromTeam(Person person) {
		
		ListIterator<Person> li=team.listIterator();
		
		while(li.hasNext()) {
			int index=li.nextIndex();
			Person listPerson=li.next();
			if (listPerson.getId() == person.getId()) {
				team.remove(index);
				break;
			}
		}
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
