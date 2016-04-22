package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "org_units")
public class OrgUnit {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;	
	
	@Column(name="ime_organizacije", length=256, nullable=false)
	private String orgName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
