package ie.mccormack.blygh.CRUD.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import org.apache.commons.codec.language.bm.PhoneticEngine;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.linkedin.api.PhoneNumber;

@Entity
public class Record {

	//auto increment field
	  //no duplicates
	    //will be primary key
    @Id  //primary key annotation
    private String pps;
    //@GeneratedValue(strategy = GenerationType.AUTO)
    
    
   // private long id;
     
    //max 25 char
    @NotNull
    @Size(min = 3, max = 25 , message = "Must be between 3 and 25 characters ")
    private String name;
    
    
    
    //dd/mm/yy
    //must be over 16
    //@DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull
    private String dob;
    
    //date record was created
    private String created;
    
    //must begin with 08
    //not required
   // @PhoneticEngine
    private String number;
    
    
    
    
    public Record(){
    }


    
    


	public Record(String name, String pps, String dob) {

		this.name = name;
		this.pps = pps;
		this.dob = dob;
	}

	public Record(String name, String pps, String dob, String number) {
		this.name = name;
		this.pps = pps;
		this.dob = dob;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPps() {
		return pps;
	}

	public void setPps(String pps) {
		this.pps = pps;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	
}
