package Data;

public class DataModelEmployee {
	private int id;
	private String name;
	private String surname;
	private String dateOfBirth;
	private String bornId;
	private String idCard;

	public DataModelEmployee(int id, String name, String surname, String dateOfBirth, String bornId, String idCard) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.bornId = bornId;
		this.idCard = idCard;
	}

	public DataModelEmployee() {

	}

	// ************* | Getters && Setters |************* \\
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBornId() {
		return bornId;
	}

	public void setBornId(String bornId) {
		this.bornId = bornId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
