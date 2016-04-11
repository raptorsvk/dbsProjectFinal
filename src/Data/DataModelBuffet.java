package Data;

public class DataModelBuffet {
	private int id;
	private String name;
	private int townId;
	private int buffetManagerId;
	
	public DataModelBuffet(int id, String name, int townId, int buffetManagerId) {
		super();
		this.id = id;
		this.name = name;
		this.townId = townId;
		this.buffetManagerId = buffetManagerId;
	}
	
	public DataModelBuffet(){
		
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
	public int getTownId() {
		return townId;
	}
	public void setTownId(int townId) {
		this.townId = townId;
	}
	public int getBuffetManagerId() {
		return buffetManagerId;
	}
	public void setBuffetManagerId(int buffetManagerId) {
		this.buffetManagerId = buffetManagerId;
	}
	
}
