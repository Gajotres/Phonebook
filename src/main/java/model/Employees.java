package model;

public class Employees {

	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String organizationName;
	
	private String subOrganizationName;
	
	private String mobilephoneNumber;

	private String telephoneNumber;
	
	private String smallImage;	

	public Employees(int id, String firstName, String lastName, String organizationName, String subOrganizationName, String mobilephoneNumber, String telephoneNumber, String smallImage) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.organizationName = organizationName;
		this.subOrganizationName = subOrganizationName;
		this.mobilephoneNumber = mobilephoneNumber;
		this.telephoneNumber = telephoneNumber;
		this.smallImage = smallImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}	
}
