package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Controller.ControllerMenu.ListenerGuiMenubtnTovarVPonuke;
import Data.AppData;
import Data.DataModelGood;
import gui.GuiManagementOfProducts;
import gui.ManagerGui;

public class ControllerManagementOfProducts  extends PsqlMethods {
	
	GuiManagementOfProducts myGuiManagementOfProducts;
	AppData myAppData;
	int selectedId;

	public ControllerManagementOfProducts(GuiManagementOfProducts theView, AppData theModel) {
		this.myGuiManagementOfProducts = theView;
		this.myAppData = theModel;
		myGuiManagementOfProducts.addListenerBtnExit(new ListenerBtnExit());
		myGuiManagementOfProducts.addListenerBtnCreate(new ListenerBtnCreate());
		myGuiManagementOfProducts.addListenerBtnEdit(new ListenerBtnEdit());
		myGuiManagementOfProducts.addListenerBtnCleardetails(new ListenerBtnCleardetails());
		myGuiManagementOfProducts.addListenerBtnRefreshtable(new ListenerBtnRefreshtable());
		myGuiManagementOfProducts.addListenerMouseClickedListOfGoods(new ListenerMouseClickedListOfGoods());
		//ListenerMouseClickedListOfGoods
		 init();
	}
	
	public void init(){
		loadCategories();
		loadDataToTable();
	}
	
	// ************* | Action Listeners |************* \\
	
	public class ListenerBtnExit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			myGuiManagementOfProducts.setVisible(false);
		}
	}
	
	public class ListenerBtnCreate implements ActionListener {

		public void actionPerformed(ActionEvent e) {
//			System.out.println("New record was inserted to table");
			 CreateNewRecord();
			 loadDataToTable();	
		}
	}
	
	public class ListenerBtnEdit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
