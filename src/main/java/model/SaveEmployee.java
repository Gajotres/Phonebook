package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveEmployee {

    @NotNull
	private int id;
    
    @NotNull
    @Size(max = 64)    
	private String mobilephoneNumber;
	
    @NotNull
    @Size(max = 64)    
	private String telephoneNumber;

    @NotNull
    @Size(max = 1000000)
	private String image;

    @NotNull
    @Size(max = 1000000)
	private String smallImage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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