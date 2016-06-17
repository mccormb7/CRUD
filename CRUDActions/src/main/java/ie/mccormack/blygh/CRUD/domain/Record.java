package ie.mccormack.blygh.CRUD.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Record {

    @Id
    //auto increment field
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    
    private long id;
    
    
    //max 25 char
    private String name;
    
    //no duplicates
    //will be primary key
    private String pps;
    
    //dd/mm/yy
    //must be over 16
    private String dob;
    
    //date record was created
    private Date created;
    
    //must begin with 08
    //not required
    private Double number;
    
    
    
    
    public Record(){
    }


    
    


	public Record(String name, String pps, String dob) {

		this.name = name;
		this.pps = pps;
		this.dob = dob;
	}

	public Record(String name, String pps, String dob, Double number) {
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
        return id;
    }


}