//			System.out.println("Record was edited");
			editRecord();
			loadDataToTable();
		}
	}
	
	public class ListenerBtnCleardetails implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			clearDetails();
		}
	}
	
	public class ListenerBtnRefreshtable implements ActionListener {

		public void actionPerformed(ActionEvent e) {
//			System.out.println("Btn Refreshtable action Performed");
			loadDataToTable();
		}
	}
	
	public class ListenerMouseClickedListOfGoods extends MouseAdapter{
         public void mouseClicked(MouseEvent e) {
        	 int row = myGuiManagementOfProducts.myTblListOfGoods.getSelectedRow();
        	 loadDetails(loadDataToDetails(myGuiManagementOfProducts.myDTMListOfGoods.getValueAt(row, 0).toString()));
         }
	 }

	// ************* | APP LOGIC methods |************* \\
	
	public void CreateNewRecord(){
		try{	
		executePsqlCommand("INSERT INTO tovar "
				+ "(nazov,kategoria,ciarovy_kod,vyrobca,krajina_povodu,hmotnost,hodnota) "
				+ "VALUES('"
				+ myGuiManagementOfProducts.getTfName()
				+ "','" +  myGuiManagementOfProducts.myCbCategory.getSelectedItem()
				+ "','" + myGuiManagementOfProducts.getTfBarcode()
				+ "','" + myGuiManagementOfProducts.getTfProducer()
				+ "','" + myGuiManagementOfProducts.getTfOrigin()
				+ "','" + myGuiManagementOfProducts.getTfWeight()
				+ "','" + myGuiManagementOfProducts.getTfValue()
				+ "')"
				);
		}catch(NumberFormatException ex){
			myGuiManagementOfProducts.displayErrorMessage("Bad data type! Enter real number for Value and Weight. \n (Use dot instead of comma) ");
		}
	}
	
	public void editRecord(){

		try{
		executePsqlCommand("UPDATE tovar "
				+ "SET "
				+ "nazov = '"+myGuiManagementOfProducts.getTfName()+"',"
				+ " kategoria = '"+myGuiManagementOfProducts.myCbCategory.getSelectedItem()+"',"
				+ " ciarovy_kod = '" + myGuiManagementOfProducts.getTfBarcode()+"',"
				+ " vyrobca = '" + myGuiManagementOfProducts.getTfProducer()+"',"
				+ " krajina_povodu = '" + myGuiManagementOfProducts.getTfOrigin()+"',"
				+ " hmotnost = " + myGuiManagementOfProducts.getTfWeight()
				+ ", hodnota = " + myGuiManagementOfProducts.getTfValue()
				+ "WHERE id = "+selectedId
				);	
		}catch(NumberFormatException ex){
			myGuiManagementOfProducts.displayErrorMessage("Bad data type! Enter real number for Value and Weight. \n (Use dot instead of comma) ");
		}
//		System.out.println("Selected id: "+selectedId);
	}

	public void clearDetails(){
		myGuiManagementOfProducts.setTfName("");
		myGuiManagementOfProducts.setTfBarcode("");
		myGuiManagementOfProducts.setTfOrigin("");
		myGuiManagementOfProducts.setTfProducer("");
		myGuiManagementOfProducts.setTfValue("");
		myGuiManagementOfProducts.setTfWeight("");
		myGuiManagementOfProducts.myCbCategory.setSelectedIndex(0);
	}
	
	public void loadCategories(){
		ArrayList<String> loadedCategories = ExecutePsqlSelectForCombobox( "SELECT nazov FROM kategoria_tovaru;","nazov");
		for (int i=0;i<loadedCategories.size();i++){
	//		System.out.println("Cat"+(i+1)+" = "+loadedCategories.get(i));
			myGuiManagementOfProducts.myCbCategory.addItem(loadedCategories.get(i));
		}
	}
	
	public void loadDataToTable(){
		myGuiManagementOfProducts.myDTMListOfGoods.setRowCount(0);
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("nazov");
		columns.add("kategoria");
		columns.add("hodnota");
				
		 ArrayList<Object> dataFromTable = loadDataForTable("SELECT nazov,kategoria,hodnota FROM tovar ORDER BY kategoria,nazov",columns);
		 putDataToTable(columns,dataFromTable, myGuiManagementOfProducts.myDTMListOfGoods );
	}
	
	public DataModelGood loadDataToDetails(String key){

		ArrayList<String> columns = new ArrayList<String>();
		columns.add("nazov");
		columns.add("kategoria");
		columns.add("ciarovy_kod");
		columns.add("vyrobca");
		columns.add("krajina_povodu");
		columns.add("hmotnost");
		columns.add("hodnota");
		columns.add("id");
		String command = ("SELECT * FROM tovar where nazov = '"+key+"'");	
		ArrayList<Object> dataFromTable = loadDataForTable(command,columns);
		DataModelGood myDataModelGood = new DataModelGood(dataFromTable.get(0).toString(),
				 										  dataFromTable.get(1).toString(),
				 										 dataFromTable.get(2).toString(),
				 										dataFromTable.get(3).toString(),
				 										dataFromTable.get(4).toString(),
				 										Float.parseFloat(dataFromTable.get(5).toString()),
				 										Float.parseFloat(dataFromTable.get(6).toString()),
				 										Integer.parseInt(dataFromTable.get(7).toString()));
		 myDataModelGood.printAllInfo();
		 return myDataModelGood;
	}
	
	void loadDetails(DataModelGood myDataModelGood){
		myGuiManagementOfProducts.setTfName(myDataModelGood.getName());
		myGuiManagementOfProducts.setTfBarcode(myDataModelGood.getBarcode());
		myGuiManagementOfProducts.setTfOrigin(myDataModelGood.getOriginCountry());
		myGuiManagementOfProducts.setTfProducer(myDataModelGood.getProducer());
		myGuiManagementOfProducts.setTfValue(String.valueOf(myDataModelGood.getValue()));
		myGuiManagementOfProducts.setTfWeight(String.valueOf(myDataModelGood.getWeight()));
		myGuiManagementOfProducts.myCbCategory.setSelectedItem(myDataModelGood.getCategory());
		selectedId= myDataModelGood.getId();
	}
	
}








