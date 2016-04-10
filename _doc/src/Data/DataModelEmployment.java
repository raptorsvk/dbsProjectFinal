package Data;

import java.sql.Date;

public class DataModelEmployment {
	private int id;
	private int buffetId;
	private int employeeId;
	private String workPosition;
	private String date;
	private String time;
	

	public DataModelEmployment(int id, int buffetId, int employeeId, String workPosition, String date, String time) {
		super();
		this.id = id;
		this.buffetId = buffetId;
		this.employeeId = employeeId;
		this.workPosition = workPosition;
		this.date = date;
		this.time = time;
	}

	public DataModelEmployment(){
		
	}
	
	// ************* | Getters && Setters |************* \\
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBuffetId() {
		return buffetId;
	}

	public void setBuffetId(int buffetId) {
		this.buffetId = buffetId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
