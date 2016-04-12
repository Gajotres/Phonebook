package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "imenik")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="id_organizacije", nullable=true)
	private int orgId;	
	
	@Column(name="korisnicko_ime", length=10, nullable=false)
	private String userId;
	
	@Column(name="ime", length=64, nullable=false)
	private String firstName;
	
	@Column(name="prezime", length=64, nullable=false)
	private String lastName;
	
	@Column(name="naziv", length=128, nullable=false)
	private String fullName;
	
	@Column(name="naziv_org_jedinice", length=128, nullable=true)
	private String organizationName;
	
	@Column(name="naziv_pod_org_jedinice", length=256, nullable=true)
	private String subOrganizationName;
	
	@Column(name="naziv_radnog_mjesta", length=128, nullable=true)
	private String jobTitle;
	
	@Temporal(TemporalType.DATE)
	@Column(name="datum_zaposlenja", nullable=true)
	private Date employmentDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="datum_prekida", nullable=true)
	private Date terminationDate;
	
	@Column(name="lokacija", length=64, nullable=true)
	private String location;
	
	@Column(name="vrsta_rada", length=64, nullable=true)
	private String jobType;
	
	@Column(name="spol", length=1, nullable=false)
	private char sex;
	
	@Column(name="email", length=64, nullable=true)
	private String email;

	@Column(name="mobitel", length=64, nullable=true)
	private String mobilephoneNumber;
	
	@Column(name="telefon", length=64, nullable=true)
	private String telephoneNumber;
	
	@Column(length=1000000)
	@Lob
	private String image;

	@Column(length=1000000)
	@Lob
	private String smallImage;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getSubOrganizationName() {
		return subOrganizationName;
	}

	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilephoneNumber() {
		return mobilephoneNumber;
	}

	public void setMobilephoneNumber(String mobilephoneNumber) {
		this.mobilephoneNumber = mobilephoneNumber;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}	
	
	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}	
}
