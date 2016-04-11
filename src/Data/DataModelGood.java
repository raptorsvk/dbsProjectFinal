package Data;

public class DataModelGood {
	private int id;
	private String name;
	private String category;
	private String barcode;
	private String producer;
	private String originCountry;
	private float weight;
	private float value;
	
	public DataModelGood(String name, String category, String barcode, String producer, String originCountry, float weight, float value,int id) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.barcode = barcode;
		this.producer = producer;
		this.originCountry = originCountry;
		this.weight = weight;
		this.value = value;
	}

	public DataModelGood(){
		
	}

	public void printAllInfo(){
		System.out.println("ID: "+id);
		System.out.println("Name: "+name);
		System.out.println("Category: "+category);
		System.out.println("Value: "+value);
		System.out.println("Barcode: "+barcode);
		System.out.println("Weight: "+weight);
		System.out.println("Origin: "+originCountry);
		System.out.println("Producer: "+producer);
	}


	// ************* | Getters && Setters |************* \\
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
